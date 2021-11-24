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
@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*")
@RequestMapping(path="/persona")
public class PersonaController {
@Autowired
private PersonaRepository personaRepository;

@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*")
@PostMapping(path="/aggiungi")
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
@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*")
@GetMapping(path="/visualizza")
public @ResponseBody Iterable<Persona> getAllPersone() { //ritorna nel corpo della pagina un iterable di utenti
  // This returns a JSON or XML with the users
  return personaRepository.findAll(); //find all corrisponde a select * from tabella
}

@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*")
@GetMapping(path = "/pernome/{name}")
public @ResponseBody Iterable<Persona> getPersonaName(@PathVariable("name") String name) {
	System.out.println(name);
    return personaRepository.findByname(name);
}
@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*")
@GetMapping(path = "/percognome/{lastname}")
public @ResponseBody Iterable<Persona> getPersonaLastName(@PathVariable("lastname") String lastname) {
    return personaRepository.findBylastname(lastname);
}

@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*")
@DeleteMapping(path = "/delete/{id}")
public @ResponseBody void deletePersona(@PathVariable("id") Integer id) {
    personaRepository.deleteById(id);
}
@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*")
@PostMapping(path="/modifica") //simile all'aggiungi, gli cambio i parametri se esiste quell'id
public @ResponseBody String AlterPersona(@RequestParam Persona p) {
if (personaRepository.existsById(p.getId())) {
personaRepository.save(p);
return "Success";
}
else
{
return "Impossibile modifica, id inesistente";
}
}
@CrossOrigin(origins ="http://localhost:4200", allowedHeaders ="*")
@GetMapping(path = "/perid/{id}")
public @ResponseBody Persona getPersonaId(@PathVariable Integer id) {
	System.out.println(id);
    return personaRepository.findByid(id);
}



}
