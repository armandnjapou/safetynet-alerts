package com.safetynet.alerts.data.person;

import com.safetynet.alerts.models.Person;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

class PersonDataTest {

    private static IPersonData personData;

    @BeforeAll
    public static void setUp() {
        personData = new PersonData();
    }

    @Test
    public void should_return_list_of_23_persons_when_read_person_from_json() throws IOException, ParseException {
        List<Person> persons =  personData.findAll();
        Assertions.assertEquals(23, persons.size());
    }

    @Test
    public void should_return_true_when_verify_if_all_read_persons_are_not_null() throws IOException, ParseException {
        List<Person> persons =  personData.findAll();
        Assertions.assertTrue(persons.stream().allMatch(Objects::nonNull));
    }

    @Test
    public void should_return_true_when_verify_if_all_read_persons_contains_non_null_values() throws IOException, ParseException {
        List<Person> persons =  personData.findAll();
        Assertions.assertTrue(persons.stream().allMatch(person -> person.getFirstName() != null && person.getLastName() != null
                && person.getAddress() != null && person.getZip() != null && person.getCity() != null && person.getPhone() != null && person.getEmail() != null ));
    }
}