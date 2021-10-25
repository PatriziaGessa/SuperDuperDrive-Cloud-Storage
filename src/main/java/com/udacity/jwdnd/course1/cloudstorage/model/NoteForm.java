package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Objects;

public class NoteForm {

    private String title;
    private String description;
    private String id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteForm noteForm = (NoteForm) o;
        return Objects.equals(title, noteForm.title) && Objects.equals(description, noteForm.description) && Objects.equals(id, noteForm.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, id);
    }
}
