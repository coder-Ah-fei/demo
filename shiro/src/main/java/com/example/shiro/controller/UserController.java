package com.example.shiro.controller;

import com.example.shiro.entity.User;
import com.example.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public List<User> getUserInfo() {
        List<User> users = userService.getUserInfo();
        System.out.println(users.toString());
        return users;
    }

    @RequestMapping("/addUserInfo")
    @ResponseBody
    public String addUserInfo() {
        User user = new User();
        user.setId(3L);
        user.setName("cwh");
        userService.insert(user);
        return "success:" + user.toString();
    }





}