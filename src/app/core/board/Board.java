package app.core.board;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * <p> Class that simulates the behavior of a Tic-Tac-Toe board. To render each
 * of the <code>cells</code> from the board, the {@link BoardCell} class is
 * used. Understand cells for each one of the square spaces of the board
 * that players can mark. Both the <code>BoardCell</code> class and <code>this</code>
 * extends {@link JPanel}, so a <code>Board</code> Object contains 9 <code>BoardCells</code>
 * (Panels) inside.</p>
 * 
 * <p> The cells are distributed in the <code>Board</code> numerically like this: </p>
 * 
 * <table border="5">
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
 * <p> This information is useful to know in which order are the board cells distributed 
 * in the array of and with which indices each of them can be accessed. </p>
 * 
 * @author Filipondios.
 * @version 30.11.2022
 * @see BoardCell
 */
public final class Board extends JPanel {

	/** <p>Array with the <code>(9)</code> Board cells</p> */
	private final BoardCell[] cells = new BoardCell[9];
	
	/** <p>Last chip touched/marked by the user</p> */
	public BoardCell user_last_move;

	/** <p>Creates a Board and adds to it 9 cells</p> */
	public Board() {
		super(new GridLayout(3,3));
		
		for (byte i = 0; i < 9; i++) {
			cells[i] = new BoardCell(i);
			this.add(cells[i]);
		}
	}

	/**
	* <p>Function that evaluates the state of the current board.</p>
	* 
	* @return 1 if the AI ​​wins in the current board state, -1 if the AI ​​loses
	* (the user wins) and 0 if no one wins.
	*/
	public int evaluate() {

		// Diagonal left up - right down
		int result = evaluateInStep(4, 4);
		if (result != 0)
			return result;

		// Diagonal right up - left down
		result = evaluateInStep(4, 2);
		if (result != 0)
			return result;

		// All the rows
		for (int i = 1; i <= 7; i += 3) {
			result = evaluateInStep(i, 1);
			if (result != 0)
				return result;
		}

		// All the columns
		for (int i = 3; i <= 5; i += 1) {
			result = evaluateInStep(i, 3);
			if (result != 0)
				return result;
		}
		return 0;
	}

	/**
	* <p>Private function that evaluates if three contiguous positions of a board 
	* have the same value. To do so, the second position to be evaluated must be 
	* passed by parameter, and at what distance this is from the third.</p>
	*
	* @param start Second cell to evaluate.
	* @param step Step to the next cell (distance).
	* @return 1 if the AI ​​wins in the current board state, -1 if the AI ​​loses (user
	* wins), 0 if nobody wins and -2 if there is a total tie (there are no more 
	* free cells and there is a tie)
	*/
	private int evaluateInStep(final int start, final int step) {
		for (int i = start; i <= step + start; i += step) {
			Ownership pre_owner = this.cells[i-step].owner;
			if (pre_owner == Ownership.NONE || this.cells[i].owner != pre_owner)
				return 0;
			if (i == start + step)
				return (pre_owner == Ownership.AI)? 1 : -1;
		}
		return 0;
	}

	/**
	* <p> Marks a cell that has been occupied by a player.</p>
	* 
	* @param tile_number cell number inside the tile.
	* @param Chip Enum constant {@link BoardCell} that identifies which player
	* has occupied the cell.
	*/
	public int markTile(final int tile_number, final Ownership Chip) {
		return cells[tile_number].setCell(Chip);
	}

	/**
	 * <p>Function that checks if the board is full (There is no <code>BoardCell</code>
	 * that has no owner (enum owner=NONE)). Useful if we want to check if there is
	 * a tie in the actual game.</p>
	 * 
	 * @return True if the board is full, false if it isn't.
	 */
	public boolean isFull() {
		for (BoardCell boardCell : cells)
			if (boardCell.owner == Ownership.NONE)
				return false;
		return true;
	}
	
	/**
	 * <p>Function that gets a cell from the board.</p>
	 * 
	 * @param index Index of the cell in the actual <code>Board</code>
	 * @return Cell with a specific index from the board.
	 */
	public BoardCell getCell(final int index) {
		return this.cells[index];
	}
	
	/**
	 * <p>Function that updates the last move that the user did. The move is
	 * "saved" updating a reference to the last cell that the user touched.</p>
	 * 
	 * @param last_move Pointer to the last cell that the user touched.
	 */
	public void makeLastMove(final BoardCell last_move) {
		this.user_last_move = last_move;
	}
}
