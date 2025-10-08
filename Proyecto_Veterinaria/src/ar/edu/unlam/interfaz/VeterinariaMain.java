package ar.edu.unlam.interfaz;

import java.util.Scanner;

import ar.edu.unlam.dominio.enums.Especialidad;
import ar.edu.unlam.dominio.enums.TipoCirugia;
import ar.edu.unlam.dominio.enums.TipoEspecialidad;
import ar.edu.unlam.dominio.enums.TipoVacuna;
import ar.edu.unlam.interfaz.enums.MenuPrincipal;
import ar.edu.unlam.dominio.gestion.Veterinaria;
import ar.edu.unlam.dominio.subclass.Cliente;
import ar.edu.unlam.dominio.subclass.Especialista;
import ar.edu.unlam.dominio.subclass.Felino;
import ar.edu.unlam.dominio.subclass.PersonalVeterinaria;
import ar.edu.unlam.dominio.superclass.Animal;

public class VeterinariaMain {
	private static Scanner teclado = new Scanner(System.in);
	private static Veterinaria gestionVeterinaria = new Veterinaria();
	public static final String RESET = "\u001B[0m";
	public static final String ROJO = "\u001B[31m";
	public static final String VERDE = "\u001B[32m";
	public static final String AMARILLO = "\u001B[33m";
	public static final String AZUL = "\u001B[34m";
	public static final String AMARILLO_CLARO = "\u001B[93m";
	public static final String BLANCO = "\u001B[37m";

	public static void main(String[] args) {
		iniciarSistema();
		agregarObjetos();
	}

	private static void iniciarSistema() {
		MenuPrincipal.obtenerMenuPrincipal();
		MenuPrincipal opcionMenu;
		do {
			opcionMenu = ingresarOpcionDelMenuPrincipal();
			switch (opcionMenu) {
			case REGISTRAR_DOCTOR:
				registrarDoctor();
				break;
			case AGREGAR_PERSONAL:
				agregarPersonal();
				break;
			case REGISTRAR_CLIENTE:
				registrarUnNuevoCliente();
				break;
			case REGISTRAR_MASCOTA:
				registrarMascotaASuDueño();
				break;
			case CREAR_TURNO:
				creaerTurno();
				break;
			case VER_CUENTA_DEL_CLIENTE:
				verDatosDelCliente();
				break;
			case VER_HISTORIAL:
				verElHistorial();
				break;
			case SALIR:
				mostrarEnAzul("\nSaliendo del sistema de Veterinaria");
				break;
			default:
				mostrarEnRojo("\nHas ingresado una opcion no valida, intente nuevamente");
				break;

			}

		} while (!opcionMenu.equals(MenuPrincipal.SALIR));

	}

	

