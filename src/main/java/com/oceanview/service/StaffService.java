package com.oceanview.service;
import com.oceanview.dao.StaffDAO;
import com.oceanview.model.Staff;
import java.util.List;
public class StaffService {
    private final StaffDAO staffDAO=new StaffDAO();
    private final AuthService authService=new AuthService();
    public List<Staff> getAllStaff() throws Exception { return staffDAO.getAllStaff(); }
    public boolean addStaff(Staff staff,String plainPassword) throws Exception {
        staff.setPasswordHash(authService.hashPassword(plainPassword)); return staffDAO.addStaff(staff);
    }
    public boolean deactivateStaff(int id) throws Exception { return staffDAO.toggleActive(id,false); }
    public boolean activateStaff(int id) throws Exception { return staffDAO.toggleActive(id,true); }
}
