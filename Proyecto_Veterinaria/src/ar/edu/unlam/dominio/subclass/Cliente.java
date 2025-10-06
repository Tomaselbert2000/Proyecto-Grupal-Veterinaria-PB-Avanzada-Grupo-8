package ar.edu.unlam.dominio.subclass;

import java.util.Objects;

import ar.edu.unlam.dominio.superclass.Persona;

public class Cliente extends Persona {

	private Long nroCliente;
	private String telefono;
	private String direccion;
	private Double saldo;

	public Cliente(String nombre, String apellido, Long dni, Long nroCliente, String telefono, String direccion,
			Double saldo) {
		super(nombre, apellido, dni);
		this.nroCliente = nroCliente;
		this.telefono = telefono;
		this.direccion = direccion;
		this.saldo = saldo;
	}

	public Long getNroCliente() {
		return this.nroCliente;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public Double getSaldo() {
		return this.saldo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nroCliente);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(nroCliente, other.nroCliente);
	}
}
