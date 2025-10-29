package arep.edu.co.microservicios;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MicroserviciosApplicationTests {

	@Test
	void contextLoads() {
	}

}
