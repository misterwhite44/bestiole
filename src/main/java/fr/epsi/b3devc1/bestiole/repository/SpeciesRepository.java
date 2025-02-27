package fr.epsi.b3devc1.bestiole.repository;

import fr.epsi.b3devc1.bestiole.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {

    // 1️⃣ Retourne la première espèce dont le nom commun correspond au paramètre fourni
    Optional<Species> findFirstByCommonName(String commonName);

    // 2️⃣ Retourne une liste d'espèces dont le nom latin contient le paramètre fourni (ignorer la casse)
    List<Species> findByLatinNameIgnoreCaseContaining(String keyword);
}
