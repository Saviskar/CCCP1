package com.syos.dao.daoImpl;

import com.syos.dao.ItemDAO;
import com.syos.model.Item;
import com.syos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public List<Item> getAllItems() {
        String sql = "SELECT * FROM item";
        List<Item> items = new ArrayList<>();

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                        rs.getString("item_code"),
                        rs.getString("name"),
                        rs.getBigDecimal("unit_price"),
                        rs.getInt("reorder_level")
                );

                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item getItemByCode(String code) {
        String sql = "SELECT * FROM item WHERE item_code = ?";
        Item item = null;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new Item(
                        rs.getString("item_code"),
                        rs.getString("name"),
                        rs.getBigDecimal("unit_price"),
                        rs.getInt("reorder_level")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public boolean addItem(Item item) {
        String sql = "INSERT INTO item (item_code, name, unit_price, reorder_level) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getItemCode());
            ps.setString(2, item.getName());
            ps.setBigDecimal(3, item.getUnitPrice());
            ps.setInt(4, item.getReorderLevel());

            int rowsInserted =  ps.executeUpdate();
            return rowsInserted > 0; // true if at least one row was inserted

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        String sql = "UPDATE item SET name = ?, unit_price = ?, reorder_level = ? WHERE item_code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setBigDecimal(2, item.getUnitPrice());
            ps.setInt(3, item.getReorderLevel());
            ps.setString(4, item.getItemCode());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0; // true if at least one row was updated

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteItem(String code) {
        String sql = "DELETE FROM item WHERE item_code = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);

            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0; // true if at least one row was deleted

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
