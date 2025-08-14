package com.syos.dao;

import com.syos.model.ShelfStock;

import java.util.List;

public interface ShelfStockDAO {
    //What does it do?

    //It updates the quantity of a specific item on a specific shelf type (saleType), either OTC (over-the-counter) or ONLINE.
    //For example, after a sale or restock, you might want to reduce or increase the quantity of the item on that shelf.

    // what I have done is it just increases the amount of quantity in the shelf when someone inputs

    boolean updateShelfQuantity(String itemCode, int quantity, String saleType);
    int getShelfQuantity(String itemCode, String saleType);
    List<ShelfStock> getLowStockItems();
}
