package com.epam.task.forth.parsing;

import com.epam.task.forth.parsing.sax.SaxParser;

public class SaxParserTest extends ParserTest {

    @Override
    protected Parser getParser() {
        return new SaxParser();
    }
}
