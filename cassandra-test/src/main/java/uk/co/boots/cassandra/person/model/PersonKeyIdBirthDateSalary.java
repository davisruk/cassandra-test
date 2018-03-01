package uk.co.boots.cassandra.person.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import uk.co.boots.cassandra.person.model.PersonKeyIdFirstNameBirthDate.PersonKeyIdFirstNameBirthDateBuilder;

@Data
@AllArgsConstructor
@Builder
@PrimaryKeyClass
public class PersonKeyIdBirthDateSalary {
	@PrimaryKeyColumn(name="date_of_birth", type=PrimaryKeyType.PARTITIONED)
	private LocalDateTime dateOfBirth;

	@PrimaryKeyColumn(name="salary", ordinal = 0, ordering=Ordering.DESCENDING)
	private double salary;
	
	@PrimaryKeyColumn(name="person_id", ordinal = 1, ordering=Ordering.DESCENDING)
	private UUID id;
}
