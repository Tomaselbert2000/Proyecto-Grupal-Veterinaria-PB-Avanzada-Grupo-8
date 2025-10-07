package ar.edu.unlam.dominio.turno;

import java.time.LocalDateTime;

import ar.edu.unlam.dominio.superclass.Servicio;

public class Turno {

	private Long dni;
	private Integer idMascota;
	private Long nroLegajo;
	private Servicio servicio;
	private LocalDateTime horaCreacionTurno, horaAAsistir;
	private Integer idTurno;

	public Turno() {
		
	}
	public Turno(Long dni, Integer idMascota, Long nroLegajo, Servicio servicio, LocalDateTime horaCreacionTurno,LocalDateTime horaAAsistir,
			Integer idTurno) {
		this.dni = dni;
		this.idMascota = idMascota;
		this.nroLegajo = nroLegajo;
		this.servicio = servicio;
		this.horaCreacionTurno = horaCreacionTurno;
		this.idTurno = idTurno;
		this.horaAAsistir= horaAAsistir;
	}

	

	public LocalDateTime getHoraAAsistir() {
		return horaAAsistir;
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

	@Override
	public String toString() {
		return "Turno [dni=" + dni + ", idMascota=" + idMascota + ", nroLegajo=" + nroLegajo + ", servicio=" + servicio
				+ ", horaCreacionTurno=" + horaCreacionTurno + ", idTurno=" + idTurno + "]";
	}
}
