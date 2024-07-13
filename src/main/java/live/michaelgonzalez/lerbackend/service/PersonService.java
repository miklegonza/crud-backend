package live.michaelgonzalez.lerbackend.service;

import live.michaelgonzalez.lerbackend.model.Person;
import live.michaelgonzalez.lerbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public <S extends Person> S save(S entity) {
        return personRepository.save(entity);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long aLong) {
        return personRepository.findById(aLong);
    }

    public Boolean deleteById(Long id) {
        boolean personExists = personRepository.existsById(id);
        if (personExists)
            personRepository.deleteById(id);
        return personExists;
    }

    public void delete(Person entity) {
        personRepository.delete(entity);
    }

}
