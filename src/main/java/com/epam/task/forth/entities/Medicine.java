package com.epam.task.forth.entities;


import java.util.Objects;

public abstract class Medicine {
    private String pharmaName;
    private String name;
    private MedicineGroup group;
    private double price;


    public Medicine() {

    }

    public Medicine(String pharmaName, String name, MedicineGroup group, double price) {
        this.pharmaName = pharmaName;
        this.name = name;
        this.group = group;
        this.price = price;
    }


    public String getPharmaName() {
        return pharmaName;
    }

    public String getName() {
        return name;
    }

    public MedicineGroup getGroup() {
        return group;
    }

    public double getPrice() {
        return price;
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
        if (!Objects.equals(pharmaName, medicine.pharmaName)) {
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
        result = pharmaName != null ? pharmaName.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
