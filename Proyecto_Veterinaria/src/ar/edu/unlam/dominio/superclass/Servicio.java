package ar.edu.unlam.dominio.superclass;

import java.util.Objects;

public class Servicio {
	
	/*
	 * Para gestionar correctamente los distintos
	 * tipos de servicios de la veterinaria, cada
	 * uno de ellos va a extender desde esta clase
	 * padre e implementara una interfaz con las 
	 * operaciones requeridas para funcionar
	 */
	
	protected Integer idServicio;
	protected String descripcion;
	protected Double costoBase;
	
	public Servicio(Integer idServicio, String descripcion, Double costoBase) {
		this.idServicio = idServicio;
		this.descripcion = descripcion;
		this.costoBase = costoBase;
	}
	
	public Integer getId() {
		return this.idServicio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Double getCostoBase() {
		return this.costoBase;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idServicio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		return Objects.equals(idServicio, other.idServicio);
	}
}
