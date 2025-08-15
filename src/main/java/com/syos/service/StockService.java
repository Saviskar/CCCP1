package com.syos.service;

import com.syos.dao.ShelfStockDAO;
import com.syos.dao.StockBatchDAO;
import com.syos.model.ShelfStock;

import java.util.List;

public class StockService {
    private final StockBatchDAO stockBatchDAO;
    private final ShelfStockDAO shelfStockDAO;

    public StockService(StockBatchDAO stockBatchDAO, ShelfStockDAO shelfStockDAO) {
        this.stockBatchDAO = stockBatchDAO;
        this.shelfStockDAO = shelfStockDAO;
    }

    public void reshelve(String itemCode, int qty, String saleType) {
        shelfStockDAO.updateShelfQuantity(itemCode, qty, saleType);
    }

    public List<ShelfStock> getLowStockItems() {
        return shelfStockDAO.getLowStockItems();
    }
}
