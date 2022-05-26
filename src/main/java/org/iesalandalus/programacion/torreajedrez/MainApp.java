package org.iesalandalus.programacion.torreajedrez;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	private static Torre torre;
	
	public static void main(String[] args) {
		int control=1;
		int selector;
		System.out.println("Torre de Ajedrez");	
		System.out.println("****************");
		do {
			mostrarMenu();
			selector=elegirOpcion();
			ejecutarOpcion(selector);
			mostrarTorre();
			
			if (selector==5) {
				control=0;
			}
		} while (control!=0);
	}

	// Metodo mostrar torre
	
	private static void mostrarTorre() {
		try {
			System.out.println(torre.toString());
		} catch (NullPointerException e){
			System.out.println("ERROR: Hay que crear la torre no está creada.");
		}
	}
	
	// metodo mostrarMenu
	
	private static void mostrarMenu() {
		System.out.println("***");
		System.out.println("***");
		System.out.println("1. Crear torre eligiendo color y posición");
		System.out.println("2. Crear torre por color");
		System.out.println("3. Crear torre por defecto");
		System.out.println("4. Mover la torre");
		System.out.println("5. Salir");
	}


	// Metodo elegirOpcion  y ejecutar opción
	
	private static int elegirOpcion() {
		int opcionMenu;
		
		do {
			System.out.print("Elije una opción: ");
			System.out.print(" ");
			opcionMenu = Entrada.entero();
		} while (opcionMenu<1 || opcionMenu>5);
		return opcionMenu;	
	}
	
	private static void ejecutarOpcion(int opcionEjecucion) {
		switch (opcionEjecucion) {
		case 1:
			crearTorreColorColumna();
			break;
		case 2:
			crearTorreColor();
			break;
		case 3:
			crearTorreDefecto();			
			break;
		case 4:
			mover();
			break;
		case 5:
			System.out.println("¡Hasta luegorrrr!");
			break;
		}
	}
	
	
	
	// Metodo elegirColor
	
	private static Color elegirColor() {
		Color color=null;
		int opcionColor=0;
		do {
			System.out.println("***");
			System.out.println("***");
			System.out.println("1. Negras");
			System.out.println("2. Blancas");
			System.out.println(" ");
			System.out.print("Elija un color: ");
			opcionColor=Entrada.entero();
		} while (opcionColor!=1 && opcionColor!=2);
		
		switch (opcionColor) {
		case 1:
			color=Color.NEGRO;
			break;
		case 2:
			color=Color.BLANCO;
			break;
		}
		
		return color;
	}
	
	//MÃ©todo elegirColumnaInicial
	private static char elegirColumnaInicial() {
		char columna = 0;
		
		do {
			System.out.print("Introduzca la columna deseada (A , H): ");
			System.out.print("");
			columna=Entrada.caracter();
		} while (columna!='a' && columna!='A' && columna!='h' && columna!='H');
	
		return columna;
	}
	
	//Metodo mostrarMenuDirecciones
	
	private static void mostrarMenuDirecciones() {
		System.out.println("***");
		System.out.println("***");
		System.out.println("1. Mover hacia arriba");
		System.out.println("2. Mover hacia abajo");
		System.out.println("3. Mover a izquierda");
		System.out.println("4. Mover a derecha");
		System.out.println("5. Enroque corto");
		System.out.println("6. Erroque largo");
		System.out.println("***");
		System.out.println("***");
	}
	
	//Metodo elegirDireccion
	
	private static Direccion elegirDireccion() {
		Direccion direccion = null;
		int opcionDireccion = 0;
		
		do {
			System.out.println("***");
			System.out.println("***");
			System.out.print("Elija una opción: ");
			opcionDireccion = Entrada.entero();
		} while (opcionDireccion<1 || opcionDireccion>6);
		
		switch (opcionDireccion) {
		case 1:
			direccion=Direccion.ARRIBA;
			break;
		case 2:
			direccion=Direccion.ABAJO;
			break;
		case 3:
			direccion=Direccion.IZQUIERDA;
			break;
		case 4:
			direccion=Direccion.DERECHA;
			break;
		case 5:
			direccion=Direccion.ENROQUE_CORTO;
			break;
		case 6:
			direccion=Direccion.ENROQUE_LARGO;
			break;
		}
		
		return direccion;
	}
	
	// Metodo crearTorreDefecto
	
	private static void crearTorreDefecto() {
		torre=new Torre();
	}
	
	// Metodo  crearTorreColor
	
	private static void crearTorreColor() {
		torre=new Torre(elegirColor());
	}
	
	// Metodo crearTorreColorColumna
	
	private static void crearTorreColorColumna() {
		torre=new Torre(elegirColor(), elegirColumnaInicial());
	}
	
	// Metodo  mover
	
	private static void mover() {
		Direccion direccion=null;
		int pasos;
		if (torre == null)
		{
			System.out.println("ERROR: Hay que crear la torre no está creada.");
		} else 
		{
			mostrarMenuDirecciones();	
			direccion=elegirDireccion();
			
			if (direccion.equals(Direccion.ENROQUE_CORTO) || direccion.equals(Direccion.ENROQUE_LARGO)) {
				try {
					torre.enrocar(direccion);
				} catch (OperationNotSupportedException e) {
					System.out.println(e.getMessage());
				}
			} else
			{
				System.out.println(" ");
				System.out.print("Introduzca el número de pasos: ");
				System.out.print(" ");
				pasos = Entrada.entero();
				
				try {
					torre.mover(direccion, pasos);
				} catch (OperationNotSupportedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	

	
}
