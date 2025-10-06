package ar.edu.unlam.dominio.subclass;

import ar.edu.unlam.dominio.superclass.Animal;

public class Felino extends Animal {

	// esta clase sirve para probar la herencia y si las propiedades se trasladan
	// desde Animal
	private String especie;
	private String colorPrincipal;
	private String colorSecundario;

	public Felino(Integer idMascota, String nombre, Double peso, Double altura, String fechaNacimiento, Long idDueño,
			String especie, String colorPrincipal, String colorSecundario) {
		super(idMascota, nombre, peso, altura, fechaNacimiento, idDueño);
		this.especie = especie;
		this.colorPrincipal = colorPrincipal;
		this.colorSecundario = colorSecundario;
	}

	public String getEspecie() {
		return this.especie;
	}

	public String getColorPrincipal() {
		return this.colorPrincipal;
	}

	public String getColorSecundario() {
		return this.colorSecundario;
	}
}
