package com.syos.dao.daoImpl;

import com.syos.dao.OnlineOrderDAO;
import com.syos.model.OnlineOrder;
import com.syos.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OnlineOrderDAOImpl implements OnlineOrderDAO {

    @Override
    public boolean createOrder(OnlineOrder order) {
        String sql = "INSERT INTO OnlineOrder (user_id, bill_id, order_date) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, order.getUserId());
            ps.setInt(2, order.getBillId());
            ps.setDate(3, java.sql.Date.valueOf(order.getOrderDate()));

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0; // true if insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<OnlineOrder> getOrdersByUser(int userId) {
        List<OnlineOrder> orders = new ArrayList<>();
        String sql = "SELECT * FROM OnlineOrder WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OnlineOrder order = new OnlineOrder();
                    order.setOrderId(rs.getInt("order_id"));
                    order.setUserId(rs.getInt("user_id"));
                    order.setBillId(rs.getInt("bill_id"));
                    order.setOrderDate(rs.getDate("order_date").toLocalDate());
                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
