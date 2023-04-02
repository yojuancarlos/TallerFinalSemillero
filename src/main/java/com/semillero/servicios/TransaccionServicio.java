package com.semillero.servicios;
import java.util.List;
import java.util.Map;

import com.semillero.entidades.Transacciones;
import com.semillero.repositorios.Repositorio;
import com.semillero.repositorios.TransaccionesDB;
public class TransaccionServicio {
    private static Repositorio transacccionRepositorio;

    public TransaccionServicio() {
        transacccionRepositorio = new TransaccionesDB();
    }

    
    public void guardartransaccion(Map datos) {
        
        String fecha = (String) datos.get("fecha");
        String hora = (String) datos.get("hora");
        String tipo_transaccion = (String) datos.get("tipo_transaccion");
        int monto = (int) datos.get("monto");
        int id_cuenta = (int) datos.get("id_cuenta");
        String tipo_cuenta_destino = (String) datos.get("tipo_cuenta_destino");
    


        Transacciones newPerson = new Transacciones(id_cuenta, fecha, hora, tipo_transaccion, monto, id_cuenta, tipo_cuenta_destino);
        transacccionRepositorio.guardar(newPerson);
    }

    public static List<Transacciones> listartTransacciones() {
        
        return (List<Transacciones>) transacccionRepositorio.listar();
    }

    public Transacciones buscarctTransacciones(String numeroCuenta) throws Exception {
        Object transaccion = transacccionRepositorio.buscar(numeroCuenta);
        if (transaccion == null) {
            throw new Exception("No se encontro la Cuenta");
        }
        return (Transacciones) transaccion;
    }

    public Transacciones depositar(String monto) throws Exception {
        Object transaccion = transacccionRepositorio.buscar(monto);
        if (transaccion == null) {
            throw new Exception("No se encontro la Cuenta");
        }
        return (Transacciones) transaccion;
    }

    public void eliminartransacciones(String identificador) {
        transacccionRepositorio.eliminar(identificador);
    }

    public static void actualizartramsacciones(Map datos) {
        String fecha = (String) datos.get("fecha");
        String hora = (String) datos.get("hora");
        String tipo_transaccion = (String) datos.get("tipo_transaccion");
        int monto = (int) datos.get("monto");
        int id_cuenta = (int) datos.get("id_cuenta");
        String tipo_cuenta_destino = (String) datos.get("tipo_cuenta_destino");
    


        Transacciones newPerson = new Transacciones(id_cuenta, fecha, hora, tipo_transaccion, monto, id_cuenta, tipo_cuenta_destino);
        transacccionRepositorio.actualizar(newPerson);
    }

    public void actualizartransaccionId(Map datos, String id) {
        String fecha = (String) datos.get("fecha");
        String hora = (String) datos.get("hora");
        String tipo_transaccion = (String) datos.get("tipo_transaccion");
        int monto = (int) datos.get("monto");
        int id_cuenta = (int) datos.get("id_cuenta");
        String tipo_cuenta_destino = (String) datos.get("tipo_cuenta_destino");
    


        Transacciones newPerson = new Transacciones(id_cuenta, fecha, hora, tipo_transaccion, monto, id_cuenta, tipo_cuenta_destino);
        transacccionRepositorio.actualizarId(newPerson,id);
    }
}
