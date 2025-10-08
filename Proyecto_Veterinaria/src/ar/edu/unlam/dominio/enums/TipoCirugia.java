package ar.edu.unlam.dominio.enums;

public enum TipoCirugia {
	CASTRACION("Castracion"), CIRUGIA_TEJIDOS_BLANDOS("Cirugia tejidos blandos"),
	CIRUGIA_ORTOPEDICA("Cirugia ortopedica"), CIRUGIA_OCULAR("Cirugia ocular"), CIRUGIA_DENTAL("Cirugia dental"),
	CESAREA("Cesarea"), CIRUGIA("Cirugia"), CIRUGIA_CARCIOBASCULAR("Cirugia");

	private String descripcion;

	TipoCirugia(String descripcion) {
		this.descripcion = descripcion;
	}

	private String getDescripcion() {
		return this.descripcion;
	}

	public static String obtenerTipoCirugia() {
		String menu = "\n\033[0;96m*Elige el tipo de cirugias \033[0m\n";
		TipoCirugia[] opciones = TipoCirugia.values();
		for (int i = 0; i < opciones.length; i++) {
			menu += "\n" + (i + 1) + ". " + opciones[i].getDescripcion();
		}
		return menu;
	}
	
	public static TipoCirugia obtenerOpcionDeTipoCirugia(int opcionIngresada) {
		return TipoCirugia.values()[opcionIngresada -1];
	}
}