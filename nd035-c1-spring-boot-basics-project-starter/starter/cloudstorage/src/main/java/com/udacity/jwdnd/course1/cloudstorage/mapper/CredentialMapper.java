package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> getCredentialsByUserId(Integer userId);

    @Insert("INSERT INTO CREDENTIALS (url, username,key,password, userId) VALUES(#{url},#{username},#{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insertCredential(Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username =#{username}, key =#{key}, password=#{password} WHERE credentialId =#{credentialId}")
    int updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialId =#{credentialId}")
    int deleteCredential(int credentialId);

}

