package com.utn.golmundial.frontendadministrativo.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Long id;
    private String username;
    private String nombre;
    private String rol; // "ADMINISTRADOR", "USUARIO" o "INVITADO"

    public Usuario() {
    }

    public Usuario(Long id, String username, String nombre, String rol) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}