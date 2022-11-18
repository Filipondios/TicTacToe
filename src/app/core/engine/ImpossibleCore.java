package app.core.engine;

import app.core.board.Board;

public class ImpossibleCore extends Core {

	/** Crea un ImpossibleCore (core de dificultad imposible) y asicia a este un tablero.
	 * @param board  Tablero donde se van a realizar todos los movimientos durante la partida. */
	public ImpossibleCore(Board board) {
		super(board);
	}
	
	@Override
	public void makeMove() {
		
	}	
}