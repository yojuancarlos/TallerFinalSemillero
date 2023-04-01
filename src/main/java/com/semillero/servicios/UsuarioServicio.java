package com.semillero.servicios;

import java.util.List;
import java.util.Map;

import com.semillero.entidades.Cuentas;
import com.semillero.entidades.Usuarios;
import com.semillero.repositorios.CuentaDB;
import com.semillero.repositorios.Repositorio;

public class UsuarioServicio {
    private Repositorio usuarioRepositorio;




    
    

    public UsuarioServicio() {
        usuarioRepositorio = new CuentaDB();
    }

    protected int id;
    protected String nombre;
    protected String apellido;
    protected int cedula;
    public void guardarUsuario(Map datos) {
        int id = (int) datos.get("id");
        String nombre = (String) datos.get("nombre");
        
        String apellido = (String) datos.get("apellido");
        int cedula = (int) datos.get("cedula");
        

        Usuarios newPerson = new Usuarios(id, nombre, apellido, cedula);
        usuarioRepositorio.guardar(newPerson);
    }

    public List<Usuarios> listarusuario() {
        
        return (List<Usuarios>) usuarioRepositorio.listar();
    }

    public Usuarios buscarcUsuarios(String cedula) throws Exception {
        Object usuario = usuarioRepositorio.buscar(cedula);
        if (usuario == null) {
            throw new Exception("No se encontro la Cuenta");
        }
        return (Usuarios) usuario;
    }

    public void eliminarusuario(String cedula) {
        usuarioRepositorio.eliminar(cedula);
    }

    public void actualizarusuario(Map datos) {
        int id = (int) datos.get("id");
        String nombre = (String) datos.get("nombre");
        
        String apellido = (String) datos.get("apellido");
        int cedula = (int) datos.get("cedula");


        Usuarios newPerson = new Usuarios(id, nombre, apellido, cedula);
        usuarioRepositorio.actualizar(newPerson);
    }

    public void actualizarUsuarioId(Map datos, String id_Usuario) {
        int id = (int) datos.get("id");
        String nombre = (String) datos.get("nombre");
        
        String apellido = (String) datos.get("apellido");
        int cedula = (int) datos.get("cedula");


        Usuarios newPerson = new Usuarios(id, nombre, apellido, cedula);
        usuarioRepositorio.actualizarId(newPerson, id_Usuario);
    }
}
