package app.core.board;

/**
 * <p> Enum that has 3 possible constants. Represents each of the states or
 * values ​​of a cell in a board. These can be <i>NONE</i> if the cell is empty,
 * <i>AI</i> if the application engine marked the cell and <i>PLAYER</i> if
 * it was the player. </p>
 *
 * @author Filippondios
 * @version 27.11.2022
 */
public enum Ownership {
	AI,
	PLAYER,
	NONE
};