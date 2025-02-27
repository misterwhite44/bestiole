package fr.epsi.b3devc1.bestiole.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "person_role")
public class PersonRole {
    @Id
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

