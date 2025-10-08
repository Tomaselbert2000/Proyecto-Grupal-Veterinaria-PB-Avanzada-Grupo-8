package ar.edu.unlam.dominio.enums;

public enum TipoEspecialidad {
	CLINICO_GENERAL("Clinico general"), CIRUGIA("Cirugia"), VACUNACION("Vacunacion"), DERMATOLOGIA("Dermatologia"),
	ODONTOLOGIA("Odontologia");

	private String descripcion;

	TipoEspecialidad(String descripcion) {
		this.descripcion = descripcion;
	}

	private String getDescripcion() {
		return this.descripcion;
	}

	public static String obtenerTipoEspecialidad() {
		String menu = "\n\033[0;96m*Elige un tipo de Especialidad \033[0m\n";
		TipoEspecialidad[] opciones = TipoEspecialidad.values();
		for (int i = 0; i < opciones.length; i++) {
			menu += "\n" + (i + 1) + ". " + opciones[i].getDescripcion();
		}
		return menu;
	}
	
	public static TipoEspecialidad obtenerOpcionDeTipoEspecialidad(int opcionIngresada) {
		return TipoEspecialidad.values()[opcionIngresada -1];
	}
}