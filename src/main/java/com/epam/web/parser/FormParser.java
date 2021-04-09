package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

public interface FormParser {

    ParseResult parse(FileItem item) throws Exception;
}
