package com.syos;

import com.syos.dao.StockBatchDAO;
import com.syos.dao.daoImpl.ShelfStockDAOImpl;
import com.syos.dao.daoImpl.StockBatchDAOImpl;
import com.syos.model.ShelfStock;
import com.syos.model.StockBatch;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShelfStockDAOImpl dao = new ShelfStockDAOImpl();

        List<ShelfStock> lowStockItems = dao.getLowStockItems();

        if (lowStockItems.isEmpty()) {
            System.out.println("No low stock items found.");
        } else {
            System.out.println("Low stock items:");
            for (ShelfStock stock : lowStockItems) {
                System.out.println(
                        "Shelf ID: " + stock.getShelfId() +
                                ", Item Code: " + stock.getItemCode() +
                                ", Quantity: " + stock.getQuantity() +
                                ", Sale Type: " + stock.getSaleType()
                );
            }
        }
    }
}