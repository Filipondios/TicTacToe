package app.core.board;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * <p>
 * Clase que simula una <i>celda</i> del tablero del juego de 3 en raya.
 * </p>
 * 
 * @author Filipondios
 * @version 19.11.2022
 */
public class BoardChip extends JPanel {

	/**
	 * <p>
	 * Autentico valor de la celda. Al crear la celda, su valor el <i>NONE</i>, y
	 * cuando
	 * un jugador marca esa casilla, puede valer <i>AI</i> o <i>PLAYER</i>. Este
	 * ultimo valor no será modificable.
	 * </p>
	 */
	public Ownership owner;

	public int boardPosition;
	
	/**
	 * <p>
	 * Constructor que crea una celda y la inicializa a <i>NONE</i>
	 * </p>
	 * . Le asigna un color al panel,
	 * un borde y un {@link MouseListener}.
	 */
	public BoardChip(int position) {
		this.owner = Ownership.NONE;
		this.boardPosition = position;

		this.setBorder(new LineBorder(new Color(209, 177, 154))); // light brown
		this.setBackground(new Color(60, 63, 65)); // light black

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

				
				// Comprobar que la casilla no este ocupada
				if (owner != Ownership.NONE)
					return;

				/* Marcar esta casilla de la propiedad del jugador */
				setChip(Ownership.PLAYER);
				Board.userLastMove = mirror();
				
				/* Evaluar si el jugador ha hecho una jugada ganadora */
				int board_value = Board.evaluate();
				if (board_value != 0)
					endGame(board_value);
				if (Board.boardIsFull())
					endGame(0);

				/* Marcar esta casilla de la propiedad de la IA */
				Board.game_core.makeMove();

				/* Evaluar si la IA ha hecho una jugada ganadora */
				board_value = Board.evaluate();
				if (board_value != 0)
					endGame(board_value);
			}
		});
	}

	/**
	 * <p>
	 * Le da un valor de un jugador (<i>AI</i> o <i>PLAYER</i>) a la celda solo si
	 * esta tenía
	 * como valor <i>NONE</i>.
	 * </p>
	 * 
	 * @param Chip Constante del enum {@link Chip} que indica si la celda la va a
	 *             ocupar el jugador o la IA.
	 */
	public int setChip(Ownership owner) {
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
	 * Funcion que termina el juego y manda un mensaje con el resultado de la
	 * partida.
	 * 
	 * @param value Valor de la partida. Si la IA gano, entonces value=-1, si el
	 *              usuario
	 *              gano, entonces 1, y si hubo empate, 0.
	 */
	private void endGame(int value) {
		String message = (value == 0) ? "Hubo empate :/" : null;
		JOptionPane.showMessageDialog(null,
				(message == null) ? ((value == -1) ? "Has ganado :)" : "Has perdido :(") : message);
		System.exit(0);
	}
	
	public int markTile(Ownership Chip) {
		return setChip(Chip);
	}
	
	public BoardChip mirror() {
		return this;
	}
}