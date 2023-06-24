package sg.edu.iss.team6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sg.edu.iss.team6.model.User;
import sg.edu.iss.team6.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.annotation.Resource;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class Team6Application {

	public static void main(String[] args) {
		SpringApplication.run(Team6Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Component
	public class UserCommandLineRunner implements CommandLineRunner {

		@Resource
		private UserRepository userRepository;
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;


		@Override
		public void run(String... args) throws Exception {
			User[] users = {
					new User("adm_1_John", passwordEncoder.encode("adm1")),
					new User("adm_2_Jane", passwordEncoder.encode("adm2")),
					new User("lec_1_David", passwordEncoder.encode("lec1")),
					new User("lec_2_Sarah", passwordEncoder.encode("lec2")),
					new User("lec_3_Michael", passwordEncoder.encode("lec3")),
					new User("stu_1_Jennifer", passwordEncoder.encode("stu1")),
					new User("stu_10_Ryan", passwordEncoder.encode("stu10")),
					new User("stu_2_Thomas", passwordEncoder.encode("stu2")),
					new User("stu_3_Jessica", passwordEncoder.encode("stu3")),
					new User("stu_4_William", passwordEncoder.encode("stu4")),
					new User("stu_5_Melissa", passwordEncoder.encode("stu5")),
					new User("stu_6_Benjamin", passwordEncoder.encode("stu6")),
					new User("stu_7_Sophia", passwordEncoder.encode("stu7")),
					new User("stu_8_Daniel", passwordEncoder.encode("stu8")),
					new User("stu_9_Emily", passwordEncoder.encode("stu9"))
			};

			for (User user : users) {
				userRepository.save(user);
			}

			System.out.println("User entities created and saved.");
		}
	}


}
