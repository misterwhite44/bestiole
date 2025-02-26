package fr.epsi.b3devc1.bestiole;

import fr.epsi.b3devc1.bestiole.entity.Person;
import fr.epsi.b3devc1.bestiole.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BestioleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestioleApplication.class, args);
    }

    @Bean
    CommandLineRunner displayData(PersonRepository personRepository) {
        return args -> {
            // Utilisation de findAll() pour récupérer toutes les personnes
            List<Person> persons = personRepository.findAll();

            if (persons.isEmpty()) {
                System.out.println("📌 Aucune personne trouvée en base de données.");
            } else {
                System.out.println("📋 Liste des personnes :");
                for (Person person : persons) {
                    System.out.println(person.getFirstname() + " " + person.getLastname() + " (" + person.getAge() + " ans)");
                }
            }
        };
    }
}

