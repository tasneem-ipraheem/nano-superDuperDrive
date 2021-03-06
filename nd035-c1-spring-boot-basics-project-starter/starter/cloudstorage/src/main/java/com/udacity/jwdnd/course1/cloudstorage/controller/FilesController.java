package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.udacity.jwdnd.course1.cloudstorage.util.MessegesUtil;

@Controller
@RequestMapping("/files")
public class FilesController {// implements HandlerExceptionResolver{

	FilesService filesService;
	UserService userService;
	User user;
	Integer userId;
	
	public FilesController(FilesService filesService, UserService userService) {
		this.filesService = filesService;
		this.userService = userService;
	}

	@PostMapping
	public String uploadFile(@RequestParam MultipartFile multipartFile, Authentication authentication,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		

		if (multipartFile.isEmpty()) {
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage", MessegesUtil.FileMessages.FAIL_NO_SELECTED_FILE);
			return "redirect:/home";
		}

		user = this.userService.getUser(authentication.getName());
		userId = user.getUserId();

		try {
			if (filesService.isFilenameAvailable(multipartFile, userId)) {

				redirectAttributes.addFlashAttribute("error", true);
				redirectAttributes.addFlashAttribute("errorMessage",  MessegesUtil.FileMessages.FAIL_UPLOAD_EXIST_FILE);
				return "redirect:/home";
			}

			filesService.createFile(multipartFile, userId);
			redirectAttributes.addFlashAttribute("success", true);
			redirectAttributes.addFlashAttribute("successMessage", MessegesUtil.FileMessages.SUCCESS_UPLOAD);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			redirectAttributes.addFlashAttribute("error", true);
			redirectAttributes.addFlashAttribute("errorMessage",  e.getMessage());
		}
		return "redirect:/home";
	}
	
    @GetMapping("/deleteFile/{id}")
    public String deleteFile(@PathVariable Integer id,RedirectAttributes redirectAttributes ) {

        try {
            filesService.deleteFile(id);
            redirectAttributes.addFlashAttribute("success", true);
            redirectAttributes.addFlashAttribute("successMessage", MessegesUtil.FileMessages.SUCCESS_DELETED);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("errorMessage",  e.getMessage());
        }
        return "redirect:/home";
    }
    
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable Integer fileId) {
        Files files = filesService.getFileById(fileId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + files.getFilename());
        httpHeaders.add("Cache-control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Pragma", "no-cache");
        httpHeaders.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(files.getFiledata());
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);

    }

//	@Override
//	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
//			Exception ex) {
//		// TODO Auto-generated method stub
////		return null;
//		
//		 ModelAndView modelAndView = new ModelAndView("file");
//		    if (ex instanceof MaxUploadSizeExceededException) {
//		        modelAndView.getModel().put("message", "File size exceeds limit!");
//		        
////				redirectAttributes.addFlashAttribute("errorMessage", "Select file to upload !");
////				return "redirect:/home";
//		    }
//		    return modelAndView;
//	}

}
