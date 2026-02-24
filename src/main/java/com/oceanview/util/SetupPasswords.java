package com.oceanview.util;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
public class SetupPasswords {
    public static void main(String[] args) throws Exception {
        Connection conn = DBConnection.getInstance().getConnection();
        String sql = "UPDATE staff SET password_hash = ? WHERE username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, BCrypt.hashpw("manager123", BCrypt.gensalt(12))); ps.setString(2, "manager"); ps.executeUpdate();
        System.out.println("Manager password set!");
        ps.setString(1, BCrypt.hashpw("staff123", BCrypt.gensalt(12))); ps.setString(2, "reception1"); ps.executeUpdate();
        System.out.println("Reception1 password set!");
        ps.setString(1, BCrypt.hashpw("staff123", BCrypt.gensalt(12))); ps.setString(2, "reception2"); ps.executeUpdate();
        System.out.println("Reception2 password set!");
        System.out.println("All passwords set successfully!"); conn.close();
    }
}
