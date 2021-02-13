package com.epam.task.forth.entities;

public class Pills extends Medicine {

    private int quantity;
    private double dosageMilligrams;

    public Pills() {
    }

    public Pills(String pharmaName, String name, MedicineGroup group, double price, int quantity, double dosageMilligrams) {
        super(pharmaName, name, group, price);
        this.quantity = quantity;
        this.dosageMilligrams = dosageMilligrams;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getDosageMilligrams() {
        return dosageMilligrams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pills)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Pills pills = (Pills) o;

        if (quantity != pills.quantity) return false;
        return Double.compare(pills.dosageMilligrams, dosageMilligrams) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(dosageMilligrams);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
