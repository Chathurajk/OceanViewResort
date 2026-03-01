package com.oceanview.servlet;
import com.oceanview.model.Reservation;
import com.oceanview.model.Room;
import com.oceanview.service.ReservationService;
import com.oceanview.service.RoomService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
@WebServlet(urlPatterns={"/staff/dashboard","/manager/dashboard"})
public class DashboardServlet extends HttpServlet {
    private final ReservationService reservationService=new ReservationService();
    private final RoomService roomService=new RoomService();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        try {
            List<Room> allRooms=roomService.getAllRooms();
            List<Reservation> reservations=reservationService.getAll();
            long available=allRooms.stream().filter(r->"AVAILABLE".equals(r.getStatus())).count();
            long occupied=allRooms.stream().filter(r->"OCCUPIED".equals(r.getStatus())).count();
            long confirmed=reservations.stream().filter(r->"CONFIRMED".equals(r.getStatus())).count();
            req.setAttribute("availableCount",available); req.setAttribute("occupiedCount",occupied);
            req.setAttribute("confirmedCount",confirmed); req.setAttribute("totalRooms",allRooms.size());
            req.setAttribute("recentReservations",reservations.subList(0,Math.min(5,reservations.size())));
            String role=(String)req.getSession().getAttribute("userRole");
            if("MANAGER".equals(role)) req.getRequestDispatcher("/WEB-INF/views/dashboard_manager.jsp").forward(req,resp);
            else req.getRequestDispatcher("/WEB-INF/views/dashboard_staff.jsp").forward(req,resp);
        } catch(Exception e){
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/dashboard_staff.jsp").forward(req,resp);
        }
    }
}
