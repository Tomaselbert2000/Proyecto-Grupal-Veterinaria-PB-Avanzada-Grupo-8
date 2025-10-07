package ar.edu.unlam.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.dominio.enums.Especialidad;
import ar.edu.unlam.dominio.enums.TipoCirugia;
import ar.edu.unlam.dominio.enums.TipoEspecialidad;
import ar.edu.unlam.dominio.enums.TipoVacuna;
import ar.edu.unlam.dominio.gestion.Veterinaria;
import ar.edu.unlam.dominio.subclass.Cirugia;
import ar.edu.unlam.dominio.subclass.Cliente;
import ar.edu.unlam.dominio.subclass.ConsultaGeneral;
import ar.edu.unlam.dominio.subclass.Felino;
import ar.edu.unlam.dominio.subclass.PersonalVeterinaria;
import ar.edu.unlam.dominio.subclass.Vacunacion;
import ar.edu.unlam.dominio.subclass.Especialista;
import ar.edu.unlam.dominio.superclass.Animal;
import ar.edu.unlam.dominio.superclass.Empleado;
import ar.edu.unlam.dominio.superclass.Persona;
import ar.edu.unlam.dominio.superclass.Servicio;
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

		// agregamos el parametro de delta = 0 para los metodos que comparan valores
		// numericos
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
		Boolean seRegistroLaSegunda = this.gestionVeterinaria.registrarNuevaPersona(clienteDePrueba);

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

		Cliente segundoClienteDePrueba = new Cliente(nombre2, apellido2, dni2, nroCliente2, telefono2, direccion2,
				saldo2);

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
		Long idDueño = 1L; // aca tenemos el id del dueño pero no tenemos ninguno registrado en el sistema
							// con ese id

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

		// ahora vamos a probar crear dos mascotas con datos distintos pero el mismo id,
		// ambas asociadas al cliente
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

		// asi como con las demas clases, aca vamos a usar la sobreescritura del
		// hashcode y de equals
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

		// ahora creamos una mascota con un id erroneo a proposito para verificar que no
		// lo deja pasar
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
		String fechaNacimiento = "2020-13-20"; // ponemos una fecha erronea a proposito para probar que el sistema lo
												// valida antes de seguir
		Long dniDueño = 10L;

		Animal mascotaConFechaInvalida = new Animal(idMascota, nombreMascota, peso, altura, fechaNacimiento, dniDueño);

		Boolean seRegistro = this.gestionVeterinaria.registrarMascota(mascotaConFechaInvalida);

		assertFalse(seRegistro);
	}

	// implementamos subclases de animal correspondiente a cada especie que queramos
	// agregar

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

		Felino gato = new Felino(id, nombre, peso, altura, fechaNacimiento, dniDueño, especie, colorPrincipal,
				colorSecundario);

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

		Felino gato = new Felino(id, nombreMascota, peso, altura, fechaNacimiento, dniDueño, especie, colorPrincipal,
				colorSecundario);

		Boolean seRegistroLaSubclase = this.gestionVeterinaria.registrarMascota(gato);

		assertTrue(seRegistroLaSubclase);

		// comprobamos que la clase heredada cumple con las demas restricciones que
		// aplicamos antes como
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
		Especialidad especialidadDelDoctor = Especialidad.CIRUGIA;

		Especialista especialista = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario,
				nroMatricula, especialidadDelDoctor);

		assertEquals(nombre, especialista.getNombre());
		assertEquals(apellido, especialista.getApellido());
		assertEquals(dni, especialista.getDni());
		assertEquals(nroLegajo, especialista.getNroLegajo());
		assertEquals(fechaIngreso, especialista.getFechaIngreso());
		assertEquals(salario, especialista.getSalario());
		assertEquals(nroMatricula, especialista.getNroMatricula());
		assertEquals(especialidadDelDoctor, especialista.getEspecialistaDoctor());
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
		Especialidad especialidad = Especialidad.CIRUGIA;

		Especialista especialista = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario,
				nroMatricula, especialidad);

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
		Especialidad especialidad = Especialidad.CIRUGIA;

		Especialista especialista = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario,
				nroMatricula, especialidad);

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
		Especialidad especialidad = Especialidad.CIRUGIA;

		Especialista especialistaOriginal = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario,
				nroMatricula, especialidad);

		String nombre2 = "Juan";
		String apellido2 = "Perez";
		Long dni2 = 46L;
		Long nroLegajo2 = 3L;
		String fechaIngreso2 = "2024-09-22";
		Double salario2 = 22000.0;
		Long nroMatriculaRepetido = 204060100L; // repetimos el nro de matricula para que el sistema lo valide como
												// incorrecto
		Especialidad especialidad2 = Especialidad.CIRUGIA;

		Especialista especialistaRepetido = new Especialista(nombre2, apellido2, dni2, nroLegajo2, fechaIngreso2,
				salario2, nroMatriculaRepetido, especialidad2);

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
		Especialidad especialidad = Especialidad.CIRUGIA;

		Especialista especialista = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario,
				nroMatricula, especialidad);
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

		PersonalVeterinaria personalNuevo = new PersonalVeterinaria(nombre, apellido, dni, nroLegajo, fechaIngreso,
				salario, areaAsignada);

		assertEquals(nombre, personalNuevo.getNombre());
		assertEquals(apellido, personalNuevo.getApellido());
		assertEquals(dni, personalNuevo.getDni());
		assertEquals(nroLegajo, personalNuevo.getNroLegajo());
		assertEquals(fechaIngreso, personalNuevo.getFechaIngreso());
		assertEquals(salario, personalNuevo.getSalario());
		assertEquals(areaAsignada, personalNuevo.getAreaAsignada());
	}

	/*
	 * a partir de este punto vamos probando como relacionar las distintas entidades
	 * a traves de los turnos ya que un cliente reserva un turno para X mascota, la
	 * cual ademas es atendida ya sea por un especialista en caso de ser un tema de
	 * salud o bien por el personal de la veterinaria como puede ser para baño/corte
	 * de pelo Lo cual además implica un servicio que luego debe descontarse del
	 * saldo del cliente que lo solicita
	 */

	// para mejorar la modularizacion del codigo, el servicio es una superclase
	// que tendra caracteristicas basicas y luego otras especificas la van a usar
	// como punto de partida

	@Test
	public void dadoQueExisteUnaClaseServicioObtengoQuePuedoConsultarSusAtributos() {
		Integer idServicioAsignado = 1;
		String descripcion = "Servicio de baño completo";
		Double costoBase = 25000.0;

		Servicio servicio = new Servicio(idServicioAsignado, descripcion, costoBase);

		assertEquals(idServicioAsignado, servicio.getId());
		assertEquals(descripcion, servicio.getDescripcion());
		assertEquals(costoBase, servicio.getCostoBase());
	}

	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnNuevoServicioObtengoTrue() {
		Integer idServicioAsignado = 1;
		String descripcion = "Servicio de baño completo";
		Double costoBase = 25000.0;

		Servicio servicio = new Servicio(idServicioAsignado, descripcion, costoBase);
		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoServicio(servicio);

		assertTrue(seAgrego);
	}

	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarDosVecesElMismoServicioAla2daObtengoFalse() {
		Integer idServicioAsignado = 1;
		String descripcion = "Servicio de baño completo";
		Double costoBase = 20000.0;

		Servicio servicio = new Servicio(idServicioAsignado, descripcion, costoBase);

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

		Servicio bañoBasico = new Servicio(idServicio, descripcion, costoBase);

		Integer otroId = 1; // volvemos a usar el mismo id aca
		String otraDescripcion = "Servicio de baño premium";
		Double otroCostoBase = 18000.0;

		Servicio bañoPremium = new Servicio(otroId, otraDescripcion, otroCostoBase);

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

		Servicio bañoBasico = new Servicio(idServicio, descripcion, costoBase);

		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoServicio(bañoBasico);

		assertFalse(seAgrego);
	}

	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnServicioConUnCostoBaseNegativoObtengoFalse() {
		Integer idServicio = 10;
		String descripcion = "Servicio de baño simple";
		Double costoBase = -15000.0;

		Servicio bañoBasico = new Servicio(idServicio, descripcion, costoBase);

		Boolean seAgrego = this.gestionVeterinaria.registrarNuevoServicio(bañoBasico);

		assertFalse(seAgrego);
	}

	@Test
	public void dadoQueExisteUnaUnaConsultaGeneralPorHerenciaPuedoObtenerSusAtributos() {
		Integer idServicio = 1;
		String descripcion = "";
		Double costoBase = 15000D;
		TipoEspecialidad especialidad = TipoEspecialidad.VACUNACION;

		ConsultaGeneral consultaGeneral = new ConsultaGeneral(idServicio, descripcion, costoBase, especialidad);

		Integer idServicioObtenido = consultaGeneral.getId();
		String descripcionObtenida = consultaGeneral.getDescripcion();
		Double costoBaseObtenido = consultaGeneral.getCostoBase();
		TipoEspecialidad especialidadObtenida = consultaGeneral.getEspecialidad();

		assertEquals(idServicio, idServicioObtenido);
		assertEquals(descripcion, descripcionObtenida);
		assertEquals(costoBase, costoBaseObtenido);
		assertEquals(especialidad, especialidadObtenida);

	}

	@Test
	public void dadoQueExisteUnaUnaVacunacionPorHerenciaPuedoObtenerSusAtributos() {
		Integer idServicio = 1;
		String descripcion = "";
		Double costoBase = 15000D;
		TipoVacuna vacuna = TipoVacuna.ANTIRRABICA;

		Vacunacion vacunacion = new Vacunacion(idServicio, descripcion, costoBase, vacuna);

		Integer idServicioObtenido = vacunacion.getId();
		String descripcionObtenida = vacunacion.getDescripcion();
		Double costoBaseObtenido = vacunacion.getCostoBase();
		TipoVacuna vacunaObtenida = vacunacion.getVacuna();

		assertEquals(idServicio, idServicioObtenido);
		assertEquals(descripcion, descripcionObtenida);
		assertEquals(costoBase, costoBaseObtenido);
		assertEquals(vacuna, vacunaObtenida);

	}

	@Test
	public void dadoQueExisteUnaCirugiaPorHerenciaPuedoObtenerSusAtributos() {
		Integer idServicio = 1;
		String descripcion = "";
		Double costoBase = 15000D;
		TipoCirugia Cirugia = TipoCirugia.CASTRACION;

		Cirugia cirugia = new Cirugia(idServicio, descripcion, costoBase, Cirugia);

		Integer idServicioObtenido = cirugia.getId();
		String descripcionObtenida = cirugia.getDescripcion();
		Double costoBaseObtenido = cirugia.getCostoBase();
		TipoCirugia cirugiaObtenida = cirugia.getTipoCirugia();

		assertEquals(idServicio, idServicioObtenido);
		assertEquals(descripcion, descripcionObtenida);
		assertEquals(costoBase, costoBaseObtenido);
		assertEquals(Cirugia, cirugiaObtenida);

	}

	@Test
	public void dadoQueExisteUnaVeterinariaPorHerenciaObtengoQuePuedoCrearUnTurnoDevolviendomeTrue() {
		Cliente clientePrueba = new Cliente("Tomas", "Elbert", 204060L, 1L, "12345", "Calle falsa 123", 25000.0);
		this.gestionVeterinaria.registrarNuevoCliente(clientePrueba);
		
		Animal mascotaPrueba = new Animal(1, "Mimi", 2.5, 0.30, "2025-05-12", 204060L);
		this.gestionVeterinaria.registrarMascota(mascotaPrueba);
		
		Especialista especialistaPrueba = new Especialista("Juan", "Perez", 103050L, 1L, "2019-11-06", 250000.0, 369L, Especialidad.CIRUGIA);
		this.gestionVeterinaria.registrarNuevoEmpleado(especialistaPrueba);
		
		Servicio servicioPrueba = new Servicio(1, "Corte de pelo", 10000.0);
		this.gestionVeterinaria.registrarNuevoServicio(servicioPrueba);
		
		Turno turno = new Turno(clientePrueba.getNroCliente(), mascotaPrueba.getId(), especialistaPrueba.getNroLegajo(), servicioPrueba, LocalDateTime.now(), 1);
		Boolean resultadoObtenido = this.gestionVeterinaria.registrarTurno(turno);
		assertTrue(resultadoObtenido);
	}

	@Test
	public void dadoQueExisteUnaVeterinariaPorHerenciaObtengoQuePuedoCrearUnTurnoConAtributosCuandoLoConsultoObtengoSusAtributos() {
		// Generamos el entorno para el test

		// Cliente
		String nombre = "Leonardo";
		String apellido = "Saavedra";
		Long dni = 45369852L;
		Long nroCliente = 3L;
		String direccion = "Calle falsa 1234";
		String telefono = "123456789";
		Double saldo = 25000.0;
		Cliente cliente = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		this.gestionVeterinaria.registrarNuevoCliente(cliente);

		// Mascota
		Integer id = 10;
		String nombreFelino = "Panqueque";
		Double peso = 2.5;
		Double altura = 0.35;
		String fechaNacimiento = "2025-06-02";
		Long dniDueño = 45369852L;
		String especie = "Siames";
		String colorPrincipal = "Gris";
		String colorSecundario = "Blanco";
		Animal gato = new Felino(id, nombreFelino, peso, altura, fechaNacimiento, dniDueño, especie, colorPrincipal,
				colorSecundario);
		this.gestionVeterinaria.registrarMascota(gato);

		// Doctor
		String nombreDoctor = "Matias";
		String apellidoDOctor = "Martinez";
		Long dniDoctor = 31L;
		Long nroLegajo = 1L;
		String fechaIngreso = "2025-01-01";
		Double salario = 10000.0;
		Long nroMatricula = 204060100L;
		Especialidad especialidad = Especialidad.CIRUGIA;
		Especialista especialista = new Especialista(nombreDoctor, apellidoDOctor, dniDoctor, nroLegajo, fechaIngreso,
				salario, nroMatricula, especialidad);
		this.gestionVeterinaria.registrarNuevoEmpleado(especialista);

		LocalDateTime horaCreacionTurno = LocalDateTime.now();

		// Paso a asignarle un id al turno manualmente, luego sera autoincrementado
		Integer idTurno = 0;

		// Paso a agregarle un costo a la consulta y registro del servicio

		Integer idServicio = 2;
		String descripcion = "Cirujia cardiovascular";
		Double costoBase = 200000.0D;
		TipoCirugia tipoCirugia = TipoCirugia.CIRUGIA_CARDIOVASCULAR;
		Servicio servicio = new Cirugia(idServicio, descripcion, costoBase, tipoCirugia);
		Turno nuevoTurno = new Turno(cliente.getDni(), gato.getId(), especialista.getNroLegajo(), servicio,
				horaCreacionTurno, idTurno);
		this.gestionVeterinaria.registrarTurno(nuevoTurno);

		// Paso a consultar el turno

		Long nroClienteObtenido = nuevoTurno.getNroCliente();
		Integer idMascotaObtenida = nuevoTurno.getIdMascota();
		Long numeroDeLegajoEspecialistaObtenido = nuevoTurno.getNroLegajoEspecialistaConsultado();
		Servicio servicioObtenido = nuevoTurno.getServicio();
		LocalDateTime horaTurnoObtenida = nuevoTurno.getHoraCreacionTurno();
		Integer idTurnoObtenido = nuevoTurno.getIdTurno();

		assertEquals(cliente.getDni(), nroClienteObtenido);
		assertEquals(gato.getId(), idMascotaObtenida);
		assertEquals(especialista.getNroLegajo(), numeroDeLegajoEspecialistaObtenido);
		assertEquals(servicioObtenido, servicio);
		assertEquals(horaTurnoObtenida, horaCreacionTurno);
		assertEquals(idTurnoObtenido, idTurno);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnTurnoConUnClienteNoRegistradoObtengoFalse() {
		
		Animal mascotaPrueba = new Animal(10, "Mimi", 2.5, 0.30, "2023-01-03", 123L); // apuntamos al dueño con su clave primaria
		this.gestionVeterinaria.registrarMascota(mascotaPrueba);
		
		Especialidad especialidadParaPrueba = Especialidad.CLINICA_GENERAL;
		Especialista nuevoEspecialista = new Especialista("Juan", "Perez", 12345L, 2L, "2022-03-25", 250000.0, 895632L, especialidadParaPrueba);
		this.gestionVeterinaria.registrarNuevoEmpleado(nuevoEspecialista);
		
		Servicio servicioCortePelo = new Servicio(1, "Corte de pelo", 12000.0);
		this.gestionVeterinaria.registrarNuevoServicio(servicioCortePelo);
		
		Integer idParaElTurno = 1;
		LocalDateTime fechaYhora = LocalDateTime.of(2025, 10, 7, 17, 15);
		
		// para el turno ponemos un valor de nro de cliente que sabemos que no corresponde a ningun cliente que se haya registrado
		Turno turnoInvalido = new Turno(5L, mascotaPrueba.getId(), nuevoEspecialista.getNroLegajo(), servicioCortePelo, fechaYhora, idParaElTurno);
		
		// la validacion del turno falla cuando encuentra el valor del nro cliente, la siguiente linea tiene que dar false
		Boolean seRegistroElTurno = this.gestionVeterinaria.registrarTurno(turnoInvalido);
		
		assertFalse(seRegistroElTurno); 
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnTurnoConUnEspecialistaNoRegistradoObtengoFalse() {
		Cliente cliente = new Cliente("Tomas", "Elbert", 123L, 1L, "1234567", "Calle falsa 123", 25000.0);
		this.gestionVeterinaria.registrarNuevoCliente(cliente);
		
		Animal mascotaPrueba = new Animal(10, "Mimi", 2.5, 0.30, "2023-01-03", 123L); // apuntamos al dueño con su clave primaria
		this.gestionVeterinaria.registrarMascota(mascotaPrueba);
		
		Especialidad especialidadParaPrueba = Especialidad.CLINICA_GENERAL;
		Especialista nuevoEspecialista = new Especialista("Juan", "Perez", 12345L, 2L, "2022-03-25", 250000.0, 895632L, especialidadParaPrueba);
		// notar que este especialista fue creado pero no registrado
		
		Servicio servicioCortePelo = new Servicio(1, "Corte de pelo", 12000.0);
		this.gestionVeterinaria.registrarNuevoServicio(servicioCortePelo);
		
		Turno turnoInvalido = new Turno(cliente.getNroCliente(), mascotaPrueba.getId(), nuevoEspecialista.getNroLegajo(), servicioCortePelo, LocalDateTime.now(), 1);
		Boolean seRegistroElTurno = this.gestionVeterinaria.registrarTurno(turnoInvalido);
		
		assertFalse(seRegistroElTurno);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnTurnoConUnaMascotaNoRegistradaObtengoFalse() {
		Cliente cliente = new Cliente("Tomas", "Elbert", 123L, 1L, "1234567", "Calle falsa 123", 25000.0);
		this.gestionVeterinaria.registrarNuevoCliente(cliente);
		
		Animal mascotaPrueba = new Animal(10, "Mimi", 2.5, 0.30, "2023-01-03", 123L); // apuntamos al dueño con su clave primaria
		// en este caso creamos la mascota pero no la registramos
		
		Especialidad especialidadParaPrueba = Especialidad.CLINICA_GENERAL;
		Especialista nuevoEspecialista = new Especialista("Juan", "Perez", 12345L, 2L, "2022-03-25", 250000.0, 895632L, especialidadParaPrueba);
		this.gestionVeterinaria.registrarNuevoEmpleado(nuevoEspecialista);
		
		Servicio servicioCortePelo = new Servicio(1, "Corte de pelo", 12000.0);
		this.gestionVeterinaria.registrarNuevoServicio(servicioCortePelo);
		
		Turno turnoInvalido = new Turno(cliente.getNroCliente(), mascotaPrueba.getId(), nuevoEspecialista.getNroLegajo(), servicioCortePelo, LocalDateTime.now(), 1);
		Boolean seRegistroElTurno = this.gestionVeterinaria.registrarTurno(turnoInvalido);
		
		assertFalse(seRegistroElTurno);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaSiIntentoRegistrarUnTurnoConUnServicioNoRegistradoObtengoFalse() {
		Cliente cliente = new Cliente("Tomas", "Elbert", 123L, 1L, "1234567", "Calle falsa 123", 25000.0);
		this.gestionVeterinaria.registrarNuevoCliente(cliente);
		
		Animal mascotaPrueba = new Animal(10, "Mimi", 2.5, 0.30, "2023-01-03", 123L); // apuntamos al dueño con su clave primaria
		this.gestionVeterinaria.registrarMascota(mascotaPrueba);
		
		Especialidad especialidadParaPrueba = Especialidad.CLINICA_GENERAL;
		Especialista nuevoEspecialista = new Especialista("Juan", "Perez", 12345L, 2L, "2022-03-25", 250000.0, 895632L, especialidadParaPrueba);
		this.gestionVeterinaria.registrarNuevoEmpleado(nuevoEspecialista);
		
		Servicio servicioCortePelo = new Servicio(1, "Corte de pelo", 12000.0);
		
		Turno turnoInvalido = new Turno(cliente.getNroCliente(), mascotaPrueba.getId(), nuevoEspecialista.getNroLegajo(), servicioCortePelo, LocalDateTime.now(), 1);
		Boolean seRegistroElTurno = this.gestionVeterinaria.registrarTurno(turnoInvalido);
		
		assertFalse(seRegistroElTurno);
	}
	
	@Test
	public void dadoQueExisteUnaVeterinariaUnClienteConSaldo25000YUnaMascotaSiElServicioVale10000obtengoQueSuNuevoSaldoEs15Mil() {
		// generamos las instancias de objetos necesarias y registramos para ejecutar el test
		
		Cliente cliente = new Cliente("Tomas", "Elbert", 123L, 1L, "1234567", "Calle falsa 123", 25000.0);
		this.gestionVeterinaria.registrarNuevoCliente(cliente);
		
		Animal mascotaPrueba = new Animal(10, "Mimi", 2.5, 0.30, "2023-01-03", 123L); // apuntamos al dueño con su clave primaria
		this.gestionVeterinaria.registrarMascota(mascotaPrueba);
		
		Especialidad especialidadParaPrueba = Especialidad.CLINICA_GENERAL;
		Especialista nuevoEspecialista = new Especialista("Juan", "Perez", 12345L, 2L, "2022-03-25", 250000.0, 895632L, especialidadParaPrueba);
		this.gestionVeterinaria.registrarNuevoEmpleado(nuevoEspecialista);
		
		Servicio servicioCortePelo = new Servicio(1, "Corte de pelo", 12000.0);
		this.gestionVeterinaria.registrarNuevoServicio(servicioCortePelo);
		
		// creamos el turno una vez tenemos los datos necesarios registrados
		Integer idParaElTurno = 1;
		LocalDateTime fechaYhora = LocalDateTime.of(2025, 10, 7, 17, 15);
		Turno nuevoTurno = new Turno(cliente.getDni(), mascotaPrueba.getId(), nuevoEspecialista.getNroLegajo(), servicioCortePelo, fechaYhora, idParaElTurno);
		this.gestionVeterinaria.registrarTurno(nuevoTurno);
		
		Double saldoDelClienteEsperado = 13000.0;
		Double saldoDelClienteObtenido = cliente.getSaldo();
		
		assertEquals(saldoDelClienteEsperado, saldoDelClienteObtenido);
	}
}
