package com.oceanview.model;
public class Room {
    private int roomId; private String roomNumber,roomType,status,description; private double pricePerNight;
    public Room() {}
    public Room(String roomNumber,String roomType,double pricePerNight,String description) {
        this.roomNumber=roomNumber; this.roomType=roomType; this.pricePerNight=pricePerNight;
        this.description=description; this.status="AVAILABLE";
    }
    public int getRoomId() { return roomId; }
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public String getStatus() { return status; }
    public String getDescription() { return description; }
    public void setRoomId(int v) { roomId=v; }
    public void setRoomNumber(String v) { roomNumber=v; }
    public void setRoomType(String v) { roomType=v; }
    public void setPricePerNight(double v) { pricePerNight=v; }
    public void setStatus(String v) { status=v; }
    public void setDescription(String v) { description=v; }
    public boolean isAvailable() { return "AVAILABLE".equals(status); }
    @Override public String toString() { return "Room{number="+roomNumber+",type="+roomType+",status="+status+"}"; }
}
