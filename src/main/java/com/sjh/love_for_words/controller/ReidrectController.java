package com.sjh.love_for_words.controller;

import com.sjh.love_for_words.bean.Words;
import com.sjh.love_for_words.mapper.WordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ReidrectController {

    @Autowired
    WordMapper wordMapper;

    @GetMapping("/")
    public String index(){
        return "hello";
    }

    @GetMapping("toadd")
    public String toadd(HttpSession session){
        String username = (String) session.getAttribute("username");
        if (username ==null || "".equals(username)){
            session.setAttribute("unlogin",true);
            return "hello";
        }
        return "addwords";
    }

    @GetMapping("toupdate")
    public String toupdate(HttpSession session){
        String username = (String) session.getAttribute("username");
        if (username ==null || "".equals(username)){
            session.setAttribute("unlogin",true);
            return "hello";
        }
        return "updatewords";
    }
    @GetMapping("toreview")
    public String toreview(HttpSession session){
        String username = (String) session.getAttribute("username");
        if (username ==null || "".equals(username)){
            session.setAttribute("unlogin",true);
            return "hello";
        }
        ArrayList<Words> allWords;
        allWords = wordMapper.findAllWords(username);
        session.setAttribute("allWords",allWords);
        return "review";
    }

}
