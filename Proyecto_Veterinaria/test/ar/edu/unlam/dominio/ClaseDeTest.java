package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.dominio.gestion.Veterinaria;
import ar.edu.unlam.dominio.subclass.Cliente;
import ar.edu.unlam.dominio.subclass.Felino;
import ar.edu.unlam.dominio.subclass.PersonalVeterinaria;
import ar.edu.unlam.dominio.subclass.ServicioBaño;
import ar.edu.unlam.dominio.subclass.Especialista;
import ar.edu.unlam.dominio.superclass.Animal;
import ar.edu.unlam.dominio.superclass.Empleado;
import ar.edu.unlam.dominio.superclass.Persona;
import ar.edu.unlam.dominio.turno.Turno;


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
		Double salario = 10000.0;
		
		Empleado empleado = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		assertEquals("Tomas", empleado.getNombre());
		assertEquals("Elbert", empleado.getApellido());
		assertEquals(1L, empleado.getDni(), 0);
		assertEquals(1L, empleado.getNroLegajo(), 0);
		assertEquals("2025-01-01", empleado.getFechaIngreso());
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
		Double salario = 10000.0;
		
		Empleado empleado = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		
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
		Double salario = 10000.0;
		
		Empleado primerEmpleado = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		
		String nombre2 = "Juan";
		String apellido2 = "Perez";
		Long dni2 = 1L;
		Long nroLegajo2 = 1L;
		String fechaIngreso2 = "2025-03-01";
		Double salario2 = 20000.0;
		
		Empleado segundoEmpleado = new Empleado(nombre2, apellido2, dni2, nroLegajo2, fechaIngreso2, salario2);
		
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
		Double salario = -10000.0;
		
		Empleado empleadoSalarioNegativo = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		
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
		Double salario = 10000.0;
		
		Empleado empleadoDniNegativo = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		
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
		Double salario = 10000.0;
		
		Empleado empleadoConLegajoNegativo = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		
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
		Double salario = 10000.0;
		
		Empleado empleadoConFechaIncorrecta = new Empleado(nombre, apellido, dni, nroLegajo, fechaIngreso, salario);
		
		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoEmpleado(empleadoConFechaIncorrecta);
		
		assertFalse(seAgrego);
	}
	
	@Test
	public void dadoQueExisteUnAnimalObtengoQuePuedoConsultarSusAtributos() {
		Integer idMascota = 10;
		String nombre = "Firulais";
		Double peso = 10.5;
		Double altura = 0.50;
		String fechaNacimiento = "2023-03-20";
		Long idDueño = 1L;
		
		Animal nuevaMascota = new Animal(idMascota, nombre, peso, altura, fechaNacimiento, idDueño);
		
		assertEquals(10, nuevaMascota.getId(), 0);
		assertEquals("Firulais", nuevaMascota.getNombre());
		assertEquals(10.5, nuevaMascota.getPeso(), 0.0);
		assertEquals(0.50, nuevaMascota.getAltura(), 0.0);
		assertEquals("2023-03-20", nuevaMascota.getFechaNacimiento());
		assertEquals(1L, nuevaMascota.getDniDueño(), 0);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnAnimalSinUnDueñoAsociadoRegistradoObtengoFalse() {
		Integer idMascota = 10;
		String nombre = "Firulais";
		Double peso = 10.5;
		Double altura = 0.50;
		String fechaNacimiento = "2023-03-20";
		Long idDueño = 1L; // aca tenemos el id del dueño pero no tenemos ninguno registrado en el sistema con ese id
		
		Animal mascotaSinDueño = new Animal(idMascota, nombre, peso, altura, fechaNacimiento, idDueño);
		Boolean seAgrego = this.gestionVeterinaria.registrarMascota(mascotaSinDueño);
		assertFalse(seAgrego);
	}
	
	@Test
	public void dadoQueExisteUnaMascotaSiYUnClienteSiIntentoRegistrarlaObtengoTrue() {
		// para preparar el entorno de test aca vamos a registrar un dueño
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 10L;
		
		Long nroCliente = 20L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		// registramos el cliente
		Cliente cliente = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		this.gestionVeterinaria.registrarNuevoCliente(cliente);
		
		Integer idMascota = 10;
		String nombreMascota = "Firulais";
		Double peso = 10.5;
		Double altura = 0.50;
		String fechaNacimiento = "2023-03-20";
		Long dniDueño = 10L;
		
		// creamos y registramos la mascota
		Animal nuevaMascota = new Animal(idMascota, nombreMascota, peso, altura, fechaNacimiento, dniDueño);
		Boolean seAgrego = this.gestionVeterinaria.registrarMascota(nuevaMascota);
		
		assertTrue(seAgrego);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaConUnClienteRegistradoSiIntentoRegistrarDosVecesLaMismaMascotaAla2daObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 10L;
		
		Long nroCliente = 20L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		// registramos el cliente
		Cliente cliente = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		this.gestionVeterinaria.registrarNuevoCliente(cliente);
		
		// ahora creamos la instancia de la mascota
		Integer idMascota = 10;
		String nombreMascota = "Firulais";
		Double peso = 10.5;
		Double altura = 0.50;
		String fechaNacimiento = "2023-03-20";
		Long dniDueño = 10L;
		
		Animal nuevaMascota = new Animal(idMascota, nombreMascota, peso, altura, fechaNacimiento, dniDueño);
		
		// probamos registrar dos veces y comparamos los resultados
		Boolean seRegistroLaPrimera = this.gestionVeterinaria.registrarMascota(nuevaMascota);
		Boolean seRegistroLaSegunda = this.gestionVeterinaria.registrarMascota(nuevaMascota);
		
		assertTrue(seRegistroLaPrimera);
		assertFalse(seRegistroLaSegunda);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaConUnClienteSiIntentoRegistrarDosMascotasConElMismoIdAla2daObtengoFalse() {
		// creamos un cliente para probar el test
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 10L;
		
		Long nroCliente = 20L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		// registramos el cliente
		Cliente cliente = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		this.gestionVeterinaria.registrarNuevoCliente(cliente);
		
		// ahora vamos a probar crear dos mascotas con datos distintos pero el mismo id, ambas asociadas al cliente
		Integer idMascota1 = 10;
		String nombreMascota1 = "Firulais";
		Double peso1 = 10.5;
		Double altura1 = 0.50;
		String fechaNacimiento1 = "2023-03-20";
		Long dniDueño1 = 10L;
		
		Animal mascotaOriginal = new Animal(idMascota1, nombreMascota1, peso1, altura1, fechaNacimiento1, dniDueño1);
		
		Integer idMascota2 = 10; // aca repetimos el id que usamos en la instancia anterior
		String nombreMascota2 = "Mimi";
		Double peso2 = 3.5;
		Double altura2 = 0.25;
		String fechaNacimiento2 = "2024-04-10";
		Long dniDueño2 = 10L;
		
		Animal mascotaRepetida = new Animal(idMascota2, nombreMascota2, peso2, altura2, fechaNacimiento2, dniDueño2);
		
		Boolean seRegistroLaPrimera = this.gestionVeterinaria.registrarMascota(mascotaOriginal);
		Boolean seRegistroLaSegunda = this.gestionVeterinaria.registrarMascota(mascotaRepetida);
		
		// asi como con las demas clases, aca vamos a usar la sobreescritura del hashcode y de equals
		assertTrue(seRegistroLaPrimera);
		assertFalse(seRegistroLaSegunda);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaConUnClienteRegistradoSiIntentoRegistrarlaConUnIdNegativoObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 10L;
		
		Long nroCliente = 20L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		// creamos y registramos el cliente
		Cliente nuevoCliente = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		this.gestionVeterinaria.registrarNuevoCliente(nuevoCliente);
		
		// ahora creamos una mascota con un id erroneo a proposito para verificar que no lo deja pasar
		Integer idMascota = -10;
		String nombreMascota = "Firulais";
		Double peso = 10.5;
		Double altura = 0.50;
		String fechaNacimiento = "2023-03-20";
		Long dniDueño = 10L;
		
		Animal mascotaConIdNegativo = new Animal(idMascota, nombreMascota, peso, altura, fechaNacimiento, dniDueño);
		
		Boolean seAgrego = this.gestionVeterinaria.registrarMascota(mascotaConIdNegativo);
		
		assertFalse(seAgrego);
	}
	
	@Test
	public void dadoQueExisteUnClienteConUnaMascotaSiIntentoRegistrarlaConUnaFechaDeNacimientoInvalidaObtengoFalse() {
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 10L;
		
		Long nroCliente = 20L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		// creamos y registramos el cliente
		Cliente nuevoCliente = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		this.gestionVeterinaria.registrarNuevoCliente(nuevoCliente);

		Integer idMascota = 100;
		String nombreMascota = "Firulais";
		Double peso = 10.5;
		Double altura = 0.50;
		String fechaNacimiento = "2020-13-20"; // ponemos una fecha erronea a proposito para probar que el sistema lo valida antes de seguir
		Long dniDueño = 10L;
		
		Animal mascotaConFechaInvalida = new Animal(idMascota, nombreMascota, peso, altura, fechaNacimiento, dniDueño);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarMascota(mascotaConFechaInvalida);
		
		assertFalse(seRegistro);
	}
	
	// implementamos subclases de animal correspondiente a cada especie que queramos agregar
	
	@Test
	public void dadoQueExisteUnFelinoPorHerenciaObtengoQuePuedoConsultarSusAtributos() {
		Integer id = 5;
		String nombre = "Mimi";
		Double peso = 2.5;
		Double altura = 0.35;
		String fechaNacimiento = "2025-06-01";
		Long dniDueño = 5L;
		String especie = "Siames";
		String colorPrincipal = "Gris";
		String colorSecundario = "Blanco";
		
		Felino gato = new Felino(id, nombre, peso, altura, fechaNacimiento, dniDueño, especie, colorPrincipal, colorSecundario);
		
		assertEquals(5, gato.getId(), 0);
		assertEquals("Mimi", gato.getNombre());
		assertEquals(2.5, gato.getPeso(), 0.0);
		assertEquals(0.35, gato.getAltura(), 0.0);
		assertEquals("2025-06-01", gato.getFechaNacimiento());
		assertEquals(5L, gato.getDniDueño(), 0);
		assertEquals("Siames", gato.getEspecie());
		assertEquals("Gris", gato.getColorPrincipal());
		assertEquals("Blanco", gato.getColorSecundario());
	}
	
	@Test
	public void dadoQueExisteUnFelinoPorHerenciaSiLoRegistroObtengoTrue() {
		// creamos un cliente para poder realizar el registro con la subclase
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 10L;
		
		Long nroCliente = 20L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;
		
		Cliente nuevoCliente = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		this.gestionVeterinaria.registrarNuevoCliente(nuevoCliente);
		
		Integer id = 5;
		String nombreMascota = "Mimi";
		Double peso = 2.5;
		Double altura = 0.35;
		String fechaNacimiento = "2025-06-01";
		Long dniDueño = 10L;
		String especie = "Siames";
		String colorPrincipal = "Gris";
		String colorSecundario = "Blanco";
		
		Felino gato = new Felino(id, nombreMascota, peso, altura, fechaNacimiento, dniDueño, especie, colorPrincipal, colorSecundario);
		
		Boolean seRegistroLaSubclase = this.gestionVeterinaria.registrarMascota(gato);
		
		assertTrue(seRegistroLaSubclase);
		
		// comprobamos que la clase heredada cumple con las demas restricciones que aplicamos antes como
		// el dni de dueño pre registrado, id no duplicado, fecha de nacimiento, etc.
	}
	
	// creacion de subclases para Empleados
	
	@Test
	public void dadoQueExisteUnaClaseEspecialistaPorHerenciaObtengoQuePuedoConsultarSusAtributos() {
		String nombre = "Matias";
		String apellido = "Martinez";
		Long dni = 31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		Double salario = 10000.0;
		
		// aca agregamos atributos especificos para los veterinarios y relacionados
		
		Long nroMatricula = 204060100L;
		String especialidad = "Cardiologia";
		
		Especialista especialista = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario, nroMatricula, especialidad);
		
		assertEquals(nombre, especialista.getNombre());
		assertEquals(apellido, especialista.getApellido());
		assertEquals(dni, especialista.getDni());
		assertEquals(nroLegajo, especialista.getNroLegajo());
		assertEquals(fechaIngreso, especialista.getFechaIngreso());
		assertEquals(salario, especialista.getSalario());
		assertEquals(nroMatricula, especialista.getNroMatricula());
		assertEquals(especialidad, especialista.getEspecialidad());
	}
	
	@Test
	public void dadoQueExisteUnEspecialistaPorHerenciaSiLoRegistroObtengoTrue() {
		String nombre = "Matias";
		String apellido = "Martinez";
		Long dni = 31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		Double salario = 10000.0;
		
		// aca agregamos atributos especificos para los veterinarios y relacionados
		
		Long nroMatricula = 204060100L;
		String especialidad = "Cardiologia";
		
		Especialista especialista = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario, nroMatricula, especialidad);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarNuevoEmpleado(especialista);
		
		assertTrue(seRegistro);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarDosVecesElMismoEspecialistaAl2doObtengoFalse() {
		String nombre = "Matias";
		String apellido = "Martinez";
		Long dni = 31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		Double salario = 10000.0;
		
		// aca agregamos atributos especificos para los veterinarios y relacionados
		
		Long nroMatricula = 204060100L;
		String especialidad = "Cardiologia";
		
		Especialista especialista = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario, nroMatricula, especialidad);
		
		Boolean seRegistro = this.gestionVeterinaria.registrarNuevoEmpleado(especialista);
		Boolean seRegistroDeNuevo = this.gestionVeterinaria.registrarNuevoEmpleado(especialista);
		
		assertTrue(seRegistro);
		assertFalse(seRegistroDeNuevo);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarDosEspecialistasConElMismoNumeroDeMatriculaAl2doObtengoFalse() {
		String nombre = "Matias";
		String apellido = "Martinez";
		Long dni = 31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		Double salario = 10000.0;
		
		Long nroMatricula = 204060100L;
		String especialidad = "Cardiologia";
		
		Especialista especialistaOriginal = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario, nroMatricula, especialidad);
		
		String nombre2 = "Juan";
		String apellido2 = "Perez";
		Long dni2 = 46L;
		Long nroLegajo2 = 3L;
		String fechaIngreso2 = "2024-09-22";
		Double salario2 = 22000.0;
		Long nroMatriculaRepetido = 204060100L; // repetimos el nro de matricula para que el sistema lo valide como incorrecto
		String especialidad2 = "Traumatologia";
		
		Especialista especialistaRepetido = new Especialista(nombre2, apellido2, dni2, nroLegajo2, fechaIngreso2, salario2, nroMatriculaRepetido, especialidad2);
		
		Boolean seRegistroElPrimero = this.gestionVeterinaria.registrarNuevoEmpleado(especialistaOriginal);
		Boolean seRegistroElSegundo = this.gestionVeterinaria.registrarNuevoEmpleado(especialistaRepetido);
		
		assertTrue(seRegistroElPrimero);
		assertFalse(seRegistroElSegundo);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnEspecialistaConNroMatriculaNegativoObtengoFalse() {
		String nombre = "Matias";
		String apellido = "Martinez";
		Long dni = 31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		Double salario = 10000.0;
		
		Long nroMatricula = -204060100L; // aca usamos un numero de matricula que se debe validar como incorrecto
		String especialidad = "Cardiologia";
		
		Especialista especialista = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario, nroMatricula, especialidad);
		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoEmpleado(especialista);
		
		assertFalse(seAgrego);
	}
	
	@Test
	public void dadoQueExisteUnPersonalDeVeterinariaPorHerenciaObtengoQuePuedoConsultarSusAtributos() {
		String nombre = "Matias";
		String apellido = "Martinez";
		Long dni = 31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		Double salario = 10000.0;
		String areaAsignada = "Administracion";
		
		PersonalVeterinaria personalNuevo = new PersonalVeterinaria(nombre, apellido, dni, nroLegajo, fechaIngreso, salario, areaAsignada);
		
		assertEquals(nombre, personalNuevo.getNombre());
		assertEquals(apellido, personalNuevo.getApellido());
		assertEquals(dni, personalNuevo.getDni());
		assertEquals(nroLegajo, personalNuevo.getNroLegajo());
		assertEquals(fechaIngreso, personalNuevo.getFechaIngreso());
		assertEquals(salario, personalNuevo.getSalario());
		assertEquals(areaAsignada, personalNuevo.getAreaAsignada());
	}
	
	/*
	 * a partir de este punto vamos probando como relacionar las distintas entidades a traves de los turnos
	 * ya que un cliente reserva un turno para X mascota, la cual ademas es atendida ya sea por un especialista
	 * en caso de ser un tema de salud o bien por el personal de la veterinaria como puede ser para baño/corte de pelo
	 * Lo cual además implica un servicio que luego debe descontarse del saldo del cliente que lo solicita
	 */
	
	// para mejorar la modularizacion del codigo, el servicio como tal es una clase abstracta
	// que tendra caracteristicas basicas y luego otras especificas la van a usar como punto de partida
	
	@Test
	public void dadoQueExisteUnaClaseServicioDeBañoObtengoQuePuedoConsultarSusAtributos() {
		Integer idServicioAsignado = 1;
		String descripcion = "Servicio de baño completo";
		Double costoBase = 25000.0;
		
		ServicioBaño servicio = new ServicioBaño(idServicioAsignado, descripcion, costoBase);
		
		assertEquals(idServicioAsignado, servicio.getId());
		assertEquals(descripcion, servicio.getDescripcion());
		assertEquals(costoBase, servicio.getCostoBase());
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnNuevoServicioObtengoTrue() {
		Integer idServicioAsignado = 1;
		String descripcion = "Servicio de baño completo";
		Double costoBase = 25000.0;
		
		ServicioBaño servicio = new ServicioBaño(idServicioAsignado, descripcion, costoBase);
		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoServicio(servicio);
		
		assertTrue(seAgrego);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarDosVecesElMismoServicioAla2daObtengoFalse() {
		Integer idServicioAsignado = 1;
		String descripcion = "Servicio de baño completo";
		Double costoBase = 20000.0;
		
		ServicioBaño servicio = new ServicioBaño(idServicioAsignado, descripcion, costoBase);
		
		Boolean seAgregoLaPrimera = this.gestionVeterinaria.registrarNuevoServicio(servicio);
		Boolean seAgregoLaSegunda = this.gestionVeterinaria.registrarNuevoServicio(servicio);
		
		assertTrue(seAgregoLaPrimera);
		assertFalse(seAgregoLaSegunda);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarDosServiciosDistintosConElMismoIdAl2doObtengoFalse() {
		Integer idServicio = 1;
		String descripcion = "Servicio de baño simple";
		Double costoBase = 12000.0;
		
		ServicioBaño bañoBasico = new ServicioBaño(idServicio, descripcion, costoBase);
		
		Integer otroId = 1; // volvemos a usar el mismo id aca
		String otraDescripcion = "Servicio de baño premium";
		Double otroCostoBase = 18000.0;
		
		ServicioBaño bañoPremium = new ServicioBaño(otroId, otraDescripcion, otroCostoBase);
		
		Boolean seAgregoElPrimero = this.gestionVeterinaria.registrarNuevoServicio(bañoBasico);
		Boolean seAgregoElSegundo = this.gestionVeterinaria.registrarNuevoServicio(bañoPremium);
		
		assertTrue(seAgregoElPrimero);
		assertFalse(seAgregoElSegundo);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnServicioConIdNegativoObtengoFalse() {
		Integer idServicio = -1;
		String descripcion = "Servicio de baño simple";
		Double costoBase = 12000.0;
		
		ServicioBaño bañoBasico = new ServicioBaño(idServicio, descripcion, costoBase);
		
		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoServicio(bañoBasico);
		
		assertFalse(seAgrego);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnServicioConUnCostoBaseNegativoObtengoFalse() {
		Integer idServicio = 10;
		String descripcion = "Servicio de baño simple";
		Double costoBase = -15000.0;
		
		ServicioBaño bañoBasico = new ServicioBaño(idServicio, descripcion, costoBase);
		
		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoServicio(bañoBasico);
		
		assertFalse(seAgrego);
	}
	
	
	@Test
	public void dadoQueExisteUnaVeterinariaPorHerenciaObtengoQuePuedoCrearUnTurnoDevolviendomeTrue() {
		Turno turno = new Turno();
		Boolean resultadoObtenido = gestionVeterinaria.registrarTurno(turno);
		assertTrue(resultadoObtenido);
	}
	
	
	@Test
	public void dadoQueExisteUnaVeterinariaPorHerenciaObtengoQuePuedoCrearUnTurnoConAtrinutosCuandoLoConsultoObtengoSusAtributos() {
		//Paso a registrar el clinete
		
		
		String nombre = "Leonardo";
		String apellido = "Saavedra";
		Long dni = 45369852L;
		
		Long nroCliente = 3L;
		String direccion = "Calle falsa 1234";
		String telefono = "123456789";
		Double saldo = 25000.0;
		
		 Cliente cliente = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		
		 Boolean seRegistroCliente = this.gestionVeterinaria.registrarNuevoCliente(cliente);
		assertTrue(seRegistroCliente);
		 
		 
		 
		//Paso a registrar a la mascota 
		Integer id = 10;
		String nombreFelino = "Panqueque";
		Double peso = 2.5;
		Double altura = 0.35;
		String fechaNacimiento = "2025-06-02";
		Long dniDueño = 45369852L;
		String especie = "Siames";
		String colorPrincipal = "Gris";
		String colorSecundario = "Blanco";
		
		Animal gato = new Felino(id, nombreFelino, peso, altura, fechaNacimiento, dniDueño, especie, colorPrincipal, colorSecundario);
		Boolean seRegistroGato = this.gestionVeterinaria.registrarMascota(gato);
		
		assertTrue(seRegistroGato);
		
		
		
		
		
		//Paso a registrar al doctor 
		String nombreDoctor = "Matias";
		String apellidoDOctor = "Martinez";
		Long dniDoctor = 31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		Double salario = 10000.0;
		Long nroMatricula = 204060100L;
		String especialidad = "Cardiologia";
		
		Especialista especialista = new Especialista(nombreDoctor, apellidoDOctor, dniDoctor, nroLegajo, fechaIngreso, salario, nroMatricula, especialidad);
		Boolean seRegistroEspecialista = gestionVeterinaria.registrarNuevoEmpleado(especialista);
		assertTrue(seRegistroEspecialista);
		
		//Paso a crear la hora del turno 
		LocalDateTime horaTurno = LocalDateTime.of(2025, 2, 5, 15, 0);
		//Paso a crear la hora en la que se efectuo el turno
		LocalDateTime horaCreacionTurno = LocalDateTime.now();
		
		//Paso a asignarle un id al turno manualmente, luego sera autoincrementado 
		Integer idTurno = 0;
		//Paso a registrar el turno 
		
		
		Turno nuevoTurno = new Turno(cliente.getDni(),gato.getId(),especialista.getNroLegajo(),horaTurno,horaCreacionTurno,idTurno);
		
		Boolean turnoCreado = gestionVeterinaria.registrarTurno(nuevoTurno);
		 
		assertTrue(turnoCreado);
		
		
		//Paso a consultar el turno
		
		Long dniClienteObtenido = nuevoTurno.getDni();
		Long idMascotaObtenida = nuevoTurno.getNumeroLegajoDoctor();
		LocalDateTime horaCreacionDeTurnoObtenida = nuevoTurno.getHoraCreacionTurno();
		LocalDateTime horaTurnoObtenida = nuevoTurno.getHoraTurno();
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
