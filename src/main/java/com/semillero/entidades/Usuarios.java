package com.semillero.entidades;

public class Usuarios {
    protected int id;
    protected String nombre;
    protected String apellido;
    protected int cedula;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getCedula() {
        return cedula;
    }
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    public Usuarios( String nombre, String apellido, int cedula) {
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
    }
    
}
