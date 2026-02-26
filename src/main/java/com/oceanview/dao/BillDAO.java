package com.oceanview.dao;
import com.oceanview.model.Bill;
import com.oceanview.util.DBConnection;
import java.sql.*;
public class BillDAO {
    public boolean addBill(Bill bill) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("INSERT INTO bill(reservation_id,amount_paid,payment_method)VALUES(?,?,?)");
        ps.setInt(1,bill.getReservationId()); ps.setDouble(2,bill.getAmountPaid()); ps.setString(3,bill.getPaymentMethod());
        return ps.executeUpdate()>0;
    }
    public Bill getBillByReservation(int reservationId) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM bill WHERE reservation_id=?");
        ps.setInt(1,reservationId); ResultSet rs=ps.executeQuery();
        if(rs.next()){Bill b=new Bill(); b.setBillId(rs.getInt("bill_id")); b.setReservationId(rs.getInt("reservation_id")); b.setAmountPaid(rs.getDouble("amount_paid")); b.setPaymentMethod(rs.getString("payment_method")); return b;}
        return null;
    }
}
