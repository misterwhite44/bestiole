package fr.epsi.b3devc1.bestiole;

import fr.epsi.b3devc1.bestiole.entity.Animal;
import fr.epsi.b3devc1.bestiole.entity.Person;
import fr.epsi.b3devc1.bestiole.entity.Species;
import fr.epsi.b3devc1.bestiole.repository.AnimalRepository;
import fr.epsi.b3devc1.bestiole.repository.PersonRepository;
import fr.epsi.b3devc1.bestiole.repository.SpeciesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BestioleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestioleApplication.class, args);
    }

    @Bean
    CommandLineRunner displayData(
            PersonRepository personRepository,
            AnimalRepository animalRepository,
            SpeciesRepository speciesRepository) {
        return args -> {

            // 🟢 1. Afficher toutes les personnes
            List<Person> persons = personRepository.findAll();
            if (persons.isEmpty()) {
                System.out.println("📌 Aucune personne trouvée en base de données.");
            } else {
                System.out.println("📋 Liste des personnes :");
                persons.forEach(p -> System.out.println("- " + p.getFirstname() + " " + p.getLastname() + " (" + p.getAge() + " ans)"));
            }

            // 🟢 2. Ajouter une nouvelle espèce et un animal
            Species newSpecies = new Species();
            newSpecies.setCommonName("Tigre");
            newSpecies.setLatinName("Panthera tigris");
            speciesRepository.save(newSpecies);

            Animal newAnimal = new Animal();
            newAnimal.setName("Rex");
            newAnimal.setColor("Orange et noir");
            newAnimal.setSex("MALE");
            newAnimal.setSpecies(newSpecies);
            animalRepository.save(newAnimal);

            System.out.println("✅ Nouvel animal ajouté : " + newAnimal.getName() + " (" + newAnimal.getColor() + ")");

            // 🟢 3. Trouver un animal par ID
            Optional<Animal> foundAnimal = animalRepository.findById(newAnimal.getId());
            foundAnimal.ifPresentOrElse(
                    animal -> System.out.println("🔍 Animal trouvé : " + animal.getName() + " (" + animal.getColor() + ")"),
                    () -> System.out.println("❌ Aucun animal trouvé avec cet ID.")
            );

            // 🟢 4. Supprimer un animal et vérifier la suppression
            animalRepository.delete(newAnimal);
            System.out.println("🗑️ Animal supprimé : " + newAnimal.getName());

            long remainingAnimals = animalRepository.count();
            System.out.println("📌 Nombre total d'animaux restants : " + remainingAnimals);
        };
    }
}