	private static void registrarUnNuevoCliente() {
		String nombre = "";
		String apellido = "";
		long nroCliente = 0;
		double saldo = 0.0;
		do {
			nombre = ingresarString("\nIngrese el nombre de pila del cliente");
		} while (nombre.isEmpty());

		do {
			apellido = ingresarString("\nIngrese el apellido del cliente");
		} while (apellido.isEmpty());

		long dni = ingresarEnteroLargo("\nIngrese el numero de documento del cliente");

		do {
			nroCliente = ingresarEnteroLargo("\nColoque que numero de legajo que desea para el cliente");
		} while (nroCliente < 0);

		String telefono = ingresarString("\nIngrese un numero de telefono del ciente");
		String direccion = ingresarString("\nIngrese la direccion del cliente");

		do {
			saldo = ingresarDecimal("\nSaldo que el ciente desea registrar a su cuenta");
		} while (saldo < 0);

		Cliente clienteNuevo = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);
		boolean seResgistro = gestionVeterinaria.registrarNuevoCliente(clienteNuevo);
		if (seResgistro) {
			mostrarEnVerde("\nEl cliente " + clienteNuevo.getApellido()
					+ " se registro con exito, su numero de cliente es " + clienteNuevo.getNroCliente());
		} else {
			mostrarEnRojo("\nAh ocurrido un error el cliente no se pudo registrar, intente nuevamente");
		}
	}

	private static void registrarDoctor() {
		String nombre = "";
		String apellido = "";
		long nroLegajo = 0;
		double salario = 0.0;
		do {
			nombre = ingresarString("\nIngrese el nombre de pila del doctor");
		} while (nombre.isEmpty());

		do {
			apellido = ingresarString("\nIngrese el apellido del doctor");
		} while (apellido.isEmpty());
		long dni = ingresarEnteroLargo("\nIngrese el numero de documento del doctor");
		do {
			nroLegajo = ingresarEnteroLargo("\nColoque que numero de legajo que desea para el doctor");
		} while (nroLegajo < 0);
		String fechaIngreso = ingresarString("\nIngrese la fecha de ingreso a la institucion");
		long nroMatricula = ingresarEnteroLargo("\nIngrese el numero de matricula del doctor");
		do {
			salario = ingresarDecimal("\nSaldo que el doctor posee en su cuenta");
		} while (salario < 0);
		Especialidad especialistaDoctor = ingresarOpcionDeEspecialidadDelDoctor();

		Especialista doctor = new Especialista(nombre, apellido, dni, nroLegajo, fechaIngreso, salario, nroMatricula,
				especialistaDoctor);
		boolean seAgrego = gestionVeterinaria.registrarNuevoEmpleado(doctor);
		if (seAgrego) {
			mostrarEnVerde(
					"\nEl medico " + doctor.getApellido() + " especialista en " + doctor.getEspecialistaDoctor()
							+ " se agrego existosamente, en el dia " + doctor.getFechaIngreso());
		} else {
			mostrarEnRojo("\nEL medico no se pudo agregar, verifique los datos ingresados");
		}
	}

	private static void agregarPersonal() {

		String nombre = "";
		String apellido = "";
		long nroLegajo = 0;
		double salario = 0.0;
		do {
			nombre = ingresarString("\nIngrese el nombre de pila del personal");
		} while (nombre.isEmpty());

		do {
			apellido = ingresarString("\nIngrese el apellido del personal");
		} while (apellido.isEmpty());
		long dni = ingresarEnteroLargo("\nIngrese el numero de documento del personal");
		do {
			nroLegajo = ingresarEnteroLargo("\nColoque que numero de legajo que desea para el personal");
		} while (nroLegajo < 0);
		String fechaIngreso = ingresarString("\nIngrese la fecha de ingreso a la institucion");
		do {
			salario = ingresarDecimal("\nSaldo que el personal posee en su cuenta");
		} while (salario < 0);
		
		String areaAsignada = ingresarString("\nIndique el area a encargo del personal");
		
		PersonalVeterinaria personal = new PersonalVeterinaria(nombre, apellido, dni, nroLegajo, fechaIngreso, salario,
				areaAsignada);
		boolean seAgrego = gestionVeterinaria.registrarNuevoEmpleado(personal);
		if (seAgrego) {
			mostrarEnVerde("\nSe agrego nuevo personal a cargo del area de " + personal.getAreaAsignada());
		} else {
			mostrarEnRojo("\nEL personal no se pudo agregar, verifique los datos ingresados");
		}

	}

	private static void registrarMascotaASuDueño() {
		String nombre = "";
		double peso = 0; 
		double altura = 0;
		
		int idMascota = ingresarNumeroEntero("\nIngrese un numero de indentificacion para la mascota");
		 
		do{
			peso = ingresarDecimal("\nIngrese el peso actual de la mascota");
		}while(peso<0);
		
        do{
			altura = ingresarDecimal("\nIngrese la altura actual de la mascota");
		}while(altura<0);
					
        String fechaNacimiento = ingresarString("\nIngrese la fecha de nacimiento de la mascota");
        
        long idDuenio = ingresarEnteroLargo("\nIngrese el numero de cliente del duenio");
        
        Animal mascota = new Animal (idMascota, nombre , peso , altura , fechaNacimiento, idDuenio);
        
        Boolean seRegistroSiTieneDuenio = gestionVeterinaria.registrarMascota(mascota);
        if(seRegistroSiTieneDuenio) {
        	mostrarEnVerde("\nLa mascota  "+ mascota.getNombre() + " se puedo registrar con exito " );
        }else {
        	mostrarEnRojo("\nNo se ah podido registrar debido a un error o la mascota no tiene un dueño registrado");
        }
        
	}

	private static void creaerTurno() {
		
		Long nroCliente = ingresarEnteroLargo("\nIngrese que Nuemro de cliente que desea buscar");
		Cliente clienteBuscado = gestionVeterinaria.buscarClientePorId(nroCliente);
		
		
	}

	
	private static void verDatosDelCliente() {
		
	}
	
	private static void verElHistorial() {
		
	}
	
	private static MenuPrincipal ingresarOpcionDelMenuPrincipal() {
		mostrarPorPantalla(MenuPrincipal.obtenerMenuPrincipal());
		int numero = 0;
		do {
			numero = ingresarNumeroEntero(BLANCO + "\nIngrese el numero de la opcion:" + RESET);
		} while (numero < 0 || numero > MenuPrincipal.values().length);
		return MenuPrincipal.obtenerOpcionDelMenuPrincipal(numero);
	}

	private static Especialidad ingresarOpcionDeEspecialidadDelDoctor() {
		mostrarPorPantalla(Especialidad.obtenerEspecialistaDoctor());
		int numero = 0;
		do {
			numero = ingresarNumeroEntero(BLANCO + "\nIngrese el numero de la opcion:" + RESET);
		} while (numero < 0 || numero > Especialidad.values().length);
		return Especialidad.obtenerOpcionDeEspecialistaDoctor(numero);
	}

	private static TipoCirugia ingresarOpcionDelTipoDeCirugia() {
		mostrarPorPantalla(TipoCirugia.obtenerTipoCirugia());
		int numero = 0;
		do {
			numero = ingresarNumeroEntero(BLANCO + "\nIngrese el numero de la opcion:" + RESET);
		} while (numero < 0 || numero > TipoCirugia.values().length);
		return TipoCirugia.obtenerOpcionDeTipoCirugia(numero);
	}

	private static TipoVacuna ingresarOpcionDeTipoDeVacuna() {
		mostrarPorPantalla(TipoVacuna.obtenerTipoVacuna());
		int numero = 0;
		do {
			numero = ingresarNumeroEntero(BLANCO + "\nIngrese el numero de la opcion:" + RESET);
		} while (numero < 0 || numero > TipoVacuna.values().length);
		return TipoVacuna.obtenerOpcionDeTipoVacuna(numero);
	}

	private static TipoEspecialidad ingresarOpcionDeTipoDeEspecialidad() {
		mostrarPorPantalla(TipoEspecialidad.obtenerTipoEspecialidad());
		int numero = 0;
		do {
			numero = ingresarNumeroEntero(BLANCO + "\nIngrese el numero de la opcion:" + RESET);
		} while (numero < 0 || numero > TipoEspecialidad.values().length);
		return TipoEspecialidad.obtenerOpcionDeTipoEspecialidad(numero);
	}

	private static int ingresarNumeroEntero(String mensaje) {
		mostrarPorPantalla(AMARILLO + mensaje + RESET);
		return teclado.nextInt();
	}

	private static long ingresarEnteroLargo(String mensaje) {
		mostrarPorPantalla(AMARILLO + mensaje + RESET);
		return teclado.nextLong();
	}

	private static double ingresarDecimal(String mensaje) {
		mostrarPorPantalla(AMARILLO + mensaje + RESET);
		return teclado.nextDouble();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(AMARILLO + mensaje + RESET);
		return teclado.next();
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarEnRojo(String mensaje) {
		System.out.println(ROJO + mensaje + RESET);
	}

	private static void mostrarEnVerde(String mensaje) {
		System.out.println(VERDE + mensaje + RESET);
	}

	private static void mostrarEnAzul(String mensaje) {
		System.out.println(AZUL + mensaje + RESET);
	}
	//Este metodo agrega cliente ,mascotas y doctores.
	private static void agregarObjetos() {
		
		//ESPECIALISTA
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
		gestionVeterinaria.registrarNuevoEmpleado(especialista);
		
		//CLIENTE1
		String nombre = "Tomas";
		String apellido = "Elbert";
		Long dni = 1L;

		Long nroCliente = 1L;
		String direccion = "Calle falsa 123";
		String telefono = "123456";
		Double saldo = 25000.0;

		Cliente primerClienteDePrueba = new Cliente(nombre, apellido, dni, nroCliente, telefono, direccion, saldo);

		//CLIENTE2
		String nombre2 = "Leonardo";
		String apellido2 = "Saavedra";
		Long dni2 = 45369852L;

		Long nroCliente2 = 3L;
		String direccion2 = "Calle falsa 1234";
		String telefono2 = "123456789";
		Double saldo2 = 25000.0;

		Cliente segundoClienteDePrueba = new Cliente(nombre2, apellido2, dni2, nroCliente2, telefono2, direccion2,
				saldo2);

		gestionVeterinaria.registrarNuevaPersona(primerClienteDePrueba);
		gestionVeterinaria.registrarNuevaPersona(segundoClienteDePrueba);
		
		//ANIMAL
		Integer idMascota = 10;
		String nombreMascota = "Firulais";
		Double peso = 10.5;
		Double altura = 0.50;
		String fechaNacimiento = "2023-03-20";
		Long idDueño = 1L;

		Animal nuevaMascota = new Animal(idMascota, nombreMascota, peso, altura, fechaNacimiento, idDueño);
		gestionVeterinaria.registrarMascota(nuevaMascota);
		
		//MASCOTA1
		Integer id = 5;
		String nombreFelino = "Mimi";
		Double pesoFelino = 2.5;
		Double alturaFelino = 0.35;
		String fechaNacimientoDelFelino = "2025-06-01";
		Long dniDueño = 5L;
		String especie = "Siames";
		String colorPrincipal = "Gris";
		String colorSecundario = "Blanco";

		Felino gato = new Felino(id, nombreFelino, pesoFelino, alturaFelino, fechaNacimientoDelFelino, dniDueño, especie, colorPrincipal,
				colorSecundario);
		gestionVeterinaria.registrarMascota(gato);
		
		//MASCOTA2
		Integer id2 = 10;
		String nombreFelino2 = "Panqueque";
		Double peso2 = 2.5;
		Double altura2 = 0.35;
		String fechaNacimiento2 = "2025-06-02";
		Long dniDueño2 = 45369852L;
		String especie2 = "Siames";
		String colorPrincipal2 = "Gris";
		String colorSecundario2 = "Blanco";

		Animal gato2 = new Felino(id2, nombreFelino2, peso2, altura2, fechaNacimiento2, dniDueño2, especie2, colorPrincipal2,
				colorSecundario2);
		gestionVeterinaria.registrarMascota(gato2);
	}

}