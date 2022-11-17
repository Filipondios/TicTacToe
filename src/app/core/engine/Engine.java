package app.core.engine;

public class Engine {
	
	private final Core core;
	
	public Engine(Hardness hardness) {
		switch (hardness) {
			case EASY: this.core = new EasyCore(); break;
			case NORMAL: this.core = new NormalCore(); break;
			case IMPOSSIBLE: this.core = new ImpossibleCore(); break;
			default: this.core = null; break;
		}	
	}
}
