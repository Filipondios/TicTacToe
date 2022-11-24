package app.run;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.formdev.flatlaf.FlatDarkLaf;
import app.core.board.Board;
import app.core.engine.Core;
import app.core.engine.EasyCore;
import app.core.engine.ImpossibleCore;
import app.core.engine.NormalCore;

public class MainFrame extends JFrame {

	public static void main(String[] args) {

		try {
			javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String[] options = new String[] { "Easy", "Normal", "Impossible" };
		int option = JOptionPane.showOptionDialog(null, "Select the game difficulty", "Tic Tac Toe",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		Board game_board = new Board();
		Core game_core = null;

		if (option == 0)
			game_core = new EasyCore(game_board);
		else if (option == 1 || option == -1)
			game_core = new NormalCore(game_board);
		else
			game_core = new ImpossibleCore(game_board);

		game_board.setCore(game_core);
		new MainFrame(game_board); // Empezar el juego
	}

	public MainFrame(Board game_board) {
		this.setSize(400, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(game_board);
		this.setVisible(true);
		this.setTitle("Tic Tac Toe");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}