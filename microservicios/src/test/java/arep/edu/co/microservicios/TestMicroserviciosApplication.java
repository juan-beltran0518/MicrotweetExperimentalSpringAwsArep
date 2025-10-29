package arep.edu.co.microservicios;

import org.springframework.boot.SpringApplication;

public class TestMicroserviciosApplication {

	public static void main(String[] args) {
		SpringApplication.from(MicroserviciosApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
