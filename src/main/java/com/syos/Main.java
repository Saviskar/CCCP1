package com.syos;

import com.syos.dao.StockBatchDAO;
import com.syos.dao.daoImpl.StockBatchDAOImpl;
import com.syos.model.StockBatch;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StockBatchDAO batchDAO = new StockBatchDAOImpl();

        List<StockBatch> batches = batchDAO.getAllBatches();

        if (batches.isEmpty()) {
            System.out.println("No batches found in the database.");
        } else {
            for (StockBatch batch : batches) {
                System.out.println(
                        "Batch ID: " + batch.getBatchID() +
                                ", Item Code: " + batch.getItemCode() +
                                ", Purchase Date: " + batch.getPurchaseDate() +
                                ", Quantity: " + batch.getQuantity() +
                                ", Expiry Date: " + batch.getExpiryDate()
                );
            }
        }
    }
}