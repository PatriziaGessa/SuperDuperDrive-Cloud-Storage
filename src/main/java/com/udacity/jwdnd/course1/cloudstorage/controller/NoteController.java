package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NoteController {
    private NoteService noteService;
    private UserMapper userMapper;
    private FileService fileService;
    private CredentialService credentialService;

    public NoteController(NoteService noteService, UserMapper userMapper, FileService fileService, CredentialService credentialService) {
        this.noteService = noteService;
        this.userMapper = userMapper;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @PostMapping("/notes")
    public String postNote(@ModelAttribute("credential") CredentialForm credentialForm, Model model, @ModelAttribute("note") NoteForm note, Authentication authentication) {
        // System.out.println("postNote" + note);
        String username = authentication.getName();
        int userId = userMapper.getUser(username).getUserId();
        Note noteObj = new Note();

        noteObj.setUserId(userId);
        noteObj.setNoteTitle(note.getTitle());
        noteObj.setNoteDescription(note.getDescription());
        if (!note.getId().isBlank()) {
            noteObj.setNoteId(Integer.parseInt(note.getId()));
            noteService.updateNote(noteObj);
        } else {
            noteService.createNote(noteObj);
        }
        model.addAttribute("success", true);
        model.addAttribute("tab", "nav-notes-tab");
        model.addAttribute("notes", noteService.getAllUserNote(userId));
        model.addAttribute("files", fileService.getUserFile(userId));
        model.addAttribute("credentials", credentialService.getUserCredential(userId));
        return Constants.HOME;
    }


    @RequestMapping(value = "/delete/{id}")
    private String deleteNote(@PathVariable(name = "id") String id, RedirectAttributes redirectAttributes) {
        //System.out.println("deleteNote: " + id);
        noteService.deleteNote(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("tab", "nav-notes-tab");
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/home";
    }

}
