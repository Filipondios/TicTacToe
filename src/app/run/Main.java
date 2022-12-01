package app.run;

import javax.swing.JOptionPane;
import com.formdev.flatlaf.FlatDarkLaf;
import app.core.engine.Core;
import app.core.engine.EasyCore;
import app.core.engine.ImpossibleCore;
import app.core.engine.NormalCore;
import app.frames.MainFrame;

/**
 * Class where the application is run.
 * @author Filipondios
 * @version 01.12.2022
 */
public final class Main {
	
	public static void main(String[] args) {

		try { javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf()); }
		catch (Exception ex) { ex.printStackTrace(); }

		String[] options = new String[] { "Easy", "Normal", "Impossible" };
		int option = JOptionPane.showOptionDialog(null, "Select the AI difficulty - intelligence", "Tic Tac Toe",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		Core game_core;
		if (option == 0) game_core = new EasyCore();
		else if (option == 1 || option == -1) game_core = new NormalCore();
		else game_core = new ImpossibleCore();
		
		new MainFrame(game_core);
	}
}