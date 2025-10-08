package ar.edu.unlam.dominio.enums;

public enum Especialidad {
	CLINICA_GENERAL("Clinica General"), CIRUGIA("Cirugia"), DERMATOLOGIA("Dermatologia"), ODONTOLOGIA("Odontologia"),
	OFTALMOLOGIA("Oftalmologia"), VACUNACION("Vacunacion");

	private String descripcion;

	Especialidad(String descripcion) {
		this.descripcion = descripcion;
	}

	private String getDescripcion() {
		return this.descripcion;
	}

	public static String obtenerEspecialistaDoctor() {
		String menu = "\n\033[0;96m*Elige la Especialidad correspondiente \033[0m\n";
		Especialidad[] opciones = Especialidad.values();
		for (int i = 0; i < opciones.length; i++) {
			menu += "\n" + (i + 1) + ". " + opciones[i].getDescripcion();
		}
		return menu;
	}
	
	public static Especialidad obtenerOpcionDeEspecialistaDoctor(int opcionIngresada) {
		return Especialidad.values()[opcionIngresada -1];
	}

}
