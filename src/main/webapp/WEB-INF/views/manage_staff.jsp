<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><title>Manage Staff</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head><body>
<div class="sidebar"><div class="sidebar-brand"><div class="brand-icon"></div><h2>Ocean View Resort</h2><p>Manager Panel</p></div>
<nav class="sidebar-menu">
<a href="${pageContext.request.contextPath}/manager/dashboard"> Dashboard</a>
<a href="${pageContext.request.contextPath}/manager/reservation?action=add"> New Reservation</a>
<a href="${pageContext.request.contextPath}/manager/reservation"> Manage Reservations</a>
<a href="${pageContext.request.contextPath}/manager/staff" class="active">👥 Manage Staff</a>
<a href="${pageContext.request.contextPath}/manager/report"> Monthly Report</a>
<a href="${pageContext.request.contextPath}/logout" style="color:#ff8a80;"> Logout</a>
</nav></div>
<div class="main-content"><div class="topbar"><span class="topbar-title"> Manage Staff</span></div>
<div class="page-content">
<c:if test="${not empty param.success}"><div class="alert alert-success"> Operation successful!</div></c:if>
<c:if test="${not empty error}"><div class="alert alert-error">️ ${error}</div></c:if>
<div class="card"><div class="card-title"> Add New Staff</div>
<form action="${pageContext.request.contextPath}/manager/staff" method="post">
<input type="hidden" name="action" value="add">
<div style="display:grid;grid-template-columns:1fr 1fr 1fr;gap:16px;">
<div class="form-group"><label>Username *</label><input type="text" name="username" required></div>
<div class="form-group"><label>Password *</label><input type="password" name="password" required></div>
<div class="form-group"><label>Full Name</label><input type="text" name="fullName"></div>
<div class="form-group"><label>Role</label>
<select name="role"><option value="RECEPTION">Reception</option><option value="MANAGER">Manager</option></select></div>
<div class="form-group"><label>Email</label><input type="email" name="email"></div>
<div class="form-group"><label>Contact</label><input type="text" name="contact" maxlength="10"></div>
</div>
<button type="submit" class="btn btn-primary"> Add Staff</button>
</form></div>
<div class="card"><div class="card-title">All Staff Members</div>
<div class="table-container"><table>
<thead><tr><th>ID</th><th>Username</th><th>Full Name</th><th>Role</th><th>Email</th><th>Contact</th><th>Status</th><th>Action</th></tr></thead>
<tbody>
<c:forEach var="staff" items="${staffList}">
<tr>
<td>${staff.staffId}</td><td><strong>${staff.username}</strong></td>
<td>${staff.fullName}</td>
<td><span class="badge badge-${staff.role.toLowerCase()}">${staff.role}</span></td>
<td>${staff.email}</td><td>${staff.contact}</td>
<td><span class="badge badge-${staff.active}">${staff.active ? 'Active' : 'Inactive'}</span></td>
<td>
<c:if test="${staff.active}">
<form action="${pageContext.request.contextPath}/manager/staff" method="post" style="display:inline;">
<input type="hidden" name="action" value="deactivate">
<input type="hidden" name="staffId" value="${staff.staffId}">
<button type="submit" class="btn btn-danger" style="padding:5px 10px;font-size:12px;">Deactivate</button>
</form>
</c:if>
<c:if test="${!staff.active}">
<form action="${pageContext.request.contextPath}/manager/staff" method="post" style="display:inline;">
<input type="hidden" name="action" value="activate">
<input type="hidden" name="staffId" value="${staff.staffId}">
<button type="submit" class="btn btn-success" style="padding:5px 10px;font-size:12px;">Activate</button>
</form>
</c:if>
</td>
</tr>
</c:forEach>
</tbody></table></div></div>
</div></div></body></html>
