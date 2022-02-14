package com.safetynet.alerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.services.person.PersonService;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc
            mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void should_return_status_200_when_get_persons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_status_201_when_create_person() throws Exception {
        Person person = new Person();
        person.setFirstName("Miguel");
        person.setLastName("Ruiz");
        person.setAddress("2345 Calm St");
        person.setCity("Lisboa");
        person.setZip("67543");
        person.setPhone("734-900-4367");
        person.setEmail("m.ruiz@car.fr");
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(person));
        mockMvc.perform(
                post("/person").contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())
                ).andExpect(status().isCreated());
    }

    @Test
    public void should_return_stattus_200_when_update_person() throws Exception {
        Person person = new Person();
        person.setFirstName("Miguel");
        person.setLastName("Ruiz");
        person.setAddress("2345 Calm St");
        person.setCity("Lisboa");
        person.setZip("67543");
        person.setPhone("734-900-4367");
        person.setEmail("m.ruiz@car.fr");
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(person));
        mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
                ).andExpect(status().isOk());
    }

    @Test
    public void should_return_200_when_delete_person() throws Exception {
        Person person = new Person();
        person.setFirstName("Miguel");
        person.setLastName("Ruiz");
        person.setAddress("2345 Calm St");
        person.setCity("Lisboa");
        person.setZip("67543");
        person.setPhone("734-900-4367");
        person.setEmail("m.ruiz@car.fr");
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(person));
        mockMvc.perform(delete("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
        ).andExpect(status().isOk());
    }
}