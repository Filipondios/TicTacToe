package app.core.engine;

import java.util.ArrayList;
import java.util.Arrays;
import app.core.board.BoardCell;
import app.core.board.Ownership;
import app.frames.MainFrame;

public final class NormalCore implements Core {

	@Override
	public void makeMove() {
		
		BoardCell target = canWinOrLose();
		
		if(target!=null) {
			
			target.setCell(Ownership.AI);
			return;
		}
		
		// Generar posibles celdas donde se haga un movimiento.
		// Debe ser alrededor de Board.last o si no hay hueco, en una posicion del
		// tablero aleatoria
		
		target = null;
		int last = MainFrame.getLastMove().boardPosition;
		ArrayList<Integer> candidates = new ArrayList<>(Arrays.asList(last-1,last+1,last-3,last+3));
						
		for (Integer index : candidates) {
						
			try { target = MainFrame.getChipFromBoard(index); }
			catch (Exception e) { continue; }
			
			if(target.owner == Ownership.NONE) {
				target.setCell(Ownership.AI);
				return;
			}
		}
		
		// No hay celdas libres alrededor del ultimo movimiento del usuario.
		// Hay que obtener una celda del tablero de forma aleatoria.
		
		boolean let = true;
		int chip = 0;

		while (let) { // Mientas la casilla calculada este ocupada, seguir el loop
			chip = (int) (Math.random())<<3; // Generar casilla del tablero aleatoria
			let = (MainFrame.game_board.markTile(chip, Ownership.AI) == -1) ? true : false; // Comprobar si esta ocupada
		}
		MainFrame.game_board.markTile(chip, Ownership.AI); // Marcar casilla como IA (machine)
	}

	/**
	 * Funcion que evalua el tablero, y comprueba si puede haber un movimiento que
	 * gane la partida, que evite que el usuario gane o en oto caso, ninguna de las
	 * dos anteriores (Situacion sin riesgo ni ganancia).
	 * 
	 * @return si existe un movimiento ganador o de defensa se devuleve un objeto
	 *         {@link BoardCell}
	 *         que sea una referencia a la celda donde hacer el movimiento, null si
	 *         no hay peligro ni ganancia.
	 */
	private BoardCell canWinOrLose() {
		// Diagonal izquierda arriba - derecha abajo
		BoardCell target = evaluateInStep(0, 4);
		if (target != null)
			return target;

		// Diagonal izquierda arriba - derecha abajo
		target = evaluateInStep(2, 2);
		if (target != null)
			return target;

		// Todas las filas
		for (int i = 0; i <= 6; i += 3) {
			target = evaluateInStep(i, 1);
			if (target != null)
				return target;
		}

		// Todas las columnas
		for (int i = 0; i <= 2; i++) {
			target = evaluateInStep(i, 3);
			if (target != null)
				return target;
		}
		return null;
	}

	/**
	 * Evalua si dadas 3 celdas existe peligro de derrota, posible victoria
	 * o una situacion de no ganancia.
	 * @param start Primera celda a evaluar.
	 * @param step  Salto hasta la siguiente celda (distancia).
	 * @return Objeto de tipo {@link BoardCell} que hace referencia a la celda del
	 * tablero donde se debe hacer el movimiento, null si no ha peligro o posible victoria.
	 */
	private BoardCell evaluateInStep(int start, int step) {
		
		BoardCell actual, target = null;
		int gaps = 0, player = 0, ai = 0;
		
		for (int i=start; i<=start+step*2; i+=step) {
			actual =  MainFrame.getChipFromBoard(i);
			
			if(actual.owner == Ownership.NONE) {
				gaps++; target = actual;
				if(gaps==2) break;
			}
			
			else if(actual.owner == Ownership.PLAYER) player++;
			else ai++;
		}		
		return ((ai==2 || player==2) && gaps==1)? target : null;
	}
}