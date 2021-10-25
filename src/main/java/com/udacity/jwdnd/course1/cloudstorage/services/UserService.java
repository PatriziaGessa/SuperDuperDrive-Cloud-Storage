package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * private Integer userId;
 * private String username;
 * private String salt;
 * private String pass;
 * private String firstname;
 * private String lastname;
 */
@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    /**
     * Checks if the username exists on the DB
     *
     * @param username
     * @return boolean result of the operation
     */
    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    /**
     * Tries to create a new User on DB
     *
     * @param user object containing the signup form data
     * @return int result of the operation
     */
    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPass(), encodedSalt);
        return userMapper.insert(new User(
                null,
                user.getUsername(),
                encodedSalt,
                hashedPassword,
                user.getFirstname(),
                user.getLastname()));
    }

    /**
     * Gets a User based on the given username
     *
     * @param username
     * @return a user
     */
    public User getUser(String username) {
        return userMapper.getUser(username);
    }
}
