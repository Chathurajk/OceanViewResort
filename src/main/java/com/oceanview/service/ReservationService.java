package com.oceanview.service;

import com.oceanview.dao.GuestDAO;
import com.oceanview.dao.ReservationDAO;
import com.oceanview.dao.RoomDAO;
import com.oceanview.model.Guest;
import com.oceanview.model.Reservation;
import com.oceanview.model.Room;
import com.oceanview.util.ReservationNumberGenerator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final GuestDAO       guestDAO       = new GuestDAO();
    private final RoomDAO        roomDAO        = new RoomDAO();

    public String createReservation(Guest guest, int roomId, LocalDate checkIn,
            LocalDate checkOut, int staffId, double discountPercent, String specialRequests) throws Exception {

        if (!checkOut.isAfter(checkIn))
            throw new IllegalArgumentException("Check-out must be after check-in.");

        Room room = roomDAO.getRoomById(roomId);
        if (room == null || !room.isAvailable())
            throw new IllegalStateException("Selected room is not available.");

        Guest existing = guestDAO.getGuestByNIC(guest.getNic());
        if (existing == null) {
            guestDAO.addGuest(guest);
            existing = guestDAO.getGuestByNIC(guest.getNic());
        }

        long   nights   = ChronoUnit.DAYS.between(checkIn, checkOut);
        double total    = nights * room.getPricePerNight();
        double discount = total * (discountPercent / 100.0);
        double finalAmt = total - discount;

        Reservation reservation = new Reservation();
        reservation.setReservationNumber(ReservationNumberGenerator.generate());
        reservation.setGuestId(existing.getGuestId());
        reservation.setRoomId(roomId);
        reservation.setStaffId(staffId);
        reservation.setCheckInDate(checkIn);
        reservation.setCheckOutDate(checkOut);
        reservation.setTotalNights((int) nights);
        reservation.setTotalAmount(total);
        reservation.setDiscountPercent(discountPercent);
        reservation.setFinalAmount(finalAmt);
        reservation.setStatus("CONFIRMED");
        reservation.setSpecialRequests(specialRequests);

        reservationDAO.addReservation(reservation);

        if (guest.getEmail() != null && !guest.getEmail().trim().isEmpty()) {
            EmailService.sendReservationConfirmation(
                guest.getEmail(), guest.getFullName(),
                reservation.getReservationNumber(), room.getRoomType(),
                checkIn.toString(), checkOut.toString(), (int) nights, finalAmt);
        }

        return reservation.getReservationNumber();
    }

    public Reservation getByNumber(String number) throws Exception {
        return reservationDAO.getByReservationNumber(number);
    }

    public List<Reservation> getAll() throws Exception {
        return reservationDAO.getAllReservations();
    }

    public boolean cancelReservation(int id) throws Exception {
        return reservationDAO.updateStatus(id, "CANCELLED");
    }

    public boolean updateStatus(int id, String status) throws Exception {
        return reservationDAO.updateStatus(id, status);
    }
}
