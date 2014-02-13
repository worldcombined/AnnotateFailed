package com.springapp.sectest.repository;

import com.springapp.sectest.domain.User;

public interface UserDAO {
    public User getUser(String login);
}
