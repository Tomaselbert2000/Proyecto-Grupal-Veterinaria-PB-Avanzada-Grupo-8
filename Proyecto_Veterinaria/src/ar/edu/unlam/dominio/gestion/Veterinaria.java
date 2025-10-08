package ar.edu.unlam.dominio.gestion;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;
import ar.edu.unlam.dominio.subclass.Cliente;
import ar.edu.unlam.dominio.subclass.Especialista;
import ar.edu.unlam.dominio.superclass.Animal;
import ar.edu.unlam.dominio.superclass.Empleado;
import ar.edu.unlam.dominio.superclass.Historial;
import ar.edu.unlam.dominio.superclass.Persona;
import ar.edu.unlam.dominio.superclass.Servicio;
import ar.edu.unlam.dominio.turno.Turno;

public class Veterinaria {

	private HashSet<Persona> listaPersonas;
	private HashSet<Animal> listaMascotas;
	private HashSet<Servicio> listaDeServicios;
	private HashSet<Turno> listaTurnos;
	private HashSet<Historial> listaDeHistorial;

	public Veterinaria() {
		this.listaPersonas = new HashSet<>();
		this.listaMascotas = new HashSet<>();
		this.listaDeServicios = new HashSet<>();
		this.listaTurnos = new HashSet<Turno>();
	}

	public HashSet<Animal> getListaMascotas() {
		return listaMascotas;
	}

	public Boolean registrarNuevaPersona(Persona nuevaPersona) {
		return this.listaPersonas.add(nuevaPersona);
	}

	private Boolean dniValido(Persona nuevaPersona) {
		return nuevaPersona.getDni() > 0;
	}

	public Boolean registrarNuevoCliente(Cliente nuevoCliente) {
		return this.dniValido(nuevoCliente) && this.numeroClienteValido(nuevoCliente) && this.saldoValido(nuevoCliente)
				&& this.listaPersonas.add(nuevoCliente);
	}

	private Boolean numeroClienteValido(Cliente nuevoCliente) {
		return nuevoCliente.getNroCliente() > 0;
	}

	private Boolean saldoValido(Cliente nuevoCliente) {
		return nuevoCliente.getSaldo() >= 0.0;
	}

	public Boolean registrarNuevoEmpleado(Empleado nuevoEmpleado) {
		return this.dniValido(nuevoEmpleado) && this.fechaIngresoValida(nuevoEmpleado)
				&& this.numeroLegajoValido(nuevoEmpleado) && this.salarioValido(nuevoEmpleado)
				&& this.listaPersonas.add(nuevoEmpleado);
	}

	// aplicamos sobrecarga en el metodo anterior para que pueda cargar
	// especialistas
	public Boolean registrarNuevoEmpleado(Especialista especialista) {
		return this.dniValido(especialista) && this.fechaIngresoValida(especialista)
				&& this.numeroLegajoValido(especialista) && this.salarioValido(especialista)
				&& this.nroMatriculaValido(especialista) && this.nroMatriculaNoDuplicado(especialista)
				&& this.listaPersonas.add(especialista);
	}

	private Boolean nroMatriculaNoDuplicado(Especialista especialista) {
		for (Persona persona : this.listaPersonas) {

			/*
			 * Para poder distinguir entre los distintos especialistas registrados vamos a
			 * usar su numero de matricula, por lo tanto casteamos leemos la lista general
			 * de personas y a cada una la casteamos para usarla como Especialista,
			 * consultamos su nroMatricula si lo tiene y lo comparamos con el nroMatricula
			 * del especialista que queremos ingresar Si son iguales, indica que esta
			 * repetido y por lo tanto retorna false indicando que se considera duplicado.
			 * De otro modo, pasa directamente al true y el metodo que ingresa los
			 * especialistas al sistema funciona como se espera
			 * 
			 * ¿Por que aplicar una validacion extra? Porque si bien el sistema hasta el
			 * momento ya valida tanto el dni como el nro de legajo, dado que la coleccion
			 * es un hashset, si tenemos distinto dni + distinto nro legajo, ya se calcula
			 * el hash con valores diferentes entonces dos especialistas con igual matricula
			 * se tomarian como diferentes instancias porque al sumar el hash de la
			 * matricula de igual modo ambos resultados son diferentes.
			 */

			if (persona instanceof Especialista) {
				if (((Especialista) persona).getNroMatricula().equals(especialista.getNroMatricula())) {
					return false;
				}
			}
		}
		return true;
	}

	public HashSet<Turno> getListaTurnos() {
		return listaTurnos;
	}

	private Boolean fechaIngresoValida(Empleado nuevoEmpleado) {
		try {

			/*
			 * Intentamos parsear el string de la fecha para convertirlo en un objeto de
			 * tipo LocalDate. Si el parseo llegase a ser exitoso, indica que la fecha
			 * ingresada efectivamente existe en el calendario
			 * 
			 * Por otra parte, si no es valida, se lanzara una excepcion la cual se captura
			 * y directamente retorna el false necesario para el metodo de registro de
			 * empleados
			 */

			LocalDate.parse(nuevoEmpleado.getFechaIngreso());
			return true;

		} catch (DateTimeException e) {
			return false;
		}
	}

	private Boolean numeroLegajoValido(Empleado nuevoEmpleado) {
		return nuevoEmpleado.getNroLegajo() > 0;
	}

