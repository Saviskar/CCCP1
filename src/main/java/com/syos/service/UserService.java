package com.syos.service;

import org.mindrot.jbcrypt.BCrypt;
import com.syos.dao.UserAccountDAO;
import com.syos.model.UserAccount;

public class UserService {
    private final UserAccountDAO userAccountDAO;

    public UserService(UserAccountDAO userAccountDAO) {
        this.userAccountDAO = userAccountDAO;
    }

    public boolean registerUser(UserAccount user) {
        // Hash password before storing
        user.setPasswordHash(hashPassword(user.getPasswordHash()));
        return userAccountDAO.registerUser(user);
    }

    private String hashPassword(String password) {
        // Use BCrypt or other secure hash
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
