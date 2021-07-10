package com.neoshock.controllers;

import com.neoshock.models.UsuarioDAO;
import com.neoshock.models.UsuarioModel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named(value = "usuarioMB")
@SessionScoped
public class UsuarioManagedBean implements Serializable {

    private UsuarioModel usuario;
    private UsuarioDAO usuarioDAO;

    private FacesContext facesContext = FacesContext.getCurrentInstance();
    private HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", message);
    }

    public void showWarn(String message) {
        addMessage(FacesMessage.SEVERITY_WARN, "Error", message);
    }

    @PostConstruct
    public void iniciarDatos() {
        usuario = new UsuarioModel();
        usuarioDAO = new UsuarioDAO();
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public String setDataLogin() {
        if (!usuario.getUserName().isEmpty() && !usuario.getPasswordUser().isEmpty()) {
            UsuarioModel credential = usuarioDAO.loginUser(usuario);
            if (credential.getUserName() == "Credenciales incorrectas") {
                showWarn(credential.getUserName());
                return "";
            } else {
                showInfo(credential.getUserName());
                session.setAttribute("usuario", credential);
                return "Pages/producto";
            }
        } else {
            return "";
        }
    }

    public String logOutUser() {
        session.removeAttribute("usuario");
        usuarioDAO = new UsuarioDAO();
        usuario = new UsuarioModel();
        return "/index";
    }
}
