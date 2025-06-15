package com.tuancui.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    USER_EXISTED(1002, "User already existed"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    INVALID_MESSAGE_KEY(1004, "Invalid message key"),
    USER_NOT_FOUND(1005, "User not found"),
    BOOK_NOT_FOUND(2000, "Book not found"),
    AUTHOR_NOT_FOUND(2001, "Author not found")
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

