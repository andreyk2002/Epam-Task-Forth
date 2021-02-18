package com.epam.task.forth.parsing;

import com.epam.task.forth.entities.Drops;
import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.entities.MedicineGroup;
import com.epam.task.forth.entities.Pills;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {

    private static final Logger LOGGER = LogManager.getLogger(DomParser.class);

    @Override
    public List<Medicine> parse(String file) throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Medicine> medicines;
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            medicines = buildMedicines(file, documentBuilder);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ParserException(e.getMessage(), e);
        }
        return medicines;
    }

    private List<Medicine> buildMedicines(String file, DocumentBuilder builder)
            throws IOException, SAXException {
        Document document = builder.parse(file);
        Element root = document.getDocumentElement();

        NodeList dropsList = root.getElementsByTagName("drops");
        NodeList pillsList = root.getElementsByTagName("pills");

        List<Medicine> medicines = new ArrayList<>();
        addMedicines(dropsList, medicines);
        addMedicines(pillsList, medicines);

        return medicines;
    }

    private void addMedicines(NodeList medicinesList, List<Medicine> medicines) {
        for (int i = 0; i < medicinesList.getLength(); i++) {
            Element medicineElement = (Element) medicinesList.item(i);
            Medicine medicine = buildMedicine(medicineElement);
            medicines.add(medicine);
        }
    }

    private Medicine buildMedicine(Element medicineElement) {
        String parentTag = medicineElement.getTagName();
        Medicine medicine = parentTag.equals("pills") ? new Pills() : new Drops();
        String id = medicineElement.getAttribute("id");
        medicine.setId(id);
        String name = medicineElement.getAttribute("name");
        medicine.setName(name);
        setMedicineGroup(medicine, medicineElement);

        String priceString = getElementTextContent(medicineElement, "price");
        double price = Double.parseDouble(priceString);
        medicine.setPrice(price);

        String pharma = getElementTextContent(medicineElement, "pharma");
        medicine.setPharma(pharma);

        return (medicine instanceof Pills) ? buildPills(medicineElement, medicine) :
                buildDrops(medicineElement,  medicine);
    }

    private Medicine buildDrops(Element medicineElement, Medicine medicine) {
        Drops current = (Drops) medicine;
        String volumeString = getElementTextContent(medicineElement, "volume-milligrams");
        double volume = Integer.parseInt(volumeString);
        current.setVolumeMilligrams(volume);
        return current;
    }

    private Medicine buildPills(Element medicineElement, Medicine medicine) {
        Pills current = (Pills) medicine;
        String quantityString = medicineElement.getAttribute("quantity");
        int quantity = Integer.parseInt(quantityString);
        current.setQuantity(quantity);
        return medicine;
    }

    private void setMedicineGroup(Medicine medicine, Element medicineElement) {
        String medicineGroup = medicineElement.getAttribute("group");
        if (medicineGroup != null && !medicineGroup.equals("")) {
            String medicineGroupUpper = medicineGroup.toUpperCase();
            MedicineGroup group = MedicineGroup.valueOf(medicineGroupUpper);
            medicine.setGroup(group);
        }
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodes = element.getElementsByTagName(elementName);
        Node node = nodes.item(0);
        return node.getTextContent();
    }
}
