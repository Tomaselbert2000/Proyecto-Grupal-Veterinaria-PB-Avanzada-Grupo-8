package ar.edu.unlam.dominio.subclass;

import java.util.Objects;

import ar.edu.unlam.dominio.enums.Especialidad;
import ar.edu.unlam.dominio.superclass.Empleado;

public class Especialista extends Empleado{

	private Long nroMatricula;
	private Especialidad especialistaDoctor;

	public Especialista(String nombre, String apellido, Long dni, Long nroLegajo, String fechaIngreso, Double salario, Long nroMatricula, Especialidad especialistaDoctor) {
		super(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		this.nroMatricula = nroMatricula;
		this.especialistaDoctor = especialistaDoctor;
	}

	public Long getNroMatricula() {
		return this.nroMatricula;
	}

	

	public Especialidad getEspecialistaDoctor() {
		return especialistaDoctor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nroMatricula);
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
		Especialista other = (Especialista) obj;
		return Objects.equals(nroMatricula, other.nroMatricula);
	}
}
