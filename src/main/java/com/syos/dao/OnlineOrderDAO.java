package com.syos.dao;

import com.syos.model.OnlineOrder;

import java.util.List;

public interface OnlineOrderDAO {
    boolean createOrder(OnlineOrder order);
    List<OnlineOrder> getOrdersByUser(int userId);
}
