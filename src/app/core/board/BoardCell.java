package app.core.board;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import app.frames.MainFrame;

/**
 * <p>Class that simulates a <i>cell</i> of the Tic-Tac-Toe game board.</p>
 * 
 * @author Filipondios
 * @version 27.11.2022
 */
@SuppressWarnings("serial")
public final class BoardCell extends JPanel {

	/**
	* <p> Authentic value of the cell. When creating the cell, its value is <i>NONE</i>, and
	* when a player checks that box, it can be worth <i>AI</i> or <i>PLAYER</i>. East
	* last value will not be modifiable. </p>
	*/
	public Ownership owner = Ownership.NONE;

	/** Relative position on the board that contains it */
	public int boardPosition;
	
	/**
	* <p> Constructor that creates a cell and assigns it an identifying number
	* within the board you are on. Assigns a color to the panel,
	* a border and {@link MouseListener}.</p>
	* 
	* @param position Relative position in the board that contains this cell.
	*/
	public BoardCell(int position) {
		this.boardPosition = position;

		this.setBorder(new LineBorder(new Color(209, 177, 154)));
		this.setBackground(new Color(60, 63, 65));

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				/* Comprobar que la casilla no este ocupada */
				if (owner != Ownership.NONE)
					return;

				/* Marcar esta casilla de la propiedad del jugador */
				setCell(Ownership.PLAYER);
				MainFrame.setLastMove(mirror());
								
				/* Evaluar si el jugador ha hecho una jugada ganadora */
				int board_value = MainFrame.evaluateBoard();
				
				if (board_value != 0)
					endGame(board_value);
				if (MainFrame.boardIsFull())
					endGame(0);

				/* Marcar esta casilla de la propiedad de la IA */
				MainFrame.game_core.makeMove();

				/* Evaluar si la IA ha hecho una jugada ganadora */
				board_value = MainFrame.evaluateBoard();
				
				if (board_value != 0)
					endGame(board_value);
			}
		});
	}

	/**
	* <p> Gives a value of a player (<i>AI</i> or <i>PLAYER</i>) to the cell only if
	* this has the value <i>NONE</i>. </p>
	*
	* @param Chip Constant of the enum {@link Chip} that indicates if the cell will be
	* occupy the player or the AI.
	*/
	public int setCell(Ownership owner) {
		if (this.owner != Ownership.NONE)
			return -1;
		this.owner = owner;
		if (owner == Ownership.AI)
			this.setBackground(new Color(171, 122, 101));
		else
			this.setBackground(new Color(237, 216, 192));
		return 0;
	}

	/**
	 * <p>Funcion que termina el juego y manda un mensaje con el resultado de la
	 * partida.</p>
	 * 
	 * @param value Valor de la partida. Si la IA gano, entonces value=-1, si el
	 *        usuario gano, entonces 1, y si hubo empate, 0.
	 */
	private void endGame(int value) {
		String message = (value == 0) ? "There's a draw :0" : null;
		JOptionPane.showMessageDialog(null,
				(message == null) ? ((value == -1) ? "You won :)" : "You lose :(") : message);
		System.exit(0);
	}
	
	/**
	 * <p>Method that returns the actual BoardCell. Used in the mouselistener -
	 * Mousepressed method.</p>
	 * 
	 * @return This BoardCell object.
	 */
	private BoardCell mirror() {
		return this;
	}
}