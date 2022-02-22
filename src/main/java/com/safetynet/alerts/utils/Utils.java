package com.safetynet.alerts.utils;

import java.time.LocalDateTime;

public abstract class Utils {
    public static int computeAgeFromBirthdate(String birthdate) {
        if (birthdate != null) {
            String[] tab = birthdate.split("/");
            int year = Integer.parseInt(tab[2]);
            return LocalDateTime.now().getYear() - year;
        } else return 0;
    }
}
