package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
	Iterable<Persona> findByname(String name);
	Iterable<Persona> findBylastname(String lastname);
	Iterable<Persona> findByeta(int eta);
	Persona findByid(Integer id);
	Iterable<Persona> findByNameAndEta(String name, int eta);
	Iterable<Persona> findByNameAndLastname(String name, String lastname);
	Iterable<Persona> findByLastnameAndEta(String lastname, int eta);
	Iterable<Persona> findByNameAndLastnameAndEta(String name, String lastname, int eta);
	
}
