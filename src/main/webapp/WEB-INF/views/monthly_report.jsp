<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Monthly Report</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head><body>
<div class="sidebar"><div class="sidebar-brand"><div class="brand-icon"></div><h2>Ocean View Resort</h2><p>Manager Panel</p></div>
<nav class="sidebar-menu">
<a href="${pageContext.request.contextPath}/manager/dashboard"> Dashboard</a>
<a href="${pageContext.request.contextPath}/manager/reservation"> Manage Reservations</a>
<a href="${pageContext.request.contextPath}/manager/staff"> Manage Staff</a>
<a href="${pageContext.request.contextPath}/manager/report" class="active"> Monthly Report</a>
<a href="${pageContext.request.contextPath}/logout" style="color:#ff8a80;"> Logout</a>
</nav></div>
<div class="main-content"><div class="topbar"><span class="topbar-title"> Monthly Report</span></div>
<div class="page-content">
<c:if test="${not empty error}"><div class="alert alert-error"> ${error}</div></c:if>
<div class="card"><div class="card-title">Select Period</div>
<form action="${pageContext.request.contextPath}/manager/report" method="get" style="display:flex;gap:16px;align-items:flex-end;">
<div class="form-group" style="margin:0;">
<label>Year</label>
<select name="year">
<c:forEach begin="2023" end="${currentYear}" var="y"><option value="${y}" ${selectedYear == y ? 'selected' : ''}>${y}</option></c:forEach>
</select></div>
<div class="form-group" style="margin:0;">
<label>Month</label>
<select name="month">
<option value="1" ${selectedMonth==1?'selected':''}>January</option>
<option value="2" ${selectedMonth==2?'selected':''}>February</option>
<option value="3" ${selectedMonth==3?'selected':''}>March</option>
<option value="4" ${selectedMonth==4?'selected':''}>April</option>
<option value="5" ${selectedMonth==5?'selected':''}>May</option>
<option value="6" ${selectedMonth==6?'selected':''}>June</option>
<option value="7" ${selectedMonth==7?'selected':''}>July</option>
<option value="8" ${selectedMonth==8?'selected':''}>August</option>
<option value="9" ${selectedMonth==9?'selected':''}>September</option>
<option value="10" ${selectedMonth==10?'selected':''}>October</option>
<option value="11" ${selectedMonth==11?'selected':''}>November</option>
<option value="12" ${selectedMonth==12?'selected':''}>December</option>
</select></div>
<button type="submit" class="btn btn-primary">Generate Report</button>
</form></div>
<c:if test="${not empty reportData}">
<div class="card"><div class="card-title">Report for ${selectedYear}/${selectedMonth}</div>
<div class="table-container"><table>
<thead><tr><th>Reservation #</th><th>Guest</th><th>Room</th><th>Type</th><th>Check-In</th><th>Check-Out</th><th>Nights</th><th>Amount (LKR)</th><th>Status</th></tr></thead>
<tbody>
<c:forEach var="res" items="${reportData}">
<tr><td>${res.reservationNumber}</td><td>${res.guestName}</td><td>${res.roomNumber}</td><td>${res.roomType}</td>
<td>${res.checkInDate}</td><td>${res.checkOutDate}</td><td>${res.totalNights}</td>
<td>${res.finalAmount}</td><td><span class="badge badge-${res.status.toLowerCase()}">${res.status}</span></td></tr>
</c:forEach>
</tbody></table></div>
<div style="margin-top:20px;padding:20px;background:#eafaf1;border-radius:8px;font-size:18px;font-weight:bold;color:#1a5276;">
 Total Revenue: LKR ${totalRevenue}
</div></div></c:if>
</div></div></body></html>
