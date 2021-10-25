package com.udacity.jwdnd.course1.cloudstorage.services;
import com.udacity.jwdnd.course1.cloudstorage.exception.FileExists;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    /**
     * Tries to store a file on the database
     *
     * @param file the uploaded file
     * @throws FileExists signals when a file with the same name is already associated to the current logged user
     */
    public void createFile(File file) throws FileExists {
        if (fileMapper.getFileByFilename(
                file.getUserId(),
                file.getFilename()).isEmpty()) {
            fileMapper.insert(file);
        } else {
            throw new FileExists();
        }
    }

    /**
     * Tries to delete a certain file based on the given file ID and the user ID
     *
     * @param fileId
     */
    public void deleteFile(int fileId) {
        fileMapper.deleteFile(fileId);
    }


    /**
     * Gets all the current logged user files
     *
     * @param userId
     * @return a list of files
     */

    public List<File> getUserFile(int userId) {
        return fileMapper.getFiles(userId);
    }


    public File getFileById(int fileId) {
        return fileMapper.getFileById(fileId);
    }


}
