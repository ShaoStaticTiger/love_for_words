package com.sjh.love_for_words.mapper;

import com.sjh.love_for_words.bean.Words;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.ArrayList;

public interface WordMapper {
    public Words findByWord(@Param("word") String word,@Param("username") String username);

    public int updateWord(@Param("word") String word, @Param("chinese") String chinese,@Param("username") String username);



    void add(String word, String chinese, int total_times, Date date, int accuracy, int wrong_times,@Param("username") String username);

    ArrayList<Words> findAllWords(@Param("username") String username);

    void updateTotalWord(@Param("word") String word, @Param("chinese") String chinese,@Param("totalTimes") int totalTimes, @Param("lastTime") Date lastTime,@Param("accuracy") int accuracy,@Param("wrongTimes") int wrongTimes,@Param("username") String username);
}
