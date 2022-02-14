package com.safetynet.alerts.controllers;

import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.services.person.PersonService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public List<Person> findAll() throws IOException, ParseException {
        return personService.findAll();
    }

    @PostMapping("/person")
    public ResponseEntity<Object> add(@RequestBody Person person) throws AlreadyExistingException {
        personService.add(person);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getEmail())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/person")
    public void update(@RequestBody Person person) {
        personService.update(person);
    }

    @DeleteMapping("/person")
    public void delete(@RequestBody Person person) {
        personService.delete(person);
    }
}
