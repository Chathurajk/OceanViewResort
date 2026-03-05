<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Bill – Ocean View Resort</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body style="background:#f0f4f8;">
<div class="no-print" style="text-align:center;padding:20px;">
<button onclick="window.print()" class="btn btn-primary"> Print Bill</button>&nbsp;
<a href="javascript:history.back()" class="btn btn-warning">← Back</a></div>
<div class="bill-wrap">
<div class="bill-header"><h1> Ocean View Resort</h1><p>Galle, Sri Lanka | Tel: +94 91 222 3344</p><p>info@oceanviewresort.lk</p></div>
<h2 style="text-align:center;color:#1a5276;margin-bottom:24px;">OFFICIAL RECEIPT</h2>
<div class="bill-row"><span>Reservation Number</span><span><strong>${reservation.reservationNumber}</strong></span></div>
<div class="bill-row"><span>Guest Name</span><span>${reservation.guestName}</span></div>
<div class="bill-row"><span>NIC</span><span>${reservation.guestNic}</span></div>
<div class="bill-row"><span>Contact</span><span>${reservation.guestContact}</span></div>
<div class="bill-row"><span>Room Number</span><span>${reservation.roomNumber}</span></div>
<div class="bill-row"><span>Room Type</span><span>${reservation.roomType}</span></div>
<div class="bill-row"><span>Price Per Night (LKR)</span><span>${reservation.pricePerNight}</span></div>
<div class="bill-row"><span>Check-In Date</span><span>${reservation.checkInDate}</span></div>
<div class="bill-row"><span>Check-Out Date</span><span>${reservation.checkOutDate}</span></div>
<div class="bill-row"><span>Total Nights</span><span>${reservation.totalNights}</span></div>
<div class="bill-row"><span>Sub Total (LKR)</span><span>${reservation.totalAmount}</span></div>
<div class="bill-row"><span>Discount (${reservation.discountPercent}%)</span><span>- ${reservation.discountAmount}</span></div>
<div class="bill-total"><span>TOTAL PAYABLE (LKR)</span><span>${reservation.finalAmount}</span></div>
<div style="text-align:center;margin-top:32px;color:#7f8c8d;font-size:13px;">
<p>Thank you for choosing Ocean View Resort!</p><p>We hope to welcome you again. </p></div></div>
</body></html>
