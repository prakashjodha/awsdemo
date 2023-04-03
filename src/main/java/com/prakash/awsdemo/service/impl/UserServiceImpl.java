package com.prakash.awsdemo.service.impl;

import com.prakash.awsdemo.dao.UserRepository;
import com.prakash.awsdemo.dto.User;
import com.prakash.awsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;
    @Override
    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        userRepo.findAll().forEach(result::add);
        return result;

    }

    @Override
    public User getUserById(long id) {
        Optional<User> user=userRepo.findById(id);
        if(user.isEmpty())throw new IllegalArgumentException("No User found for "+id);
        return user.get();

    }

    @Override
    public long saveUser(User user) {
        return userRepo.save(user).getId();
    }
}
