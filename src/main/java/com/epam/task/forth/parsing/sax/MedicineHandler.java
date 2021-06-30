package com.epam.task.forth.parsing.sax;

import com.epam.task.forth.entities.Drops;
import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.entities.MedicineGroup;
import com.epam.task.forth.entities.Pills;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MedicineHandler extends DefaultHandler {

    private final static String PILLS = "pills";
    private final static String DROPS = "drops";
    private final List<Medicine> medicines;
    private Medicine currentMedicine;
    private MedicineTag currentTag;


    public MedicineHandler() {
        medicines = new ArrayList<>();
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (DROPS.equals(localName)) {
            currentMedicine = new Drops();
            if (attributes.getLength() == 3) {
                int groupIndex = 2;
                setGroup(attributes, groupIndex);
            }
        } else if (PILLS.equals(localName)) {
            currentMedicine = new Pills();
            int attributeIndex = 2;
            String fieldName = attributes.getLocalName(attributeIndex);
            setPillsFields(attributes, attributeIndex, fieldName);
        }
        if (PILLS.equals(localName) || DROPS.equals(localName)) {
            String id = attributes.getValue(0);
            currentMedicine.setId(id);
            String name = attributes.getValue(1);
            currentMedicine.setName(name);
        } else {
            currentTag = MedicineTag.getByTagName(localName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (PILLS.equals(localName) || DROPS.equals(localName)) {
            medicines.add(currentMedicine);
        }
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        String content = new String(chars, start, length).trim();

        if (currentTag != null) {
            switch (currentTag) {
                case PRICE:
                    double price = Double.parseDouble(content);
                    currentMedicine.setPrice(price);
                    break;
                case PHARMA:
                    currentMedicine.setPharma(content);
                    break;
                case VOLUME_MILLIGRAMS:
                    double volumeMilligrams = Double.parseDouble(content);
                    Drops current = (Drops) currentMedicine;
                    current.setVolumeMilligrams(volumeMilligrams);
                    break;
            }
        }
        currentTag = null;
    }

    private void setPillsFields(Attributes attributes, int attributeIndex, String firstAdditionalAttribute) {

        if (firstAdditionalAttribute.equals("quantity")) {
            setQuantity(attributes, attributeIndex);
            if (attributes.getLength() > 3) {
                int groupIndex = 3;
                setGroup(attributes, groupIndex);
            }

        } else {
            int groupIndex = 2;
            setGroup(attributes, groupIndex);
            setQuantity(attributes, attributeIndex + 1);
        }
    }

    private void setQuantity(Attributes attributes, int attributeIndex) {
        String quantityString = attributes.getValue(attributeIndex);
        int quantity = Integer.parseInt(quantityString);
        ((Pills) currentMedicine).setQuantity(quantity);
    }

    private void setGroup(Attributes attributes, int groupIndex) {
        String groupAttribute = attributes.getValue(groupIndex);
        String groupAttributeUpper = groupAttribute.toUpperCase();
        MedicineGroup group = Enum.valueOf(MedicineGroup.class, groupAttributeUpper);
        currentMedicine.setGroup(group);
    }


}
