package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Service
public class CredentialService {
	
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }
    
    
    
    public int deleteCredential(int credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }



	public int createCredential(Credential credential) {
        return credentialMapper.insertCredential(credential);

	}
	

}
