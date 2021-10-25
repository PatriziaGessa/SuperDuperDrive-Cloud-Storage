package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Service
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    /**
     * Injects the credential mapper and the encryption service.
     * @param credentialMapper will be used for CRUD operations
     * @param encryptionService
     */
    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    /**
     * Adds a new Credential to the DB
     * @param credential credential object containing the credential's form data
     */
    public void createCredential(Credential credential) {
        String encodedSalt = generateKey();
        credential.setKey(encodedSalt);
        String codedPass = encryptionService.encryptValue(credential.getNotCypherPassword(), encodedSalt);
        credential.setPassword(codedPass);

        credentialMapper.insert(credential);
    }

    /**
     * Updates an existing credential
     * @param credential contains the credential's form data
     */
    public void updateCredential(Credential credential) {
        //System.out.println("updateCredential " + credential.getCredentialId());
        String encodedSalt = generateKey();
        credential.setKey(encodedSalt);
        String codedPass = encryptionService.encryptValue(credential.getNotCypherPassword(), encodedSalt);
        credential.setPassword(codedPass);

        credentialMapper.updateCredential(credential);
    }
    /**
     * Tries to delete a Credential based on the given credential ID
     * @param credentialId
     */
    public void deleteCredential(int credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

    public List<Credential> getUserCredential(int userId) {
        List<Credential> credentialList = credentialMapper.getCredential(userId);
        return credentialList.stream()
                .map(this::wrapCredential)
                .collect(toList());
    }


    private Credential getCredentialById(int credentialId) {
        return wrapCredential(credentialMapper.getCredentialById(credentialId));
    }

    private String generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String getNotCodedPassword(Credential credential) {
        return encryptionService.decryptValue(
                credential.getPassword(),
                credential.getKey());
    }


    private Credential wrapCredential(Credential credential) {
        Credential mapped = new Credential(
                credential.getCredentialId(),
                credential.getUrl(),
                credential.getUsername(),
                null,
                credential.getPassword(),
                credential.getUserId());
        mapped.setNotCypherPassword(getNotCodedPassword(credential));
        return mapped;
    }


}
