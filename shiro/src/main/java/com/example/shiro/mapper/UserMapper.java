package com.example.shiro.mapper;

import com.example.shiro.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> findUserInfo();

    int addUserInfo(User user);

    int delUserInfo(int id);

    User getUserByName(String name);
}