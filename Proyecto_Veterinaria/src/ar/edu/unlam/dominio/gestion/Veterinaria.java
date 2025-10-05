package ar.edu.unlam.dominio.gestion;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashSet;

import ar.edu.unlam.dominio.subclass.Cliente;
import ar.edu.unlam.dominio.subclass.Empleado;
import ar.edu.unlam.dominio.superclass.Animal;
import ar.edu.unlam.dominio.superclass.Persona;

public class Veterinaria {

	private HashSet<Persona> listaPersonas;
	private HashSet<Animal> listaMascotas;
	
	public Veterinaria() {
		this.listaPersonas = new HashSet<>();
		this.listaMascotas = new HashSet<>();
	}

	public Boolean registrarNuevaPersona(Persona nuevaPersona) {
		return this.listaPersonas.add(nuevaPersona);
	}

	private boolean dniValido(Persona nuevaPersona) {
		return nuevaPersona.getDni() > 0;
	}

	public Boolean registrarNuevoCliente(Cliente nuevoCliente) {
		return 
				this.dniValido(nuevoCliente)
				&& this.numeroClienteValido(nuevoCliente)
				&& this.saldoValido(nuevoCliente)
				&& this.listaPersonas.add(nuevoCliente);
	}

	private boolean numeroClienteValido(Cliente nuevoCliente) {
		return nuevoCliente.getNroCliente() > 0;
	}

	private boolean saldoValido(Cliente nuevoCliente) {
		return nuevoCliente.getSaldo() >= 0.0;
	}

	public Boolean registrarNuevoEmpleado(Empleado nuevoEmpleado) {
		return this.dniValido(nuevoEmpleado)
				&& this.fechaIngresoValida(nuevoEmpleado)
				&& this.numeroLegajoValido(nuevoEmpleado)
				&& this.salarioValido(nuevoEmpleado) 
				&& this.listaPersonas.add(nuevoEmpleado);
	}

	private boolean fechaIngresoValida(Empleado nuevoEmpleado) {
		try {
			
			/*
			 * Intentamos parsear el string de la fecha para
			 * convertirlo en un objeto de tipo LocalDate.
			 * Si el parseo llegase a ser exitoso, indica que la
			 * fecha ingresada efectivamente existe en el calendario
			 * 
			 * Por otra parte, si no es valida, se lanzara una
			 * excepcion la cual se captura y directamente retorna el
			 * false necesario para el metodo de registro de empleados
			 */
			
			LocalDate.parse(nuevoEmpleado.getFechaIngreso());
			return true;
			
		} catch (DateTimeException e) {
			return false;
		}
	}

	private boolean numeroLegajoValido(Empleado nuevoEmpleado) {
		return nuevoEmpleado.getNroLegajo() > 0;
	}

	private boolean salarioValido(Empleado nuevoEmpleado) {
		return nuevoEmpleado.getSalario() > 0;
	}

	public Boolean registrarMascota(Animal nuevaMascota) {
		return this.tieneDueñoRegistrado(nuevaMascota)
				&& this.fechaNacimientoValida(nuevaMascota)
				&& this.idMascotaValido(nuevaMascota)
				&& this.listaMascotas.add(nuevaMascota);
	}

	private boolean fechaNacimientoValida(Animal nuevaMascota) {
		// dentro de este metodo aplicamos la misma logica que con los empleados
		try {
			LocalDate.parse(nuevaMascota.getFechaNacimiento());
			return true;
		} catch (DateTimeException e) {
			return false;
		}
	}

	private boolean idMascotaValido(Animal nuevaMascota) {
		return nuevaMascota.getId() > 0;
	}

	private Boolean tieneDueñoRegistrado(Animal nuevaMascota) {
		for(Persona cliente : this.listaPersonas) {
			if(cliente.getDni().equals(nuevaMascota.getDniDueño())) {
				return true;
			}
		}
		return false;
	}

}
