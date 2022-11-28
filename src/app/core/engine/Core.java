package app.core.engine;

/**
 * <p>Interface that indicates how a Core must handle the calculations and requests
 * of a game board. It has a main function that calculates and performs the
 * moves that a core sees as the <i>"best"</i>.</p>
 * 
 * <p>Depending on the Core, the <i>"best"</i> move depends on the hardness that a Core 
 * wants to represent. For example, if a Core generates a move that its lightly based on
 * a random decision, the most possible thing is that it won't be the best move for a 
 * Core that searches the real best move in the board.</p>
 * 
 * @author Filipondios
 * @version 28.11.2022
 * @see #makeMove()
 */
public interface Core {

	/**
	 * <p>Principal function of a Core. A Core must follow the next requirements:</p>
	 * <ul>
  	 * 	<li>Calculate movement on the board</li>
  	 *	<li>Modify the game board after performing calculations</li>
	 * </ul> 
	 */
	void makeMove();
}