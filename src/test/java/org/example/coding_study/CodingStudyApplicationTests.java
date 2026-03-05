package org.example.coding_study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodingStudyApplicationTests {
	
	@Test
	void contextLoads() {
		
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}
	
}
