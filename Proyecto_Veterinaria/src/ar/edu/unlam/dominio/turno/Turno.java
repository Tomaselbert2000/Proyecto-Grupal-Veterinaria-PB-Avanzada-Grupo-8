package ar.edu.unlam.dominio.turno;

import java.time.LocalDateTime;

import ar.edu.unlam.dominio.superclass.Servicio;

public class Turno {

	private Long nroCliente;
	private Integer idMascota;
	private Long nroLegajoEspecialistaConsultado;
	private Servicio servicio;
	private LocalDateTime horaCreacionTurno, horaAAsistir;
	private Integer idTurno;
	

	public Turno() {
		
	}
	public Turno(Long nroCliente, Integer idMascota, Long nroLegajoEspecialistaConsultado, Servicio servicio, LocalDateTime horaCreacionTurno,LocalDateTime horaAAsistir,Integer idTurno) {

		
		this.nroCliente = nroCliente;
		this.idMascota = idMascota;
		this.nroLegajoEspecialistaConsultado = nroLegajoEspecialistaConsultado;
		this.servicio = servicio;
		this.horaCreacionTurno = horaCreacionTurno;
		this.horaAAsistir= horaAAsistir;
		this.idTurno = idTurno;
	}

	

	public LocalDateTime getHoraAAsistir() {
		return horaAAsistir;
	}




	

	public Long getNroCliente() {
		return nroCliente;

	}

	public Integer getIdMascota() {
		return idMascota;
	}

	public Long getNroLegajoEspecialistaConsultado() {
		return nroLegajoEspecialistaConsultado;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public LocalDateTime getHoraCreacionTurno() {
		return horaCreacionTurno;
	}

	public Integer getIdTurno() {
		return idTurno;
	}
	

	
}
