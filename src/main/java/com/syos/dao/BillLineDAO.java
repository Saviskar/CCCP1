package com.syos.dao;

import com.syos.model.BillLine;

import java.util.List;

public interface BillLineDAO {
    boolean addBillLine(BillLine line);
    List<BillLine> getBillLinesById(int billId);
}
