package com.oceanview.model;
import java.time.LocalDateTime;
public class Bill {
    private int billId,reservationId; private double amountPaid; private String paymentMethod; private LocalDateTime paymentDate;
    public Bill() {}
    public Bill(int reservationId,double amountPaid,String paymentMethod) {
        this.reservationId=reservationId; this.amountPaid=amountPaid; this.paymentMethod=paymentMethod;
    }
    public int getBillId() { return billId; }
    public int getReservationId() { return reservationId; }
    public double getAmountPaid() { return amountPaid; }
    public String getPaymentMethod() { return paymentMethod; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setBillId(int v) { billId=v; }
    public void setReservationId(int v) { reservationId=v; }
    public void setAmountPaid(double v) { amountPaid=v; }
    public void setPaymentMethod(String v) { paymentMethod=v; }
    public void setPaymentDate(LocalDateTime v) { paymentDate=v; }
}
