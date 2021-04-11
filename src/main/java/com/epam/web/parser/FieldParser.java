package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

interface FieldParser {

    ParseResult parse(FileItem item) throws Exception;
}
