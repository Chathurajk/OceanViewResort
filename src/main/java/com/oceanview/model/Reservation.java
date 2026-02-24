package com.oceanview.model;

import java.time.LocalDate;

public class Reservation {
    private int       reservationId;
    private String    reservationNumber;
    private int       guestId;
    private int       roomId;
    private int       staffId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int       totalNights;
    private double    totalAmount;
    private double    discountPercent;
    private double    finalAmount;
    private String    status;
    private String    specialRequests;
    private String    guestName;
    private String    guestNic;
    private String    guestEmail;
    private String    guestContact;
    private String    guestAddress;
    private String    roomNumber;
    private String    roomType;
    private double    pricePerNight;

    public Reservation() {}

    public int       getReservationId()     { return reservationId; }
    public String    getReservationNumber() { return reservationNumber; }
    public int       getGuestId()           { return guestId; }
    public int       getRoomId()            { return roomId; }
    public int       getStaffId()           { return staffId; }
    public LocalDate getCheckInDate()       { return checkInDate; }
    public LocalDate getCheckOutDate()      { return checkOutDate; }
    public int       getTotalNights()       { return totalNights; }
    public double    getTotalAmount()       { return totalAmount; }
    public double    getDiscountPercent()   { return discountPercent; }
    public double    getFinalAmount()       { return finalAmount; }
    public String    getStatus()            { return status; }
    public String    getSpecialRequests()   { return specialRequests; }
    public String    getGuestName()         { return guestName; }
    public String    getGuestNic()          { return guestNic; }
    public String    getGuestEmail()        { return guestEmail; }
    public String    getGuestContact()      { return guestContact; }
    public String    getGuestAddress()      { return guestAddress; }
    public String    getRoomNumber()        { return roomNumber; }
    public String    getRoomType()          { return roomType; }
    public double    getPricePerNight()     { return pricePerNight; }

    public void setReservationId(int reservationId)            { this.reservationId     = reservationId; }
    public void setReservationNumber(String reservationNumber)  { this.reservationNumber = reservationNumber; }
    public void setGuestId(int guestId)                        { this.guestId           = guestId; }
    public void setRoomId(int roomId)                          { this.roomId            = roomId; }
    public void setStaffId(int staffId)                        { this.staffId           = staffId; }
    public void setCheckInDate(LocalDate checkInDate)          { this.checkInDate       = checkInDate; }
    public void setCheckOutDate(LocalDate checkOutDate)        { this.checkOutDate      = checkOutDate; }
    public void setTotalNights(int totalNights)                { this.totalNights       = totalNights; }
    public void setTotalAmount(double totalAmount)              { this.totalAmount       = totalAmount; }
    public void setDiscountPercent(double discountPercent)      { this.discountPercent   = discountPercent; }
    public void setFinalAmount(double finalAmount)              { this.finalAmount       = finalAmount; }
    public void setStatus(String status)                        { this.status            = status; }
    public void setSpecialRequests(String specialRequests)      { this.specialRequests   = specialRequests; }
    public void setGuestName(String guestName)                  { this.guestName         = guestName; }
    public void setGuestNic(String guestNic)                    { this.guestNic          = guestNic; }
    public void setGuestEmail(String guestEmail)                { this.guestEmail        = guestEmail; }
    public void setGuestContact(String guestContact)            { this.guestContact      = guestContact; }
    public void setGuestAddress(String guestAddress)            { this.guestAddress      = guestAddress; }
    public void setRoomNumber(String roomNumber)                { this.roomNumber        = roomNumber; }
    public void setRoomType(String roomType)                    { this.roomType          = roomType; }
    public void setPricePerNight(double pricePerNight)          { this.pricePerNight     = pricePerNight; }

    public boolean isActive() {
        return "CONFIRMED".equals(status) || "CHECKED_IN".equals(status);
    }

    public double getDiscountAmount() {
        return totalAmount * (discountPercent / 100.0);
    }
}
