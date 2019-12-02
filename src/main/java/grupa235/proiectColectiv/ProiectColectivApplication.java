package grupa235.proiectColectiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"grupa235.proiectColectiv.model"})
public class ProiectColectivApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProiectColectivApplication.class, args);
	}

}
