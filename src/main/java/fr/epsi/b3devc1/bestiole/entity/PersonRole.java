package fr.epsi.b3devc1.bestiole.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "person_role")
public class PersonRole {
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // Getters & Setters
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
