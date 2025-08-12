package com.syos.model;

import java.time.LocalDate;

public class OnlineOrder {
    private int orderId;
    private int userId; // user_id (FK to UserAccount)
    private int billId; // bill_id (FK to Bill)
    private LocalDate orderDate;

    public OnlineOrder() {}

    public OnlineOrder(int orderId, int userId, int billId, LocalDate orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.billId = billId;
        this.orderDate = orderDate;
    }

    public int getOrderId() { return orderId; }

    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public int getBillId() { return billId; }

    public void setBillId(int billId) { this.billId = billId; }

    public LocalDate getOrderDate() { return orderDate; }

    public void setOrderDate(LocalDate orderDate) {this.orderDate = orderDate; }
}
