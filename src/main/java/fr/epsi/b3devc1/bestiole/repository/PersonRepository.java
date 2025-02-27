package fr.epsi.b3devc1.bestiole.repository;

import fr.epsi.b3devc1.bestiole.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // 1️⃣ Retourne les personnes ayant le nom ou le prénom fourni en paramètre
    List<Person> findByLastnameOrFirstname(String lastname, String firstname);

    // 2️⃣ Retourne toutes les personnes ayant un âge supérieur ou égal au paramètre fourni
    List<Person> findByAgeGreaterThanEqual(int age);
}
