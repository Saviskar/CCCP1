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

        return List.of();
    }

    @Override
    public Item getItemByCode(String code) {

        return null;
    }

    @Override
    public boolean addItem(Item item) {

        return false;
    }

    @Override
    public boolean updateItem(Item item) {

        return false;
    }

    @Override
    public boolean deleteItem(String code) {
        return false;
    }
}
