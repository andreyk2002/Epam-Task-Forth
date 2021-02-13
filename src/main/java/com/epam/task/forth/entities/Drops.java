package com.epam.task.forth.entities;

public class Drops extends Medicine {
    private double volumeMilligrams;
    private double concentration;

    public Drops() {
    }

    public Drops(String pharmaName, String name, MedicineGroup group, double price, double volumeMilligrams, double concentration) {
        super(pharmaName, name, group, price);
        this.volumeMilligrams = volumeMilligrams;
        this.concentration = concentration;
    }

    public double getVolumeMilligrams() {
        return volumeMilligrams;
    }

    public double getConcentration() {
        return concentration;
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

        if (Double.compare(drops.volumeMilligrams, volumeMilligrams) != 0) {
            return false;
        }
        return Double.compare(drops.concentration, concentration) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(volumeMilligrams);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(concentration);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
