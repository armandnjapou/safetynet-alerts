package com.safetynet.alerts.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.services.medicalrecord.MedicalRecordService;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    public void should_return_status_200_when_getMedicalRecords() throws Exception {
        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_status_201_when_create_medical_record() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Juan");
        medicalRecord.setLastName("Pablo");
        medicalRecord.setBirthdate("09/09/1996");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(medicalRecord));
        mockMvc.perform(
                post("/medicalRecord").contentType(MediaType.APPLICATION_JSON).content(json.toJSONString())
        ).andExpect(status().isCreated());
    }

    @Test
    public void should_return_stattus_200_when_update_medical_record() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Juan");
        medicalRecord.setLastName("Pablo");
        medicalRecord.setBirthdate("09/09/1996");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(medicalRecord));
        mockMvc.perform(put("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
        ).andExpect(status().isOk());
    }

    @Test
    public void should_return_200_when_delete_medical_record() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("Juan");
        medicalRecord.setLastName("Pablo");
        medicalRecord.setBirthdate("09/09/1996");
        medicalRecord.setMedications(new ArrayList<>());
        medicalRecord.setAllergies(new ArrayList<>());
        JSONObject json = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(medicalRecord));
        mockMvc.perform(delete("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.toJSONString())
        ).andExpect(status().isOk());
    }
}