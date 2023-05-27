package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.exception.GlobalExceptionHandling;
import com.blubank.doctorappointment.model.AppointmentSlot;
import com.blubank.doctorappointment.model.Doctor;
import com.blubank.doctorappointment.repository.AppointmentRepository;
import com.blubank.doctorappointment.repository.AppointmentSlotRepository;
import com.blubank.doctorappointment.repository.DoctorRepository;
import com.blubank.doctorappointment.repository.PatientRepository;
import com.blubank.doctorappointment.service.AppointmentFacadeService;
import com.blubank.doctorappointment.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.format.DateTimeFormatter;

import static com.blubank.doctorappointment.util.AppointmentData.appointment;
import static com.blubank.doctorappointment.util.AppointmentFacadeData.reserveAppointmentDTO;
import static com.blubank.doctorappointment.util.AppointmentSlotData.appointmentSlot;
import static com.blubank.doctorappointment.util.AppointmentSlotData.takenAppointmentSlot;
import static com.blubank.doctorappointment.util.DoctorData.doctor;
import static com.blubank.doctorappointment.util.PatientData.patient;
import static com.blubank.doctorappointment.util.PatientData.patientData;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentFacadeService appointmentFacadeService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentSlotRepository appointmentSlotRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    GlobalExceptionHandling exceptionHandler;
    private MockMvc mockMvc;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @BeforeEach
    void setup() {
        AppointmentController controller = new AppointmentController(appointmentService, appointmentFacadeService);

        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(jacksonMessageConverter)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setControllerAdvice(exceptionHandler)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        appointmentRepository.deleteAll();
        patientRepository.deleteAll();
        appointmentSlotRepository.deleteAll();
        doctorRepository.deleteAll();
    }

    @Test
    void findAll() throws Exception{
        doctorRepository.save(doctor());
        appointmentSlotRepository.save(appointmentSlot());
        patientRepository.save(patientData());
        appointmentRepository.save(appointment());

        mockMvc.perform(get("/api/v1/appointment"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].appointmentSlot.startTime").value(takenAppointmentSlot().getStartTime().format(formatter)))
                .andExpect(jsonPath("$.[0].appointmentSlot.endTime").value(takenAppointmentSlot().getEndTime().format(formatter)))
                .andExpect(jsonPath("$.[0].appointmentSlot.isAvailable").value(takenAppointmentSlot().getIsAvailable()))
                .andExpect(jsonPath("$.[0].patient.name").value(patient().getName()))
                .andExpect(jsonPath("$.[0].patient.phoneNumber").value(patient().getPhoneNumber()));
    }

    @Test
    void reserve() throws Exception {
        Doctor doctor = doctorRepository.save(doctor());
        AppointmentSlot appointmentSlot = appointmentSlotRepository.save(appointmentSlot(doctor));

        mockMvc.perform(post("/api/v1/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reserveAppointmentDTO(appointmentSlot.getId()))))
                .andExpect(status().isOk());

    }
}
