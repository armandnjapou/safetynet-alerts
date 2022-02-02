package com.safetynet.alerts.services.person;

import com.safetynet.alerts.models.Person;

import java.util.List;

public interface PersonData {
    List<Person> findAll();
    Person findByFirstNameAndLastName(String firstName, String lastName);
    Person add(Person person);
    Person update(Person person);
    void delete(Person person);
}
