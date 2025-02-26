package fr.epsi.b3devc1.bestiole.repository;

import fr.epsi.b3devc1.bestiole.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findBySpecies(String species);
}
