package com.semillero.servicios;

import java.util.List;
import java.util.Map;

import com.semillero.entidades.Cuentas;

import com.semillero.repositorios.CuentaDB;
import com.semillero.repositorios.Repositorio;

public class CuentaServicio {
    private static Repositorio CuentaRepositorio;

    public CuentaServicio() {
        CuentaRepositorio = new CuentaDB();
    }

    
    public void guardarCuenta(Map datos) {
        //int id_cuentas = (int) datos.get("id_cuentas");
        String numeroCuenta = (String) datos.get("numeroCuenta");
        int saldo = (int) datos.get("saldo");
        Integer id_usuario = (Integer) datos.get("id_usuario");
        String tipo = (String) datos.get("tipo");

        Cuentas newPerson = new Cuentas( numeroCuenta, saldo, id_usuario, tipo);
        CuentaRepositorio.guardar(newPerson);
    }

    public static List<Cuentas> listarCuenta() {
        
        return (List<Cuentas>) CuentaRepositorio.listar();
    }

    public Cuentas buscarcCuentas(String numeroCuenta) throws Exception {
        Object Cuenta = CuentaRepositorio.buscar(numeroCuenta);
        if (Cuenta == null) {
            throw new Exception("No se encontro la Cuenta");
        }
        return (Cuentas) Cuenta;
    }

    public void eliminarCuenta(String identificador) {
        CuentaRepositorio.eliminar(identificador);
    }

    public static void actualizarCuenta(Map datos) {
        int id_cuentas = (int) datos.get("id_cuentas");
        String numeroCuenta = (String) datos.get("numeroCuenta");
        int saldo = (int) datos.get("saldo");
        Integer id_usuario = (Integer) datos.get("id_usuario");
        String tipo = (String) datos.get("tipo");


        Cuentas newPerson = new Cuentas( numeroCuenta, saldo, id_usuario, tipo);
        CuentaRepositorio.actualizar(newPerson);
    }

    public void actualizarCuentaId(Map datos, String id) {
        int id_cuentas = (int) datos.get("id_cuentas");
        String numeroCuenta = (String) datos.get("numeroCuenta");
        int saldo = (int) datos.get("saldo");
        Integer id_usuario = (Integer) datos.get("id_usuario");
        String tipo = (String) datos.get("tipo");

        Cuentas newPerson = new Cuentas( numeroCuenta, saldo, id_usuario, tipo);
        CuentaRepositorio.actualizarId(newPerson, id);
    }
}
