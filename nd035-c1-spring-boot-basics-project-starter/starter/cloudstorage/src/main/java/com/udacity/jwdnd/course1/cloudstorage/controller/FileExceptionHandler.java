package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.util.MessegesUtil;

@ControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
        
		redirectAttributes.addFlashAttribute("error", true);
		redirectAttributes.addFlashAttribute("errorMessage",MessegesUtil.FileMessages.FAIL_MULTIPARTEXCEPTION);
		return "redirect:/home";

    }
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleError2(MultipartException e, RedirectAttributes redirectAttributes) {
        
		redirectAttributes.addFlashAttribute("error", true);
		redirectAttributes.addFlashAttribute("errorMessage", MessegesUtil.FileMessages.FAIL_EXCEED_SIZE);
		return "redirect:/home";

    }

}