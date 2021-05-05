package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;



class ListParser implements FieldParser {
    @Override
    public FieldParseResult parse(FileItem item) throws Exception {
        String string = item.getString();
        return new FieldParseResult(FilmField.GENREID, string);
    }
}
