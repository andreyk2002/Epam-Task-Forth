package com.epam.task.forth.parsing;

public class JaxbParserTest extends ParserTest {
    private static final String XSD_FILE = "src/main/resources/Medicines.xsd";

    @Override
    protected Parser getParser() {
        return new JaxbParser(XSD_FILE);
    }
}
