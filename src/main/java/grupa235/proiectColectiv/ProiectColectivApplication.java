package grupa235.proiectColectiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EntityScan(basePackages = {"grupa235.proiectColectiv.model"})
public class ProiectColectivApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProiectColectivApplication.class, args);
	}
}
