package live.michaelgonzalez.lerbackend.controller;

import live.michaelgonzalez.lerbackend.model.Person;
import live.michaelgonzalez.lerbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    private ResponseEntity<List<Person>> getAllPersons() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("{id}")
    private ResponseEntity<Optional<Person>> getPersonById(@PathVariable("id") Long id) {
        Optional<Person> person = personService.findById(id);
        return person.isPresent()
                ? ResponseEntity.ok(person)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<Person> createPerson(@RequestBody Person person) {
        try {
            Person newPerson = personService.save(person);
            return ResponseEntity.created(new URI("/persons/" + newPerson.getId())).body(newPerson);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("{id}")
    private ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, @RequestBody Person newPerson) {
        try {
            Optional<Person> person = personService.findById(id);
            if (person.isPresent()) {
                person.get().setName(newPerson.getName());
                person.get().setIdentificationNumber(newPerson.getIdentificationNumber());
                person.get().setPhone(newPerson.getPhone());
                person.get().setEmail(newPerson.getEmail());
                person.get().setBirthdate(newPerson.getBirthdate());
                return ResponseEntity.ok(personService.save(person.get()));
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Person> deletePerson(@PathVariable("id") Long id) {
        boolean deleted = personService.deleteById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.badRequest().build();
    }

}
