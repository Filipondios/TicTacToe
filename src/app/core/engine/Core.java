package app.core.engine;

/**
 * <p>Interface that indicates how a Core must handle the calculations and requests
 * of a game board. It has a main function that calculates and performs the
 * moves that a core sees as the "best".</p>
 * 
 * @author Filipondios
 * @version 27.11.2022
 * @see #makeMove()
 */
public interface Core {

	/**
	 * <p>Principal function of a Core. A Core must follow the next requirements:</p>
	 * <ul>
  	 * 	<li>Calculate movement on the board</li>
  	 *	<li>Modify the dashboard after performing calculations</li>
	 * </ul> 
	 */
	public void makeMove();
}