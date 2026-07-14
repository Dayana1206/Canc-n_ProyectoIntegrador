package com.utn.golmundial.frontendadministrativo.bean;

import com.utn.golmundial.frontendadministrativo.modelo.Usuario;
import com.utn.golmundial.frontendadministrativo.servicio.UsuarioService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private Usuario usuarioAutenticado;

    @Inject
    private UsuarioService usuarioService;

    public String login() {
        Usuario usuario = usuarioService.autenticar(username, password);

        if (usuario == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Credenciales incorrectas", "Usuario o contraseña inválidos"));
            return null; // se queda en login.xhtml
        }

        this.usuarioAutenticado = usuario;
        return "/admin/dashboard.xhtml?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    // Getters y setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}