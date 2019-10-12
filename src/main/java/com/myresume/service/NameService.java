package com.myresume.service;

import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    public String convertName(String name) {
        if (name.contains("-")) {
            final String[] splitName = name.split("-");
            return WordUtils.capitalize(splitName[0]) + " " + WordUtils.capitalize(splitName[1]);
        }

        return WordUtils.capitalize(name);
    }
}
