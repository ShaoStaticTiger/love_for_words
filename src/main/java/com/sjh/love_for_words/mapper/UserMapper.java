package com.sjh.love_for_words.mapper;

import com.sjh.love_for_words.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public User findByUser(@Param("username") String username);

    public int addUser(@Param("username") String username,@Param("password") String password);

    void createTable(@Param("username") String username);
}
