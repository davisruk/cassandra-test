package uk.co.boots.cassandra.person.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import uk.co.boots.cassandra.person.model.PersonKeyIdBirthDateSalary;
import uk.co.boots.cassandra.person.model.PersonWithKeyIdBirthDateSalary;

public interface PersonWithKeyIdBirthDateSalaryRepository extends CassandraRepository<PersonWithKeyIdBirthDateSalary, PersonKeyIdBirthDateSalary> {
	List<PersonWithKeyIdBirthDateSalary> findByKeyDateOfBirth(final LocalDateTime dateOfBirth);
	List<PersonWithKeyIdBirthDateSalary> findByKeyDateOfBirthAndKeySalaryLessThan(final LocalDateTime dateOfBirth, final double salary);
	List<PersonWithKeyIdBirthDateSalary> findByKeyDateOfBirthAndKeySalaryGreaterThan(final LocalDateTime dateOfBirth, final double salary);
}
