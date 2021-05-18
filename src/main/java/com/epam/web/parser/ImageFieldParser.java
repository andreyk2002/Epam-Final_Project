package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

class ImageFieldParser implements FieldParser {
    private static final String IMAGE_PATH = "../webapps/Epam_Final_Project_war/static/img/movies/";
    private static final String SAVE_PATH = "static/img/movies/";
    private static final String BACKUP_PATH;


    static {
        ResourceBundle resource = ResourceBundle.getBundle("config");
        BACKUP_PATH = resource.getString("backup-path");
    }

    @Override
    public FieldParseResult parse(FileItem item) throws Exception {
        String itemName = item.getName();
        String imagePath = IMAGE_PATH + itemName;
        File imageSaveFile = new File(imagePath);
        item.write(imageSaveFile);
        Path originalPath = Path.of(imagePath);
        Path copied = Path.of(BACKUP_PATH + itemName);
        Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
        String savePath = SAVE_PATH + itemName;
        return FieldParseResult.imagePath(savePath);
    }
}
