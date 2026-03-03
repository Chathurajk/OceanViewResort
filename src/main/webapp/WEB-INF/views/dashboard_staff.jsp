<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.oceanview.model.Staff" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<% Staff user = (Staff) session.getAttribute("loggedInUser"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard – Ocean View Resort</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="sidebar">
    <div class="sidebar-brand">
        <div class="brand-icon"></div>
        <h2>Ocean View Resort</h2>
        <p>Reception Panel</p>
    </div>
    <nav class="sidebar-menu">
        <a href="${pageContext.request.contextPath}/staff/dashboard" class="active"> Dashboard</a>
        <a href="${pageContext.request.contextPath}/staff/reservation?action=add"> New Reservation</a>
        <a href="${pageContext.request.contextPath}/staff/reservation?action=view"> View Reservation</a>
        <a href="${pageContext.request.contextPath}/staff/reservation"> All Reservations</a>
        <a href="${pageContext.request.contextPath}/staff/help"> Help</a>
        <a href="${pageContext.request.contextPath}/logout" style="color:#ff8a80;"> Logout</a>
    </nav>
</div>
<div class="main-content">
    <div class="topbar">
        <span class="topbar-title"> Dashboard</span>
        <div class="topbar-user">
            Welcome, <strong><%= user != null ? user.getFullName() : "" %></strong>
            <span class="user-badge">Reception</span>
        </div>
    </div>
    <div class="page-content">
        <c:if test="${not empty param.error}">
            <div class="alert alert-error"> You are not authorized to access that page.</div>
        </c:if>
        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-number" style="color:#27ae60;">${availableCount}</div>
                <div class="stat-label">Available Rooms</div>
            </div>
            <div class="stat-card">
                <div class="stat-number" style="color:#e74c3c;">${occupiedCount}</div>
                <div class="stat-label">Occupied Rooms</div>
            </div>
            <div class="stat-card">
                <div class="stat-number" style="color:#3498db;">${confirmedCount}</div>
                <div class="stat-label">Confirmed Bookings</div>
            </div>
            <div class="stat-card">
                <div class="stat-number" style="color:#9b59b6;">${totalRooms}</div>
                <div class="stat-label">Total Rooms</div>
            </div>
        </div>
        <div class="card">
            <div class="card-title">⚡ Quick Actions</div>
            <a href="${pageContext.request.contextPath}/staff/reservation?action=add" class="btn btn-primary"> New Reservation</a>
            &nbsp;
            <a href="${pageContext.request.contextPath}/staff/reservation?action=view" class="btn btn-success"> Search Reservation</a>
        </div>
        <div class="card">
            <div class="card-title"> Recent Reservations</div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr><th>Reservation #</th><th>Guest</th><th>Room</th><th>Check-In</th><th>Check-Out</th><th>Status</th><th>Amount (LKR)</th><th>Action</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="res" items="${recentReservations}">
                        <tr>
                            <td><strong>${res.reservationNumber}</strong></td>
                            <td>${res.guestName}</td>
                            <td>${res.roomNumber} (${res.roomType})</td>
                            <td>${res.checkInDate}</td>
                            <td>${res.checkOutDate}</td>
                            <td><span class="badge badge-${res.status.toLowerCase()}">${res.status}</span></td>
                            <td>${res.finalAmount}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/staff/reservation?action=bill&resNum=${res.reservationNumber}"
                                   class="btn btn-warning" style="padding:5px 10px;font-size:12px;"> Bill</a>
                            </td>
                        </tr>
                        </c:forEach>
                        <c:if test="${empty recentReservations}">
                            <tr><td colspan="8" style="text-align:center;color:#999;padding:24px;">No reservations found.</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
