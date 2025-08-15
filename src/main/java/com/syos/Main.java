package com.syos;

import com.syos.dao.daoImpl.ShelfStockDAOImpl;
import com.syos.dao.daoImpl.StockBatchDAOImpl;
import com.syos.model.ShelfStock;
import com.syos.service.StockService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create DAO implementations (real DB connections)
        StockBatchDAOImpl stockBatchDAO = new StockBatchDAOImpl();
        ShelfStockDAOImpl shelfStockDAO = new ShelfStockDAOImpl();

        // Create StockService
        StockService stockService = new StockService(stockBatchDAO, shelfStockDAO);

        // Test reshelve() method
        String testItemCode = "ITM001"; // Make sure this exists in DB
        int quantityToAdd = 100;
        String saleType = "ONLINE"; // depends on how your DAO interprets this

        System.out.println("Reshelving item " + testItemCode + " with quantity: " + quantityToAdd);
        stockService.reshelve(testItemCode, quantityToAdd, saleType);
        System.out.println("Reshelve operation complete.");

        // Test getLowStockItems() method
        System.out.println("\nFetching low stock items...");
        List<ShelfStock> lowStockItems = stockService.getLowStockItems();

        if (lowStockItems.isEmpty()) {
            System.out.println("No low stock items found.");
        } else {
            for (ShelfStock shelfStock : lowStockItems) {
                System.out.println("Item Code: " + shelfStock.getItemCode()
                        + " | Quantity: " + shelfStock.getQuantity());
            }
        }
    }
}