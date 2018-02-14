package uk.co.boots.cassandra.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import uk.co.boots.cassandra.model.Person;
import uk.co.boots.cassandra.model.PersonKey;

@Repository
public interface PersonRepository extends CassandraRepository<Person, PersonKey> {

	List<Person> findByKeyFirstName(final String firstName);
	List<Person> findByKeyFirstNameAndKeyDateOfBirthGreaterThan(final String firstName, final LocalDateTime dateOfBirth);
	
}
