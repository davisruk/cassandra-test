package uk.co.boots.cassandra;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.boots.cassandra.model.Person;
import uk.co.boots.cassandra.model.PersonKey;
import uk.co.boots.cassandra.repository.PersonRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	private PersonRepository pr;

	public static void main (final String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		final PersonKey key = PersonKey.builder()
				.firstName("Richard")
				.dateOfBirth(LocalDateTime.of(LocalDate.of(1970,8,5), LocalTime.of(1, 0, 0)))
				.id(UUID.randomUUID())
				.build();
		final Person p = Person.builder()
				.key(key)
				.lastName("Davis")
				.salary(10000000.00)
				.build();
		pr.insert(p);
		pr.findByKeyFirstName("Richard").forEach(System.out::println);
		pr.findByKeyFirstNameAndKeyDateOfBirthGreaterThan("Richard",
				LocalDateTime.of(LocalDate.of(1960, 1, 1), LocalTime.of(1,0,0)))
				.forEach(System.out::println);
	}
}
