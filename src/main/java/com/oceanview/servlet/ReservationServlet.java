package com.oceanview.servlet;

import com.oceanview.model.Guest;
import com.oceanview.model.Reservation;
import com.oceanview.service.ReservationService;
import com.oceanview.service.RoomService;
import com.oceanview.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/staff/reservation", "/manager/reservation"})
public class ReservationServlet extends HttpServlet {

    private final ReservationService reservationService = new ReservationService();
    private final RoomService        roomService        = new RoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                req.setAttribute("availableRooms", roomService.getAvailableRooms());
                req.getRequestDispatcher("/WEB-INF/views/add_reservation.jsp").forward(req, resp);
            } else if ("view".equals(action)) {
                String resNum = req.getParameter("resNum");
                if (resNum != null && !resNum.trim().isEmpty()) {
                    Reservation res = reservationService.getByNumber(resNum.trim().toUpperCase());
                    if (res == null) req.setAttribute("notFound", true);
                    else             req.setAttribute("reservation", res);
                }
                req.getRequestDispatcher("/WEB-INF/views/view_reservation.jsp").forward(req, resp);
            } else if ("bill".equals(action)) {
                String resNum = req.getParameter("resNum");
                req.setAttribute("reservation", reservationService.getByNumber(resNum));
                req.getRequestDispatcher("/WEB-INF/views/bill.jsp").forward(req, resp);
            } else {
                req.setAttribute("reservations", reservationService.getAll());
                req.getRequestDispatcher("/WEB-INF/views/manage_reservations.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/manage_reservations.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) handleAdd(req, resp);
        else if ("updateStatus".equals(action)) handleUpdateStatus(req, resp);
    }

    private void handleAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String fullName    = req.getParameter("fullName");
        String nic         = req.getParameter("nic");
        String email       = req.getParameter("email");
        String contact     = req.getParameter("contact");
        String address     = req.getParameter("address");
        String roomIdStr   = req.getParameter("roomId");
        String checkInStr  = req.getParameter("checkInDate");
        String checkOutStr = req.getParameter("checkOutDate");
        String discStr     = req.getParameter("discount");
        String special     = req.getParameter("specialRequests");

        StringBuilder errors = new StringBuilder();
        if (!ValidationUtil.isNotEmpty(fullName))  errors.append("Full name is required. ");
        if (!ValidationUtil.isValidNIC(nic))        errors.append("Valid NIC required (old: 901234567V, new: 199012345678). ");
        if (ValidationUtil.isNotEmpty(email) && !ValidationUtil.isValidEmail(email)) errors.append("Invalid email format. ");
        if (!ValidationUtil.isValidPhone(contact))  errors.append("Valid 10-digit phone required. ");
        if (!ValidationUtil.isNotEmpty(address))    errors.append("Address is required. ");
        if (!ValidationUtil.isNotEmpty(roomIdStr))  errors.append("Please select a room. ");

        LocalDate checkIn = null, checkOut = null;
        try {
            checkIn  = LocalDate.parse(checkInStr);
            checkOut = LocalDate.parse(checkOutStr);
            if (!ValidationUtil.isFutureOrToday(checkIn))        errors.append("Check-in cannot be in the past. ");
            if (!ValidationUtil.isValidDateRange(checkIn, checkOut)) errors.append("Check-out must be after check-in. ");
        } catch (Exception e) { errors.append("Invalid date format. "); }

        if (errors.length() > 0) {
            req.setAttribute("error", errors.toString().trim());
            try { req.setAttribute("availableRooms", roomService.getAvailableRooms()); } catch (Exception ignored) {}
            req.getRequestDispatcher("/WEB-INF/views/add_reservation.jsp").forward(req, resp);
            return;
        }

        try {
            Guest guest = new Guest();
            guest.setFullName(fullName.trim());
            guest.setNic(nic.trim().toUpperCase());
            guest.setEmail(email != null ? email.trim() : "");
            guest.setContact(contact.trim());
            guest.setAddress(address.trim());

            int    roomId   = Integer.parseInt(roomIdStr);
            double discount = (discStr != null && !discStr.isEmpty()) ? Double.parseDouble(discStr) : 0.0;
            int    staffId  = (int) req.getSession().getAttribute("staffId");
            String role     = (String) req.getSession().getAttribute("userRole");

            String resNum = reservationService.createReservation(guest, roomId, checkIn, checkOut, staffId, discount, special);
            String base   = "MANAGER".equals(role) ? "/manager" : "/staff";
            resp.sendRedirect(req.getContextPath() + base + "/reservation?action=view&resNum=" + resNum + "&success=1");
        } catch (Exception e) {
            req.setAttribute("error", "Failed to create reservation: " + e.getMessage());
            try { req.setAttribute("availableRooms", roomService.getAvailableRooms()); } catch (Exception ignored) {}
            req.getRequestDispatcher("/WEB-INF/views/add_reservation.jsp").forward(req, resp);
        }
    }

    private void handleUpdateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int    id     = Integer.parseInt(req.getParameter("reservationId"));
            String status = req.getParameter("newStatus");
            reservationService.updateStatus(id, status);
            resp.sendRedirect(req.getContextPath() + "/manager/reservation?success=updated");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/manager/reservation?error=failed");
        }
    }
}
