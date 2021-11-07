package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;
    private UserMapper userMapper;

    public HomeController(NoteService noteService, FileService fileService, CredentialService credentialService, UserMapper userMapper) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String home(@ModelAttribute("note") NoteForm note, @ModelAttribute("credential") CredentialForm credentialForm, Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userMapper.getUser(username);
        if (user != null) {
            int userId = user.getUserId();
            model.addAttribute("notes", noteService.getAllUserNote(userId));
            model.addAttribute("files", fileService.getUserFile(userId));
            model.addAttribute("credentials", credentialService.getUserCredential(userId));
            return Constants.HOME;
        }
        return Constants.SIGNUP;
    }
}
