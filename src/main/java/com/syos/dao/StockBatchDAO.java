package com.syos.dao;

import com.syos.model.StockBatch;

import java.util.List;

public interface StockBatchDAO {
    boolean addBatch(StockBatch batch);
    List<StockBatch> getBatchesByItem(String itemCode);

    //What does this method do? ->  boolean reduceBatchQuantity(int batchId, int quantity);

    //It reduces the quantity of a specific stock batch by the given amount.
    //For example, if batch #5 currently has 100 units, and a customer buys 3 units from that batch, this method
    // updates the database to reflect that now batch #5 has 97 units.

    boolean reduceBatchQuantity(int batchId, int quantity);

    List<StockBatch> getAllBatches();
}

// I feel like there is a conflic between stockbatch dao and shelfstock dao sort it out
