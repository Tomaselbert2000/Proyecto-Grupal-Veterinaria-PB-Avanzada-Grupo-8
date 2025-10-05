package ar.edu.unlam.dominio;

import java.util.Objects;

public class Empleado extends Persona{

	private Long nroLegajo;
	private String fechaIngreso;
	private String puesto;
	private Double salario;

	public Empleado(String nombre, String apellido, Long dni, Long nroLegajo, String fechaIngreso, String puesto, Double salario) {
		super(nombre, apellido, dni);
		this.nroLegajo = nroLegajo;
		this.fechaIngreso = fechaIngreso;
		this.puesto = puesto;
		this.salario = salario;
	}

	public Long getNroLegajo() {
		return this.nroLegajo;
	}

	public String getFechaIngreso() {
		return this.fechaIngreso;
	}

	public String getPuesto() {
		return this.puesto;
	}

	public Double getSalario() {
		return this.salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nroLegajo);
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
		Empleado other = (Empleado) obj;
		return Objects.equals(nroLegajo, other.nroLegajo);
	}
}
