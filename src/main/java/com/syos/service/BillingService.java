package com.syos.service;

import com.syos.dao.*;
import com.syos.model.Bill;
import com.syos.model.BillLine;
import com.syos.model.Item;
import com.syos.model.StockBatch;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class BillingService {
    private final ItemDAO itemDAO;
    private final ShelfStockDAO shelfStockDAO;
    private final StockBatchDAO stockBatchDAO;
    private final BillDAO billDAO;
    private final BillLineDAO billLineDAO;

    public BillingService(ItemDAO itemDAO, ShelfStockDAO shelfStockDAO, StockBatchDAO stockBatchDAO,
                          BillDAO billDAO, BillLineDAO billLineDAO) {
        this.itemDAO = itemDAO;
        this.shelfStockDAO = shelfStockDAO;
        this.stockBatchDAO = stockBatchDAO;
        this.billDAO = billDAO;
        this.billLineDAO = billLineDAO;
    }

    public int processSale(Map<String, Integer> itemsWithQty, BigDecimal cashTendered, String billType) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        // Calculate total amount
        for (Map.Entry<String, Integer> entry : itemsWithQty.entrySet()) {
            Item item = itemDAO.getItemByCode(entry.getKey());
            int qty = entry.getValue();
            totalAmount = totalAmount.add(item.getUnitPrice().multiply(BigDecimal.valueOf(qty)));
        }

        // Calculate change
        BigDecimal changeReturned = cashTendered.subtract(totalAmount);

        // Create bill
        Bill bill = new Bill();
        bill.setBillDate(LocalDate.now());
        bill.setTotalAmount(totalAmount);
        bill.setDiscount(BigDecimal.ZERO);
        bill.setCashTendered(cashTendered);
        bill.setCashReturned(changeReturned);
        bill.setBillType(Bill.BillType.valueOf(billType));

        int billId = billDAO.createBill(bill);

        // Add bill lines & update stock
        for (Map.Entry<String, Integer> entry : itemsWithQty.entrySet()) {
            String itemCode = entry.getKey();
            int qty = entry.getValue();

            Item item = itemDAO.getItemByCode(itemCode);
            BigDecimal totalPrice = item.getUnitPrice().multiply(BigDecimal.valueOf(qty));

            BillLine line = new BillLine();
            line.setBillId(billId);
            line.setItemCode(itemCode);
            line.setQuantity(qty);
            line.setTotalPrice(totalPrice);
            billLineDAO.addBillLine(line);

            // Reduce shelf stock
            shelfStockDAO.updateShelfQuantity(itemCode, qty, "SALE");

            // Reduce batch quantities
            reduceBatches(itemCode, qty);
        }

        return billId;
    }

    private void reduceBatches(String itemCode, int quantity) {
        List<StockBatch> batches = stockBatchDAO.getBatchesByItem(itemCode);

        // Sort by expiry date first, then purchase date
        batches.sort(Comparator.comparing(StockBatch::getExpiryDate)
                .thenComparing(StockBatch::getPurchaseDate));

        for (StockBatch batch : batches) {
            if (quantity <= 0) break;

            int available = batch.getQuantity();
            int toReduce = Math.min(available, quantity);

            stockBatchDAO.reduceBatchQuantity(batch.getBatchID(), toReduce);
            quantity -= toReduce;
        }
    }
}
