package org.iesalandalus.programacion.torreajedrez;

import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Torre {
	private Color color;
	private Posicion posicion;
	
	
	
	// creamos constructor 8,h
	public Torre() {
		setColor(Color.NEGRO);
		setPosicion(new Posicion(8,'h'));
	}
	// creamos constructor color
	public Torre(Color color) {
		setColor(color);
		if (color.equals(Color.NEGRO)) {
			setPosicion(new Posicion(8,'h'));
		}
		else {
			setPosicion(new Posicion(1,'h'));
		}
	}
	
	public Torre(Color color, char columna) {
		setColor(color);
		
		if (columna=='A' || columna=='H' || columna=='a' || columna == 'h') 
		{
			if (color.equals(Color.BLANCO)) 
			{
				setPosicion(new Posicion(1,columna));
			} else
			{
				setPosicion(new Posicion(8,columna));
			}
		} else
			throw new IllegalArgumentException("ERROR: Columna no válida.");
	}
	
	public Color getColor() {
		return color;
	}
	private void setColor(Color color) {
		if (color == null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		this.color = color;
	}
	public Posicion getPosicion() {
		return posicion;
	}
	private void setPosicion(Posicion posicion) {
		if (posicion == null) {
			throw new NullPointerException("ERROR: La posicion no puede ser nula.");
		}
		this.posicion = posicion;
	}
	

	public void mover (Direccion direccion, int pasos) throws OperationNotSupportedException {
		if (pasos < 1) 
		{
			throw new IllegalArgumentException("ERROR: El número de pasos debe ser positivo.");
		} else if (direccion==null)
		{
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		switch (direccion) {
			case ARRIBA:
				if (color==Color.NEGRO)
				{
					try {
						setPosicion(new Posicion(posicion.getFila() -pasos , posicion.getColumna()));
					} catch (IllegalArgumentException e) {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
					}
				} else
				{
					try {
						setPosicion(new Posicion(posicion.getFila() +pasos , posicion.getColumna()));
					} catch (IllegalArgumentException e) {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
					}
				}
			break;
			case ABAJO:
				if (color==Color.NEGRO)
				{
					try {
						setPosicion(new Posicion(posicion.getFila() +pasos , posicion.getColumna()));
					} catch (IllegalArgumentException e) {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
					}
				} else
				{
					try {
						setPosicion(new Posicion(posicion.getFila() -pasos , posicion.getColumna()));
					} catch (IllegalArgumentException e) {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
					}
				}
			break;
			case IZQUIERDA:
				if (color==Color.NEGRO)
				{
					try {
						setPosicion(new Posicion(posicion.getFila() , (char)(posicion.getColumna() +pasos)));
					} catch (IllegalArgumentException e) {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
					}
				} else
				{
					try {
						setPosicion(new Posicion(posicion.getFila() , (char)(posicion.getColumna() -pasos)));
					} catch (IllegalArgumentException e) {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
					}
				}
			break;
			case DERECHA:
				if (color==Color.NEGRO)
				{
					try {
						setPosicion(new Posicion(posicion.getFila() , (char)(posicion.getColumna() -pasos)));
					} catch (IllegalArgumentException e) {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
					}
				} else
				{
					try {
						setPosicion(new Posicion(posicion.getFila() , (char)(posicion.getColumna() +pasos)));
					} catch (IllegalArgumentException e) {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido (se sale del tablero).");
					}
				}
			break;
			default:
				throw new NullPointerException("ERROR: La dirección no puede ser nula");
		}
	}
	

	public void enrocar (Direccion direccion) throws OperationNotSupportedException {
		if (direccion==null) {
			throw new NullPointerException("ERROR: La dirección no puede ser nula.");
		}
		switch (direccion) {
		case ENROQUE_CORTO:
			if (posicion.getColumna()=='h' && (posicion.getFila()==1 || posicion.getFila()==8))
			{
				setPosicion(new Posicion(posicion.getFila(),'f'));
			} else 
			{
				throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
			}
			break;
		case ENROQUE_LARGO:
			if (posicion.getColumna()=='a' && (posicion.getFila()==1 || posicion.getFila()==8))
			{
				setPosicion(new Posicion(posicion.getFila(),'d'));
			}else 
			{
				throw new OperationNotSupportedException("ERROR: Movimiento de enroque no válido.");
			} 
			break;
		default:
			break;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, posicion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Torre other = (Torre) obj;
		return color == other.color && Objects.equals(posicion, other.posicion);
	}

	@Override
	public String toString() {
		return  "La fila es= " + posicion.getFila() + ", La columna es= " + posicion.getColumna() + ", El color es= " + color;
	}
		
	
}
