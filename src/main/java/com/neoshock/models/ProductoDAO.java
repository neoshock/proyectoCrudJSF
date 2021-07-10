package com.neoshock.models;

import com.neoshock.config.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Conexion conexion = new Conexion();
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;

    private List<ProductoModel> productos = new ArrayList<>();

    public List<ProductoModel> getProductos() {
        String sql = String.format("select * from public.\"Products\" where habilitado = 'true'");
        productos = new ArrayList<>();
        try {
            connection = conexion.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                productos.add(new ProductoModel(resultSet.getLong("id"), resultSet.getString("nombre"), 
                        resultSet.getString("descripcion")));
            }
        } catch (Exception e) {
            productos.add(new ProductoModel(1l, "Error", e.getMessage()));
        }
        return productos;
    }

    public boolean registrarProducto(ProductoModel producto, UsuarioModel usuarioModel) {
        String sql = String.format("INSERT INTO public.\"Products\"(nombre, descripcion, id_user,habilitado) "
                + "VALUES ('%1$s', '%2$s',%3$d,'true')", producto.getNombre(), producto.getDescripcion(), 
                usuarioModel.getUserId());
        try {
            connection = conexion.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean editarProducto(ProductoModel producto) {
        String sql = String.format("UPDATE public.\"Products\" SET nombre='%1$s', descripcion='%2$s' WHERE id = %3$d", 
                producto.getNombre(), producto.getDescripcion(), producto.getId());
        try {
            connection = conexion.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean borrarProducto(ProductoModel producto) {
        String sql = String.format("UPDATE public.\"Products\" SET habilitado= 'false' WHERE id = '%1$d'",
                producto.getId());
        try {
            connection = conexion.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
