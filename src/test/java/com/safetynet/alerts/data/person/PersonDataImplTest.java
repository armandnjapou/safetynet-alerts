package com.safetynet.alerts.data.person;

import com.safetynet.alerts.models.Person;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

class PersonDataImplTest {

    private static PersonData personData;

    @BeforeAll
    public static void setUp() {
        personData = new PersonDataImpl();
    }

    @Test
    public void should_return_list_of_23_persons_when_find_all_persons() throws IOException, ParseException {
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

    @Test
    public void should_return_person_with_firstname_allison_and_lastname_boyd_when_find_by_firstname_and_lastname_with_firstname_allison_and_lastname_boyd() {
        Person person = personData.findByFirstNameAndLastName("Allison", "Boyd");
        Assertions.assertTrue(person.getFirstName().equals("Allison") && person.getLastName().equals("Boyd"));
    }

    @Test
    public void should_return_null_when_find_by_firstname_and_lastname_with_firstname_julie_and_lastname_philippe() {
        Person person = personData.findByFirstNameAndLastName("Julie", "Philippe");
        Assertions.assertNull(person);
    }
}