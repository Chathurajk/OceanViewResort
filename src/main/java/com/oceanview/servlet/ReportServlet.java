package com.oceanview.servlet;
import com.oceanview.model.Reservation;
import com.oceanview.service.ReportService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
@WebServlet("/manager/report")
public class ReportServlet extends HttpServlet {
    private final ReportService reportService=new ReportService();
    @Override protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        String yearStr=req.getParameter("year"); String monthStr=req.getParameter("month");
        if(yearStr!=null&&monthStr!=null){
            try {
                int year=Integer.parseInt(yearStr); int month=Integer.parseInt(monthStr);
                List<Reservation> reportData=reportService.getMonthlyReport(year,month);
                req.setAttribute("reportData",reportData); req.setAttribute("totalRevenue",reportService.getTotalRevenue(reportData));
                req.setAttribute("selectedYear",year); req.setAttribute("selectedMonth",month);
            } catch(Exception e){ req.setAttribute("error","Error: "+e.getMessage()); }
        }
        req.setAttribute("currentYear",LocalDate.now().getYear());
        req.getRequestDispatcher("/WEB-INF/views/monthly_report.jsp").forward(req,resp);
    }
}
