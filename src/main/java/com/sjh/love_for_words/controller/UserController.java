package com.sjh.love_for_words.controller;

import com.sjh.love_for_words.bean.User;
import com.sjh.love_for_words.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("login")
    @ResponseBody
    public  String login( String username, String password, HttpSession session){
        User byUsername = userMapper.findByUser(username);
        String msg;
        if (byUsername == null || !byUsername.getPassword().equals(password)){
            msg = "login_fail";
        }else {
            msg = "login_successful";
            session.setAttribute("username", username);
            session.setAttribute("unlogin",false);
        }
        return msg;
    }

    @ResponseBody
    @RequestMapping("register")
    public String register(String username,String password,HttpSession session){
        User byUser = userMapper.findByUser(username);
        if (byUser != null){
            return "register_fail";
        }
        int i = userMapper.addUser(username, password);
        session.setAttribute("username", username);
        userMapper.createTable(username);
        session.setAttribute("unlogin",false);
        return i == 0 ? "register_fail" : "register_successful";
    }

    @ResponseBody
    @RequestMapping("checkUser")
    public String checkUser(String username){
        User byUser = userMapper.findByUser(username);
        if (byUser==null){
            return "username_available";
        }else{
            return "username_not_available";
        }
    }

    @ResponseBody
    @RequestMapping("out")
    public String out(HttpSession session){
        session.removeAttribute("username");
        return "successful";
    }
}
