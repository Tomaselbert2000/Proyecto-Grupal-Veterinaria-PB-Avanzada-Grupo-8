package ar.edu.unlam.dominio.enums;

public enum TipoVacuna {
	ANTIRRABICA("Antirrabica"), QUINTUPLE_CANINA("Quintuple Canina"), SEXTUPLE_CANINA("Sextuple Canina"),
	PARVOVIRUS("Parvovirus"), TOS_DE_LAS_PERRERAS("Tos de las perreras"), TRIPLE_FELINA("Triple felina"),
	LEUCEMIA_FELINA("Leucemia Felina");

	private String descripcion;

	TipoVacuna(String descripcion) {
		this.descripcion = descripcion;
	}

	private String getDescripcion() {
		return this.descripcion;
	}

	public static String obtenerTipoVacuna() {
		String menu = "\n\033[0;96m*Elige el tipos de Vacunas\033[0m\n";
		TipoVacuna[] opciones = TipoVacuna.values();
		for (int i = 0; i < opciones.length; i++) {
			menu += "\n" + (i + 1) + ". " + opciones[i].getDescripcion();
		}
		return menu;
	}

	public static TipoVacuna obtenerOpcionDeTipoVacuna(int opcionIngresada) {
		return TipoVacuna.values()[opcionIngresada - 1];
	}

}