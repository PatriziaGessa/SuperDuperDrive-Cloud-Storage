package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * private int credentialId;
 * private String url;
 * private String username;
 * private String notCypherPassword;
 * private String key;
 * private String password;
 * private int userId;
 */
@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM credentials WHERE userid = #{userId}")
    List<Credential> getCredential(int userId);

    @Insert("INSERT INTO credentials (url, username, key, password, userid) " +
            "VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Update("UPDATE credentials SET url = #{url}, username = #{username}, key = #{key}, password= #{password}, userid = #{userid} " +
            "WHERE credentialid = #{credentialId}")
    void updateCredential(Credential credential);

    @Delete("DELETE FROM credentials " +
            "WHERE credentialid = #{credentialId}")
    void deleteCredential(int credentialId);

    @Select("SELECT * FROM credentials " +
            "WHERE credentialid = #{credentialId}")
    Credential getCredentialById(int credentialId);
}
