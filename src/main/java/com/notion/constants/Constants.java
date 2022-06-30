package com.notion.constants;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Constants {
    public static final String ROOT_PATH = System.getProperty("user.dir");
    public static final String DEFAULT_SUCCESS_MESSAGE = "Success";
    public static final String DEFAULT_ERROR_MESSAGE = "Error";
    public static final String DATE_FORMAT = "EEE, MMM dd, yyyy 'at' hh:mm a zzz";
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);

    public static final String REJECTION_KEY = "rejection_key";
    public static final String REJECTED_VALUE = "rejected_value";
    public static final String REJECTION_REASON = "rejection_reason";
    public static final String FORWARD_SLASH = "/";

    public static class Resources {
        public static final String PAGE = "page";
        public static final String BLOCK = "block";
        public static final String USER = "user";
        public static final String DATABASE = "database";
    }

    public static class Path {
        public static final String ID = FORWARD_SLASH + "{id}";
        public static final String PAGE_ENDPOINT = FORWARD_SLASH + Resources.PAGE;
        public static final String BLOCK_ENDPOINT = FORWARD_SLASH + Resources.BLOCK;
        public static final String USER_ENDPOINT = FORWARD_SLASH + Resources.USER;
        public static final String DATABASE_ENDPOINT = FORWARD_SLASH + Resources.DATABASE;
        public static final String IMPORT = FORWARD_SLASH + "import";
        public static final String EXPORT = FORWARD_SLASH + "export";
        public static final String PING = FORWARD_SLASH + "ping";
    }

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

    public static class UserException {
        public static final String EMPTY_NAME_EXCEPTION = "Name cannot be null or empty string";
        public static final String USER_ID_NOT_FOUND = "User with the user_id: %s, not found";
        public static final String USER_EMAIL_NOT_FOUND = "User with the email: %s, not found";
        public static final String DUPLICATE_EMAIL_EXCEPTION = "User with the email: %s, already exists";
        public static final String INVALID_EMAIL_EXCEPTION = "Invalid email";
        public static final String INVALID_PASSWORD_EXCEPTION = "Invalid Password: Must contains at least eight characters, at least one uppercase letter, one lowercase letter, one number and one special character";
    }

    public static class Regex {
        public static final String EMAIL_REGEX = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
        public static final String PASSWORD_REGEX = "^(?=.*[0-9])" +
                "(?=.*[a-z])(?=.*[A-Z])" +
                "(?=.*[@#$%^&-+=()])" +
                "(?=\\S+$).{8,20}$";
        public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    }

}
