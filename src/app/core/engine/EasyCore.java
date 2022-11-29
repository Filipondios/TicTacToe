package app.core.engine;

import app.core.board.Ownership;
import app.frames.MainFrame;

/**
 * Easy level Core. Generates a random number that represents the index of a cell in
 * a board and if it's free, marks it as AI's property. For more info about this 
 * implementation, see the makeMove method at this class.
 *  
 * @author Filipondios
 * @version 30.11.2022
 * @see #makeMove()
 */
public final class EasyCore implements Core {

	/**
	 * Starts a loop where generates a random number that represents the index of a cell
	 * in a board. If calculated cell is occupied, the loop continues, otherwise, the cell
	 * is marked as AI's property and exists the loop.
	 */
	@Override
	public void makeMove() {
		boolean let = true;
		byte chip;

		while (let) { // While the calculated cell is occupied, stay in the loop
			chip = (byte) (Math.random()*8); // Generate a random index of a board cell (0-8)
			let = (MainFrame.game_board.markTile(chip, Ownership.AI) == -1); //Check if its occupied
		}
	}
}