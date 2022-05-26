package org.iesalandalus.programacion.torreajedrez;

import java.util.Objects;

public class Posicion {
	private int fila;
	private char columna;

	// Creamos constructor con parametros fila y columna
	public Posicion(int fila, char columna) {
		setFila(fila);
		setColumna(columna);
	}

	// Creamos constructor copia
	public Posicion(Posicion posicion) {
		if (posicion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una posición nula.");
		}
		setFila(posicion.getFila());
		setColumna(posicion.getColumna());

	}

	// Creamos getter y setter
	public int getFila() {
		return fila;
	}

	private void setFila(int fila) {
		// Comprobamos la fila
		if (fila < 1 || fila > 8) {
			// Lanzamos excepción si no es válida
			throw new IllegalArgumentException("ERROR: Fila no válida.");
		}
		this.fila = fila;
	}

	public char getColumna() {
		return columna;
	}

	private void setColumna(char columna) {
		// Comprobamos la columna
		if (columna < 'a' || columna > 'h') {
			// Lanzamos excepción si no es válida
			throw new IllegalArgumentException("ERROR: Columna no válida.");

		}
		this.columna = columna;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columna, fila);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		return columna == other.columna && fila == other.fila;
	}

	@Override
	public String toString() {
		return "fila=" + fila + ", columna=" + columna;
	}
	
	

}
