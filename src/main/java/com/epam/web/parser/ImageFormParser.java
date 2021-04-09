package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

public class ImageFormParser implements FormParser {
    private static final String IMAGE_PATH =
            "D:\\Java\\Projects\\epam-final_project\\src\\main\\webapp\\static\\img\\movies\\";
    private static final String SAVE_PATH = "static/img/movies/";

    @Override
    public ParseResult parse(FileItem item) throws Exception {
        String imagePath = IMAGE_PATH + item.getName();
        File imageSaveFile = new File(imagePath);
        item.write(imageSaveFile);
        String savePath = SAVE_PATH + item.getName();
        return ParseResult.imagePath(savePath);
    }
}
