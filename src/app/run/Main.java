package app.run;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import com.formdev.flatlaf.FlatDarkLaf;
import app.core.engine.Core;
import app.core.engine.EasyCore;
import app.core.engine.CustomCore;
import app.core.engine.NormalCore;
import app.frames.MainFrame;

/**
 * Class where the application is run.
 * @author Filipondios
 * @version 30.11.2022
 */
public final class Main {
	
	public static void main(String[] args) {

		try {
			javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		String[] options = new String[] { "Random", "Pseudo-Random", "Custom" };
		int option = JOptionPane.showOptionDialog(null, "Select the AI difficulty - intelligence", "Tic Tac Toe",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		Core game_core;
		if (option == 0) game_core = new EasyCore();
		else if (option == 1 || option == -1) game_core = new NormalCore();
		else {
			
			JFrame slider_frame = new JFrame();			
			JPanel panel = new JPanel();
			
			JSlider slider = new JSlider(1,8);
		    slider.setMajorTickSpacing(1);
		    slider.setPaintTicks(true);
		    slider.setPaintLabels(true);
		    slider.setValue(4);
		    panel.add(slider);
		    
		    int dialog = JOptionPane.showOptionDialog(
		    		slider_frame, panel, "MAX exploration Depth (Default=4)",
		    		JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
		    		null, new String[] {"Ok"}, null
            );
		    
		    game_core = new CustomCore((JOptionPane.OK_OPTION == dialog)?
		    		slider.getValue() : 4);
		}
		new MainFrame(game_core);
	}
	
	
}