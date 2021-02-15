package com.epam.task.forth.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "MedicineGroup")
@XmlEnum
public enum MedicineGroup {

    @XmlEnumValue("antibiotic")
    ANTIBIOTIC("antibiotic"),

    @XmlEnumValue("bud")
    BUD("bud"),

    @XmlEnumValue("painkiller")
    PAINKILLER("painkiller"),

    @XmlEnumValue("vitamin")
    VITAMIN("vitamin");

    private final String value;

    MedicineGroup(String v) {
        value = v;
    }

    public static MedicineGroup fromValue(String v) {
        for (MedicineGroup c : MedicineGroup.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }

}
