package com.semillero.repositorios;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;


import com.semillero.entidades.Cuentas;

public class CuentaDB implements Repositorio {
    private String cadenaConexion;
    public CuentaDB(){
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            cadenaConexion="jdbc:sqlite:banco.db";
            creartabla();
        } catch (Exception e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }
    }
    public  void creartabla() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            String cadenaConexion = "jdbc:sqlite:banco.db";
            String sql = 
                    "CREATE TABLE  if NOT EXISTS CUENTAS(\n" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "NUMERO_CUENTA TEXT NOT NULL UNIQUE,\n" +
                    "SALDO_REAL INTEGER NOT NULL,\n" +
                    "TIPO_CUENTA TEXT NOT NULL,\n" +
                    "ID_USUARIO INTEGER NOT NULL,\n" +
                    "FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID)\n" +
                    ");\n";

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
            Cuentas Cuentas = (Cuentas) objeto;
            String sentenciaSql = "INSERT INTO CUENTAS (NUMERO_CUENTA, SALDO_REAL, TIPO_CUENTA,ID_USUARIO) " +
            " VALUES('" + Cuentas.getNumeroCuenta() + "', " + Cuentas.getSaldo()
                     + ",'" + Cuentas.getTipo()
                     + "', " + Cuentas.getId_usuario() + ");";
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String NUMERO_CUENTA) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE on cascade FROM Cuentas WHERE NUMERO_CUENTA = '" + NUMERO_CUENTA + "';";
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
            Cuentas Cuentas = (Cuentas) objeto;
            String sentenciaSql = "UPDATE Cuentas SET NUMERO_CUENTA, = '" + Cuentas.getNumeroCuenta() + "', SALDO REAL = '"
                    + Cuentas.getSaldo() + "', TIPO_CUENTA = " + Cuentas.getTipo() + ", ID_USUARIO = '"
                    + Cuentas.getId_usuario() + "' WHERE NUMERO_CUENTA = '" + Cuentas.getNumeroCuenta() + "';";
                    java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public Object buscar(String NUMERO_CUENTA) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM Cuentas WHERE NUMERO_CUENTA = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, NUMERO_CUENTA);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                Cuentas Cuentas = null;
                String NUMEROCUENTA = resultadoConsulta.getString("NUMERO_CUENTA");
                Integer SALDOREAL = resultadoConsulta.getInt("SALDO_REAL");
                String TIPOCUENTA = resultadoConsulta.getString("TIPO_CUENTA");
                Integer IDcuenta = resultadoConsulta.getInt("ID_USUARIO");
                
                Cuentas = new Cuentas( NUMEROCUENTA, SALDOREAL, IDcuenta, TIPOCUENTA);
                
                return Cuentas;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
        List<Cuentas> Cuentass = new ArrayList<Cuentas>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM CUENTAS";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    Cuentas Cuentas = null;
                    int id = resultadoConsulta.getInt("id");
                    String NUMEROCUENTA = resultadoConsulta.getString("NUMERO_CUENTA");
                Integer SALDOREAL = resultadoConsulta.getInt("SALDO_REAL");
                String TIPOCUENTA = resultadoConsulta.getString("TIPO_CUENTA");
                Integer IDUSUARIO = resultadoConsulta.getInt("ID_USUARIO");

                    Cuentas = new Cuentas( NUMEROCUENTA, SALDOREAL, IDUSUARIO, TIPOCUENTA);
                    Cuentas.setId_cuentas(id);
                    Cuentass.add(Cuentas);
                }
                return Cuentass;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public void actualizarId(Object objeto, String id) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Cuentas Cuentas = (Cuentas) objeto;
            String sentenciaSql = "UPDATE Cuentas SET NUMERO_CUENTA = '" + Cuentas.getNumeroCuenta() + "', SALDO REAL = "
                    + Cuentas.getSaldo() + ", TIPO_CUENTA = '" + Cuentas.getTipo()  + "' WHERE id = " + id
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
