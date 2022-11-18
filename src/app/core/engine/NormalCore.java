package app.core.engine;

import app.core.board.Board;

public class NormalCore extends Core{

	/** Crea un NormalCore (core de dificultad normal) y asicia a este un tablero.
	 * @param board  Tablero donde se van a realizar todos los movimientos durante la partida. */
	public NormalCore(Board board) {
		super(board);
	}
	
	@Override
	public void makeMove() {
		
	}	
}