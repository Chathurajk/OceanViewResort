<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>View Reservation</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head><body>
<div class="sidebar"><div class="sidebar-brand"><div class="brand-icon"></div><h2>Ocean View Resort</h2></div>
<nav class="sidebar-menu">
<a href="${pageContext.request.contextPath}/staff/dashboard"> Dashboard</a>
<a href="${pageContext.request.contextPath}/staff/reservation?action=add">➕ New Reservation</a>
<a href="${pageContext.request.contextPath}/staff/reservation?action=view" class="active"> View Reservation</a>
<a href="${pageContext.request.contextPath}/staff/reservation"> All Reservations</a>
<a href="${pageContext.request.contextPath}/logout" style="color:#ff8a80;"> Logout</a>
</nav></div>
<div class="main-content"><div class="topbar"><span class="topbar-title"> View Reservation</span></div>
<div class="page-content">
<c:if test="${param.success == 1}"><div class="alert alert-success"> Reservation created successfully!</div></c:if>
<div class="card"><div class="card-title">Search Reservation</div>
<form action="${pageContext.request.contextPath}/staff/reservation" method="get" style="display:flex;gap:12px;">
<input type="hidden" name="action" value="view">
<input type="text" name="resNum" placeholder="e.g. OVR-20250101-0001" style="flex:1;padding:11px 14px;border:1.5px solid #d5d8dc;border-radius:8px;font-size:14px;">
<button type="submit" class="btn btn-primary"> Search</button>
</form></div>
<c:if test="${notFound}"><div class="alert alert-error">️ Reservation not found.</div></c:if>
<c:if test="${not empty reservation}">
<div class="card"><div class="card-title">Reservation – ${reservation.reservationNumber}</div>
<div style="display:grid;grid-template-columns:1fr 1fr;gap:32px;">
<div><h3 style="color:#1a5276;margin-bottom:16px;">👤 Guest Info</h3>
<table style="width:100%;font-size:14px;">
<tr><td style="padding:8px;font-weight:bold;color:#555;">Full Name</td><td style="padding:8px;">${reservation.guestName}</td></tr>
<tr style="background:#f8fafc;"><td style="padding:8px;font-weight:bold;color:#555;">NIC</td><td style="padding:8px;">${reservation.guestNic}</td></tr>
<tr><td style="padding:8px;font-weight:bold;color:#555;">Email</td><td style="padding:8px;">${reservation.guestEmail}</td></tr>
<tr style="background:#f8fafc;"><td style="padding:8px;font-weight:bold;color:#555;">Contact</td><td style="padding:8px;">${reservation.guestContact}</td></tr>
<tr><td style="padding:8px;font-weight:bold;color:#555;">Address</td><td style="padding:8px;">${reservation.guestAddress}</td></tr>
</table></div>
<div><h3 style="color:#1a5276;margin-bottom:16px;"> Booking Info</h3>
<table style="width:100%;font-size:14px;">
<tr><td style="padding:8px;font-weight:bold;color:#555;">Room</td><td style="padding:8px;">${reservation.roomNumber} – ${reservation.roomType}</td></tr>
<tr style="background:#f8fafc;"><td style="padding:8px;font-weight:bold;color:#555;">Check-In</td><td style="padding:8px;">${reservation.checkInDate}</td></tr>
<tr><td style="padding:8px;font-weight:bold;color:#555;">Check-Out</td><td style="padding:8px;">${reservation.checkOutDate}</td></tr>
<tr style="background:#f8fafc;"><td style="padding:8px;font-weight:bold;color:#555;">Nights</td><td style="padding:8px;">${reservation.totalNights}</td></tr>
<tr><td style="padding:8px;font-weight:bold;color:#555;">Status</td><td style="padding:8px;"><span class="badge badge-${reservation.status.toLowerCase()}">${reservation.status}</span></td></tr>
<tr style="background:#eafaf1;"><td style="padding:8px;font-weight:bold;color:#1a5276;font-size:15px;">Total (LKR)</td><td style="padding:8px;color:#27ae60;font-weight:bold;font-size:15px;">${reservation.finalAmount}</td></tr>
</table></div></div>
<div style="margin-top:24px;">
<a href="${pageContext.request.contextPath}/staff/reservation?action=bill&resNum=${reservation.reservationNumber}" class="btn btn-primary"> Print Bill</a>
</div></div></c:if>
</div></div></body></html>
