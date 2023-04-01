
package com.semillero.entidades;


public class Ahorros extends Cuentas{
    
	
	public Ahorros(int id_cuentas, String numeroCuenta, int saldo, Integer id_usuario, String tipo) {
        super(id_cuentas, numeroCuenta, saldo, id_usuario, tipo);
        //TODO Auto-generated constructor stub
    }

    private int numeroRetiros;
    private double porcentajeDescuentoRetiros;
    private int numeroTransferencias;
    private double porcentajeCobroTransferencias;
    private double porcentajeBonificacionDepositos;
    private int depositosRealizados;
	private int cantidadRetiros;
    
















    
    public int getNumeroRetiros() {
        return numeroRetiros;
    }

    public void setNumeroRetiros(int numeroRetiros) {
        this.numeroRetiros = numeroRetiros;
    }

    public double getPorcentajeDescuentoRetiros() {
        return porcentajeDescuentoRetiros;
    }

    public void setPorcentajeDescuentoRetiros(double porcentajeDescuentoRetiros) {
        this.porcentajeDescuentoRetiros = porcentajeDescuentoRetiros;
    }

    public int getNumeroTransferencias() {
        return numeroTransferencias;
    }

    public void setNumeroTransferencias(int numeroTransferencias) {
        this.numeroTransferencias = numeroTransferencias;
    }

    public double getPorcentajeCobroTransferencias() {
        return porcentajeCobroTransferencias;
    }

    public void setPorcentajeCobroTransferencias(double porcentajeCobroTransferencias) {
        this.porcentajeCobroTransferencias = porcentajeCobroTransferencias;
    }

    public double getPorcentajeBonificacionDepositos() {
        return porcentajeBonificacionDepositos;
    }

    public void setPorcentajeBonificacionDepositos(double porcentajeBonificacionDepositos) {
        this.porcentajeBonificacionDepositos = porcentajeBonificacionDepositos;
    }

    public int getDepositosRealizados() {
        return depositosRealizados;
    }

    public void setDepositosRealizados(int depositosRealizados) {
        this.depositosRealizados = depositosRealizados;
    }

    public int getCantidadRetiros() {
        return cantidadRetiros;
    }

    public void setCantidadRetiros(int cantidadRetiros) {
        this.cantidadRetiros = cantidadRetiros;
    }

    public Ahorros(int id_cuentas, String numeroCuenta, int saldo, Integer id_usuario, String tipo, int numeroRetiros,
            double porcentajeDescuentoRetiros, int numeroTransferencias, double porcentajeCobroTransferencias,
            double porcentajeBonificacionDepositos, int depositosRealizados, int cantidadRetiros) {
        super(id_cuentas, numeroCuenta, saldo, id_usuario, tipo);
        this.numeroRetiros = numeroRetiros;
        this.porcentajeDescuentoRetiros = porcentajeDescuentoRetiros;
        this.numeroTransferencias = numeroTransferencias;
        this.porcentajeCobroTransferencias = porcentajeCobroTransferencias;
        this.porcentajeBonificacionDepositos = porcentajeBonificacionDepositos;
        this.depositosRealizados = depositosRealizados;
        this.cantidadRetiros = cantidadRetiros;
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a retirar debe ser mayor que cero.");
        } else if (monto > saldo) {
            System.out.println("Error: no se puede retirar un monto mayor que el saldo disponible.");
        } else {
            saldo -= monto;
            cantidadRetiros++;
            if (cantidadRetiros > 3) {
                double comision = monto * 0.01;
                saldo -= comision;
                System.out.println("Se ha aplicado una comisión por más de 3 retiros en el mes: " + comision);
            }
            System.out.println("Se ha retirado $" + monto + " de la cuenta de ahorro.");
        }
    }
    
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a depositar debe ser mayor que cero.");
        } else {
            saldo += monto;
            if (depositosRealizados < 2) {
                double bonificacion = monto * 0.005;
                saldo += bonificacion;
                System.out.println("Se ha aplicado una bonificación por los primeros depósitos en la cuenta: " + bonificacion);
            }
            depositosRealizados++;
            System.out.println("Se ha depositado $" + monto + " en la cuenta de ahorro.");
        }
    }
    
    public void transferir(Cuentas cuentaDestino, double monto) {
        if (monto <= 0) {
            System.out.println("Error: el monto a transferir debe ser mayor que cero.");
        } else if (monto > saldo) {
            System.out.println("Error: no se puede transferir un monto mayor que el saldo disponible.");
        } else if (cuentaDestino == null) {
            System.out.println("Error: la cuenta de destino no existe.");
        } else {
            if (cuentaDestino instanceof Corriente) {
                double comision = monto * 0.015;
                saldo -= monto;
                saldo -= comision;
                cuentaDestino.depositar(monto);
                System.out.println("Se ha transferido $" + monto + " de la cuenta de ahorro a la cuenta corriente.");
                System.out.println("Se ha aplicado una comisión del 1.5% por la transferencia: " + comision);
            } else {
                saldo -= monto;
                cuentaDestino.depositar(monto);
                System.out.println("Se ha transferido $" + monto + " de la cuenta de ahorro a la cuenta de ahorro.");
            }
        }
    }
    
    public String toString() {
        return "Cuenta de Ahorro\n" + super.toString() + "\n";
    }


   
}
