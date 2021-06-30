package com.epam.task.forth.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    private static final Logger LOGGER = LogManager.getLogger(XmlValidator.class);
    private final String xsdFile;

    XmlValidator(String xsdFile) {
        this.xsdFile = xsdFile;
    }

    public boolean isValid(String filename) throws ValidatorException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            File schemeFile = new File(xsdFile);
            Schema schema = schemaFactory.newSchema(schemeFile);
            Validator validator = schema.newValidator();
            Source xmlToValidate = new StreamSource(filename);
            validator.validate(xmlToValidate);
        } catch (SAXException e) {
            LOGGER.warn("Validation of file" + filename + "failed" + e.getMessage(), e);
            return false;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ValidatorException(e.getMessage(), e);
        }
        return true;
    }
}
