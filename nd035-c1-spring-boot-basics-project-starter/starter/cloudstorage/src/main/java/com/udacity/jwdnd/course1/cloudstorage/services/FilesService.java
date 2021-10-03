package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;

@Service
public class FilesService {

	private FilesMapper filesMapper;

	public FilesService(FilesMapper filesMapper) {
		this.filesMapper = filesMapper;
	}

	public List<Files> getAllFilesByUserId(Integer userid) {
		return filesMapper.getAllFilesByUserId(userid);
	}
	
	public int createFile(MultipartFile uploadFile, Integer userid) throws IOException {
		System.out.println("********** FilesService : createFile ********** ");
		Files files = new Files();
		files.setFilename(uploadFile.getOriginalFilename());
		files.setContenttype(uploadFile.getContentType());
		files.setFiledata(uploadFile.getBytes());
		files.setFilesize(""+uploadFile.getSize());
		files.setUserid(userid);
		
		System.out.println("********** filesMapper.insertFile ********** ");

		return filesMapper.insertFile(files);
	}

	public boolean isFilenameAvailable(MultipartFile uploadFile, Integer userid) throws IOException {
		System.out.println("********** FilesService : isFilenameAvailable ********** ");
		Files files = new Files();
		files.setFilename(uploadFile.getOriginalFilename());
		files.setUserid(userid);
		
        return ((this.filesMapper.getFile(files) == null) ? false : true);

	}
	
	   public int deleteFile(Integer fileId) {
	       System.out.println(fileId);
	        return filesMapper.delete(fileId);
	   }

}