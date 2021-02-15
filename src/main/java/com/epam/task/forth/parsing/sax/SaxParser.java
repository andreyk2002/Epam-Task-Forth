package com.epam.task.forth.parsing.sax;

import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.parsing.Parser;
import com.epam.task.forth.parsing.ParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {
    private final Logger LOGGER = LogManager.getLogger(SaxParser.class);


    @Override
    public List<Medicine> parse(String filename) throws ParserException {
        MedicineHandler handler = new MedicineHandler();

        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(filename);
        } catch (SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ParserException(e.getMessage(), e);
        }
        return handler.getMedicines();
    }

}
