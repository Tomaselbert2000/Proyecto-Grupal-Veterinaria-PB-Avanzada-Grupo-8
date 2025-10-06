package ar.edu.unlam.dominio.turno;

import java.time.LocalDateTime;

public class Turno {
	private Long dni;
	private Integer id;
	private Long numeroLegajoDoctor;
	private LocalDateTime horaTurno;
	private LocalDateTime horaCreacionTurno;
	private Integer idTurno;
	
	
	public Turno() {
		
	}
	
	public Turno(Long dni, Integer id, Long numeroLegajoDoctor, LocalDateTime horaTurno, LocalDateTime horaCreacionTurno, Integer idTurno) {
		super();
		this.dni = dni;
		this.id = id;
		this.numeroLegajoDoctor = numeroLegajoDoctor;
		this.horaTurno = horaTurno;
		this.horaCreacionTurno = horaCreacionTurno;
		this.idTurno = idTurno;
	}

	public Integer getIdTurno() {
		return idTurno;
	}

	public Long getDni() {
		return dni;
	}

	public Integer getId() {
		return id;
	}


	public Long getNumeroLegajoDoctor() {
		return numeroLegajoDoctor;
	}

	public LocalDateTime getHoraTurno() {
		return horaTurno;
	}

	public LocalDateTime getHoraCreacionTurno() {
		return horaCreacionTurno;
	}
	
	
	
}
