package uk.co.boots.cassandra;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uk.co.boots.cassandra.person.dto.PersonDTO;
import uk.co.boots.cassandra.person.model.PersonWithKeyIdFirstNameBirthDate;
import uk.co.boots.cassandra.person.service.PersonService;

@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
	private PersonService ps;

	public static void main (final String args[]) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		final PersonDTO p1 = PersonDTO.builder()
				.firstName("Richard")
				.dateOfBirth(LocalDateTime.of(LocalDate.of(1970,8,5), LocalTime.of(1, 0, 0)))
				.id(UUID.randomUUID())
				.lastName("Davis")
				.salary(100000.00)
				.build();
		ps.save(p1);
		
		final PersonDTO p2 = PersonDTO.builder()
				.firstName("Merle")
				.dateOfBirth(LocalDateTime.of(LocalDate.of(1974,2,24), LocalTime.of(1, 0, 0)))
				.id(UUID.randomUUID())
				.lastName("Davis")
				.salary(50000.00)
				.build();

		ps.save(p2);		

		final PersonDTO p3 = PersonDTO.builder()
				.firstName("Richard")
				.dateOfBirth(LocalDateTime.of(LocalDate.of(1974,2,24), LocalTime.of(1, 0, 0)))
				.id(UUID.randomUUID())
				.lastName("David")
				.salary(10000.00)
				.build();
		ps.save(p3);

		System.out.println("Get all Richards");
		ps.getByFirstName("Richard").forEach(System.out::println);

		System.out.println("Get all Richards born after 01-01-1971");
		ps.getByFirstNameAndBirthDateGreaterThan("Richard", LocalDateTime.of(LocalDate.of(1971, 1, 1), LocalTime.of(1,0,0)))
				.forEach(System.out::println);
		
		System.out.println("Get all People born on 24-02-1974 with salary greater than 45000");
		ps.getByDateOfBirthAndSalaryGreaterThan(LocalDateTime.of(LocalDate.of(1974, 2, 24), LocalTime.of(1,0,0)), 45000.00)
				.forEach(System.out::println);

		System.out.println("Get all People born after 24-02-1974 with salary less than 45000");
		ps.getByDateOfBirthAndSalaryLessThan(LocalDateTime.of(LocalDate.of(1974, 2, 24), LocalTime.of(1,0,0)), 45000.00)
				.forEach(System.out::println);

		ps.delete(p1);
		ps.delete(p2);
		ps.delete(p3);
	}
}
