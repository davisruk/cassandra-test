package uk.co.boots.cassandra.person.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.boots.cassandra.person.dto.PersonDTO;
import uk.co.boots.cassandra.person.model.PersonKeyIdBirthDateSalary;
import uk.co.boots.cassandra.person.model.PersonKeyIdFirstNameBirthDate;
import uk.co.boots.cassandra.person.model.PersonWithKeyIdBirthDateSalary;
import uk.co.boots.cassandra.person.model.PersonWithKeyIdFirstNameBirthDate;
import uk.co.boots.cassandra.person.repository.PersonWithKeyIdBirthDateSalaryRepository;
import uk.co.boots.cassandra.person.repository.PersonWithKeyIdNameBirthDateRepository;

@Service
public class PersonService {

	@Autowired
	PersonWithKeyIdNameBirthDateRepository nameBirthDateRepo;
	@Autowired
	PersonWithKeyIdBirthDateSalaryRepository birthDateSalaryRepo;
	
/**************************************
 * 
 * Data Services - All Repositories
 * 
 **************************************/
	public void save (PersonDTO p) {
		PersonWithKeyIdFirstNameBirthDate personWithIdFirstNameBirthDate = toPersonWithKeyIdFirstNameBirthDateFromDTO(p);
		PersonWithKeyIdBirthDateSalary personWithIdBirthDateSalary = toPersonWithKeyIdBirthDateSalaryFromDTO(p);

		birthDateSalaryRepo.save(personWithIdBirthDateSalary);
		nameBirthDateRepo.save(personWithIdFirstNameBirthDate);
	}
	
	public void delete (PersonDTO p) {
		PersonWithKeyIdFirstNameBirthDate personWithIdFirstNameBirthDate = toPersonWithKeyIdFirstNameBirthDateFromDTO(p);
		PersonWithKeyIdBirthDateSalary personWithIdBirthDateSalary = toPersonWithKeyIdBirthDateSalaryFromDTO(p);

		birthDateSalaryRepo.delete(personWithIdBirthDateSalary);
		nameBirthDateRepo.delete(personWithIdFirstNameBirthDate);
	}

/**********************************************************
 * 
 * Data Services - PersonWithKeyIdNameBirthDateRepository
 * 
 **********************************************************/

	public List<PersonDTO> getByFirstName(String firstName) {
		return toDTOListFromPersonWithKeyIdFirstNameBirthDateList(nameBirthDateRepo.findByKeyFirstName(firstName));
	}
	
	public List<PersonDTO> getByFirstNameAndBirthDateGreaterThan(String firstName, LocalDateTime birthDate) {
		return toDTOListFromPersonWithKeyIdFirstNameBirthDateList(nameBirthDateRepo.findByKeyFirstNameAndKeyDateOfBirthGreaterThan(firstName, birthDate));
	}


/**********************************************************
 * 
 * Data Services - PersonWithKeyIdBirthDateSalaryRepository
 * 
 **********************************************************/
	
	public List<PersonDTO> getByBirthDate(LocalDateTime birthDate) {
		return toDTOListFromPersonWithKeyIdBirthDateSalaryList(birthDateSalaryRepo.findByKeyDateOfBirth(birthDate));
	}

	public List<PersonDTO> getByDateOfBirthAndSalaryLessThan(final LocalDateTime dateOfBirth, final double salary){
		return toDTOListFromPersonWithKeyIdBirthDateSalaryList(birthDateSalaryRepo.findByKeyDateOfBirthAndKeySalaryLessThan(dateOfBirth, salary));
	}
	
	public List<PersonDTO> getByDateOfBirthAndSalaryGreaterThan(final LocalDateTime dateOfBirth, final double salary){
		return toDTOListFromPersonWithKeyIdBirthDateSalaryList(birthDateSalaryRepo.findByKeyDateOfBirthAndKeySalaryGreaterThan(dateOfBirth, salary));		
	}
	
	
/**************************************************
 * 
 * Conversion Services
 * 
 **************************************************/
	public List<PersonDTO> toDTOListFromPersonWithKeyIdFirstNameBirthDateList(List<PersonWithKeyIdFirstNameBirthDate> people){
		List<PersonDTO> returnValues = new ArrayList<PersonDTO>();
		people.forEach(p -> {
			returnValues.add(toDTOFromPersonWithKeyIdFirstNameBirthDate(p));
		});
		return returnValues;
	}

