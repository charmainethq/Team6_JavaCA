package sg.edu.iss.team6;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import sg.edu.iss.team6.model.Student;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class Team6Application {

	public static void main(String[] args) {
		SpringApplication.run(Team6Application.class, args);


	}
	
	

}
