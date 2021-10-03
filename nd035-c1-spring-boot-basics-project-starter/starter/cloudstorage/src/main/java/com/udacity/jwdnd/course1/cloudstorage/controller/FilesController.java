package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

}
