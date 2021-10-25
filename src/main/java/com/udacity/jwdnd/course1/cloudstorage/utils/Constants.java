package com.udacity.jwdnd.course1.cloudstorage.utils;

import java.lang.reflect.Field;

public final class Constants {

    // Success Credential
    public static final String SUCCESS_CREDENTIAL_CREATE = "Credential added successfully";
    public static final String SUCCESS_CREDENTIAL_UPDATE = "Credential updated successfully";
    public static final String SUCCESS_CREDENTIAL_DELETE = "Credential deleted successfully";

    // Success File
    public static final String SUCCESS_FILE_UPLOAD = "File uploaded successfully";
    public static final String SUCCESS_FILE_DELETE = "File deleted successfully";

    // Success Note
    public static final String SUCCESS_NOTE_CREATE = "Note added successfully";
    public static final String SUCCESS_NOTE_UPDATE = "Note successfully updated";
    public static final String SUCCESS_NOTE_DELETE = "Note deleted successfully";

    // Success Signup
    public static final String SUCCESS_SIGNUP = "You successfully signed up!";

    // Error General
    public static final String ERROR_GENERAL = "There was an error. Please try again.";

    // Error Credential
    public static final String ERROR_CREDENTIAL_URL_REQUIRED = "Url field is required";
    public static final String ERROR_CREDENTIAL_USERNAME_REQUIRED = "Username field is required";
    public static final String ERROR_CREDENTIAL_PASSWORD_REQUIRED = "Password field is required";
    public static final String ERROR_CREDENTIAL_DUPLICATE = "This username/url already exists on database.";

    // Error File
    public static final String ERROR_FILE_REQUIRED = "You must select a file";
    public static final String ERROR_FILE_UPLOAD = "Could not upload the file";
    public static final String ERROR_FILE_EMPTY = "File is empty";
    public static final String ERROR_FILE_ALREADY_EXISTS = "File already exists!";

    // Error Note
    public static final String ERROR_NOTE_TITLE_REQUIRED = "Note title field is required";
    public static final String ERROR_NOTE_DESCRIPTION_REQUIRED = "Note description field is required";
    public static final String ERROR_NOTE_DESCRIPTION_LENGTH = "Note description field can't exceed the 1000 characters";
    public static final String ERROR_NOTE_DUPLICATE = "This note already exists on database.";

    // Error Signup
    public static final String ERROR_SIGNUP_GENERAL = "There was an error signing you up. Please try again.";
    public static final String ERROR_SIGNUP_USERNAME = "The username already exists";




    /**
     * The constructor is set as private so that the class can't be instantiated
     */
    private Constants() {
    }

}
