package ar.edu.unlam.dominio.subclass;

import ar.edu.unlam.dominio.enums.TipoEspecialidad;
import ar.edu.unlam.dominio.superclass.Servicio;

public class ConsultaGeneral extends Servicio{
	private TipoEspecialidad especialidad;
	
	
	
	
	public ConsultaGeneral(Integer idServicio, String descripcion, Double costoBase,TipoEspecialidad especialidad) {
		super(idServicio, descripcion, costoBase);
		this.especialidad = especialidad;
	}

	public TipoEspecialidad getEspecialidad() {
		return especialidad;
	}





	
	
	
}
