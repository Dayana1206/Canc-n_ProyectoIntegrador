package com.utn.golmundial.frontendadministrativo.servicio;

import com.utn.golmundial.frontendadministrativo.modelo.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioServiceMock implements UsuarioService {

    @Override
    public Usuario autenticar(String username, String password) {
        // Datos de prueba tomados del seed del proyecto.
        // TODO: reemplazar por una llamada REST real cuando el backend esté listo.
        if ("admin".equals(username) && "admin123".equals(password)) {
            return new Usuario(1L, "admin", "Administrador del Torneo", "ADMINISTRADOR");
        }
        return null;
    }
}