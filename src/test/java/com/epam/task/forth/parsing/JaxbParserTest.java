package com.epam.task.forth.parsing;

public class JaxbParserTest extends ParserTest {

    @Override
    protected Parser getParser() {
        return new JaxbParser();
    }
}
