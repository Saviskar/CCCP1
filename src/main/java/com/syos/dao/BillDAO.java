package com.syos.dao;

import com.syos.model.Bill;

import java.time.LocalDate;
import java.util.List;

public interface BillDAO {
    int createBill(Bill bill);
    Bill getBillBtId(int billId);
    List<Bill> getBillsByDate(LocalDate date);
}
