package app.core.engine;

public abstract class Core {

	/**
	 * Funcion principal de un <i>Core</i>. Debe seguir los siguientes
	 * requisitos:<br>
	 * &emsp; --> Controlar que el usuario no pueda tocar el tablero mientras se
	 * realizan calculos <br>
	 * &emsp; --> Calcular movimiento <br>
	 * &emsp; --> Modificar el tablero tras realizar calculos
	 * </p>
	 * 
	 * @param board Tablero en el que se va a hacer el movimiento.
	 */
	public abstract void makeMove();
}