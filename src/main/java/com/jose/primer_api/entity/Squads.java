package com.jose.primer_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name="squads")
public class Squads {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String technology;
    
    // Constructor
    public Squads(){}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTechnology() { return technology; }
    public void setTechnology(String technology) { this.technology = technology; }
}
