package com.epam.task.forth.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static String NAMESPACE_URL = "http://www.forth.task.epam.com/entities";
    private final static QName _Medicine_QNAME = new QName(NAMESPACE_URL, "medicine");
    private final static QName _Pills_QNAME = new QName(NAMESPACE_URL, "pills");
    private final static QName _Drops_QNAME = new QName(NAMESPACE_URL, "drops");


    public ObjectFactory() {
    }

    public Medicine createMedicine() {
        return new Medicine();
    }

    public Medicines createMedicines() {
        return new Medicines();
    }

    public Pills createPills() {
        return new Pills();
    }

    public Drops createDrops() {
        return new Drops();
    }

    @XmlElementDecl(
            namespace = NAMESPACE_URL,
            name = "medicine"
    )
    public JAXBElement<Medicine> createMedicine(Medicine value) {
        return new JAXBElement<>(_Medicine_QNAME, Medicine.class, null, value);
    }

    @XmlElementDecl(
            namespace = NAMESPACE_URL,
            name = "pills",
            substitutionHeadNamespace = NAMESPACE_URL,
            substitutionHeadName = "medicine"
    )
    public JAXBElement<Pills> createPills(Pills value) {
        return new JAXBElement<>(_Pills_QNAME, Pills.class, null, value);
    }

    @XmlElementDecl(
            namespace = NAMESPACE_URL,
            name = "drops",
            substitutionHeadNamespace = NAMESPACE_URL,
            substitutionHeadName = "medicine"
    )
    public JAXBElement<Drops> createDrops(Drops value) {
        return new JAXBElement<>(_Drops_QNAME, Drops.class, null, value);
    }

}
