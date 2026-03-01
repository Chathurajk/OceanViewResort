package com.oceanview.servlet;
import com.oceanview.model.Staff;
import com.oceanview.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService=new AuthService();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        HttpSession session=req.getSession(false);
        if(session!=null&&session.getAttribute("loggedInUser")!=null){
            Staff user=(Staff)session.getAttribute("loggedInUser");
            redirectByRole(req,resp,user.getRole()); return;
        }
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }
    @Override protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        String username=req.getParameter("username"); String password=req.getParameter("password");
        if(username==null||username.trim().isEmpty()||password==null||password.isEmpty()){
            req.setAttribute("error","Please enter both username and password.");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp); return;
        }
        try {
            Staff staff=authService.authenticate(username,password);
            if(staff!=null){
                HttpSession session=req.getSession();
                session.setAttribute("loggedInUser",staff); session.setAttribute("userRole",staff.getRole());
                session.setAttribute("staffId",staff.getStaffId()); session.setAttribute("staffName",staff.getFullName());
                session.setMaxInactiveInterval(30*60); redirectByRole(req,resp,staff.getRole());
            } else {
                req.setAttribute("error","Invalid username or password."); req.setAttribute("lastUsername",username);
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
            }
        } catch(Exception e){
            req.setAttribute("error","System error: "+e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }
    }
    private void redirectByRole(HttpServletRequest req,HttpServletResponse resp,String role) throws IOException {
        if("MANAGER".equals(role)) resp.sendRedirect(req.getContextPath()+"/manager/dashboard");
        else resp.sendRedirect(req.getContextPath()+"/staff/dashboard");
    }
}
