package com.safetynet.alerts.data.person;

import com.safetynet.alerts.data.ProcessData;
import com.safetynet.alerts.data.ProcessDataImpl;
import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.utils.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

class PersonDataImplTest {

    private static PersonData personData;
    private static final ProcessData processData = new ProcessDataImpl();
    private static JSONObject data;

    @BeforeAll
    public static void setUp() throws IOException, ParseException {
        personData = new PersonDataImpl();
        data  = processData.readDatafromJsonFile(Constants.JSON_PATH);
    }

    @AfterAll
    public static void tearDown() {
        processData.writeDataInJsonFile(Constants.JSON_PATH, data);
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
        personData.update(person);
        Assertions.assertEquals("Paris", personData.findByFirstNameAndLastName("Allison", "Boyd").getCity());
    }

    @Test
    public void should_add_person_with_firstname_julie_when_add_person_with_firstname_julie() throws AlreadyExistingException {
        Person expected = new Person();
        expected.setFirstName("Julie");
        expected.setLastName("Philip");
        expected.setAddress("2345 Calm St");
        expected.setCity("Manishma");
        expected.setZip("67543");
        expected.setPhone("734-900-4367");
        expected.setEmail("j.philip@car.fr");
        personData.add(expected);
        Assertions.assertEquals(personData.findByFirstNameAndLastName("Julie", "Philip"), expected);
    }

    @Test
    public void should_throw_exception_when_add_existing_person() throws AlreadyExistingException {
        Throwable throwable = null;
        Person expected = new Person();
        expected.setFirstName("Miguel");
        expected.setLastName("Ruiz");
        expected.setAddress("2345 Calm St");
        expected.setCity("Lisboa");
        expected.setZip("67543");
        expected.setPhone("734-900-4367");
        expected.setEmail("m.ruiz@car.fr");
        personData.add(expected);
        try {
            personData.add(expected);
        } catch (Throwable t) {
            throwable = t;
        }
        Assertions.assertTrue(throwable instanceof AlreadyExistingException);
    }

    @Test
    public void should_delete_person_when_delete_person() {
        Person person = personData.findByFirstNameAndLastName("Julie", "Philip");
        personData.delete(person);
    }
}