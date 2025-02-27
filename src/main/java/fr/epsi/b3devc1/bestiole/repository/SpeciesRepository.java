package fr.epsi.b3devc1.bestiole.repository;

import fr.epsi.b3devc1.bestiole.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {
    List<Species> findByCommonNameContaining(String keyword);
}
