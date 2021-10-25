package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Objects;

public class Note {
    private int noteId;
    private String noteTitle;
    private String noteDescription;
    private int userId;

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return noteId == note.noteId && userId == note.userId && Objects.equals(noteTitle, note.noteTitle) && Objects.equals(noteDescription, note.noteDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, noteTitle, noteDescription, userId);
    }
}
