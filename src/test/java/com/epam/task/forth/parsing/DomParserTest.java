package com.epam.task.forth.parsing;


public class DomParserTest extends ParserTest {


    @Override
    protected Parser getParser() {
        return new DomParser();
    }
}
