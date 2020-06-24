package com.sjh.love_for_words.service.impl;

import com.sjh.love_for_words.bean.Words;
import com.sjh.love_for_words.mapper.WordMapper;
import com.sjh.love_for_words.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class WordServiceImpl implements WordService {

    @Autowired
    public WordMapper wordMapper;


    @Override
    public void add(String word, String chinese, String username) {
        Date d = new Date();
        java.sql.Date date = new java.sql.Date(d.getTime());
        wordMapper.add(word,chinese,0,date,0,0,username);
    }


}
