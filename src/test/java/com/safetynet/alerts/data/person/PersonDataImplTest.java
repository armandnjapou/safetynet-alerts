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

    @Test
    public void should_update_person_city_to_paris_when_update_person_city_to_paris() {
        Person person = personData.findByFirstNameAndLastName("Allison", "Boyd");
        person.setCity("Paris");
        personData.updatePerson(person);
        Assertions.assertEquals("Paris", personData.findByFirstNameAndLastName("Allison", "Boyd").getCity());
    }

    @Test
    public void should_add_person_with_firstname_julie_when_add_person_with_firstname_julie() {
        Person expected = new Person();
        expected.setFirstName("Julie");
        expected.setLastName("Philip");
        expected.setAddress("2345 Calm St");
        expected.setCity("Manishma");
        expected.setZip("67543");
        expected.setPhone("734-900-4367");
        expected.setEmail("j.philip@car.fr");
        personData.addPerson(expected);
        Assertions.assertEquals(personData.findByFirstNameAndLastName("Julie", "Philip").getFirstName(), expected.getFirstName());
    }
}