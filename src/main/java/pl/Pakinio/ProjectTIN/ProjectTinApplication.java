package pl.Pakinio.ProjectTIN;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.Pakinio.ProjectTIN.dto.PlayerDTO;
import pl.Pakinio.ProjectTIN.model.Player;

@SpringBootApplication
public class ProjectTinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectTinApplication.class, args);
	}

}
