package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        
		redirectAttributes.addFlashAttribute("error", true);
		redirectAttributes.addFlashAttribute("errorMessage", "The file exceeds the upload limit");
		return "redirect:/home";

    }
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleError2(MultipartException e, RedirectAttributes redirectAttributes) {
        
		redirectAttributes.addFlashAttribute("error", true);
		redirectAttributes.addFlashAttribute("errorMessage", "The  file exceeds the upload limit 5M !!");
		return "redirect:/home";

    }

}