package ar.edu.unlam.dominio;

import java.util.Objects;

public class Persona {

	protected String nombre;
	protected String apellido;
	protected Long dni;

	public Persona(String nombre, String apellido, Long dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	public Long getDni() {
		return this.dni;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}
}
