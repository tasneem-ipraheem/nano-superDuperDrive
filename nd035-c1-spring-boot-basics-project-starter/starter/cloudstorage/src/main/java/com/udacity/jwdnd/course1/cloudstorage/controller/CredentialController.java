package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.util.MessegesUtil;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
	
    private CredentialService credentialService;
    private UserService userService;
    private EncryptionService encryptionService;
    User user;
    Integer userId ;
    
    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }
    
    @PostMapping
    public String createCredential(@ModelAttribute Credential credential, Authentication authentication, RedirectAttributes redirectAttributes, Model model){

        user = userService.getUser(authentication.getName());
        userId = user.getUserId();
        credential.setUserId(userId);
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodedKey);

        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(encryptedPassword);
        
        
        int rows = credentialService.createCredential(credential);

        if (rows > 0) {
            redirectAttributes.addFlashAttribute("success",true);
            redirectAttributes.addFlashAttribute("successMessage",MessegesUtil.CredentialMessages.SUCCESS_ADD);
        } else {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("errorMessage", MessegesUtil.CredentialMessages.FAIL_ADD_DATABASE);
        }

        return "redirect:/home";
    }
    
    @PutMapping
    public String updateCredential(@ModelAttribute Credential credential, Authentication authentication, RedirectAttributes redirectAttributes){
        user = userService.getUser(authentication.getName());
        userId = user.getUserId();
        credential.setUserId(userId);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), credential.getKey());
        credential.setPassword(encryptedPassword);
        int rows = credentialService.updateCredential(credential);
        
        
        if (rows > 0){
            redirectAttributes.addFlashAttribute("success",true);
            redirectAttributes.addFlashAttribute("successMessage",  MessegesUtil.CredentialMessages.SUCCESS_EDIT);
        } else {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("errorMessage",MessegesUtil.CredentialMessages.FAIL_EDIT_DATABASE);
        }

        return "redirect:/home";
    }
    
    
    @DeleteMapping
    public String deleteCredential(@ModelAttribute Credential credential, Authentication authentication, RedirectAttributes redirectAttributes){
        user = userService.getUser(authentication.getName());
        userId = user.getUserId();
        credential.setUserId(userId);
        
        int rows = credentialService.deleteCredential(credential.getCredentialId());
        if (rows > 0) {
            redirectAttributes.addFlashAttribute("success",true);
            redirectAttributes.addFlashAttribute("successMessage",MessegesUtil.CredentialMessages.SUCCESS_DELETE);
        } else {
            redirectAttributes.addFlashAttribute("error", true);
            redirectAttributes.addFlashAttribute("errorMessage",MessegesUtil.CredentialMessages.FAIL_DELETE_DATABASE);
        }

        return "redirect:/home";

    }

}
