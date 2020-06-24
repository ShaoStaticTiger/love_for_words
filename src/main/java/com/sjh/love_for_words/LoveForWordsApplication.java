package com.sjh.love_for_words;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(value = "com.sjh.love_for_words.mapper")
@SpringBootApplication
public class LoveForWordsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveForWordsApplication.class, args);
    }

}
