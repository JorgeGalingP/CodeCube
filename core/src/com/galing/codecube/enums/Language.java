package com.galing.codecube.enums;

public enum Language {
    EN("en"),
    ES_ES("es_ES");

    private final String type;

    Language(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static String toString(Language language) {
        String type = "";
        switch (language) {
            case EN:
                type = "English";
                break;
            case ES_ES:
                type = "Español";
                break;
        }

        return type;
    }
}
