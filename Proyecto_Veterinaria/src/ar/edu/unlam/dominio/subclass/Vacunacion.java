package ar.edu.unlam.dominio.subclass;

import ar.edu.unlam.dominio.enums.TipoVacuna;
import ar.edu.unlam.dominio.superclass.Servicio;

public class Vacunacion extends Servicio{
private TipoVacuna vacuna;
	
	
	
	public Vacunacion(Integer idServicio, String descripcion, Double costoBase,TipoVacuna vacuna) {
		super(idServicio, descripcion, costoBase);
	this.vacuna = vacuna;
	
	}



	public TipoVacuna getVacuna() {
		return vacuna;
	}
	
	
	

	

}
