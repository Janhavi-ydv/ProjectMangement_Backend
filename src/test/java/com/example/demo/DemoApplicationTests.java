package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplication.class)  // ✅ Explicitly specify main class
public class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
}
