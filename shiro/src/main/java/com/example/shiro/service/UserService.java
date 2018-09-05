package com.example.shiro.service;

import com.example.shiro.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUserInfo();

    void insert(User user);

    User getUserByName(String name);
}