package GDG_Project.family_communication_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "family")
public class FamilyCommunicationBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(FamilyCommunicationBoardApplication.class, args);
	}

}
