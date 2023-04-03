package com.prakash.awsdemo.service;

import com.prakash.awsdemo.dto.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User getUserById(long id);

    long saveUser(User user);
}
