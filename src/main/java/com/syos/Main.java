package com.syos;

import com.syos.dao.OnlineOrderDAO;
import com.syos.dao.daoImpl.OnlineOrderDAOImpl;
import com.syos.model.OnlineOrder;
import com.syos.service.OnlineOrderService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1. Create the DAO implementation
        OnlineOrderDAO onlineOrderDAO = new OnlineOrderDAOImpl();

        // 2. Create the service
        OnlineOrderService service = new OnlineOrderService(onlineOrderDAO);

        // 3. Create a new order object
        OnlineOrder order = new OnlineOrder();
        order.setUserId(4); // replace with an existing user_id from your DB
        order.setBillId(2); // replace with an existing bill_id from your DB
        order.setOrderDate(LocalDate.now());

        // 4. Call the service to create the order
        boolean success = service.createOrder(order);

        // 5. Print the result
        if (success) {
            System.out.println("Order created successfully!");
        } else {
            System.out.println("Failed to create order.");
        }
    }
}