	private Boolean salarioValido(Empleado nuevoEmpleado) {
		return nuevoEmpleado.getSalario() > 0;
	}

	public Boolean registrarMascota(Animal nuevaMascota) {
		return this.tieneDueñoRegistrado(nuevaMascota) && this.fechaNacimientoValida(nuevaMascota)
				&& this.idMascotaValido(nuevaMascota) && this.listaMascotas.add(nuevaMascota);
	}

	private Boolean fechaNacimientoValida(Animal nuevaMascota) {
		// dentro de este metodo aplicamos la misma logica que con los empleados
		try {
			LocalDate.parse(nuevaMascota.getFechaNacimiento());
			return true;
		} catch (DateTimeException e) {
			return false;
		}
	}

	private Boolean idMascotaValido(Animal nuevaMascota) {
		return nuevaMascota.getId() > 0;
	}

	private Boolean tieneDueñoRegistrado(Animal nuevaMascota) {
		for (Persona cliente : this.listaPersonas) {
			if (cliente.getDni().equals(nuevaMascota.getDniDueño())) {
				return true;
			}
		}
		return false;
	}

	private Boolean nroMatriculaValido(Especialista especialista) {
		return especialista.getNroMatricula() > 0;
	}

	// para evitar problemas al registrar distintas clases de servicios, el metodo
	// recibe un objeto de la superclase
	public Boolean registrarNuevoServicio(Servicio servicio) {
		return this.idServicioValido(servicio) && this.costoBaseValido(servicio) && this.listaDeServicios.add(servicio);
	}

	private Boolean costoBaseValido(Servicio servicio) {
		return servicio.getCostoBase() > 0.0;
	}

	private Boolean idServicioValido(Servicio servicio) {
		return servicio.getId() > 0;
	}

	public Boolean registrarTurno(Turno turno) {
		Boolean precondiciones = 
				this.clienteRegistrado(turno) 
				&& this.clienteConSaldoSuficiente(turno)
				&& this.servicioRegistrado(turno) 
				&& this.mascotaRegistrada(turno)
				&& this.especialistaConsultadoRegistrado(turno);

		if (precondiciones) {
			this.actualizarSaldoCliente(turno);
			return this.listaTurnos.add(turno);
		}

		return false;
	}

	private void actualizarSaldoCliente(Turno turno) {
		HashSet<Cliente> listaClientes = new HashSet<>();
		for (Persona persona : this.listaPersonas) {
			if (persona instanceof Cliente) {
				listaClientes.add((Cliente) persona);
			}
		}

		for (Cliente cliente : listaClientes) {
			if (cliente.getNroCliente().equals(turno.getNroCliente())) {
				cliente.descontarSaldoAbonado(turno.getServicio().getCostoBase());
			}
		}
	}

	private Boolean clienteConSaldoSuficiente(Turno turno) {
		for (Persona person : this.listaPersonas) {
			if (person instanceof Cliente) {
				if (((Cliente) person).getSaldo() >= turno.getServicio().getCostoBase()) {
					return true;
				}
			}
		}
		return false;
	}

	private Boolean servicioRegistrado(Turno turno) {
		for (Servicio service : this.listaDeServicios) {
			if (service.equals(turno.getServicio())) {
				return true;
			}
		}
		return false;
	}

	private Boolean mascotaRegistrada(Turno turno) {
		for (Animal mascota : this.listaMascotas) {
			if (mascota.getId().equals(turno.getIdMascota())) {
				return true;
			}
		}
		return false;
	}

	private Boolean especialistaConsultadoRegistrado(Turno turno) {
		for (Persona especialista : this.listaPersonas) {
			if (especialista instanceof Especialista) {
				if (((Especialista) especialista).getNroMatricula().equals(turno.getNroMatriculaEspecialistaConsultado())) {
					return true;
				}
			}
		}
		return false;
	}

	private Boolean clienteRegistrado(Turno turno) {
		for (Persona cliente : this.listaPersonas) {
			if (cliente instanceof Cliente) {
				if (((Cliente) cliente).getNroCliente().equals(turno.getNroCliente())) {
					return true;
				}
			}
		}
		return false;
	}

	public Boolean cancelarTurno(Integer idTurnoBuscado) {
		if (!listaTurnos.isEmpty()) {
			for (Turno turno : this.listaTurnos) {
				if (turno.getIdTurno() != null && turno.getIdTurno().equals(idTurnoBuscado)) {
					return this.listaTurnos.remove(turno);

				}
			}
		}
		return false;
	}

	public HashSet<Turno> obtenerTurnosPorNroCliente(Long nroCliente) {
		HashSet<Turno> listaTurnosADevolver = new HashSet<Turno>();
		for(Turno turno : this.listaTurnos) {
			if(turno.getNroCliente().equals(nroCliente)) {
				listaTurnosADevolver.add(turno);
			}
		}
		return listaTurnosADevolver;
	}

	public HashSet<Animal> ObtenerAnimalPorDniCliente(Long dni) {
		HashSet<Animal> listaTurnosADevolver = new HashSet<Animal>();
		if (!listaMascotas.isEmpty()) {
			for (Animal animal : this.listaMascotas) {
				if (animal.getDniDueño() != null && animal.getDniDueño().equals(dni)) {
					listaTurnosADevolver.add(animal);
				}
			}
		}
		return listaTurnosADevolver;
	}

}
