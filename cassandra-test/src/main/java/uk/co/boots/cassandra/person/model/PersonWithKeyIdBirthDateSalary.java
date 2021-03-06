package uk.co.boots.cassandra.person.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("people_by_id_birthdate_salary")
public class PersonWithKeyIdBirthDateSalary {
	@PrimaryKey
	private PersonKeyIdBirthDateSalary key;
	@Column("first_name")
	private String firstName;
	@Column("last_name")
	private String lastName;
}
