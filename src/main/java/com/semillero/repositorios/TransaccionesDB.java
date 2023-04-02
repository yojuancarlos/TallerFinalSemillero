package com.semillero.repositorios;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.semillero.entidades.Transacciones;

import java.util.List;

public class TransaccionesDB implements Repositorio{
    private String cadenaConexion;
    public TransaccionesDB(){
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

                    "CREATE TABLE if NOT EXISTS TRANSACCIONES(\n" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "FECHA TEXT NOT NULL,\n" +
                    "HORA TEXT NOT NULL,\n" +
                    "TIPO_TRANSACCION TEXT NOT NULL,\n" +
                    "MONTO_REAL INTEGER NOT NULL,\n" +
                    "ID_CUENTA INTEGER NOT NULL,\n" +
                    "TIPO_CUENTA_DESTINO TEXT,\n" +
                    "FOREIGN KEY(ID_CUENTA) REFERENCES CUENTAS(ID)\n" +
                    ");";

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
            Transacciones Transacciones = (Transacciones) objeto;
            String sentenciaSql = "INSERT INTO Transacciones (FECHA, HORA, TIPO_TRANSACCION,MONTO_REAL,ID_CUENTA,TIPO_CUENTA_DESTINO) " +
            " VALUES('" + Transacciones.getFecha() + "', '" + Transacciones.getHora()
                         + "', '" + Transacciones.getTipo_transaccion() + "', " + Transacciones.getMonto()
                              + "," + Transacciones.getId_cuenta()
                              + ", '" + Transacciones.getTipo_cuenta_destino() + "');";
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }                   

    
    public void tranferencia(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Transacciones transacciones = (Transacciones) objeto;
            String sentenciaSql = "UPDATE Cuentas SET MONTO_REALL = '"
                    + transacciones.getMonto() + "' WHERE NUMERO_CUENTA = '" +"';";
                    java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }
   

    @Override
    public void eliminar(String identificacion) {
       
    }

    @Override
    public void actualizar(Object objeto) {
       
    }

    @Override
    public Object buscar(String ID_CUENTA) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM Transacciones WHERE ID_CUENTA = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, ID_CUENTA);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                Transacciones Transaccionesa = null;
                String FECHA = resultadoConsulta.getString("FECHA");
                String HORA = resultadoConsulta.getString("HORA");
                String TIPO_TRANSACCION = resultadoConsulta.getNString("TIPO_TRANSACCION");
                int MONTO = resultadoConsulta.getInt("MONTO_REAL");
                int TIPO_CUENTA_DESTINO = resultadoConsulta.getInt("TIPO_CUENTA_DESTINO");
                int ID_CUENTAResultado = resultadoConsulta.getInt("ID_CUENTA");
                

                

                Transaccionesa = new Transacciones(TIPO_CUENTA_DESTINO, FECHA, HORA, TIPO_TRANSACCION, MONTO, ID_CUENTAResultado, ID_CUENTA);
                return Transaccionesa;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
        List<Transacciones> Transaccioness = new ArrayList<Transacciones>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM TRANSACCIONES";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    Transacciones Transacciones = null;
                String FECHA = resultadoConsulta.getString("FECHA");
                String HORA = resultadoConsulta.getString("HORA");
                String TIPO_TRANSACCION = resultadoConsulta.getString("TIPO_TRANSACCION");
                int MONTO = resultadoConsulta.getInt("MONTO_REAL");
                int TIPO_CUENTA_DESTINO = resultadoConsulta.getInt("TIPO_CUENTA_DESTINO");
                int ID_CUENTAResultado = resultadoConsulta.getInt("ID_CUENTA");

                    Transacciones = new Transacciones(ID_CUENTAResultado, FECHA, HORA, TIPO_TRANSACCION, MONTO, ID_CUENTAResultado, TIPO_TRANSACCION);
                    Transaccioness.add(Transacciones);
                }
                return Transaccioness;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
        
}
}