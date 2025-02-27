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

            // 🔹 Affichage des personnes en base
            List<Person> persons = personRepository.findAll();
            if (persons.isEmpty()) {
                System.out.println("Aucune personne trouvée.");
            } else {
                System.out.println("Liste des personnes :");
                persons.forEach(p -> System.out.println("- " + p.getFirstname() + " " + p.getLastname() + " (" + p.getAge() + " ans)"));
            }

            // 🔹 Ajout d'une espèce
            Species newSpecies = new Species();
            newSpecies.setCommonName("Tigre");
            newSpecies.setLatinName("Panthera tigris");
            speciesRepository.save(newSpecies);

            // 🔹 Ajout d'un animal
            Animal newAnimal = new Animal();
            newAnimal.setName("Rex");
            newAnimal.setColor("Orange et noir");
            newAnimal.setSex("MALE");
            newAnimal.setSpecies(newSpecies);
            animalRepository.save(newAnimal);
            System.out.println("\n✅ Nouvel animal ajouté : " + newAnimal.getName() + " (" + newAnimal.getColor() + ")");

            // 🔹 Test des nouvelles requêtes dans SpeciesRepository
            List<Species> orderedSpecies = speciesRepository.findAllOrderedByCommonName();
            System.out.println("\n📌 Espèces triées par nom commun :");
            orderedSpecies.forEach(s -> System.out.println("- " + s.getCommonName()));

            List<Species> speciesLike = speciesRepository.findByCommonNameLike("Tigre");
            if (speciesLike.isEmpty()) {
                System.out.println("\n🔍 Aucune espèce trouvée contenant 'Tigre' dans son nom commun.");
            } else {
                System.out.println("\n🔍 Espèces contenant 'Tigre' dans leur nom commun :");
                speciesLike.forEach(s -> System.out.println("- " + s.getCommonName()));
            }

            // 🔹 Test des nouvelles requêtes dans PersonRepository
            List<Person> peopleByAgeRange = personRepository.findByAgeBetween(20, 40);
            if (peopleByAgeRange.isEmpty()) {
                System.out.println("\n👤 Aucune personne trouvée entre 20 et 40 ans.");
            } else {
                System.out.println("\n👤 Personnes entre 20 et 40 ans :");
                peopleByAgeRange.forEach(p -> System.out.println("- " + p.getFirstname() + " " + p.getLastname()));
            }

            // 🔹 Test des nouvelles requêtes dans AnimalRepository
            long maleCount = animalRepository.countBySex("MALE");
            System.out.println("\n🦁 Nombre d'animaux mâles : " + maleCount);

            // 🔹 Test de la requête `existsByOwner`
            boolean isOwned = animalRepository.existsByOwner(newAnimal);
            System.out.println("\n📌 L'animal " + newAnimal.getName() + " appartient à quelqu'un ? " + (isOwned ? "Oui" : "Non"));

            // 🔹 Suppression d'un animal et vérification
            animalRepository.delete(newAnimal);
            System.out.println("\n❌ Animal supprimé : " + newAnimal.getName());

            long remainingAnimals = animalRepository.count();
            System.out.println("\n📊 Nombre total d'animaux restants : " + remainingAnimals);

            System.out.println("\n🏁 FIN DU PROGRAMME\n");
        };
    }
}
