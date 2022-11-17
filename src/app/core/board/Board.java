package app.core.board;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/** <p>Clase que simula el comportamiento de un tablero de 3 en raya. Contiene la clase
 * {@link BoardChip} que ayuda a representar cada una de las <i>celdas</i> del tablero.
 * Entiendase celdas por cada uno de los espacios cuadrados del tablero que los jugadores
 *  pueden marcar. </p> 
 *  <br>
 * @author Filipondios. 
 * @version 17.11.2022 */
public class Board extends JPanel{
	
	/** {@link ArrayList} con las celdas del tablero 3x3 : 9 celdas em total */
	public static ArrayList<BoardChip> tiles = new ArrayList<>(9);			
	
	/** Crea un tablero y añade las 9 celdas */
	public Board() {
		super(new GridLayout(3,3));			
		
		for (int i=0; i<9; i++) {
			BoardChip chip = new BoardChip();
			this.add(chip); // Añadir chip al Panel
			tiles.add(chip); // Añadir chip a la lista
		}
	}
	
	/** <p>Marca una celda que ha sido ocupada por un jugador.</p>
	 * @param tile_number numero de celda dentro del tablero.
	 * @param Chip Constante del Enum {@link Chip} que identifica que jugador ha ocupado la celda. */
	public static void markTile(int tile_number, Ownership Chip) {
		tiles.get(tile_number).setChip(Chip);
	}
	
	//------------------------------------------------------------------------------------------------------------------- BoardChip
	
	/** <p>Clase que simula una <i>celda</i> del tablero del juego de 3 en raya.</p>
	 * @author Filipondios 
	 * @version 17.11.2022 */
	private class BoardChip extends JPanel{
		
		/**<p>Autentico valor de la celda. Al crear la celda, su valor el <i>NONE</i>, y cuando
		 * un jugador marca esa casilla, puede valer <i>AI</i> o <i>PLAYER</i>. Este ultimo valor no será modificable.</p> */
		private Ownership owner;	
				
		/**<p>Constructor que crea una celda y la inicializa a <i>NONE</i></p>. Le asigna un color al panel,
		 * un borde y un {@link MouseListener}.*/
		public BoardChip() {
			this.owner = Ownership.NONE;
			
			this.setBorder(new LineBorder(new Color(209,177,154))); // light brown
			this.setBackground(new  Color(60,63,65)); // light black
			
			this.addMouseListener(new MouseAdapter() {
		        public void mousePressed(MouseEvent e) {
		        	setBackground(new Color(237,216,192)); // beige
		        }
		    });
		}
		
		/** <p>Le da un valor de un jugador (<i>AI</i> o <i>PLAYER</i>) a la celda solo si esta tenía
		 * como valor <i>NONE</i>. </p>
		 * @param Chip Constante del enum {@link Chip} que indica si la celda la va a ocupar el jugador o la IA.*/
		public void setChip(Ownership owner) {
			if(this.owner != Ownership.NONE) return;
			this.owner = owner;
			if(owner == Ownership.AI) this.setBackground(new Color(171,122,101)); // brown
		}
	}
}
