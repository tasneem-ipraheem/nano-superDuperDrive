package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;

@Mapper
public interface FilesMapper {

	@Insert("INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{filename}, #{contenttype}, #{filesize}, #{filedata}, #{userid})")
	public int insertFile(Files files);
	
	@Select("SELECT * FROM FILES WHERE userId = #{userid}")
    public List<Files> getAllFilesByUserId(int userid);
	
    @Select("SELECT * FROM FILES WHERE filename = #{filename} AND userid = #{userid}")
    Files getFile(Files files);
	
//    private Integer fileId;
//    private Integer userid;
//    private String filename;
//    private String contenttype;
//    private String filesize;
//    private byte[] filedata;
}
