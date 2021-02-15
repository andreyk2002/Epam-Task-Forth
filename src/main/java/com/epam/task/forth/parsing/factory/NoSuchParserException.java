package com.epam.task.forth.parsing.factory;

public class NoSuchParserException extends Exception {
    public NoSuchParserException() {
    }

    public NoSuchParserException(String message) {
        super(message);
    }

    public NoSuchParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchParserException(Throwable cause) {
        super(cause);
    }

}
