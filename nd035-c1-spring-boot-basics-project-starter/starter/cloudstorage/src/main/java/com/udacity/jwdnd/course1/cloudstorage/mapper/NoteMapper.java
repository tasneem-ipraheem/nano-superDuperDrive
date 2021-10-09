package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;


@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    public Notes getNote(int noteid);

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    public List<Notes> getNotsForUserId(int userid);
//
//    CREATE TABLE IF NOT EXISTS NOTES (
//    	    noteid INT PRIMARY KEY auto_increment,
//    	    notetitle VARCHAR(20),
//    	    notedescription VARCHAR (1000),
//    	    userid INT,
//    	    foreign key (userid) references USERS(userid)
//    	);
//    
//	private int noteId;
//	private String noteTitle;
//	private String noteDescription;
//	private int userId;
//	
    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    public int insertNote(Notes note) throws Exception;

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    public int deleteNote(int noteid);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    public int updateNote(Notes note) throws Exception;
    
    @Select("SELECT * FROM NOTES WHERE notetitle = #{noteTitle} AND notedescription = #{noteDescription}")
    Notes getOldNote(Notes note);
}
