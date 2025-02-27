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

            System.out.println("\nDÉMARRAGE DE L'APPLICATION BESTIOLE\n");

            List<Person> persons = personRepository.findAll();
            if (persons.isEmpty()) {
                System.out.println("Aucune personne trouvée en base de données.");
            } else {
                System.out.println("Liste des personnes :");
                persons.forEach(p -> System.out.println("- " + p.getFirstname() + " " + p.getLastname() + " (" + p.getAge() + " ans)"));
            }

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

            System.out.println("\nNouvel animal ajouté : " + newAnimal.getName() + " (" + newAnimal.getColor() + ")\n");

            Optional<Animal> foundAnimal = animalRepository.findById(newAnimal.getId());
            foundAnimal.ifPresentOrElse(
                    animal -> System.out.println("🔍 Animal trouvé : " + animal.getName() + " (" + animal.getColor() + ")"),
                    () -> System.out.println("Aucun animal trouvé avec cet ID.")
            );


            Optional<Species> foundSpecies = speciesRepository.findFirstByCommonName("Tigre");
            foundSpecies.ifPresent(species -> System.out.println("Espèce trouvée : " + species.getCommonName() + " (" + species.getLatinName() + ")"));

            List<Species> speciesList = speciesRepository.findByLatinNameIgnoreCaseContaining("panthera");
            System.out.println("\nEspèces contenant 'panthera' dans leur nom latin :");
            speciesList.forEach(s -> System.out.println("- " + s.getCommonName() + " (" + s.getLatinName() + ")"));

            List<Person> peopleByName = personRepository.findByLastnameOrFirstname("Dupont", "Alice");
            System.out.println("\nPersonnes avec nom 'Dupont' ou prénom 'Alice' :");
            peopleByName.forEach(p -> System.out.println("- " + p.getFirstname() + " " + p.getLastname()));

            List<Person> peopleByAge = personRepository.findByAgeGreaterThanEqual(30);
            System.out.println("\nPersonnes âgées de 30 ans ou plus :");
            peopleByAge.forEach(p -> System.out.println("- " + p.getFirstname() + " " + p.getLastname() + " (" + p.getAge() + " ans)"));

            List<Animal> animalsBySpecies = animalRepository.findBySpecies(newSpecies);
            System.out.println("\nAnimaux de l'espèce " + newSpecies.getCommonName() + " :");
            animalsBySpecies.forEach(a -> System.out.println("- " + a.getName() + " (" + a.getColor() + ")"));

            List<Animal> animalsByColor = animalRepository.findByColorIn(List.of("Orange et noir", "Blanc"));
            System.out.println("\nAnimaux avec une couleur spécifique :");
            animalsByColor.forEach(a -> System.out.println("- " + a.getName() + " (" + a.getColor() + ")"));

            // 🟢 5. Supprimer un animal et vérifier la suppression
            animalRepository.delete(newAnimal);
            System.out.println("\nAnimal supprimé : " + newAnimal.getName());

            long remainingAnimals = animalRepository.count();
            System.out.println("\nNombre total d'animaux restants : " + remainingAnimals);

            System.out.println("\nFIN DU PROGRAMME\n");
        };
    }
}
