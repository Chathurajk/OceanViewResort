package com.oceanview.model;
public class Guest {
    private int guestId; private String fullName, nic, email, contact, address;
    public Guest() {}
    public Guest(String fullName, String nic, String email, String contact, String address) {
        this.fullName=fullName; this.nic=nic; this.email=email; this.contact=contact; this.address=address;
    }
    public int getGuestId() { return guestId; }
    public String getFullName() { return fullName; }
    public String getNic() { return nic; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
    public String getAddress() { return address; }
    public void setGuestId(int v) { guestId=v; }
    public void setFullName(String v) { fullName=v; }
    public void setNic(String v) { nic=v; }
    public void setEmail(String v) { email=v; }
    public void setContact(String v) { contact=v; }
    public void setAddress(String v) { address=v; }
    @Override public String toString() { return "Guest{id="+guestId+",name="+fullName+",nic="+nic+"}"; }
}
