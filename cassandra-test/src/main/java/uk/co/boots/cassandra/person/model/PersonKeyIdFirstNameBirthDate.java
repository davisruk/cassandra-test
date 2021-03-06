package uk.co.boots.cassandra.person.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@PrimaryKeyClass
public class PersonKeyIdFirstNameBirthDate implements Serializable {

	private static final long serialVersionUID = 8802959217985842816L; 
	
	@PrimaryKeyColumn(name="first_name", type=PrimaryKeyType.PARTITIONED)
	private String firstName;
	
	@PrimaryKeyColumn(name="date_of_birth", ordinal = 0)
	private LocalDateTime dateOfBirth;
	
	@PrimaryKeyColumn(name="person_id", ordinal = 1, ordering=Ordering.DESCENDING)
	private UUID id;
}
