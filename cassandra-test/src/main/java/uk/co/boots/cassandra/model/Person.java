package uk.co.boots.cassandra.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Table("people_by_first_name")
public class Person {

	@PrimaryKey
	private PersonKey key;
	@Column("last_name")
	private String lastName;
	@Column("salary")
	private double salary;
	
}
