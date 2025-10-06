package ar.edu.unlam.dominio.turno;

import java.time.LocalDateTime;

import ar.edu.unlam.dominio.superclass.Servicio;

public class Turno {

	private Long dni;
	private Integer idMascota;
	private Long nroLegajo;
	private Servicio servicio;
	private LocalDateTime horaCreacionTurno;
	private Integer idTurno;

	public Turno(Long dni, Integer idMascota, Long nroLegajo, Servicio servicio, LocalDateTime horaCreacionTurno,
			Integer idTurno) {
		this.dni = dni;
		this.idMascota = idMascota;
		this.nroLegajo = nroLegajo;
		this.servicio = servicio;
		this.horaCreacionTurno = horaCreacionTurno;
		this.idTurno = idTurno;
	}

	public Turno() {
	}

	public Long getDni() {
		return dni;
	}

	public Integer getIdMascota() {
		return idMascota;
	}

	public Long getNroLegajo() {
		return nroLegajo;
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
