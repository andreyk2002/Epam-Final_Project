package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;

/**
 * Interface FieldParser is responsible to parsing multipart-data form fields
 */

interface FieldParser {

    /**
     * Parses one field of multipart-data form
     * @param item item, representing form fields
     * @return instance of {@link FieldParseResult} class,
     * contains information about field type corresponding to
     * {@link com.epam.web.entity.Film} entity
     * @throws Exception in parsing error occurs
     */
    FieldParseResult parse(FileItem item) throws Exception;
}
