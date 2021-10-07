package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Service
public class CredentialService {
	
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }


	public int createCredential(Credential credential) {
        return credentialMapper.insertCredential(credential);

	}



	public List<Credential> getCredentials(Integer userId) {
		return credentialMapper.getCredentialsByUserId(userId);
	}
    
    
    
    public int deleteCredential(int credentialId) {
        return credentialMapper.deleteCredential(credentialId);
    }



	public int updateCredential(Credential credential) {
		 return credentialMapper.updateCredential(credential);
	}
	

}
