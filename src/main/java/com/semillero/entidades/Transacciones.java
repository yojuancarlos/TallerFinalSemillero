package com.semillero.entidades;

public class Transacciones {
    protected int id;
    protected String fecha;
    protected String hora;
    protected String tipo_transaccion;
    protected int monto;
    protected int id_cuenta;
    protected String tipo_cuenta_destino;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getTipo_transaccion() {
        return tipo_transaccion;
    }
    public void setTipo_transaccion(String tipo_transaccion) {
        this.tipo_transaccion = tipo_transaccion;
    }
    public int getMonto() {
        return monto;
    }
    public void setMonto(int monto) {
        this.monto = monto;
    }
    public int getId_cuenta() {
        return id_cuenta;
    }
    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }
    public String getTipo_cuenta_destino() {
        return tipo_cuenta_destino;
    }
    public void setTipo_cuenta_destino(String tipo_cuenta_destino) {
        this.tipo_cuenta_destino = tipo_cuenta_destino;
    }
    public Transacciones(int id, String fecha, String hora, String tipo_transaccion, int monto, int id_cuenta,
            String tipo_cuenta_destino) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo_transaccion = tipo_transaccion;
        this.monto = monto;
        this.id_cuenta = id_cuenta;
        this.tipo_cuenta_destino = tipo_cuenta_destino;
    }



    public void depositar(double saldo) {
        double montoConAdicional = monto;
        // Solo se aplica el adicional para los primeros dos depósitos.
        if (this.monto == 0 && saldo > 0) {
            montoConAdicional += (saldo * 0.005);
        } else if (this.monto > 0 && saldo > 0) {
            montoConAdicional += (monto * 0.005);
            this.monto += montoConAdicional;
        }
        this.monto += monto;
    }

    public void retirar(double saldo) {
        if (saldo > 0 && saldo <= this.monto) {
            this.monto -= saldo;
        }
    }
	
	
	
    
        
}
