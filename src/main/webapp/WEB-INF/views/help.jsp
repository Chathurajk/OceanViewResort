<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.oceanview.model.Staff" %>
<% Staff user = (Staff) session.getAttribute("loggedInUser"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Help – Ocean View Resort</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="sidebar">
    <div class="sidebar-brand">
        <div class="brand-icon"></div>
        <h2>Ocean View Resort</h2>
    </div>
    <nav class="sidebar-menu">
        <% if (user != null && user.isManager()) { %>
            <a href="${pageContext.request.contextPath}/manager/dashboard">🏠 Dashboard</a>
            <a href="${pageContext.request.contextPath}/manager/reservation?action=add">➕ New Reservation</a>
            <a href="${pageContext.request.contextPath}/manager/reservation?action=view">🔍 View Reservation</a>
            <a href="${pageContext.request.contextPath}/manager/reservation">📋 Manage Reservations</a>
            <a href="${pageContext.request.contextPath}/manager/staff">👥 Manage Staff</a>
            <a href="${pageContext.request.contextPath}/manager/report">📊 Monthly Report</a>
            <a href="${pageContext.request.contextPath}/manager/help" class="active"> Help</a>
        <% } else { %>
            <a href="${pageContext.request.contextPath}/staff/dashboard">🏠 Dashboard</a>
            <a href="${pageContext.request.contextPath}/staff/reservation?action=add">➕ New Reservation</a>
            <a href="${pageContext.request.contextPath}/staff/reservation?action=view">🔍 View Reservation</a>
            <a href="${pageContext.request.contextPath}/staff/reservation">📋 All Reservations</a>
            <a href="${pageContext.request.contextPath}/staff/help" class="active"> Help</a>
        <% } %>
        <a href="${pageContext.request.contextPath}/logout" style="color:#ff8a80;"> Logout</a>
    </nav>
</div>
<div class="main-content">
    <div class="topbar">
        <span class="topbar-title"> Help Guide</span>
        <div class="topbar-user">
            Welcome, <strong><%= user != null ? user.getFullName() : "" %></strong>
            <span class="user-badge"><%= user != null ? user.getRole() : "" %></span>
        </div>
    </div>
    <div class="page-content">
        <div class="card">
            <div class="card-title">System User Guide</div>
            <div style="line-height:2.2;">
                <h3 style="color:#1a5276;">1. Adding a New Reservation</h3>
                <p>Click "New Reservation" → Fill guest details (Name, NIC, Contact, Address) → Select room → Choose dates → Click Confirm.</p>

                <h3 style="color:#1a5276;margin-top:20px;">2. Viewing a Reservation</h3>
                <p>Click "View Reservation" → Enter Reservation Number (e.g. OVR-20250101-0001) → Click Search.</p>

                <h3 style="color:#1a5276;margin-top:20px;">3. Printing a Bill</h3>
                <p>Search for reservation → Click "Print Bill" → Click Print button.</p>

                <h3 style="color:#1a5276;margin-top:20px;">4. NIC Formats Accepted</h3>
                <p>Old: 9 digits + V or X &nbsp;→&nbsp; e.g. <strong>901234567V</strong></p>
                <p>New: 12 digits &nbsp;→&nbsp; e.g. <strong>199012345678</strong></p>

                <h3 style="color:#1a5276;margin-top:20px;">5. Contact Support</h3>
                <p> +94 91 222 3344 &nbsp;|&nbsp;  info@oceanviewresort.lk</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
```


