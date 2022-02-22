package com.safetynet.alerts.controllers;

import com.safetynet.alerts.services.GeneralService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}