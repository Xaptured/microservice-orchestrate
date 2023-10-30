package com.esportarena.microservices.orchestrateapi;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEncryptableProperties
@OpenAPIDefinition(
		info = @Info(
				title = "Orchestrate_APIs",
				description = "All the orchestrate APIs are available here",
				version = "1.0.0",
				termsOfService = "ESportsArena.com",
				contact = @Contact(
						name = "Jack",
						email = "jk19011999@gmail.com"
				),
				license = @License(
						name = "ESportsArena",
						url = "ESportsArena.com"
				)
		)
)
public class OrchestrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrchestrateApplication.class, args);
	}

}
