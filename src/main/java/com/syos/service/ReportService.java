package com.syos.service;

import com.syos.dao.BillDAO;
import com.syos.dao.BillLineDAO;
import com.syos.dao.ShelfStockDAO;
import com.syos.dao.StockBatchDAO;
import com.syos.model.Bill;
import com.syos.model.StockBatch;

import java.time.LocalDate;
import java.util.List;

public class ReportService {
    private final BillDAO billDAO;
    private final BillLineDAO billLineDAO;
    private final ShelfStockDAO shelfStockDAO;
    private final StockBatchDAO stockBatchDAO;

    public ReportService(BillDAO billDAO, BillLineDAO billLineDAO, ShelfStockDAO shelfStockDAO, StockBatchDAO stockBatchDAO) {
        this.billDAO = billDAO;
        this.billLineDAO = billLineDAO;
        this.shelfStockDAO = shelfStockDAO;
        this.stockBatchDAO = stockBatchDAO;
    }

    public List<Bill> getDailySales(LocalDate date) {
        return billDAO.getBillsByDate(date);
    }

    public List<StockBatch> getStockReport() {
        return stockBatchDAO.getAllBatches();
    }
}
