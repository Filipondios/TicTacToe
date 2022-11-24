package app.core.engine;

import app.core.board.Board;

public abstract class Core {

	/** Tablero donde se van a realizar todos los movimientos durante la partida. */
	private Board board;

	/**
	 * Crea un Core y asicia a este un tablero.
	 * 
	 * @param board Tablero donde se van a realizar todos los movimientos 
	 * 				durante la partida.
	 */
	public Core(Board board) {
		this.board = board;
	}

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

	public Board getBoard() {
		return this.board;
	}
}