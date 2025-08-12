package com.syos.dao;

import com.syos.model.UserAccount;

public interface UserAccountDAO {
    boolean registerUser(UserAccount user);
    UserAccount getUserByUsername(String username);
    boolean updateUser(UserAccount user);
}
