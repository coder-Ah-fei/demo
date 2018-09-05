package com.example.shiro.service.impl;

import com.example.shiro.entity.User;
import com.example.shiro.mapper.UserMapper;
import com.example.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUserInfo() {
        return userMapper.findUserInfo();
    }


    @Override
    public void insert(User user) {
        userMapper.addUserInfo(user);

    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}