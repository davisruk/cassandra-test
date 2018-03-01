package uk.co.boots.cassandra.person.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import uk.co.boots.cassandra.person.model.PersonWithKeyIdFirstNameBirthDate;
import uk.co.boots.cassandra.person.model.PersonKeyIdFirstNameBirthDate;

@Repository
public interface PersonWithKeyIdNameBirthDateRepository extends CassandraRepository<PersonWithKeyIdFirstNameBirthDate, PersonKeyIdFirstNameBirthDate> {

	List<PersonWithKeyIdFirstNameBirthDate> findByKeyFirstName(final String firstName);
	List<PersonWithKeyIdFirstNameBirthDate> findByKeyFirstNameAndKeyDateOfBirthGreaterThan(final String firstName, final LocalDateTime dateOfBirth);
	List<PersonWithKeyIdFirstNameBirthDate> findByKeyDateOfBirthGreaterThan(final LocalDateTime dateOfBirth);
	
}
