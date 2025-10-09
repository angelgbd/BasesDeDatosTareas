/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.videojuegosjpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author angel
 */


@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pseudonimo", nullable = false, unique = true)
    private String pseudonimo;

    @Column(name = "sexo")
    private String sexo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechanacimiento")
    private Date fechaNacimiento;

    // Relación Uno a Uno con Direccion
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private Direccion direccion;

    // Relación Muchos a Muchos con Videojuego
    @ManyToMany
    @JoinTable(
        name = "videojuego_jugador",
        joinColumns = @JoinColumn(name = "jugador_id"),
        inverseJoinColumns = @JoinColumn(name = "videojuego_id")
    )
    private Set<Videojuego> videojuegos;

    // Constructores, Getters y Setters

    public Jugador() {
    }

    public Jugador(Long id, String pseudonimo, String sexo, Date fechaNacimiento, Direccion direccion, Set<Videojuego> videojuegos) {
        this.id = id;
        this.pseudonimo = pseudonimo;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.videojuegos = videojuegos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Set<Videojuego> getVideojuegos() {
        return videojuegos;
    }

    public void setVideojuegos(Set<Videojuego> videojuegos) {
        this.videojuegos = videojuegos;
    }
    
}
