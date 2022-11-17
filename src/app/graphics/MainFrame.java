package app.graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.formdev.flatlaf.FlatDarkLaf;
import app.core.board.Board;
import app.core.engine.Engine;
import app.core.engine.Hardness;

public class MainFrame extends JFrame{
	
	public static Board game_board;
	static Engine gameEngine;
	
	public static void main(String[] args) {
		
		try { javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf()); }		
		catch (Exception ex) { ex.printStackTrace(); }
		
		// Seleccionar la dificultad: -1 se cerro la ventana, 0-2 opciones de izquierda a derecha
		String[] options = new String[] {"Easy", "Normal", "Impossible"};
		int option = JOptionPane.showOptionDialog(null, "Select the game difficulty", "Tic Tac Toe",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);
				
		switch (option) {
			case 0 : gameEngine = new Engine(Hardness.EASY);
			case -1 : case 1 : gameEngine = new Engine(Hardness.NORMAL);
			case 2 : gameEngine = new Engine(Hardness.IMPOSSIBLE);
		}
		
		new MainFrame(); // Empezar el juego	
	}
	
	public MainFrame() {
		this.setSize(400,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.add(game_board = new Board());
		this.setVisible(true);
		this.setTitle("Tic Tac Toe");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
