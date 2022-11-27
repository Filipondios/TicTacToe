package app.frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import app.core.board.Board;
import app.core.board.BoardChip;
import app.core.engine.Core;

@SuppressWarnings("serial")
public final class MainFrame extends JFrame {

		public static Core game_core;
		public static Board game_board;
		
		public MainFrame(Core core) {
			
			final Toolkit screen = Toolkit.getDefaultToolkit();
			final Dimension screen_size = screen.getScreenSize();
	
			this.setSize(screen_size.width/5, screen_size.width/5);		
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.add(game_board = new Board());
			this.setVisible(true);
			this.setTitle("Tic Tac Toe");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			game_core = core;
		}
		
		public static int evaluateBoard() {
			return game_board.evaluate();
		}
		
		public static boolean boardIsFull() {
			return game_board.isFull();
		}
		
		public static BoardChip getChipFromBoard(int index) {
			return game_board.getChip(index);
		}
		
		public static void setLastMove(BoardChip chip) {
			game_board.makeLastMove(chip);
		}
		
		public static BoardChip getLastMove() {
			return game_board.userLastMove;
		}
}