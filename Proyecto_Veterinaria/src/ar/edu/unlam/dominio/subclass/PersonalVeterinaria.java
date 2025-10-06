package ar.edu.unlam.dominio.subclass;

import ar.edu.unlam.dominio.superclass.Empleado;

public class PersonalVeterinaria extends Empleado{

	private String areaAsignada;

	public PersonalVeterinaria(String nombre, String apellido, Long dni, Long nroLegajo, String fechaIngreso,
			Double salario, String areaAsignada) {
		super(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		this.areaAsignada = areaAsignada;
	}

	public String getAreaAsignada() {
		return this.areaAsignada;
	}

}
