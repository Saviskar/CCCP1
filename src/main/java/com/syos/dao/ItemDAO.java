package com.syos.dao;

import com.syos.model.Item;

import java.util.List;

public interface ItemDAO {
    List<Item> getAllItems();
    Item getItemByCode(String code);
    boolean addItem(Item item);
    boolean updateItem(Item item);
    boolean deleteItem(String code);
}
