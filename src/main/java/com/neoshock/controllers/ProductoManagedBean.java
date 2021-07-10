package com.neoshock.controllers;

import com.neoshock.models.ProductoModel;
import com.neoshock.models.ProductoDAO;
import com.neoshock.models.UsuarioModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;

@Named(value = "productoMB")
@SessionScoped
public class ProductoManagedBean implements Serializable {

    private ProductoDAO productoDAO = new ProductoDAO();
    private List<ProductoModel> productos = new ArrayList<>();
    private ProductoModel producto;
    private UsuarioModel usuario;

    private FacesContext facesContext = FacesContext.getCurrentInstance();
    private HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo() {
        addMessage(FacesMessage.SEVERITY_INFO, "Atencion", "Registro Exitoso");
    }
    
     public void showInfo(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, "Atencion", message);
    }

    public void showWarn(String message) {
        addMessage(FacesMessage.SEVERITY_WARN, "Error", message);
    }

    @PostConstruct
    public void llenarDatos() {
        usuario = (UsuarioModel) session.getAttribute("usuario");
        producto = new ProductoModel();
        productos = productoDAO.getProductos();
    }

    public void actualizarLista() {
        productos = productoDAO.getProductos();
    }

    public List<ProductoModel> getProductos() {
        return productos;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public void registrarProducto() {
        if (!producto.getNombre().isEmpty()) {
            if (productoDAO.registrarProducto(producto,usuario)) {
                showInfo();
                producto = new ProductoModel();
            } else {
                showWarn("Error al ingresar los datos");
            }
        } else {
            showWarn("Debe ingresar un nombre para el producto");
        }
    }
    
    public void borrarProducto(){
        if(productoDAO.borrarProducto(producto)){
            showInfo("Elemento eliminado correctamente");
            productos.remove(producto);
        }else{
            showWarn("Error no se pudo eliminar los datos");
        }
    }

    public void onRowEdit(RowEditEvent<ProductoModel> event) {
        FacesMessage msg = new FacesMessage("Cambios realizados", String.valueOf(event.getObject().getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        producto = event.getObject();
        if (productoDAO.editarProducto(producto)) {
            producto = new ProductoModel();
        } else {
            showWarn("Error al actualizar los datos");
        }
    }

}
