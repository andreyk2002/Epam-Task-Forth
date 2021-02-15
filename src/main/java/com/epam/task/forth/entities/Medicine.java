package com.epam.task.forth.entities;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Medicine", propOrder = {
        "pharma",
        "price"
})
@XmlSeeAlso({
        Drops.class,
        Pills.class
})

public class Medicine {

    @XmlElement(required = true)
    private String pharma;

    private double price;

    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    private String id;

    @XmlAttribute(required = true)
    private String name;

    @XmlAttribute
    //default value = antibiotic
    private MedicineGroup group = MedicineGroup.ANTIBIOTIC;

    public Medicine(String pharma, double price, String id, String name, MedicineGroup group) {
        this.pharma = pharma;
        this.price = price;
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public Medicine() {

    }

    public String getPharma() {
        return pharma;
    }

    public void setPharma(String value) {
        this.pharma = value;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double value) {
        this.price = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public MedicineGroup getGroup() {
        return group;
    }

    public void setGroup(MedicineGroup value) {
        this.group = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Medicine)) {
            return false;
        }

        Medicine medicine = (Medicine) o;

        if (Double.compare(medicine.price, price) != 0) {
            return false;
        }
        if (!Objects.equals(pharma, medicine.pharma)) {
            return false;
        }
        if (!Objects.equals(id, medicine.id)) {
            return false;
        }
        if (!Objects.equals(name, medicine.name)) {
            return false;
        }
        return group == medicine.group;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = pharma != null ? pharma.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        return result;
    }
}
