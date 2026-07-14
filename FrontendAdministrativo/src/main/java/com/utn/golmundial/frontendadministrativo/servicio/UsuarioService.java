package com.utn.golmundial.frontendadministrativo.servicio;

import com.utn.golmundial.frontendadministrativo.modelo.Usuario;

public interface UsuarioService {

    /**
     * Intenta autenticar. Devuelve el Usuario si las credenciales son
     * correctas, o null si no lo son.
     */
    Usuario autenticar(String username, String password);
}