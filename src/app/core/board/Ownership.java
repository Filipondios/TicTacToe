package app.core.board;

/** <p>Enum que tiene 3 posibles constantes. Representa cada uno de los estados o valores de
 * una celda del tablero. Estos pueden ser <i>NONE</i> si la celda esta vacia, <i>AI</i> si
 * el motor de la aplicacion marco la celda y <i>PLAYER</i> si fue el jugador.<p>
 * @author Filipondios
 * @version 17.11.2022 */
public enum Ownership{ 
	AI,
	PLAYER,
	NONE
};