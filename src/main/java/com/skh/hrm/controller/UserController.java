package com.skh.hrm.controller;

import com.skh.hrm.domain.User;
import com.skh.hrm.service.HrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by skh on 2018/8/14.
 */
@Controller
public class UserController {
    @Autowired
    private HrmService mHrmService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User getUsers() {
        User user = mHrmService.findUserById(1);
        return user;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public User getLogin() {
        User user = mHrmService.login("admin", "123456");
        return user;
    }

    @GetMapping(value = "/add")
    @ResponseBody
    public String addUser() {
        User user = new User();
        user.setId(2);
        user.setLoginName("sss");
        user.setPassword("123456");
        user.setStatus(1);
        user.setCreateDate(new Date());
        user.setUserName("测试人员");
        mHrmService.addUser(user);
        return "OK";
    }
}
