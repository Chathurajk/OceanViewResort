package com.oceanview.dao;
import com.oceanview.model.Guest;
import com.oceanview.util.DBConnection;
import java.sql.*;
public class GuestDAO {
    public Guest getGuestByNIC(String nic) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM guest WHERE nic=?");
        ps.setString(1,nic); ResultSet rs=ps.executeQuery();
        if(rs.next())return mapGuest(rs); return null;
    }
    public boolean addGuest(Guest g) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("INSERT INTO guest(full_name,nic,email,contact,address)VALUES(?,?,?,?,?)");
        ps.setString(1,g.getFullName()); ps.setString(2,g.getNic()); ps.setString(3,g.getEmail());
        ps.setString(4,g.getContact()); ps.setString(5,g.getAddress()); return ps.executeUpdate()>0;
    }
    private Guest mapGuest(ResultSet rs) throws SQLException {
        Guest g=new Guest(); g.setGuestId(rs.getInt("guest_id")); g.setFullName(rs.getString("full_name"));
        g.setNic(rs.getString("nic")); g.setEmail(rs.getString("email"));
        g.setContact(rs.getString("contact")); g.setAddress(rs.getString("address")); return g;
    }
}
