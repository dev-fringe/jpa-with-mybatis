package dev.fringe;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import dev.fringe.entity.Person;
import dev.fringe.repositories.PersonMapper;
import dev.fringe.repositories.PersonRepository;

@SpringBootApplication /* CommandLineRunner needs */
public class Application implements CommandLineRunner{
	
	@Autowired PersonRepository personRepository;
	@Autowired PersonMapper mapper;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).logStartupInfo(false).bannerMode(Banner.Mode.OFF).run(args);
	}

	public void run(String... args) throws Exception {
		personRepository.save(new Person(0, "awa", "sdasdsd", new Date()));
		System.out.println(mapper.select());
	}

}
