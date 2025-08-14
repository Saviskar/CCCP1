package com.syos.dao.daoImpl;

import com.syos.dao.ShelfStockDAO;
import com.syos.model.ShelfStock;
import com.syos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelfStockDAOImpl implements ShelfStockDAO {

    @Override
    public boolean updateShelfQuantity(String itemCode, int quantity, String saleType) {
        String sql = "UPDATE Shelfstock SET quantity = quantity + ? WHERE item_code = ? AND sale_type = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setString(2, itemCode);
            ps.setString(3, saleType);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getShelfQuantity(String itemCode, String saleType) {
        String sql = "SELECT quantity FROM ShelfStock WHERE item_code = ? AND sale_type = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, itemCode);
            ps.setString(2, saleType);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ShelfStock> getLowStockItems() {
        // Assuming "low stock" means quantity <= 5 — adjust as needed
        String sql = "SELECT * FROM shelfstock WHERE quantity <= 5";
        List<ShelfStock> lowStockList = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ShelfStock stock = new ShelfStock(
                        rs.getInt("shelf_id"),
                        rs.getString("item_code"),
                        rs.getInt("quantity"),
                        ShelfStock.SaleType.valueOf(rs.getString("sale_type"))
                );
                lowStockList.add(stock);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lowStockList;
    }
}

















