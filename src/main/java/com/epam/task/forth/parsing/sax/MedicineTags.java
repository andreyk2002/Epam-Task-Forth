package com.epam.task.forth.parsing.sax;

public enum MedicineTags {
    MEDICINES("medicines"),
    PILLS("pills"),
    DROPS("drops"),
    PHARMA("pharma"),
    PRICE("price"),
    VOLUME_MILLIGRAMS("volume-milligrams");

    private final String value;

    MedicineTags(String value) {
        this.value = value;
    }

    public static MedicineTags enumFromTag(String tagName) {
        if (tagName.equalsIgnoreCase("volume-milligrams")) {
            return VOLUME_MILLIGRAMS;
        }
        return MedicineTags.valueOf(tagName);
    }

    public String getValue() {
        return value;
    }

}
