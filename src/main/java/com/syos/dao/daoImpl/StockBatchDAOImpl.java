package com.syos.dao.daoImpl;

import com.syos.dao.StockBatchDAO;
import com.syos.model.StockBatch;
import com.syos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBatchDAOImpl implements StockBatchDAO {

    @Override
    public boolean addBatch(StockBatch batch) {
        String sql = "INSERT INTO StockBatch (item_code, purchase_date, quantity, expiry_date) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, batch.getItemCode());
            ps.setDate(2, java.sql.Date.valueOf(batch.getPurchaseDate()));
            ps.setInt(3, batch.getQuantity());

            // expiry_date can be null, so check before setting
            if (batch.getExpiryDate() != null) {
                ps.setDate(4, java.sql.Date.valueOf(batch.getExpiryDate()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<StockBatch> getBatchesByItem(String itemCode) {
        String sql = "SELECT * FROM StockBatch WHERE item_code = ?";
        List<StockBatch> batches = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, itemCode);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                StockBatch batch = new StockBatch(
                        rs.getInt("batch_id"),
                        rs.getString("item_code"),
                        rs.getDate("purchase_date").toLocalDate(),
                        rs.getInt("quantity"),
                        rs.getDate("expiry_date") != null ? rs.getDate("expiry_date").toLocalDate() : null
                );

                batches.add(batch);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return batches;
    }

    @Override
    public boolean reduceBatchQuantity(int batchId, int quantity) {
        String sql = "UPDATE StockBatch SET quantity = quantity - ? WHERE batch_id = ? AND quantity >= ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, batchId);
            ps.setInt(3, quantity);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // true if update happened, false if quantity insufficient or batch not found

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<StockBatch> getAllBatches() {
        String sql = "SELECT * FROM stockbatch";
        List<StockBatch> batches = new ArrayList<>();

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                batches.add(new StockBatch(
                        rs.getInt("batch_id"),
                        rs.getString("item_code"),
                        rs.getDate("purchase_date").toLocalDate(),
                        rs.getInt("quantity"),
                        rs.getDate("expiry_date") != null ? rs.getDate("expiry_date").toLocalDate() : null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return batches;
    }

}
