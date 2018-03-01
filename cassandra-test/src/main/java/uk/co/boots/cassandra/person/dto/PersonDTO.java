package uk.co.boots.cassandra.person.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {
	private UUID id;
	private String lastName;
	private double salary;
	private String firstName;
	private LocalDateTime dateOfBirth;
}
