package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*") //per lavorare in locale 
@RequestMapping(path="/persona") //path iniziale 
public class PersonaController {
@Autowired
private PersonaRepository personaRepository;
 
@PostMapping(path="/aggiungi") //aggiunge una persona
public @ResponseBody String addNewPersona (@RequestParam String name, @RequestParam String lastname, 
											@RequestParam String email, @RequestParam int eta){ 
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    Persona n = new Persona();
    n.setName(name);
    n.setLastname(lastname);
    n.setEmail(email);
    n.setEta(eta);
    personaRepository.save(n);
    return "Saved";
  }


@GetMapping(path="/visualizza") //visualizza tutto
public @ResponseBody Iterable<Persona> getAllPersone() { //ritorna nel corpo della pagina un iterable di utenti
  // This returns a JSON or XML with the users
  return personaRepository.findAll(); //find all corrisponde a select * from tabella
}


@GetMapping(path = "/pernome/{name}") //ricerca per nome
public @ResponseBody Iterable<Persona> getPersonaName(@PathVariable("name") String name) {
    return personaRepository.findByname(name);
}

@GetMapping(path = "/percognome/{lastname}") //ricerca per cognome
public @ResponseBody Iterable<Persona> getPersonaLastName(@PathVariable("lastname") String lastname) {
    return personaRepository.findBylastname(lastname);
}

@GetMapping(path = "/perid/{id}") //ricerca per id
public @ResponseBody Persona getPersonaId(@PathVariable Integer id) {
    return personaRepository.findByid(id);
}

@DeleteMapping(path="/rimuovi/{id}") //rimuovi per id 
public @ResponseBody String DeletePersonaId (@PathVariable(required = true) Integer id){ 
	if (personaRepository.existsById(id)) { //se esiste l'id
    personaRepository.deleteById(id);
    return "Removed";
	}
	else
	{
		return "Impossibile rimuovere, id inesistente";
	}
}

@PutMapping(path="/modifica") //simile all'aggiungi, gli cambio i parametri se esiste quell'id
public @ResponseBody String AlterPersona(@RequestParam Integer id, @RequestParam String name, @RequestParam String lastname, 
											@RequestParam String email, @RequestParam int eta) {
	if (personaRepository.existsById(id)) {
		Persona n = new Persona();
		n.setId(id);
	    n.setName(name);
	    n.setLastname(lastname);
	    n.setEmail(email);
	    n.setEta(eta);
	    personaRepository.save(n);
		return "Success";
		
	}
	else
	{
		return "Impossibile modifica, id inesistente";
	}
	
}
    

}
