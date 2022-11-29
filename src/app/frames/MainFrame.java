package app.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import app.core.board.Board;
import app.core.board.BoardCell;
import app.core.engine.Core;

/**
 * Main frame of the application. Contains a board and its cells.
 * @author Filipondios
 * @version 30.11.2022
 * @see Board
 * @see BoardCell
 */
public final class MainFrame extends JFrame {

		/** <p>Core of the game</p> */
		public static Core game_core;
		
		/** <p>Board of the game</p> */
		public static Board game_board;
		
		/**
		 * Builds the main frame of the application. Adds to it a {@link Board}
		 * object with its cells and initializes its properties.
		 * @param core Desired {@link Core} of the game.
		 */
		public MainFrame(Core core) {
			
			final Toolkit screen = Toolkit.getDefaultToolkit();
			final Dimension screen_size = screen.getScreenSize();
	
			this.setSize(screen_size.width>>2, screen_size.width>>2);		
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.add(game_board = new Board());
			this.setVisible(true);
			this.setTitle("Tic Tac Toe");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game_core = core;
		}
		
		/**
		 * Evaluates the {@link #game_board}.
		 * @return The current value of the Board. For more info see {@link Board#evaluate()}.
		 */
		public static int evaluateBoard() {
			return game_board.evaluate();
		}
		
		/**
		 * Checks if the {@link #game_board} is full.
		 * @return True if is full, false if it isn't. For more info, see {@link Board#isFull()}.
		 */
		public static boolean boardIsFull() {
			return game_board.isFull();
		}
		
		/**
		 * Gets a cell by it index from the current {@link #game_board}.
		 * @param index Index of the cell inside the <code>game_board</code>.
		 * @return The selected cell from the board.
		 */
		public static BoardCell getCellFromBoard(int index) {
			return game_board.getCell(index);
		}
		
		/**
		 * Sets a new last move from the user in the {@link #game_board}.
		 * @param cell Cell of the board that was last touched by the user.
		 */
		public static void setLastMove(BoardCell cell) {
			game_board.makeLastMove(cell);
		}
		
		/**
		 * Board last move getter.		
		 * @return Last move made by the user.
		 */
		public static BoardCell getLastMove() {
			return game_board.user_last_move;
		}
}