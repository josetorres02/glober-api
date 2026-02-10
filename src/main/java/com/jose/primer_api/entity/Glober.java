package com.jose.primer_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "globers")
public class Glober {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // <--- Muchos Globers pertenecen a Una Squad
    @JoinColumn(name = "squad_id") // El nombre de la columna en la BD
    private Squads squad;

    private String name;
    private String email;
    private String seniority;
    private Double salary;

    // Constructor vacío (Obligatorio)
    public Glober() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Squads getSquad() {
        return squad;
    }

    public void setSquad(Squads squad) {
        this.squad = squad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}