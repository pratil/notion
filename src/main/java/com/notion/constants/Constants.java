package com.notion.constants;

import java.text.SimpleDateFormat;

public class Constants {
    public static final String ROOT_PATH = System.getProperty("user.dir");
    public static final String DEFAULT_SUCCESS_MESSAGE = "Success";
    public static final String DEFAULT_ERROR_MESSAGE = "Error";
    public static final String DATE_FORMAT = "EEE, MMM dd, yyyy 'at' hh:mm a zzz";
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);

    public static class PageException {
        public static final String TITLE_EMPTY_EXCEPTION = "Title cannot be null or empty string";
        public static final String PARENT_PAGE_ID_NOT_FOUND = "Parent Page with the page_id: %s, not found";
        public static final String PAGE_ID_NOT_FOUND = "Parent Page with the page_id: %s, not found";
    }

    public static class BlockException {
        public static final String BLOCK_ID_NOT_FOUND = "Parent Block with the id: %s, not found";
        public static final String INVALID_BLOCK_TYPE = "Invalid Block Type Error, BlockType: %s";
        public static final String INVALID_BLOCK_SUB_TYPE = "Invalid Block Sub Type Error, BlockSubType: %s";
        public static final String IDS_MISMATCH_EXCEPTION = "Two different ids are provided: first: %s, second: %s";
    }
//
//    public static class BlockSubTypeConstants {
//        public static final String H1_HEADING = "H1";
//        public static final String H2_HEADING = "H2";
//        public static final String H3_HEADING = "H3";
//
//        public static final String DISC_LIST_TYPE = "Disc";
//        public static final String CIRCLE_LIST_TYPE = "Circle";
//        public static final String SQUARE_LIST_TYPE = "Square";
//
//        public static final String NUMBER_LIST_TYPE = "Number";
//        public static final String LETTER_LIST_TYPE = "Letters";
//        public static final String ROMAN_NUMBER_LIST_TYPE = "Roman Numerals";
//
//        public static final String NORMAL_LINK = "link";
//        public static final String BOOKMARK_LINK = "Bookmark";
//        public static final String EMBEDDED_LINK = "Embedded";
//    }


}
