package com.myresume.utils;

import com.myresume.form.SignUpForm;
import org.apache.commons.text.WordUtils;

import java.util.Random;

public class DataUtil {

    private static final String UID_DELIMETER = "-";

    public static String generateProfileUid(SignUpForm signUpForm) {
        return normalizeName(signUpForm.getFirstName()) + UID_DELIMETER + normalizeName(signUpForm.getLastName());
    }

    public static String normalizeName(String name) {
        return name.trim().toLowerCase();
    }

    public static String capitalizeName(String name) {
        return WordUtils.capitalize(normalizeName(name));
    }

    public static String regenerateUidWithRandomSuffix(String baseUid, String alphabet, int letterCount) {
        return baseUid + UID_DELIMETER + generateRandomSuffix(alphabet, letterCount);
    }

    public static String generateRandomSuffix(String alphabet, int letterCount) {
        Random r = new Random();
        StringBuilder st = new StringBuilder();
        for (int i = 0; i < letterCount; i++) {
            st.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return st.toString();
    }
}
