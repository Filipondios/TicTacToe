package app.core.engine;

import app.core.board.Board;
import app.core.board.Ownership;

/** Core que se encarga del modo de juego facil. Calcula una posicion del tablero aleatoria
 * que no este ocupada y la marca.
 * @author Filipondios
 * @version 18.11.2022 */
public class EasyCore extends Core{

	/** Crea un EasyCore (core de dificultad facil) y asicia a este un tablero.
	 * @param board  Tablero donde se van a realizar todos los movimientos durante la partida. */
	public EasyCore(Board board) {
		super(board);
	}

	@Override
	public void makeMove() {
		boolean let = true;
		int chip = 0;
		
		while(let) { // Mientas la casilla calculada este ocupada, seguir el loop
			chip = (int) (Math.random()*8); // Generar casilla del tablero aleatoria
			let = (getBoard().markTile(chip, Ownership.AI)==-1)? true : false; // Comprobar si esta ocupada
		}
		getBoard().markTile(chip, Ownership.AI); // Marcar casilla como IA (machine)
	}
}