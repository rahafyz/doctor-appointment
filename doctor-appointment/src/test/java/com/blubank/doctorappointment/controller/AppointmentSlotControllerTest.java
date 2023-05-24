package com.blubank.doctorappointment.controller;


import com.blubank.doctorappointment.dto.CreateAppointmentSlotDTO;
import com.blubank.doctorappointment.exception.GlobalExceptionHandling;
import com.blubank.doctorappointment.mapper.AppointmentSlotMapper;
import com.blubank.doctorappointment.repository.AppointmentSlotRepository;
import com.blubank.doctorappointment.service.AppointmentSlotService;
import com.blubank.doctorappointment.util.LockUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.blubank.doctorappointment.util.AppointmentSlotData.createAppointmentSlotDTO;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentSlotControllerTest {

    @Autowired
    private AppointmentSlotRepository repository;

    @Autowired
    private AppointmentSlotMapper mapper;

    @Autowired
    private AppointmentSlotService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    GlobalExceptionHandling exceptionHandler;

    @Autowired
    private LockUtil lockUtil;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        AppointmentSlotController controller = new AppointmentSlotController(service);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(jacksonMessageConverter)
                .setControllerAdvice(exceptionHandler)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testSaveAppointmentSlot() throws Exception {
        CreateAppointmentSlotDTO createAppointmentSlotDTO = createAppointmentSlotDTO();

        mockMvc.perform(post("/api/v1/appointment-slot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAppointmentSlotDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].startTime").value(createAppointmentSlotDTO.getStartTime().toString()))
                .andExpect(jsonPath("$[0].endTime").value(createAppointmentSlotDTO.getStartTime().plusMinutes(30).toString()))
                .andExpect(jsonPath("$[0].isAvailable").value(true));
    }
}
