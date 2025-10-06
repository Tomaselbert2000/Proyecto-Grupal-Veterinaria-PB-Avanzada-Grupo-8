package ar.edu.unlam.dominio.subclass;

import ar.edu.unlam.dominio.interfaces.IntMetodosServicios;
import ar.edu.unlam.dominio.superclass.Servicio;

public class ServicioCorteDePelo extends Servicio implements IntMetodosServicios {

	public ServicioCorteDePelo(Integer idServicio, String descripcion, Double costoBase) {
		super(idServicio, descripcion, costoBase);
	}

	@Override
	public Double calcularRecargo() {
		return null;
	}

	@Override
	public Double calcularPrecioFinal() {
		return null;
	}
}
