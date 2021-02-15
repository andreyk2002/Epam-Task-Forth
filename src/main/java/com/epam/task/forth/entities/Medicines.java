package com.epam.task.forth.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "medicine"
})
@XmlRootElement(name = "medicines")
public class Medicines {

    @XmlElementRef(name = "medicine", namespace = "http://www.forth.task.epam.com/entities", type = JAXBElement.class)
    protected List<JAXBElement<? extends Medicine>> medicine;


    public List<JAXBElement<? extends Medicine>> getMedicine() {
        if (medicine == null) {
            medicine = new ArrayList<>();
        }
        return this.medicine;
    }

}
