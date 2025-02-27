package fr.epsi.b3devc1.bestiole.repository;

import fr.epsi.b3devc1.bestiole.entity.Animal;
import fr.epsi.b3devc1.bestiole.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // 1️⃣ Retourne tous les animaux appartenant à la Species fournie en paramètre
    List<Animal> findBySpecies(Species species);

    // 2️⃣ Retourne tous les animaux dont la couleur fait partie de la liste fournie
    List<Animal> findByColorIn(List<String> colors);
}
