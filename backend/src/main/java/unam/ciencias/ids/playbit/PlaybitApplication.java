package unam.ciencias.ids.playbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PlaybitApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaybitApplication.class, args);
	}

}
