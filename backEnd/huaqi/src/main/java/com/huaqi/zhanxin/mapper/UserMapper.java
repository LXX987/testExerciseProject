package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from User")
    List<UserBean> userList();

    @Select("select * from User where user_email=#{userEmail}")
    UserBean loginUser(@Param("userEmail") String userEmail);

    @Select("select * from User where user_id=#{userID}")
    UserBean selectName(@Param("userID") int userID);
}
