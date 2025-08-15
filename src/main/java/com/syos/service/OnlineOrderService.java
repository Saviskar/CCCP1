package com.syos.service;

import com.syos.dao.OnlineOrderDAO;
import com.syos.model.OnlineOrder;

public class OnlineOrderService {
    private final OnlineOrderDAO onlineOrderDAO;

    public OnlineOrderService(OnlineOrderDAO onlineOrderDAO) {
        this.onlineOrderDAO = onlineOrderDAO;
    }

    public boolean createOrder(OnlineOrder order) {
        return onlineOrderDAO.createOrder(order);
    }
}

// HAVE TO TEST THIS SERVICE