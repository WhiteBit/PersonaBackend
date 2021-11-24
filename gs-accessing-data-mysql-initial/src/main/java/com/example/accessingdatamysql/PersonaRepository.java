package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
	Iterable<Persona> findByname(String name);
	Iterable<Persona> findBylastname(String lastname);
	Persona findByid(Integer id);
	//Iterable<Persona> findBynameAndeta(String name, int eta);
	
}
