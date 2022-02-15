package com.safetynet.alerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.services.firestation.FireStationService;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationService fireStationService;

    @Test
    public void should_return_status_200_when_get_fire_stations() throws Exception {
        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_status_201_when_create_fire_station() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("20 Bd Stop");
        fireStation.setStation(5);
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(fireStation));
        mockMvc.perform(
                post("/firestation").contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())
        ).andExpect(status().isCreated());
    }

    @Test
    public void should_return_stattus_200_when_update_fire_station() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("20 Bd Stop");
        fireStation.setStation(5);
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(fireStation));
        mockMvc.perform(put("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
        ).andExpect(status().isOk());
    }

    @Test
    public void should_return_200_when_delete_fire_station() throws Exception {
        FireStation fireStation = new FireStation();
        fireStation.setAddress("20 Bd Stop");
        fireStation.setStation(5);
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(fireStation));
        mockMvc.perform(delete("/firestation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
        ).andExpect(status().isOk());
    }
}