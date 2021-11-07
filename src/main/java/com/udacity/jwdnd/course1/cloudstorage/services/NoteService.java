package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    /**
     * Tries to add a new note to the current logged user
     *
     * @param note the object containing the note's form data
     */
    public void createNote(Note note) {
        noteMapper.insert(note);
    }

    /**
     * Tries to update an existing note
     *
     * @param note the object containing the note's form data
     */
    public void updateNote(Note note) {
        noteMapper.updateNote(note);
    }

    /**
     * @param noteId
     * @return
     */

    public Note getNoteById(int noteId) {
        return noteMapper.getNoteById(noteId);
    }


    /**
     * Gets a list of all the current logged user notes
     *
     * @param userId
     * @return the list of notes
     */
    public List<Note> getAllUserNote(int userId) {
        return noteMapper.getNotes(userId);
    }

    /**
     * Tries to delete a note based on the given note's ID
     *
     * @param noteId note's ID
     */
    public void deleteNote(int noteId) {
        noteMapper.deleteNote(noteId);
    }


}
