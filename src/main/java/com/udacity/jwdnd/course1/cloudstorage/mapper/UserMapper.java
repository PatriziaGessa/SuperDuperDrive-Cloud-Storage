package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

/**
 * private Integer userId;
 * private String username;
 * private String salt;
 * private String pass;
 * private String firstname;
 * private String lastname;
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) " +
            "VALUES(#{username}, #{salt}, #{pass}, #{firstname}, #{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Delete("DELETE FROM users WHERE userid = #{id}")
    void deleteUser(int id);


}
