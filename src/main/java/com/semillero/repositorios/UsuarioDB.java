package com.semillero.repositorios;

import java.util.List;

import com.semillero.entidades.Usuarios;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDB implements Repositorio {
    private String cadenaConexion;

    public UsuarioDB() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            cadenaConexion = "jdbc:sqlite:banco.db";
            creartabla();
        } catch (Exception e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }
    }

    public void creartabla() {
        try {

            DriverManager.registerDriver(new org.sqlite.JDBC());
            String cadenaConexion = "jdbc:sqlite:banco.db";
            String sql = "CREATE TABLE if NOT EXISTS USUARIOS(id INT PRIMARY KEY AUTOINCREMENT,nombre VARCHAR(50),apellido VARCHAR(50),cedula INT NOT NULL UNIQUE );";

           

            Connection conexion = DriverManager.getConnection(cadenaConexion);
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);

        } catch (SQLException e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }
    }

    @Override
    public void guardar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Usuarios Usuarios = (Usuarios) objeto;

            String sentenciaSql = "INSERT INTO USUARIOS(nombre, apellido, cedula) " +
                    " VALUES('" + Usuarios.getNombre() + "', '" + Usuarios.getApellido()
                    + "', '" + Usuarios.getCedula() + "');";
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String cedula) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM Usuarios WHERE cedula = '" + cedula + "';";
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Usuarios Usuarios = (Usuarios) objeto;
            String sentenciaSql = "UPDATE Usuarios SET  nombre = '" + Usuarios.getNombre() + "', apellido = '"
                    + Usuarios.getApellido() + "'where  cedula = " + Usuarios.getCedula() + ";";
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public Object buscar(String cedula) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM Usuarios WHERE cedula = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, cedula);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                Usuarios Usuarios = null;
                String nombre = resultadoConsulta.getString("nombre");
                String apellido = resultadoConsulta.getString("apellido");

                int cedulaResultado = resultadoConsulta.getInt("cedula");

                Usuarios = new Usuarios(nombre, apellido, cedulaResultado);
                return Usuarios;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
        List<Usuarios> Usuarioss = new ArrayList<Usuarios>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM Usuarios";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    Usuarios Usuarios = null;
                    int id = resultadoConsulta.getInt("id");
                    String nombre = resultadoConsulta.getString("nombre");
                    String apellido = resultadoConsulta.getString("apellido");

                    int cedula = resultadoConsulta.getInt("cedula");

                    Usuarios = new Usuarios(nombre, apellido, cedula);
                    Usuarios.setId(id);
                    Usuarioss.add(Usuarios);
                }
                return Usuarioss;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Usuarios Usuarios = (Usuarios) objeto;
            String sentenciaSql = "UPDATE Usuarios SET nombre = '" + Usuarios.getNombre() + "', apellido = '"
                    + Usuarios.getApellido() + "', CEDULA = " + Usuarios.getCedula() + "' WHERE id = " + id
                    + ";";
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

}
