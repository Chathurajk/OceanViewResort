package com.oceanview.model;
public class Staff {
    private int staffId; private String username,passwordHash,fullName,role,email,contact; private boolean isActive;
    public Staff() {}
    public Staff(String username,String fullName,String role,String email) {
        this.username=username; this.fullName=fullName; this.role=role; this.email=email; this.isActive=true;
    }
    public int getStaffId() { return staffId; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName() { return fullName; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
    public boolean isActive() { return isActive; }
    public void setStaffId(int v) { staffId=v; }
    public void setUsername(String v) { username=v; }
    public void setPasswordHash(String v) { passwordHash=v; }
    public void setFullName(String v) { fullName=v; }
    public void setRole(String v) { role=v; }
    public void setEmail(String v) { email=v; }
    public void setContact(String v) { contact=v; }
    public void setActive(boolean v) { isActive=v; }
    public boolean isManager() { return "MANAGER".equals(role); }
    @Override public String toString() { return "Staff{id="+staffId+",username="+username+",role="+role+"}"; }
}
