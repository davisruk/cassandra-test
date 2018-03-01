package uk.co.boots.cassandra.person.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("people_by_id_firstname_birthdate")
public class PersonWithKeyIdFirstNameBirthDate {
	@PrimaryKey
	private PersonKeyIdFirstNameBirthDate key;
	@Column("last_name")
	private String lastName;
	@Column("salary")
	private double salary;
}
