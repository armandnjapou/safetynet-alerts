package com.safetynet.alerts.controllers;

import com.safetynet.alerts.services.GeneralService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GeneralController.class)
class GeneralControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeneralService service;

    @Test
    public void should_return_200_when_get_children_from_address() throws Exception {
        mockMvc.perform(get("/childAlert?address=892%20Downing%20Ct"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_200_when_get_phone_numbers_by_fire_station_number() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=2"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_200_when_get_flood_from_stations() throws Exception {
        mockMvc.perform(get("/flood/stations?stations=1,2,3"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_200_when_get_personal_infos() throws Exception {
        mockMvc.perform(get("/personInfo?firstName=Jules&lastName=Tortue"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_200_when_get_community_email() throws Exception {
        mockMvc.perform(get("/communityEmail?city=Paris"))
                .andExpect(status().isOk());
    }
}