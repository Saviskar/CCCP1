package com.syos.model;

import java.math.BigDecimal;

public class BillLine {
    private int billLineId;
    private int billId; // bill_id (FK to Bill)
    private String itemCode; // item_code (FK to Item)
    private int quantity;
    private BigDecimal totalPrice;

    public BillLine() {}

    public BillLine(int billLineId, int billId, String itemCode, int quantity, BigDecimal totalPrice) {
        this.billLineId = billLineId;
        this.billId = billId;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getBillLineId() { return billLineId; }

    public void setBillLineId(int billLineId) { this.billLineId = billLineId; }

    public int getBillId() { return billId; }

    public void setBillId(int billId) { this.billId = billId; }

    public String getItemCode() { return itemCode; }

    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public BigDecimal getTotalPrice() { return totalPrice; }

    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}
