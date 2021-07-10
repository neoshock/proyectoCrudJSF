/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoshock.models;

import com.neoshock.config.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pideu
 */
public class UsuarioDAO {

    private Conexion conexion = new Conexion();
    private Statement statement;
    private ResultSet resultSet;
    private Connection connection;

    private List<UsuarioModel> usuarios = new ArrayList<>();

    public UsuarioModel loginUser(UsuarioModel usuario) {
        String sql = String.format("select * from public.\"Users\" where user_name = '%1$s' and "
                + "password_user = '%2$s'", usuario.getUserName(), usuario.getPasswordUser());
        UsuarioModel resultado = new UsuarioModel();
        try {
            connection = conexion.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                resultado = new UsuarioModel(resultSet.getLong("id_user"), resultSet.getString("user_name"), 
                        resultSet.getString("first_name"), resultSet.getString("last_name"), 
                        resultSet.getString("email_user"), resultSet.getString("detail_user"));
                usuarios.add(resultado);
            }
            if (usuarios.size() == 0) {
                resultado = new UsuarioModel();
                resultado.setUserName("Credenciales incorrectas");
            }
            return resultado;
        } catch (Exception e) {
            resultado.setUserName(e.getMessage());
            return resultado;
        }
    }
}
