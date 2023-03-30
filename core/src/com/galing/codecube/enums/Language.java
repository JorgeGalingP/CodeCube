package com.galing.codecube.enums;

import com.galing.codecube.Assets;

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
                type = Assets.selectString("Language_en");
                break;
            case ES_ES:
                type = Assets.selectString("Language_es_ES");
                break;
        }

        return type;
    }
}
