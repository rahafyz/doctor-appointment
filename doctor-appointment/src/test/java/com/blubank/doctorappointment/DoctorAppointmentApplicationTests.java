package com.blubank.doctorappointment;

import com.blubank.doctorappointment.config.EmbeddedRedisConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {EmbeddedRedisConfiguration.class})
class DoctorAppointmentApplicationTests {

	@Test
	void contextLoads() {
	}

}
