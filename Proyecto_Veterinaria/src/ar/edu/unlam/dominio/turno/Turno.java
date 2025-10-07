package ar.edu.unlam.dominio.turno;

import java.time.LocalDateTime;

import ar.edu.unlam.dominio.superclass.Servicio;

public class Turno {

	private Long nroCliente;
	private Integer idMascota;
	private Long nroLegajoEspecialistaConsultado;
	private Servicio servicio;
	private LocalDateTime horaCreacionTurno;
	private Integer idTurno;

	public Turno(Long nroCliente, Integer idMascota, Long nroLegajoEspecialistaConsultado, Servicio servicio, LocalDateTime horaCreacionTurno,
			Integer idTurno) {
		this.nroCliente = nroCliente;
		this.idMascota = idMascota;
		this.nroLegajoEspecialistaConsultado = nroLegajoEspecialistaConsultado;
		this.servicio = servicio;
		this.horaCreacionTurno = horaCreacionTurno;
		this.idTurno = idTurno;
	}

	public Turno() {
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
