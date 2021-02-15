package com.epam.task.forth.entities;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pills")
public class Pills extends Medicine {

    @XmlAttribute(required = true)
    @XmlSchemaType(name = "positiveInteger")
    private Integer quantity;


    public Pills(String pharma, String name, MedicineGroup group,
                 double price, Integer quantity, String id) {
        super(pharma, price, id, name, group);
        this.quantity = quantity;
    }

    public Pills() {

    }


    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer value) {
        this.quantity = value;
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

        return Objects.equals(quantity, pills.quantity);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
