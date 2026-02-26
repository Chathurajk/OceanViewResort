package com.oceanview.service;
import com.oceanview.dao.StaffDAO;
import com.oceanview.model.Staff;
import org.mindrot.jbcrypt.BCrypt;
public class AuthService {
    private final StaffDAO staffDAO=new StaffDAO();
    public Staff authenticate(String username,String password) throws Exception {
        if(username==null||password==null)return null;
        Staff staff=staffDAO.getByUsername(username.trim());
        if(staff==null)return null;
        if(BCrypt.checkpw(password,staff.getPasswordHash()))return staff;
        return null;
    }
    public String hashPassword(String p) { return BCrypt.hashpw(p,BCrypt.gensalt(12)); }
}
