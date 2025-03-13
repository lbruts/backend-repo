package com.example.baseservice.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.baseservice.model.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    // 查询用户信息
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    // 更新用户 Token
    @Update("UPDATE user SET token = #{token} WHERE username = #{username}")
    void updateToken(@Param("username") String username, @Param("token") String token);
}