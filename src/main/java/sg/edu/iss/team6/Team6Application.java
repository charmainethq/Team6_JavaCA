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
					new User("adm_1_John", passwordEncoder.encode("adminpass1")),
					new User("adm_2_Jane", passwordEncoder.encode("adminpass2")),
					new User("lec_1_David", passwordEncoder.encode("lecturerpass1")),
					new User("lec_2_Sarah", passwordEncoder.encode("lecturerpass2")),
					new User("lec_3_Michael", passwordEncoder.encode("lecturerpass3")),
					new User("stu_1_Jennifer", passwordEncoder.encode("studentpass1")),
					new User("stu_10_Ryan", passwordEncoder.encode("studentpass10")),
					new User("stu_2_Thomas", passwordEncoder.encode("studentpass2")),
					new User("stu_3_Jessica", passwordEncoder.encode("studentpass3")),
					new User("stu_4_William", passwordEncoder.encode("studentpass4")),
					new User("stu_5_Melissa", passwordEncoder.encode("studentpass5")),
					new User("stu_6_Benjamin", passwordEncoder.encode("studentpass6")),
					new User("stu_7_Sophia", passwordEncoder.encode("studentpass7")),
					new User("stu_8_Daniel", passwordEncoder.encode("studentpass8")),
					new User("stu_9_Emily", passwordEncoder.encode("studentpass9"))
			};
			for (User user : users) {
				userRepository.save(user);
			}

			System.out.println("User entities created and saved.");
		}
	}


}
