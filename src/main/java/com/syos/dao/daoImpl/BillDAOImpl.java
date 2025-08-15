package com.syos.dao.daoImpl;

import com.syos.dao.BillDAO;
import com.syos.model.Bill;
import com.syos.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    @Override
    public int createBill(Bill bill) {
        String sql = "INSERT INTO Bill (bill_date, total_amount, discount, cash_tendered, change_returned, bill_type) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setDate(1, java.sql.Date.valueOf(bill.getBillDate()));
            ps.setBigDecimal(2, bill.getTotalAmount());
            ps.setBigDecimal(3, bill.getDiscount());
            ps.setBigDecimal(4, bill.getCashTendered());
            ps.setBigDecimal(5, bill.getCashReturned());
            ps.setString(6, bill.getBillType().name());

            ps.executeUpdate();

            // Retrieve generated bill ID
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // this is the generated bill ID
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
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
