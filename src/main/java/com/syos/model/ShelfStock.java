package com.syos.model;

public class ShelfStock {
    private int shelfId;
    private String itemCode; // item_code (FK to Item)
    private int quantity;
    private SaleType saleType; // sale_type (ENUM: OTC, ONLINE)

    public enum SaleType {
        OTC, ONLINE
    }

    public ShelfStock() {}

    public ShelfStock(int shelfId, String itemCode, int quantity, SaleType saleType) {
        this.shelfId = shelfId;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.saleType = saleType;
    }

    public int getShelfId() { return shelfId; }

    public void setShelfId(int shelfId) { this.shelfId = shelfId; }

    public String getItemCode() { return itemCode; }

    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public SaleType getSaleType() { return saleType; }

    public void setSaleType(SaleType saleType) { this.saleType = saleType; }
}
