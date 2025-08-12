package com.syos.model;

import java.time.LocalDate;

public class StockBatch {
    private int batchID;
    private String itemCode; //item_code(FK to Item)
    private LocalDate purchaseDate;
    private int quantity;
    private LocalDate expiryDate;

    StockBatch () {}

    public StockBatch(int batchID, String itemCode, LocalDate purchaseDate, int quantity, LocalDate expiryDate) {
        this.batchID = batchID;
        this.itemCode = itemCode;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getBatchID() { return batchID; }

    public void setBatchID(int batchID) { this.batchID = batchID; }

    public String getItemCode() { return itemCode; }

    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public LocalDate getPurchaseDate() { return purchaseDate; }

    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDate getExpiryDate() { return expiryDate; }

    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
}
