package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.exception.FileExists;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.utils.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FileController {
    private FileService fileService;
    private UserMapper userMapper;
    private NoteService noteService;
    private CredentialService credentialService;

    public FileController(FileService fileService, UserMapper userMapper, NoteService noteService, CredentialService credentialService) {
        this.fileService = fileService;
        this.userMapper = userMapper;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @PostMapping("/files")
    public String uploadFile(Model model, @RequestParam("fileUpload") MultipartFile file, Authentication authentication,
                             @ModelAttribute("note") NoteForm note, @ModelAttribute("credential") CredentialForm credentialForm) {
        String username = authentication.getName();
        int userId = userMapper.getUser(username).getUserId();
        if (file.isEmpty()) {
            model.addAttribute("errorMessage", Constants.ERROR_FILE_EMPTY);
        } else {
            File fileObj = new File();
            fileObj.setContentType(file.getContentType());
            fileObj.setFilename(file.getOriginalFilename());
            fileObj.setUserId(userId);
            fileObj.setFileSize(file.getSize() + "");

            try {
                fileObj.setFileData(file.getBytes());
                fileService.createFile(fileObj);
                model.addAttribute("success", Constants.SUCCESS_FILE_UPLOAD);
            } catch (FileExists e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", Constants.ERROR_FILE_ALREADY_EXISTS);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", Constants.ERROR_GENERAL);
            }
        }
        model.addAttribute("tab", "nav-files-tab");
        model.addAttribute("files", fileService.getUserFile(userId));
        model.addAttribute("notes", noteService.getAllUserNote(userId));
        model.addAttribute("credentials", credentialService.getUserCredential(userId));

        return Constants.HOME;
    }

    @RequestMapping(value = {"/files/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<byte[]> viewFile(@PathVariable(name = "id") String id,
                                           HttpServletResponse response, HttpServletRequest request) {
        File file = fileService.getFileById(Integer.parseInt(id));
        byte[] fileContents = file.getFileData();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(file.getContentType()));

        String fileName = file.getFilename();
        httpHeaders.setContentDispositionFormData(fileName, fileName);
        httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<byte[]>(fileContents, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "files/delete/{id}")
    private String deleteFile(@PathVariable(name = "id") String id, RedirectAttributes redirectAttributes) {
        fileService.deleteFile(Integer.parseInt(id));
        redirectAttributes.addFlashAttribute("tab", "nav-files-tab");
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/home";
    }
}
