package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

class ImageFieldParser implements FieldParser {
    private static final String IMAGE_PATH = "../webapps/Epam_Final_Project_war/static/img/movies/";
    private static final String BACKUP_PATH = "D:/Java/Projects/epam-final_project/src/main/webapp/static/img/movies/";
    private static final String SAVE_PATH = "static/img/movies/";

    @Override
    public ParseResult parse(FileItem item) throws Exception {
        String imagePath = IMAGE_PATH + item.getName();
        File imageSaveFile = new File(imagePath);
        item.write(imageSaveFile);
        Path originalPath = Path.of(imagePath);
        Path copied = Path.of(BACKUP_PATH + item.getName());
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        String savePath = SAVE_PATH + item.getName();
        return ParseResult.imagePath(savePath);
    }
}
