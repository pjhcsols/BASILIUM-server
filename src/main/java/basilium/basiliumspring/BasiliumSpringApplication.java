package basilium.basiliumspring;

import basilium.basiliumspring.configuration.WebMvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebMvcConfig.class})
public class BasiliumSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasiliumSpringApplication.class, args);
	}

}
