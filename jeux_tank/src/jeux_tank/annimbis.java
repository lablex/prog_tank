package jeux_tank;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class annimbis {
	Timer timer;
	Tank joueur;
	Animation anim;
	 
	public annimbis(int mseconds, Tank joueur, Animation anim) {
		timer = new Timer();
		this.anim = anim;
		this.joueur = joueur;
		timer.schedule(new task(), 0, mseconds);
	}
 
	class task extends TimerTask {
		public void run() {

	        anim.initialisation_points_tank();
	        anim.setAngleTank();
	        anim.repainting();

		}
	}
}
