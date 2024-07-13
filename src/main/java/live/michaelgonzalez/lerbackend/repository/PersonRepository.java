package live.michaelgonzalez.lerbackend.repository;

import live.michaelgonzalez.lerbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
