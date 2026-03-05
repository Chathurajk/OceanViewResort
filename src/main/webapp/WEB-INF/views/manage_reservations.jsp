<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Manage Reservations</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head><body>
<div class="sidebar"><div class="sidebar-brand"><div class="brand-icon"></div><h2>Ocean View Resort</h2><p>Manager Panel</p></div>
<nav class="sidebar-menu">
<a href="${pageContext.request.contextPath}/manager/dashboard"> Dashboard</a>
<a href="${pageContext.request.contextPath}/manager/reservation?action=add"> New Reservation</a>
<a href="${pageContext.request.contextPath}/manager/reservation?action=view"> View Reservation</a>
<a href="${pageContext.request.contextPath}/manager/reservation" class="active"> Manage Reservations</a>
<a href="${pageContext.request.contextPath}/manager/staff"> Manage Staff</a>
<a href="${pageContext.request.contextPath}/manager/report"> Monthly Report</a>
<a href="${pageContext.request.contextPath}/logout" style="color:#ff8a80;"> Logout</a>
</nav></div>
<div class="main-content"><div class="topbar"><span class="topbar-title"> Manage Reservations</span></div>
<div class="page-content">
<c:if test="${not empty param.success}"><div class="alert alert-success"> Status updated successfully!</div></c:if>
<c:if test="${not empty error}"><div class="alert alert-error">️ ${error}</div></c:if>
<div class="card"><div class="card-title">All Reservations</div>
<div class="table-container"><table>
<thead><tr><th>Reservation #</th><th>Guest</th><th>Room</th><th>Check-In</th><th>Check-Out</th><th>Nights</th><th>Amount (LKR)</th><th>Status</th><th>Actions</th></tr></thead>
<tbody>
<c:forEach var="res" items="${reservations}">
<tr>
<td><strong>${res.reservationNumber}</strong></td>
<td>${res.guestName}</td>
<td>${res.roomNumber}</td>
<td>${res.checkInDate}</td>
<td>${res.checkOutDate}</td>
<td>${res.totalNights}</td>
<td>${res.finalAmount}</td>
<td><span class="badge badge-${res.status.toLowerCase()}">${res.status}</span></td>
<td>
<form action="${pageContext.request.contextPath}/manager/reservation" method="post" style="display:inline;">
<input type="hidden" name="action" value="updateStatus">
<input type="hidden" name="reservationId" value="${res.reservationId}">
<select name="newStatus" style="padding:4px;font-size:12px;border-radius:4px;">
<option>CONFIRMED</option><option>CHECKED_IN</option><option>CHECKED_OUT</option><option>CANCELLED</option>
</select>
<button type="submit" class="btn btn-primary" style="padding:4px 8px;font-size:12px;margin-left:4px;">Update</button>
</form>
<a href="${pageContext.request.contextPath}/manager/reservation?action=bill&resNum=${res.reservationNumber}"
   class="btn btn-warning" style="padding:4px 8px;font-size:12px;"></a>
</td>
</tr>
</c:forEach>
<c:if test="${empty reservations}">
<tr><td colspan="9" style="text-align:center;color:#999;padding:24px;">No reservations found.</td></tr>
</c:if>
</tbody></table></div></div>
</div></div></body></html>
