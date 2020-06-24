package com.sjh.love_for_words.controller;

import com.sjh.love_for_words.bean.Words;
import com.sjh.love_for_words.mapper.WordMapper;
import com.sjh.love_for_words.service.WordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class WordsController {
    @Autowired
    private WordMapper wordMapper;

    @Autowired
    private WordService wordService;



    @ResponseBody
    @RequestMapping("/find")
    public String find(@RequestParam("word") String word, Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        Words checkword = wordMapper.findByWord(word,  username);
        String msg;
        if ( checkword == null || "".equals(checkword)){
            msg = "无结果";
        }else{
            msg = checkword.getChinese();
        }
        return msg;
    }



    @PostMapping("/add")
    public String add(@Param("word") String word, @Param("chinese") String chinese, Model model,HttpSession session){
        String username = (String) session.getAttribute("username");
        Words checkword = wordMapper.findByWord(word,username);
        if ( checkword == null){
            wordService.add(word, chinese,username);
            model.addAttribute("msg", "right");
        }else {
            model.addAttribute("msg", "wrong");
        }
        return "addwords";
    }

    @RequestMapping("check")
    public void check(String word, boolean result,HttpSession session) {
        String username = (String) session.getAttribute("username");
        Words byWord = wordMapper.findByWord(word, username);
        if (result){
            int totalTimes = byWord.getTotalTimes();
            int wrongTimes = byWord.getWrongTimes();
            totalTimes++;
            int accuracy = (totalTimes - wrongTimes) / totalTimes;
            byWord.setAccuracy(accuracy);
            byWord.setTotalTimes(totalTimes);
            byWord.setWrongTimes(wrongTimes);
        }else {
            int totalTimes = byWord.getTotalTimes();
            int wrongTimes = byWord.getWrongTimes();
            totalTimes++;
            wrongTimes++;
            int accuracy = (totalTimes - wrongTimes) / totalTimes;
            byWord.setAccuracy(accuracy);
            byWord.setTotalTimes(totalTimes);
            byWord.setWrongTimes(wrongTimes);
        }
        Date d = new Date();
        java.sql.Date date = new java.sql.Date(d.getTime());
        byWord.setLastTime(date);
        wordMapper.updateTotalWord(byWord.getWord(),byWord.getChinese(),byWord.getTotalTimes(),byWord.getLastTime(),byWord.getAccuracy(),byWord.getWrongTimes(),username);
    }


    @ResponseBody
    @RequestMapping("/review")
    public Words review(HttpSession session){
        String username = (String) session.getAttribute("username");
        ArrayList<Words> allWords = (ArrayList<Words>) session.getAttribute("allWords");
        if (allWords.isEmpty()){
            return null;
        }
        Words words = allWords.get(0);
        allWords.remove(0);
        return words;
    }
}
