package com.oceanview.dao;
import com.oceanview.model.Staff;
import com.oceanview.util.DBConnection;
import java.sql.*;
import java.util.*;
public class StaffDAO {
    public Staff getByUsername(String username) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM staff WHERE username=? AND is_active=TRUE");
        ps.setString(1,username); ResultSet rs=ps.executeQuery();
        if(rs.next())return mapStaff(rs); return null;
    }
    public List<Staff> getAllStaff() throws Exception {
        List<Staff> list=new ArrayList<>(); Connection conn=DBConnection.getInstance().getConnection();
        ResultSet rs=conn.createStatement().executeQuery("SELECT * FROM staff ORDER BY created_at DESC");
        while(rs.next())list.add(mapStaff(rs)); return list;
    }
    public boolean addStaff(Staff s) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("INSERT INTO staff(username,password_hash,full_name,role,email,contact)VALUES(?,?,?,?,?,?)");
        ps.setString(1,s.getUsername()); ps.setString(2,s.getPasswordHash()); ps.setString(3,s.getFullName());
        ps.setString(4,s.getRole()); ps.setString(5,s.getEmail()); ps.setString(6,s.getContact());
        return ps.executeUpdate()>0;
    }
    public boolean toggleActive(int staffId,boolean active) throws Exception {
        Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement("UPDATE staff SET is_active=? WHERE staff_id=?");
        ps.setBoolean(1,active); ps.setInt(2,staffId); return ps.executeUpdate()>0;
    }
    private Staff mapStaff(ResultSet rs) throws SQLException {
        Staff s=new Staff(); s.setStaffId(rs.getInt("staff_id")); s.setUsername(rs.getString("username"));
        s.setPasswordHash(rs.getString("password_hash")); s.setFullName(rs.getString("full_name"));
        s.setRole(rs.getString("role")); s.setEmail(rs.getString("email"));
        s.setContact(rs.getString("contact")); s.setActive(rs.getBoolean("is_active")); return s;
    }
}
