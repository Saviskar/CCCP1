package com.syos.dao.daoImpl;

import com.syos.dao.BillLineDAO;
import com.syos.model.BillLine;
import com.syos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillLineDAOImpl implements BillLineDAO {

    @Override
    public boolean addBillLine(com.syos.model.BillLine line) {
        String sql = "INSERT INTO BillLine (bill_id, item_code, quantity, total_price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, line.getBillId());
            ps.setString(2, line.getItemCode());
            ps.setInt(3, line.getQuantity());
            ps.setBigDecimal(4, line.getTotalPrice());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<BillLine> getBillLinesById(int billId) {
        List<BillLine> billLines = new ArrayList<>();
        String sql = "SELECT * FROM BillLine WHERE bill_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, billId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BillLine line = new BillLine();
                    line.setBillLineId(rs.getInt("bill_line_id"));
                    line.setBillId(rs.getInt("bill_id"));
                    line.setItemCode(rs.getString("item_code"));
                    line.setQuantity(rs.getInt("quantity"));
                    line.setTotalPrice(rs.getBigDecimal("total_price"));
                    billLines.add(line);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billLines;
    }
}
