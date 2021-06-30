package com.epam.task.forth.parsing.factory;

import com.epam.task.forth.parsing.DomParser;
import com.epam.task.forth.parsing.JaxbParser;
import com.epam.task.forth.parsing.Parser;
import com.epam.task.forth.parsing.sax.SaxParser;

public class ParserFactory {

    public Parser create(ParserType type) throws NoSuchParserException {
        switch (type) {
            case DOM:
                return new DomParser();
            case SAX:
                return new SaxParser();
            case JAXB:
                return new JaxbParser();
            default:
                throw new NoSuchParserException("Parser of type: " + type + " doesn't exist");
        }
    }
}
