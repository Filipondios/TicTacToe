package app.core.engine;

import app.core.board.Ownership;
import app.frames.MainFrame;

/**
 * Core que se encarga del modo de juego facil. Calcula una posicion del tablero
 * aleatoria que no este ocupada y la marca.
 * 
 * @author Filipondios
 * @version 18.11.2022
 */
public final class EasyCore extends Core {

	@Override
	public void makeMove() {
		boolean let = true;
		int chip = 0;

		while (let) { // Mientas la casilla calculada este ocupada, seguir el loop
			chip = (int) (Math.random() * 8); // Generar casilla del tablero aleatoria
			let = (MainFrame.game_board.markTile(chip, Ownership.AI) == -1) ? true : false; // Comprobar si esta ocupada
		}
		MainFrame.game_board.markTile(chip, Ownership.AI); // Marcar casilla como IA (machine)
	}
}