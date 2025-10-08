package ar.edu.unlam.interfaz.enums;

public enum MenuPrincipal {

	REGISTRAR_DOCTOR("Registrar medico"), AGREGAR_PERSONAL("Agregar personal nuevo"),
	REGISTRAR_CLIENTE("Registrar cliente"), REGISTRAR_MASCOTA("Registrar mascota"), CREAR_TURNO("Crear un turno"),
	VER_CUENTA_DEL_CLIENTE ("Ver los datos del cliente"), VER_HISTORIAL ("Ver el historial medico"),
	SALIR("Salir del Sistema");

	private String descripcion;

	MenuPrincipal(String descripcion) {
		this.descripcion = descripcion;
	}

	private String getDescripcion() {
		return this.descripcion;
	}

	public static String obtenerMenuPrincipal() {
		String menu = "\n\t\033[0;93m*** Menu Veterinaria ***\033[0m\n";
		MenuPrincipal[] opciones = MenuPrincipal.values();
		for (int i = 0; i < opciones.length; i++) {
			menu += "\n" + (i + 1) + ". " + opciones[i].getDescripcion();
		}
		return menu;
	}

	public static MenuPrincipal obtenerOpcionDelMenuPrincipal(int opcionIngresada) {
		return MenuPrincipal.values()[opcionIngresada - 1];
	}

}