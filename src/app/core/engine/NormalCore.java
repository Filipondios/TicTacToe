package app.core.engine;

import java.util.ArrayList;
import java.util.Arrays;
import app.core.board.BoardCell;
import app.core.board.Ownership;
import app.frames.MainFrame;

/**
 * Core that implements the normal hardness of the game. It simply checks if the
 * AI can lose or win. If any of those situations can happen, the makeMove method
 * must make a move to win or defend. Otherwise, tries to generate a logic move.
 * For more info, see the makeMove at this Class.
 * 
 * @author Filipondios
 * @version 30.10.2022
 * @see #makeMove()
 */
public final class NormalCore implements Core {

	/**
	 * <p>As described in this Class, this method checks if there is a situation where
	 * the AI can win or lose. If any of those situations can happen, the method must
	 * make a move to win or defend.</p> 
	 * 
	 * <p>Otherwise, the algorithm tries to make a move in the surrounding cells of the
	 * user's last move. If all that cells are occupied, the AI must make a move in a
	 * random move at the board. This last algorithm is the same as the
	 * {@link EasyCore#makeMove()} method.</p>
	 */
	@Override
	public void makeMove() {
		
		BoardCell target = canWinOrLose();
		
		if(target!=null) {
			target.setCell(Ownership.AI);
			return;
		}
		
		/* Generate the possible candidate cells in the surrounding cells of the
		 * user last move, specified by the board. */
		byte chip = MainFrame.getLastMove().boardPosition;
		
		ArrayList<Integer> candidates = new ArrayList<>(
				/* Up = last-3 ; Down = last+3 ; Left = last-1 ; Right = last+1 ;
				 * Diagonal-left-up = last-4 ;  Diagonal-right.down = last+4 ; 
				 * Diagonal-right-up: = last-2 ; Diagonal-right-down = last+2 */
				Arrays.asList(chip-3,chip+3,chip-1,chip+1,chip-4,chip+4,chip-2,chip+2)
		);
						
		for (Integer index : candidates) {
						
			try { target = MainFrame.getCellFromBoard(index); }
			catch (Exception e) { continue; }
			
			if(target.owner == Ownership.NONE) {
				target.setCell(Ownership.AI);
				return;
			}
		}
		
		/* At this point, if the function did not return, there are no free cells
		 * surrounding the last cell marked by the user. Generate a random cell at
		 * the board, like in the EasyCore makeMove method */
		boolean let = true;

		while (let) {
			chip = (byte) (Math.random()*8);
			let = (MainFrame.game_board.markTile(chip, Ownership.AI) == -1);
		}
	}

	/**
	* Function that evaluates the board, and checks if there can be a movement that
	* can win the game, which prevents the user from winning or otherwise, none of the
	* two previous (Situation without risk or profit).
	*
	* @return if there is a winning or defending move an object is returned
	* {@link BoardCell} that is a reference to the cell where to move, null if
	* there is no danger or gain.
	*/
	private BoardCell canWinOrLose() {
		// Diagonal left up - right down
		BoardCell target = evaluateInStep(0, 4);
		if (target != null)
			return target;

		// Diagonal left up - right down
		target = evaluateInStep(2, 2);
		if (target != null)
			return target;

		// All the rows
		for (byte i = 0; i <= 6; i += 3) {
			target = evaluateInStep(i, 1);
			if (target != null)
				return target;
		}

		// All the columns
		for (byte i = 0; i <= 2; i++) {
			target = evaluateInStep(i, 3);
			if (target != null)
				return target;
		}
		return null;
	}

	/**
	* Private function that evaluates if 3 cells may cause in the next move a win or lose. 
	* 
	* @param start First cell to evaluate.
	* @param step Step to the next cell (distance).
	* @return Object of type {@link BoardCell} that refers to the board cell where the next
	* move must be made, null if there is no danger or possible victory.
	*/
	private BoardCell evaluateInStep(final int start, final int step) {
		
		BoardCell actual, target = null;
		byte gaps = 0, player = 0, ai = 0;
		
		for (int i=start; i<=start+step*2; i+=step) {
			actual =  MainFrame.getCellFromBoard(i);
			
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