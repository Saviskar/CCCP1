package com.syos.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bill {
    private int billId;
    private LocalDate billDate;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private BigDecimal cashTendered;
    private BigDecimal cashReturned;
    private BillType billType; // bill_type (ENUM: OTC, ONLINE)

    public enum BillType {
        OTC, ONLINE
    }

    public Bill() {}

    public Bill(int billId, LocalDate billDate, BigDecimal totalAmount, BigDecimal discount, BigDecimal cashTendered, BigDecimal cashReturned, BillType billType) {
        this.billId = billId;
        this.billDate = billDate;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.cashTendered = cashTendered;
        this.cashReturned = cashReturned;
        this.billType = billType;
    }

    public int getBillId() { return billId; }

    public void setBillId(int billId) { this.billId = billId; }

    public LocalDate getBillDate() { return billDate; }

    public void setBillDate(LocalDate billDate) { this.billDate = billDate; }

    public BigDecimal getTotalAmount() { return totalAmount; }

    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public BigDecimal getDiscount() { return discount; }

    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public BigDecimal getCashTendered() { return cashTendered; }

    public void setCashTendered(BigDecimal cashTendered) { this.cashTendered = cashTendered; }

    public BigDecimal getCashReturned() { return cashReturned; }

    public void setCashReturned(BigDecimal cashReturned) { this.cashReturned = cashReturned; }

    public BillType getBillType() { return billType; }

    public void setBillType(BillType billType) { this.billType = billType; }
}
