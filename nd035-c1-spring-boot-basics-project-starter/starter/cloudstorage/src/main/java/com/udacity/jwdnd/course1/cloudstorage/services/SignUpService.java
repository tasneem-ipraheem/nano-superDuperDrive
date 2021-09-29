package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

@Service
public class SignUpService {

	
    public boolean signUp(String username, String password, String firstName, String lastName ) {

    	if (username.equals("a")  &&  password.equals("a")) 
			return true;
    	
    	
		else return false;
    }
}
