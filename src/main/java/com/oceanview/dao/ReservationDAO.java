package com.oceanview.dao;
import com.oceanview.model.Reservation;
import com.oceanview.util.DBConnection;
import java.sql.*;
import java.sql.Date;
import java.util.*;
public class ReservationDAO {
    public boolean addReservation(Reservation r) throws Exception {
        String sql="INSERT INTO reservation(reservation_number,guest_id,room_id,staff_id,check_in_date,check_out_date,total_nights,total_amount,discount_percent,final_amount,status,special_requests)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1,r.getReservationNumber()); ps.setInt(2,r.getGuestId()); ps.setInt(3,r.getRoomId()); ps.setInt(4,r.getStaffId());
        ps.setDate(5, Date.valueOf(r.getCheckInDate())); ps.setDate(6,Date.valueOf(r.getCheckOutDate()));
        ps.setInt(7,r.getTotalNights()); ps.setDouble(8,r.getTotalAmount()); ps.setDouble(9,r.getDiscountPercent());
        ps.setDouble(10,r.getFinalAmount()); ps.setString(11,r.getStatus()); ps.setString(12,r.getSpecialRequests());
        return ps.executeUpdate()>0;
    }
    public Reservation getByReservationNumber(String resNum) throws Exception {
        String sql="SELECT r.*,g.full_name AS guest_name,g.nic AS guest_nic,g.email AS guest_email,g.contact AS guest_contact,g.address AS guest_address,rm.room_number,rm.room_type,rm.price_per_night FROM reservation r JOIN guest g ON r.guest_id=g.guest_id JOIN room rm ON r.room_id=rm.room_id WHERE r.reservation_number=?";
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement(sql); ps.setString(1,resNum);
        ResultSet rs=ps.executeQuery(); if(rs.next())return mapFull(rs); return null;
    }
    public List<Reservation> getAllReservations() throws Exception {
        List<Reservation> list=new ArrayList<>();
        String sql="SELECT r.*,g.full_name AS guest_name,rm.room_number,rm.room_type FROM reservation r JOIN guest g ON r.guest_id=g.guest_id JOIN room rm ON r.room_id=rm.room_id ORDER BY r.created_at DESC";
        ResultSet rs=DBConnection.getInstance().getConnection().createStatement().executeQuery(sql);
        while(rs.next())list.add(mapBasic(rs)); return list;
    }
    public boolean updateStatus(int id,String status) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("UPDATE reservation SET status=? WHERE reservation_id=?");
        ps.setString(1,status); ps.setInt(2,id); return ps.executeUpdate()>0;
    }
    private Reservation mapBasic(ResultSet rs) throws SQLException {
        Reservation r=new Reservation();
        r.setReservationId(rs.getInt("reservation_id")); r.setReservationNumber(rs.getString("reservation_number"));
        r.setGuestId(rs.getInt("guest_id")); r.setRoomId(rs.getInt("room_id"));
        r.setCheckInDate(rs.getDate("check_in_date").toLocalDate()); r.setCheckOutDate(rs.getDate("check_out_date").toLocalDate());
        r.setTotalNights(rs.getInt("total_nights")); r.setFinalAmount(rs.getDouble("final_amount"));
        r.setStatus(rs.getString("status")); r.setGuestName(rs.getString("guest_name"));
        r.setRoomNumber(rs.getString("room_number")); r.setRoomType(rs.getString("room_type")); return r;
    }
    private Reservation mapFull(ResultSet rs) throws SQLException {
        Reservation r=mapBasic(rs);
        r.setTotalAmount(rs.getDouble("total_amount")); r.setDiscountPercent(rs.getDouble("discount_percent"));
        r.setSpecialRequests(rs.getString("special_requests")); r.setGuestNic(rs.getString("guest_nic"));
        r.setGuestEmail(rs.getString("guest_email")); r.setGuestContact(rs.getString("guest_contact"));
        r.setGuestAddress(rs.getString("guest_address")); r.setPricePerNight(rs.getDouble("price_per_night")); return r;
    }
}
