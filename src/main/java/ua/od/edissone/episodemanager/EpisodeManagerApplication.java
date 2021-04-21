package ua.od.edissone.episodemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EpisodeManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpisodeManagerApplication.class, args);
	}

}
