package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*") // per lavorare in locale
@RequestMapping(path = "/persona") // path iniziale
public class PersonaController {
	@Autowired
	private PersonaRepository personaRepository;

	@PostMapping(path = "/aggiungi", produces = MediaType.APPLICATION_JSON_VALUE) // aggiunge una persona
	public @ResponseBody Persona addNewPersona(@RequestBody Persona p) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Persona n = new Persona();
		n.setName(p.getName());
		n.setLastname(p.getLastname());
		n.setEmail(p.getEmail());
		n.setEta(p.getEta());
		personaRepository.save(n);
		return n;
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@GetMapping(path = "/visualizza") // visualizza tutto
	public @ResponseBody Iterable<Persona> getAllPersone() { // ritorna nel corpo della pagina un iterable di utenti
		// This returns a JSON or XML with the users
		return personaRepository.findAll(); // find all corrisponde a select * from tabella
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@GetMapping(path = "/pernome/{name}") // ricerca per nome
	public @ResponseBody Iterable<Persona> getPersonaName(@PathVariable("name") String name) {
		return personaRepository.findByname(name);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@GetMapping(path = "/percognome/{lastname}") // ricerca per cognome
	public @ResponseBody Iterable<Persona> getPersonaLastName(@PathVariable("lastname") String lastname) {
		return personaRepository.findBylastname(lastname);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@GetMapping(path = "/perid/{id}") // ricerca per id
	public @ResponseBody Persona getPersonaId(@PathVariable Integer id) {
		return personaRepository.findByid(id);
	}

	@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
	@DeleteMapping(path = "/rimuovi/{id}", produces = MediaType.APPLICATION_JSON_VALUE) // rimuovi per id
	public @ResponseBody Persona DeletePersonaId(@PathVariable(required = true) Integer id) {
		if (personaRepository.existsById(id)) { // se esiste l'id
			personaRepository.deleteById(id);
		} 
		return personaRepository.findByid(id);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(path = "/modifica", produces = MediaType.APPLICATION_JSON_VALUE) // simile all'aggiungi, gli cambio i parametri se esiste quell'id
																				//mediatype riceve un json dal frontend
	public @ResponseBody Persona AlterPersona(@RequestBody Persona p) {
		if (personaRepository.existsById(p.getId())) {
			personaRepository.save(p);
			
		} 
		return p;

	}

}
