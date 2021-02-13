package com.epam.task.forth;

import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.parsing.Parser;
import com.epam.task.forth.validation.ValidatorException;
import com.epam.task.forth.validation.XmlValidator;

import java.util.ArrayList;
import java.util.List;

public class Director {
    XmlValidator validator;
    Parser parser;

    public Director(XmlValidator validator, Parser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    public List<Medicine> parse(String file) throws ValidatorException {
        if (validator.isValid(file)) {
            return parser.parse(file);
        }
        return new ArrayList<>();
    }

}

//Hierarchy