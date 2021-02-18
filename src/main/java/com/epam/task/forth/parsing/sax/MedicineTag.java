package com.epam.task.forth.parsing.sax;

public enum MedicineTag {
    MEDICINES("medicines"),
    PILLS("pills"),
    DROPS("drops"),
    PHARMA("pharma"),
    PRICE("price"),
    VOLUME_MILLIGRAMS("volume-milligrams");

    private final String tagName;

    MedicineTag(String value) {
        this.tagName = value;
    }

    public static MedicineTag getByTagName(String name){
        for(MedicineTag tag : MedicineTag.values()){
            String tagName = tag.getTagName();
            if(tagName.equals(name)){
                return tag;
            }
        }
        return null;
    }

    public String getTagName() {
        return tagName;
    }

}
