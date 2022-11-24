package app.core.engine;

import java.util.ArrayList;
import java.util.Arrays;

import app.core.board.Board;
import app.core.board.BoardChip;
import app.core.board.Ownership;

public class NormalCore extends Core {

	/**
	 * Crea un NormalCore (core de dificultad normal) y asicia a este un tablero.
	 * 
	 * @param board Tablero donde se van a realizar todos los movimientos 
	 * 				durante la partida.
	 */
	public NormalCore(Board board) {
		super(board);
	}

	@Override
	public void makeMove() {
		
		BoardChip target = canWinOrLose();
		
		if(target!=null) {
			target.markTile(Ownership.AI);
			return;
		}
		
		// Generar posibles celdas donde se haga un movimiento.
		// Debe ser alrededor de Board.last o si no hay hueco, en una posicion del
		// tablero aleatoria
		System.out.println("xasnoi");
		
		target = null;
		int last = Board.userLastMove.boardPosition;
		ArrayList<Integer> candidates = new ArrayList<>(Arrays.asList(last-1,last+1,last-3,last+3));
				
		for (Integer index : candidates) {
			
			target = getBoard().getChips().get(index);
			
			try { target = getBoard().getChips().get(index); }
			catch (Exception e) { continue; }
			
			if(target.owner == Ownership.NONE) {
				target.markTile(Ownership.AI);
				return;
			}
		}
		
		// No hay celdas libres alrededor del ultimo movimiento del usuario.
		// Hay que obtener una celda del tablero de forma aleatoria.
		
		boolean let = true;
		int chip = 0;

		while (let) { // Mientas la casilla calculada este ocupada, seguir el loop
			chip = (int) (Math.random() * 8); // Generar casilla del tablero aleatoria
			let = (getBoard().markTile(chip, Ownership.AI) == -1) ? true : false; // Comprobar si esta ocupada
		}
		getBoard().markTile(chip, Ownership.AI); // Marcar casilla como IA (machine)
	}

	/**
	 * Funcion que evalua el tablero, y comprueba si puede haber un movimiento que
	 * gane la partida, que evite que el usuario gane o en oto caso, ninguna de las
	 * dos anteriores (Situacion sin riesgo ni ganancia).
	 * 
	 * @return si existe un movimiento ganador o de defensa se devuleve un objeto
	 *         {@link BoardChip}
	 *         que sea una referencia a la celda donde hacer el movimiento, null si
	 *         no hay peligro ni ganancia.
	 */
	private BoardChip canWinOrLose() {
		// Diagonal izquierda arriba - derecha abajo
		BoardChip target = evaluateInStep(0, 4);
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
	 * @return Objeto de tipo {@link BoardChip} que hace referencia a la celda del
	 * tablero donde se debe hacer el movimiento, null si no ha peligro o posible victoria.
	 */
	private BoardChip evaluateInStep(int start, int step) {
		
		BoardChip actual, target = null;
		int gaps = 0, player = 0, ai = 0;
		
		for (int i=start; i<=start+step*2; i+=step) {
			actual = getBoard().getChips().get(i);
			
			if(actual.owner == Ownership.NONE) {
				gaps++; target = actual;
				if(gaps==2) break;
			}
			
			else if(actual.owner == Ownership.PLAYER) player++;
			else ai++;
			System.out.println("---");
		}		
		return ((ai==2 || player==2) && gaps==1)? target : null;
	}
}