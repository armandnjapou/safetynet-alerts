package com.safetynet.alerts.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private static Throwable throwable;

    @Test
    public void should_return_12_when_compute_age_with_birthdate_12_02_2010() {
        assertEquals(12, Utils.computeAgeFromBirthdate("12/02/2010"));
    }

    @Test
    public void should_return_0_when_compute_age_with_null() {
        assertEquals(0, Utils.computeAgeFromBirthdate(null));
    }

    @Test
    public void should_throw_array_out_bound_exception_when_compute_age_with_non_formatted_string() {
        try {
            Utils.computeAgeFromBirthdate("string");
        } catch (Throwable t) {
            throwable = t;
        }
        assertTrue(throwable instanceof ArrayIndexOutOfBoundsException);
    }
}