package ar.edu.unlam.dominio.superclass;

import java.util.Objects;

public class Animal {

	private Integer idMascota;
	private String nombre;
	private Double peso;
	private Double altura;
	private String fechaNacimiento;
	private Long dniDueño;

	public Animal(Integer idMascota, String nombre, Double peso, Double altura, String fechaNacimiento, Long dniDueño) {
		this.idMascota = idMascota;
		this.nombre = nombre;
		this.peso = peso;
		this.altura = altura;
		this.fechaNacimiento = fechaNacimiento;
		this.dniDueño = dniDueño;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Double getPeso() {
		return this.peso;
	}

	public Double getAltura() {
		return this.altura;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public Integer getId() {
		return this.idMascota;
	}

	public Long getDniDueño() {
		return this.dniDueño;
	}

	// creamos la sobreescritura de equals y hashcode para afinar como se distinguen
	// los elementos de esta clase

	@Override
	public int hashCode() {
		return Objects.hash(idMascota);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(idMascota, other.idMascota);
	}
}
