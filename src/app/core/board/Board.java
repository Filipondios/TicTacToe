package app.core.board;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * <p>
 * Clase que simula el comportamiento de un tablero de 3 en raya. Para
 * representar cada una de las <i>celdas</i>
 * del tablero, se usa la clase {@link BoardChip}. Entiendase celdas por cada
 * uno de los espacios cuadrados del
 * tablero que los jugadores pueden marcar.
 * </p>
 * 
 * <p>
 * La distribucion de las celdas, ordenadas numericamente son:
 * </p>
 * 
 * <table class="tftable" border="5">
 * <tr>
 * <th>&emsp; <strong>0</strong></th>
 * <th>&emsp; <strong>1</strong></th>
 * <th>&emsp; <strong>2</strong></th>
 * </tr>
 * <tr>
 * <td>&emsp; <strong>3</strong></td>
 * <td>&emsp; <strong>4</strong></td>
 * <td>&emsp; <strong>5</strong></td>
 * </tr>
 * <tr>
 * <td>&emsp; <strong>6</strong></td>
 * <td>&emsp; <strong>7</strong></td>
 * <td>&emsp; <strong>8</strong></td>
 * </tr>
 * </table>
 * 
 * <p>
 * Esta informacion es util para saber en que orden se guardan el el
 * {@link ArrayList} de celdas del tablero
 * y con que indices se puede acceder a cada una de ellas.
 * </p>
 * 
 * @author Filipondios.
 * @version 19.11.2022
 */
@SuppressWarnings("serial")
public final class Board extends JPanel {
	
	/** Celdas del tablero */
	private BoardChip[] chips = new BoardChip[9];
	
	/** Last chip touched by the user */
	public BoardChip userLastMove;

	/** Crea un tablero y añade las 9 celdas */
	public Board() {
		super(new GridLayout(3, 3));

		for (int i = 0; i < 9; i++) {
			chips[i] = new BoardChip(i);
			this.add(chips[i]); // Añadir chip al Panel
		}
	}

	/**
	 * Funcion que evalua el estado del tablero actual.
	 * 
	 * @return 1 si la IA gana en el estado actual de tablero, -1 si esta pierde
	 *         (gana el usuario) y 0 si nadie gana.
	 */
	public int evaluate() {

		// Diagonal izquierda arriba - derecha abajo
		int result = evaluateInStep(4, 4);
		if (result != 0)
			return result;

		// Diagonal izquierda arriba - derecha abajo
		result = evaluateInStep(4, 2);
		if (result != 0)
			return result;

		// Todas las filas
		for (int i = 1; i <= 7; i += 3) {
			result = evaluateInStep(i, 1);
			if (result != 0)
				return result;
		}

		// Todas las columnas
		for (int i = 3; i <= 5; i += 1) {
			result = evaluateInStep(i, 3);
			if (result != 0)
				return result;
		}
		return 0;
	}

	/**
	 * Funcion privada que evalua si tres posiciones contiguas de un tablero tienen
	 * el mismo valor. Para ello,
	 * se debe pasar por parametro la segunda posicion a evaluar, y a que distancia
	 * esta esta de la tercera.
	 * 
	 * @param start Segunda celda a evaluar.
	 * @param step  Salto hasta la siguiente celda (distancia).
	 * @return 1 si la IA gana en el estado actual de tablero, -1 si esta pierde
	 *         (gana el usuario), 0 si nadie gana
	 *         y -2 si hay un empate total (no hay mas casillas libres y hay empate)
	 */
	private int evaluateInStep(int start, int step) {
		for (int i = start; i <= step + start; i += step) {
			Ownership pre_owner = this.chips[i-step].owner;
			if (pre_owner == Ownership.NONE || this.chips[i].owner != pre_owner)
				return 0;
			if (i == start + step)
				return (pre_owner == Ownership.AI) ? 1 : -1;
		}
		return 0;
	}

	/**
	 * <p>
	 * Marca una celda que ha sido ocupada por un jugador.
	 * </p>
	 * 
	 * @param tile_number numero de celda dentro del tablero.
	 * @param Chip        Constante del Enum {@link BoardChip} que identifica que jugador
	 *                    ha ocupado la celda.
	 */
	public int markTile(int tile_number, Ownership Chip) {
		return chips[tile_number].setChip(Chip);
	}

	public boolean isFull() {
		for (BoardChip boardChip : chips)
			if (boardChip.owner == Ownership.NONE)
				return false;
		return true;
	}
	
	public BoardChip getChip(int index) {
		return this.chips[index];
	}
	
	public void makeLastMove(BoardChip last_move) {
		this.userLastMove = last_move;
	}
	
	public BoardChip getLastMove() {
		return this.userLastMove;
	}
}
