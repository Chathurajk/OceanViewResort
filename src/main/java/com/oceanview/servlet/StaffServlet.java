package com.oceanview.servlet;
import com.oceanview.model.Staff;
import com.oceanview.service.StaffService;
import com.oceanview.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
@WebServlet("/manager/staff")
public class StaffServlet extends HttpServlet {
    private final StaffService staffService=new StaffService();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        try {
            req.setAttribute("staffList",staffService.getAllStaff());
            req.getRequestDispatcher("/WEB-INF/views/manage_staff.jsp").forward(req,resp);
        } catch(Exception e){ req.setAttribute("error",e.getMessage()); req.getRequestDispatcher("/WEB-INF/views/manage_staff.jsp").forward(req,resp); }
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        String action=req.getParameter("action");
        try {
            if("add".equals(action)){
                String username=req.getParameter("username"); String password=req.getParameter("password");
                String fullName=req.getParameter("fullName"); String role=req.getParameter("role");
                String email=req.getParameter("email"); String contact=req.getParameter("contact");
                if(!ValidationUtil.isNotEmpty(username)||!ValidationUtil.isNotEmpty(password)){
                    req.setAttribute("error","Username and password required."); doGet(req,resp); return;
                }
                Staff staff=new Staff(); staff.setUsername(username.trim()); staff.setFullName(fullName!=null?fullName.trim():"");
                staff.setRole(role); staff.setEmail(email); staff.setContact(contact); staff.setActive(true);
                staffService.addStaff(staff,password);
                resp.sendRedirect(req.getContextPath()+"/manager/staff?success=added");
            } else if("deactivate".equals(action)){
                staffService.deactivateStaff(Integer.parseInt(req.getParameter("staffId")));
                resp.sendRedirect(req.getContextPath()+"/manager/staff?success=deactivated");
            } else if("activate".equals(action)){
                staffService.activateStaff(Integer.parseInt(req.getParameter("staffId")));
                resp.sendRedirect(req.getContextPath()+"/manager/staff?success=activated");
            }
        } catch(Exception e){ req.setAttribute("error","Operation failed: "+e.getMessage()); doGet(req,resp); }
    }
}
