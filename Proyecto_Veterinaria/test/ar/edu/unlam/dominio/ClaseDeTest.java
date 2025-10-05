package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ClaseDeTest {
	
	private Veterinaria gestionVeterinaria;
	
	// aca inicializamos las instancias necesarias para los tests
	@Before
	public void init() {
		gestionVeterinaria = new Veterinaria();
	}

	@Test
	public void dadoQueExisteUnClienteObtengoQuePuedoConsultarSusAtributos() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		
		Persona persona = new Persona(nombre, apellido, dni);
		
		assertEquals("Tomas", persona.getNombre());
		assertEquals("Elbert", persona.getApellido());
		assertEquals(1L, persona.getDni(), 0);
	}
	
	@Test
	public void dadoQueExisteUnClientePorHerenciaObtengoQuePuedoConsultarSusAtributos() {
		// estos son atributos generales de la clase persona
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		
		// y aca ponemos atributos especificos de la clase cliente
		Long nroCliente = 2L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		Cliente clienteDePrueba = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		
		// agregamos el parametro de delta = 0 para los metodos que comparan valores numericos
		assertEquals("Tomas", clienteDePrueba.getNombre());
		assertEquals("Elbert", clienteDePrueba.getApellido());
		assertEquals(1L, clienteDePrueba.getDni(), 0);
		assertEquals(2L, clienteDePrueba.getNroCliente(), 0);
		assertEquals("123456", clienteDePrueba.getTelefono());
		assertEquals("Calle falsa 123", clienteDePrueba.getDireccion());
		assertEquals(25000.0, clienteDePrueba.getSaldo(), 0.0);
	}
	
	@Test
	public void dadoQueExisteUnPersonalPorHerenciaPuedoConsultarSusAtributos() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		String puesto = "Administrativo";
		Double salario = 10000.0;
		
		Empleado empleado = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, puesto, salario);
		assertEquals("Tomas", empleado.getNombre());
		assertEquals("Elbert", empleado.getApellido());
		assertEquals(1L, empleado.getDni(), 0);
		assertEquals(1L, empleado.getNroLegajo(), 0);
		assertEquals("2025-01-01", empleado.getFechaIngreso());
		assertEquals("Administrativo", empleado.getPuesto());
		assertEquals(10000.0, empleado.getSalario(), 0.0);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiRegistroUnaPersonaObtengoTrue() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		
		Persona nuevaPersona = new Persona(nombre, apellido, dni);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarNuevaPersona(nuevaPersona);
		assertTrue(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiRegistroUnClientePorHerenciaObtengoTrue() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		
		Long nroCliente = 2L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		Cliente clienteDePrueba = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		
		Boolean seRegistroElCliente = this.gestionVeterinaria.registrarNuevoCliente(clienteDePrueba);
		assertTrue(seRegistroElCliente);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiRegistroUnEmpleadoPorHerenciaObtengoTrue() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		String puesto = "Administrativo";
		Double salario = 10000.0;
		
		Empleado empleado = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, puesto, salario);
		
		Boolean seRegistroElEmpleado = this.gestionVeterinaria.registrarNuevoEmpleado(empleado);
		assertTrue(seRegistroElEmpleado);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarDosVecesElMismoClienteAla2daObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		
		Long nroCliente = 2L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		Cliente clienteDePrueba = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		
		Boolean seRegistroLaPrimera = this.gestionVeterinaria.registrarNuevaPersona(clienteDePrueba);
		Boolean seRegistroLaSegunda  =this.gestionVeterinaria.registrarNuevaPersona(clienteDePrueba);
		
		assertTrue(seRegistroLaPrimera);
		assertFalse(seRegistroLaSegunda);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarDosClientesConElMismoDniYnroClienteAla2daObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		
		Long nroCliente = 1L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		Cliente primerClienteDePrueba = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		
		String nombre2 = "Juan";
		String apellido2 = "Perez";
		Long dni2 = 1L;
		
		Long nroCliente2 = 1L;
		String direccion2 = "Calle falsa 456";
		String telefono2 = "7654321";
		Double saldo2 = 15000.0;
		
		Cliente segundoClienteDePrueba = new Cliente(nombre2, apellido2, dni2, nroCliente2, telefono2, direccion2, saldo2);
		
		Boolean seRegistroElPrimero = this.gestionVeterinaria.registrarNuevaPersona(primerClienteDePrueba);
		Boolean seRegistroElSegundo = this.gestionVeterinaria.registrarNuevaPersona(segundoClienteDePrueba);
		
		assertTrue(seRegistroElPrimero);
		assertFalse(seRegistroElSegundo);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarDosEmpleadosConElMismoDniYnroLegajoAl2doObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		String puesto = "Administrativo";
		Double salario = 10000.0;
		
		Empleado primerEmpleado = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, puesto, salario);
		
		String nombre2 = "Juan";
		String apellido2 = "Perez";
		Long dni2 = 1L;
		Long nroLegajo2 = 1L;
		String fechaIngreso2 = "2025-03-01";
		String puesto2 = "Encargado";
		Double salario2 = 20000.0;
		
		Empleado segundoEmpleado = new Empleado(nombre2, apellido2, dni2, nroLegajo2, fechaIngreso2, puesto2, salario2);
		
		Boolean seRegistroElPrimero = this.gestionVeterinaria.registrarNuevaPersona(primerEmpleado);
		Boolean seRegistroElSegundo = this.gestionVeterinaria.registrarNuevaPersona(segundoEmpleado);
		
		assertTrue(seRegistroElPrimero);
		assertFalse(seRegistroElSegundo);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnClienteConSaldoNegativoObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		
		Long nroCliente = 1L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = -25000.0;
		
		Cliente clienteSaldoNegativo = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		
		Boolean seRegistroElCliente = this.gestionVeterinaria.registrarNuevoCliente(clienteSaldoNegativo);
		
		assertFalse(seRegistroElCliente);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnEmpleadoConUnSalarioNegativoObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		String puesto = "Administrativo";
		Double salario = -10000.0;
		
		Empleado empleadoSalarioNegativo = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, puesto, salario);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarNuevoEmpleado(empleadoSalarioNegativo);
		assertFalse(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnClienteConDniNegativoObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = -1L;
		
		Long nroCliente = 1L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		Cliente clienteConDniNegativo = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarNuevoCliente(clienteConDniNegativo);
		
		assertFalse(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnEmpleadoConDniNegativoObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = -31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		String puesto = "Administrativo";
		Double salario = 10000.0;
		
		Empleado empleadoDniNegativo = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, puesto, salario);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarNuevoEmpleado(empleadoDniNegativo);
		
		assertFalse(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnClienteConNroClienteNegativoObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 21L;
		
		Long nroCliente = -20L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		Cliente clienteConNumeroNegativo = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarNuevoCliente(clienteConNumeroNegativo);
		
		assertFalse(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnEmpleadoConNroLegajoNegativoObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 123L;
		Long nroLegajo = -110L;
		String fechaIngreso = "2025-01-01";
		String puesto = "Administrativo";
		Double salario = 10000.0;
		
		Empleado empleadoConLegajoNegativo = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, puesto, salario);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarNuevoEmpleado(empleadoConLegajoNegativo);
		
		assertFalse(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnEmpleadoConUnaFechaDeIngresoIncorrectaObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 123L;
		Long nroLegajo = 110L;
		String fechaIngreso = "2025-02-31"; // ingresamos una fecha que no existe en el calendario
		String puesto = "Administrativo";
		Double salario = 10000.0;
		
		Empleado empleadoConFechaIncorrecta = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, puesto, salario);
		
		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoEmpleado(empleadoConFechaIncorrecta);
		
		assertFalse(seAgrego);
	}
}
