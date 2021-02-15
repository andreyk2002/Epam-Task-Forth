package com.epam.task.forth.parsing;

import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.entities.Medicines;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JaxbParser implements Parser {

    private final static Logger LOGGER = LogManager.getLogger(JaxbParser.class);
    private String schemaName;


    public JaxbParser(String schemaName) {
        this.schemaName = schemaName;
    }

    public JaxbParser() {

    }

    public void setSchema(String schemaName) {
        this.schemaName = schemaName;
    }

    @Override
    public List<Medicine> parse(String file) throws ParserException {
        List<Medicine> medicines;
        try {
            JAXBContext context = JAXBContext.newInstance("com.epam.task.forth.entities");
            Unmarshaller unmarshaller = context.createUnmarshaller();

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaFile = new File(schemaName);
            Schema schema = factory.newSchema(schemaFile);
            unmarshaller.setSchema(schema);

            File unmarshallFile = new File(file);
            Medicines parsedMedicines = (Medicines) unmarshaller.unmarshal(unmarshallFile);
            medicines = extractFromMedicines(parsedMedicines);
        } catch (JAXBException | SAXException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ParserException("Error when parsing file" + file + e.getMessage());
        }
        return medicines;
    }

    private List<Medicine> extractFromMedicines(Medicines parsedMedicines) {
        List<Medicine> medicines = new ArrayList<>();
        List<JAXBElement<? extends Medicine>> elements = parsedMedicines.getMedicine();
        for (JAXBElement<? extends Medicine> element : elements) {
            medicines.add(element.getValue());
        }
        return medicines;
    }
}
