package com.epam.task.forth.parsing.sax;

import com.epam.task.forth.entities.Drops;
import com.epam.task.forth.entities.Medicine;
import com.epam.task.forth.entities.MedicineGroup;
import com.epam.task.forth.entities.Pills;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MedicineHandler extends DefaultHandler {

    private final static String PILLS = "pills";
    private final static String DROPS = "drops";
    private final List<Medicine> medicines;
    private final EnumSet<MedicineTags> tagsWithText;
    private Medicine currentMedicine = null;
    private MedicineTags currentTag;


    public MedicineHandler() {
        medicines = new ArrayList<>();
        tagsWithText = EnumSet.range(MedicineTags.PHARMA, MedicineTags.VOLUME_MILLIGRAMS);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (DROPS.equals(localName)) {
            currentMedicine = new Drops();
            if (attributes.getLength() == 3) {
                setGroup(attributes);
            }
        } else if (PILLS.equals(localName)) {
            currentMedicine = new Pills();
            String quantityString = attributes.getValue(2);
            int quantity = Integer.parseInt(quantityString);
            ((Pills) currentMedicine).setQuantity(quantity);
            if (attributes.getLength() == 4) {
                setGroup(attributes);
            }
        }
        if (PILLS.equals(localName) || DROPS.equals(localName)) {
            String id = attributes.getValue(0);
            currentMedicine.setId(id);
            String name = attributes.getValue(1);
            currentMedicine.setName(name);
        } else {
            MedicineTags temp = MedicineTags.enumFromTag(localName.toUpperCase());
            if (tagsWithText.contains(temp)) {
                currentTag = temp;
            }
        }
    }

    private void setGroup(Attributes attributes) {
        String groupAttribute = attributes.getValue(2);
        String groupAttributeUpper = groupAttribute.toUpperCase();
        MedicineGroup group = Enum.valueOf(MedicineGroup.class, groupAttributeUpper);
        currentMedicine.setGroup(group);
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (PILLS.equals(localName) || DROPS.equals(localName)) {
            medicines.add(currentMedicine);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String content = new String(ch, start, length).trim();

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
                default:
                    throw new EnumConstantNotPresentException(currentTag.getDeclaringClass(), currentTag.name());
            }
        }
        currentTag = null;
    }


}