	public PersonDTO toDTOFromPersonWithKeyIdFirstNameBirthDate(PersonWithKeyIdFirstNameBirthDate person) {
		return PersonDTO.builder()
				.dateOfBirth(person.getKey().getDateOfBirth())
				.firstName(person.getKey().getFirstName())
				.lastName(person.getLastName())
				.salary(person.getSalary())
				.id(person.getKey().getId())
				.build();
	}
	
	public PersonDTO toDTOFromPersonWithKeyIdBirthDateSalary(PersonWithKeyIdBirthDateSalary person) {
		return PersonDTO.builder()
				.dateOfBirth(person.getKey().getDateOfBirth())
				.firstName(person.getFirstName())
				.lastName(person.getLastName())
				.salary(person.getKey().getSalary())
				.id(person.getKey().getId())
				.build();
	}
	
	public List<PersonDTO> toDTOListFromPersonWithKeyIdBirthDateSalaryList(List<PersonWithKeyIdBirthDateSalary> people){
		List<PersonDTO> returnValues = new ArrayList<PersonDTO>();
		people.forEach(p -> {
			returnValues.add(toDTOFromPersonWithKeyIdBirthDateSalary(p));
		});
		return returnValues;
	}

	public PersonWithKeyIdFirstNameBirthDate toPersonWithKeyIdFirstNameBirthDateFromPersonWithKeyIdBirthDateSalary(PersonWithKeyIdBirthDateSalary person) {
		PersonKeyIdFirstNameBirthDate id = PersonKeyIdFirstNameBirthDate.builder()
					.firstName(person.getFirstName())
					.dateOfBirth(person.getKey().getDateOfBirth())
					.id(person.getKey().getId())
					.build();
		
		return PersonWithKeyIdFirstNameBirthDate.builder()
					.key(id)
					.lastName(person.getLastName())
					.salary(person.getKey().getSalary())
					.build();
	}
	
	public PersonWithKeyIdBirthDateSalary PersonWithKeyIdBirthDateSalaryFromPersonWithKeyIdFirstNameBirthDate(PersonWithKeyIdFirstNameBirthDate person) {
		PersonKeyIdBirthDateSalary key = PersonKeyIdBirthDateSalary.builder()
											.dateOfBirth(person.getKey().getDateOfBirth())
											.id(person.getKey().getId())
											.salary(person.getSalary())
											.build();
		return PersonWithKeyIdBirthDateSalary.builder()
				.key(key)
				.firstName(person.getKey().getFirstName())
				.lastName(person.getLastName())
				.build();
	}

	public PersonWithKeyIdFirstNameBirthDate toPersonWithKeyIdFirstNameBirthDateFromDTO(PersonDTO person) {
		PersonKeyIdFirstNameBirthDate key = PersonKeyIdFirstNameBirthDate.builder()
												.id(person.getId())
												.dateOfBirth(person.getDateOfBirth())
												.firstName(person.getFirstName())
												.build();
		
		return PersonWithKeyIdFirstNameBirthDate.builder()
				.key(key)
				.lastName(person.getLastName())
				.salary(person.getSalary())
				.build();
	}
	
	public PersonWithKeyIdBirthDateSalary toPersonWithKeyIdBirthDateSalaryFromDTO(PersonDTO person) {

		PersonKeyIdBirthDateSalary key = PersonKeyIdBirthDateSalary.builder()
				.dateOfBirth(person.getDateOfBirth())
				.id(person.getId())
				.salary(person.getSalary())
				.build();
		
		return PersonWithKeyIdBirthDateSalary.builder()
				.key(key)
				.firstName(person.getFirstName())
				.lastName(person.getLastName())
				.build();
	}
}
