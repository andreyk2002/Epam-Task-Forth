package com.epam.task.forth.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Drops")

public class Drops extends Medicine {

    @XmlElement(name = "volume-milligrams")
    private double volumeMilligrams;

    public Drops(String pharma, String name, MedicineGroup group,
                 double price, double volumeMilligrams, String id) {
        super(pharma, price, id, name, group);
        this.volumeMilligrams = volumeMilligrams;
    }

    public Drops() {

    }

    public double getVolumeMilligrams() {
        return volumeMilligrams;
    }


    public void setVolumeMilligrams(double value) {
        this.volumeMilligrams = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Drops)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Drops drops = (Drops) o;

        return Double.compare(drops.volumeMilligrams, volumeMilligrams) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(volumeMilligrams);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
