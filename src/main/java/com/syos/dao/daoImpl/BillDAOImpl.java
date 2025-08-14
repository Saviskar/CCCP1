package com.syos.dao.daoImpl;

import com.syos.dao.BillDAO;
import com.syos.model.Bill;
import com.syos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    @Override
    public boolean createBill(Bill bill) {
        String sql = "INSERT INTO Bill (bill_date, total_amount, discount, cash_tendered, change_returned, bill_type) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(bill.getBillDate()));
            ps.setBigDecimal(2, bill.getTotalAmount());
            ps.setBigDecimal(3, bill.getDiscount());
            ps.setBigDecimal(4, bill.getCashTendered());
            ps.setBigDecimal(5, bill.getCashReturned());
            ps.setString(6, bill.getBillType().name());

            // return true if at least 1 row was inserted
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Bill getBillById(int billId) {
        String sql = "SELECT * FROM bill WHERE bill_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, billId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setBillDate(rs.getDate("bill_date").toLocalDate());
                bill.setTotalAmount(rs.getBigDecimal("total_amount"));
                bill.setDiscount(rs.getBigDecimal("discount"));
                bill.setCashTendered(rs.getBigDecimal("cash_tendered"));
                bill.setCashReturned(rs.getBigDecimal("change_returned"));
                bill.setBillType(Bill.BillType.valueOf(rs.getString("bill_type")));
                return bill;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // return null if no bill found
    }

    @Override
    public List<Bill> getBillsByDate(LocalDate date) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM Bill WHERE bill_date = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillId(rs.getInt("bill_id"));
                bill.setBillDate(rs.getDate("bill_date").toLocalDate());
                bill.setTotalAmount(rs.getBigDecimal("total_amount"));
                bill.setDiscount(rs.getBigDecimal("discount"));
                bill.setCashTendered(rs.getBigDecimal("cash_tendered"));
                bill.setCashReturned(rs.getBigDecimal("change_returned"));
                bill.setBillType(Bill.BillType.valueOf(rs.getString("bill_type")));

                bills.add(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }
}
