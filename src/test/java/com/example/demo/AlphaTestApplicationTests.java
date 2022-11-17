package com.example.demo;

import com.example.demo.config.LoadArgumentsFromCommandLine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AlphaTestApplicationTests {

	@MockBean
	LoadArgumentsFromCommandLine commandLineRunner;

	@Test
	void contextLoads() {
	}

}
