package ar.edu.unlam.dominio.subclass;

import ar.edu.unlam.dominio.enums.TipoCirugia;
import ar.edu.unlam.dominio.superclass.Servicio;

public class Cirugia extends Servicio {
	private TipoCirugia tipoCirugia;
	
	
	public Cirugia(Integer idServicio, String descripcion, Double costoBase,TipoCirugia tipoCirugia) {
		super(idServicio, descripcion, costoBase);
		this.tipoCirugia = tipoCirugia;
		
	}


	public TipoCirugia getTipoCirugia() {
		return tipoCirugia;
	}

	
		
}
