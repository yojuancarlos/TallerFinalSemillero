package com.semillero.entidades;



public   class Cuentas {
    protected int id_cuentas;
	protected String numeroCuenta;
	protected  int saldo;
	protected  Integer id_usuario;
	protected String tipo;

	
    
   

   

   

    public int getId_cuentas() {
        return id_cuentas;
    }

    public void setId_cuentas(int id_cuentas) {
        this.id_cuentas = id_cuentas;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuentas( String numeroCuenta, int saldo, Integer id_usuario, String tipo) {
        
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.id_usuario = id_usuario;
        this.tipo = tipo;
    }

   
	
}
