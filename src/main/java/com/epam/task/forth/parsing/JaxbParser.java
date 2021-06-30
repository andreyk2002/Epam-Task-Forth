package com.epam.task.forth.parsing;

import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.entities.Medicines;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JaxbParser implements Parser {

    private final static Logger LOGGER = LogManager.getLogger(JaxbParser.class);

    public JaxbParser() {

    }

    @Override
    public List<Medicine> parse(String file) throws ParserException {
        List<Medicine> medicines;
        try {
            JAXBContext context = JAXBContext.newInstance("com.epam.task.forth.entities");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader reader = new FileReader(file);
            Medicines parsedMedicines = (Medicines) unmarshaller.unmarshal(reader);
            medicines = extractFromMedicines(parsedMedicines);
        } catch (JAXBException | FileNotFoundException e) {
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
