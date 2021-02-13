package com.epam.task.forth.validation;

import org.xml.sax.SAXException;

public class ValidatorException extends Exception {


    public ValidatorException(SAXException e, String message) {
    }

    public ValidatorException() {
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
