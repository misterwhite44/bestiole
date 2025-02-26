package fr.epsi.b3devc1.bestiole.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "person_animals")
public class PersonAnimals {
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "animals_id")
    private Animal animal;

    // Getters & Setters
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }

    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }
}
