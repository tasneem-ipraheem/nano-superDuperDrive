package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FilesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/files")
public class FilesController {

	FilesService filesService;
	UserService userService;

	public FilesController(FilesService filesService, UserService userService) {
		this.filesService = filesService;
		this.userService = userService;
	}

	@PostMapping
	public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication,
			RedirectAttributes redirectAttributes) {

		if (multipartFile.isEmpty()) {
			redirectAttributes.addFlashAttribute("success", false);
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage", "File not selected to upload");
			return "redirect:/home";
		}

		User user = this.userService.getUser(authentication.getName());
		Integer userId = user.getUserId();

		try {
			if (filesService.isFilenameAvailable(multipartFile, userId)) {

				redirectAttributes.addFlashAttribute("success", false);
				redirectAttributes.addFlashAttribute("error", true);
				redirectAttributes.addFlashAttribute("errorMessage", "file name already exists");
				return "redirect:/home";
			}

			filesService.createFile(multipartFile, userId);
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("successMessage", "New File added successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage", "System error!" + e.getMessage());
		}
		return "redirect:/home";
	}
	
    @GetMapping("/deleteFile/{id}")
    public String deleteFile(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes ) {

        try {
            filesService.deleteFile(id);
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("successMessage", "file Deleted");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("errorMessage", "System error!" + e.getMessage());
        }
        return "redirect:/home";
    }
    
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId") Integer fileId) {
        Files files = filesService.getFileById(fileId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(httpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + files.getFilename());
        httpHeaders.add("Cache-control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Pragma", "no-cache");
        httpHeaders.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(files.getFiledata());
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);

    }

}
