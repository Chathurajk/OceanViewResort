package com.oceanview.service;
import com.oceanview.model.Reservation;
import com.oceanview.util.DBConnection;
import java.sql.*;
import java.util.*;
public class ReportService {
    public List<Reservation> getMonthlyReport(int year,int month) throws Exception {
        List<Reservation> list=new ArrayList<>();
        CallableStatement cs=DBConnection.getInstance().getConnection().prepareCall("{CALL GetMonthlyReport(?,?)}");
        cs.setInt(1,year); cs.setInt(2,month); ResultSet rs=cs.executeQuery();
        while(rs.next()){
            Reservation r=new Reservation();
            r.setReservationNumber(rs.getString("reservation_number")); r.setGuestName(rs.getString("guest_name"));
            r.setRoomNumber(rs.getString("room_number")); r.setRoomType(rs.getString("room_type"));
            r.setCheckInDate(rs.getDate("check_in_date").toLocalDate()); r.setCheckOutDate(rs.getDate("check_out_date").toLocalDate());
            r.setTotalNights(rs.getInt("total_nights")); r.setFinalAmount(rs.getDouble("final_amount"));
            r.setStatus(rs.getString("status")); list.add(r);
        }
        return list;
    }
    public double getTotalRevenue(List<Reservation> list) { return list.stream().mapToDouble(Reservation::getFinalAmount).sum(); }
}
