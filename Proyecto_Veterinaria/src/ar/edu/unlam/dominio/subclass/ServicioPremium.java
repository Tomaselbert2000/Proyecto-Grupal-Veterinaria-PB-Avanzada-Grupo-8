package ar.edu.unlam.dominio.subclass;

import ar.edu.unlam.dominio.interfaces.IntMetodosServicios;
import ar.edu.unlam.dominio.superclass.Servicio;

public class ServicioPremium extends Servicio implements IntMetodosServicios{

	private Double porcentajeRecargo;

	public ServicioPremium(Integer idServicio, String descripcion, Double costoBase, Double porcentajeRecargo) {
		super(idServicio, descripcion, costoBase);
		this.porcentajeRecargo = porcentajeRecargo;
	}

	@Override
	public Double calcularRecargo() {
		return this.costoBase * this.porcentajeRecargo;
	}

	@Override
	public Double obtenerCostoTotal() {
		return super.getCosto() + this.calcularRecargo();
	}
}
