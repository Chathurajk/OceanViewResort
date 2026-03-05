<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Reservation – Ocean View Resort</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="sidebar">
    <div class="sidebar-brand"><div class="brand-icon"></div><h2>Ocean View Resort</h2></div>
    <nav class="sidebar-menu">
        <a href="${pageContext.request.contextPath}/staff/dashboard"> Dashboard</a>
        <a href="${pageContext.request.contextPath}/staff/reservation?action=add" class="active"> New Reservation</a>
        <a href="${pageContext.request.contextPath}/staff/reservation?action=view"> View Reservation</a>
        <a href="${pageContext.request.contextPath}/staff/reservation"> All Reservations</a>
        <a href="${pageContext.request.contextPath}/staff/help"> Help</a>
        <a href="${pageContext.request.contextPath}/logout" style="color:#ff8a80;"> Logout</a>
    </nav>
</div>
<div class="main-content">
    <div class="topbar"><span class="topbar-title"> Add New Reservation</span></div>
    <div class="page-content">
        <c:if test="${not empty error}">
            <div class="alert alert-error"> ${error}</div>
        </c:if>
        <div class="card">
            <div class="card-title">Guest & Booking Details</div>
            <form action="${pageContext.request.contextPath}/staff/reservation" method="post">
                <input type="hidden" name="action" value="add">
                <h3 style="color:#1a5276;margin-bottom:16px;"> Guest Information</h3>
                <div style="display:grid;grid-template-columns:1fr 1fr;gap:20px;">
                    <div class="form-group">
                        <label>Full Name *</label>
                        <input type="text" name="fullName" placeholder="e.g. Kamal Perera" required>
                    </div>
                    <div class="form-group">
                        <label>NIC Number *</label>
                        <input type="text" name="nic" placeholder="199012345678 or 901234567V" required>
                    </div>
                    <div class="form-group">
                        <label>Email (optional)</label>
                        <input type="email" name="email" placeholder="guest@example.com">
                    </div>
                    <div class="form-group">
                        <label>Contact Number * (10 digits)</label>
                        <input type="text" name="contact" placeholder="0771234567" maxlength="10" required>
                    </div>
                </div>
                <div class="form-group">
                    <label>Address *</label>
                    <textarea name="address" rows="2" placeholder="No.10, Main Street, Colombo" required></textarea>
                </div>

                <h3 style="color:#1a5276;margin:24px 0 16px;"> Booking Details</h3>
                <div style="display:grid;grid-template-columns:1fr 1fr;gap:20px;">
                    <div class="form-group">
                        <label>Select Room *</label>
                        <select name="roomId" required onchange="updatePrice(this)">
                            <option value="">-- Choose a Room --</option>
                            <c:forEach var="room" items="${availableRooms}">
                                <option value="${room.roomId}" data-price="${room.pricePerNight}">
                                    Room ${room.roomNumber} – ${room.roomType} (LKR ${room.pricePerNight}/night)
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Discount (%)</label>
                        <input type="number" name="discount" id="discount" value="0" min="0" max="50" onchange="calculateTotal()">
                    </div>
                    <div class="form-group">
                        <label>Check-In Date *</label>
                        <input type="date" name="checkInDate" id="checkIn" required
                               min="<%= java.time.LocalDate.now() %>" onchange="calculateTotal()">
                    </div>
                    <div class="form-group">
                        <label>Check-Out Date *</label>
                        <input type="date" name="checkOutDate" id="checkOut" required onchange="calculateTotal()">
                    </div>
                </div>
                <div class="form-group">
                    <label>Special Requests</label>
                    <textarea name="specialRequests" rows="2" placeholder="Any special requirements..."></textarea>
                </div>

                <div id="billPreview" style="background:#f0f8ff;border:2px solid #1a5276;border-radius:10px;padding:20px;margin:16px 0;display:none;">
                    <strong style="color:#1a5276;"> Bill Preview</strong>
                    <p id="billDetails" style="margin-top:10px;color:#2c3e50;font-size:15px;line-height:2;"></p>
                </div>

                <button type="submit" class="btn btn-primary"> Confirm Reservation</button>
                &nbsp;
                <a href="${pageContext.request.contextPath}/staff/dashboard" class="btn btn-warning"> Cancel</a>
            </form>
        </div>
    </div>
</div>
<script>
let pricePerNight = 0;
function updatePrice(select) {
    pricePerNight = parseFloat(select.options[select.selectedIndex].getAttribute('data-price')) || 0;
    calculateTotal();
}
function calculateTotal() {
    const checkIn  = document.getElementById('checkIn').value;
    const checkOut = document.getElementById('checkOut').value;
    const discount = parseFloat(document.getElementById('discount').value) || 0;
    if (checkIn && checkOut && pricePerNight > 0) {
        const nights = Math.round((new Date(checkOut) - new Date(checkIn)) / (1000*60*60*24));
        if (nights > 0) {
            const total   = nights * pricePerNight;
            const discAmt = total * (discount / 100);
            const final_  = total - discAmt;
            document.getElementById('billPreview').style.display = 'block';
            document.getElementById('billDetails').innerHTML =
                ' Nights: <strong>' + nights + '</strong><br>' +
                ' Rate: LKR ' + pricePerNight.toFixed(2) + ' × ' + nights + ' = LKR ' + total.toFixed(2) + '<br>' +
                '🏷 Discount (' + discount + '%): - LKR ' + discAmt.toFixed(2) + '<br>' +
                '<strong style="font-size:18px;color:#27ae60;"> Total Payable: LKR ' + final_.toFixed(2) + '</strong>';
        } else {
            document.getElementById('billPreview').style.display = 'none';
        }
    }
}
</script>
</body>
</html>
