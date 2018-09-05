package com.example.shiro.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class LoginController {
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public String ajaxLogin(User user) {
        Map<String, Object> map = new HashMap<>();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try {
            subject.login(token);
            map.put("token", subject.getSession().getId());
            map.put("msg", "登录成功");
        } catch (IncorrectCredentialsException e) {
            map.put("msg", "密码错误");
        } catch (LockedAccountException e) {
            map.put("msg", "登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            map.put("msg", "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSONUtils.toJSONString(map);


    }


    public static void main(String[] args){
        //密码通过md5，加盐，
        String newPassword = new SimpleHash("MD5","123456",
                        ByteSource.Util.bytes("zhangsan"),2).toHex();

        System.out.println(newPassword);
        //42bd4e7685cb11d3ba02716c313cb04b
        //42bd4e7685cb11d3ba02716c313cb04b
        //654407ac2e454fe560337510aa6adb97
        //654407ac2e454fe560337510aa6adb97

    }

}