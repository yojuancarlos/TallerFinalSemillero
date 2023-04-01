package com.semillero.entidades;



public class Corriente extends Cuentas{
    public Corriente(int id_cuentas, String numeroCuenta, int saldo, Integer id_usuario, String tipo) {
        super(id_cuentas, numeroCuenta, saldo, id_usuario, tipo);
        //TODO Auto-generated constructor stub
    }

    protected int numRetiros;
	
    protected int numTransferenciasAhorro;
    protected int numTransferenciasCorriente;
  
























	public int getNumRetiros() {
        return numRetiros;
    }

    public void setNumRetiros(int numRetiros) {
        this.numRetiros = numRetiros;
    }

    public int getNumTransferenciasAhorro() {
        return numTransferenciasAhorro;
    }

    public void setNumTransferenciasAhorro(int numTransferenciasAhorro) {
        this.numTransferenciasAhorro = numTransferenciasAhorro;
    }

    public int getNumTransferenciasCorriente() {
        return numTransferenciasCorriente;
    }

    public void setNumTransferenciasCorriente(int numTransferenciasCorriente) {
        this.numTransferenciasCorriente = numTransferenciasCorriente;
    }

    public Corriente(int id_cuentas, String numeroCuenta, int saldo, Integer id_usuario, String tipo, int numRetiros,
            int numTransferenciasAhorro, int numTransferenciasCorriente) {
        super(id_cuentas, numeroCuenta, saldo, id_usuario, tipo);
        this.numRetiros = numRetiros;
        this.numTransferenciasAhorro = numTransferenciasAhorro;
        this.numTransferenciasCorriente = numTransferenciasCorriente;
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("El monto a retirar debe ser mayor a 0");
        } else if (monto > saldo) {
            System.out.println("El monto a retirar es mayor que el saldo disponible");
        } else if (numRetiros >= 5) {
            System.out.println("Ha excedido el número máximo de retiros en esta cuenta");
        } else {
            saldo -= monto;
            numRetiros++;
            System.out.println("Retiro exitoso. Nuevo saldo: " + saldo);
        }
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("El monto a depositar debe ser mayor a 0");
        } else {
            saldo += monto;
            System.out.println("Depósito exitoso. Nuevo saldo: " + saldo);
        }
    }

    public void transferir(Cuentas cuentaDestino, double monto) {
        if (monto <= 0) {
            System.out.println("El monto a transferir debe ser mayor a 0");
        } else if (monto > saldo) {
            System.out.println("El monto a transferir es mayor que el saldo disponible");
        } else if (cuentaDestino == null) {
            System.out.println("La cuenta de destino no existe");
        } else if (cuentaDestino == this) {
            System.out.println("No se puede transferir a sí mismo");
        } else if (!(cuentaDestino instanceof Corriente) && numTransferenciasAhorro >= 2) {
            System.out.println("Ha excedido el número máximo de transferencias a cuentas de ahorro");
        } else if (((cuentaDestino instanceof Corriente) || (cuentaDestino instanceof Ahorros)) 
                && (numTransferenciasCorriente + numTransferenciasAhorro) >= 10) {
            System.out.println("Ha excedido el número máximo de transferencias a cuentas corrientes");
        } else {
            double montoTotal = monto;
            if (cuentaDestino instanceof Ahorros) {
                montoTotal += monto * 0.015;
                numTransferenciasAhorro++;
            } else {
                montoTotal += monto * 0.02;
                numTransferenciasCorriente++;
            }
            saldo -= montoTotal;
            cuentaDestino.depositar(monto);
            System.out.println("Transferencia exitosa. Nuevo saldo: " + saldo);
        }
    }

	
    
}
