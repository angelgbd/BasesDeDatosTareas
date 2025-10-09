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

/**
 *
 * @author angel
 */
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "videojuego")
public class Videojuego implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "desarrolladora")
    private String desarrolladora;

    @Column(name = "puntaje")
    private Integer puntaje;

    // Relación Uno a Muchos con Logro
    @OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Logro> logros;

    // Lado inverso de la relación Muchos a Muchos con Jugador
    @ManyToMany(mappedBy = "videojuegos")
    private Set<Jugador> jugadores;

    // Constructores, Getters y Setters

    public Videojuego(Long id, String nombre, String desarrolladora, Integer puntaje, Set<Logro> logros, Set<Jugador> jugadores) {
        this.id = id;
        this.nombre = nombre;
        this.desarrolladora = desarrolladora;
        this.puntaje = puntaje;
        this.logros = logros;
        this.jugadores = jugadores;
    }

    public Videojuego() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Set<Logro> getLogros() {
        return logros;
    }

    public void setLogros(Set<Logro> logros) {
        this.logros = logros;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
}
