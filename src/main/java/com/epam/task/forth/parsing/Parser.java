package com.epam.task.forth.parsing;

import com.epam.task.forth.entities.Medicine;

import java.util.List;

public interface Parser {

    List<Medicine> parse(String file) throws ParserException;
}
