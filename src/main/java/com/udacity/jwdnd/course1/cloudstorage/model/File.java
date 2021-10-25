package com.udacity.jwdnd.course1.cloudstorage.model;

import java.util.Arrays;
import java.util.Objects;

public class File {
    private int fileId;
    private String filename;
    private String contentType;
    private String fileSize;
    private int userId;
    private byte[] fileData;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return fileId == file.fileId && userId == file.userId && Objects.equals(filename, file.filename) && Objects.equals(contentType, file.contentType) && Objects.equals(fileSize, file.fileSize) && Arrays.equals(fileData, file.fileData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(fileId, filename, contentType, fileSize, userId);
        result = 31 * result + Arrays.hashCode(fileData);
        return result;
    }
}
