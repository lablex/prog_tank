package jeux_tank;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class annimbis {
	Timer timer;
	Toolkit toolkit;
	Tank joueur;
	Animation anim;
	 
	public annimbis(int mseconds, Tank joueur, Animation anim) {
		timer = new Timer();
		toolkit = Toolkit.getDefaultToolkit();
		this.anim = anim;
		this.joueur = joueur;
		timer.schedule(new task(), 0, mseconds);
	}
 
	class task extends TimerTask {
		public void run() {
			if (joueur.getTankPointGauche().getPointX() <= 0) {
	            joueur.vx = 0;
	            joueur.getTankPointGauche().setPointX(1);
	        }
	        if (joueur.getTankPointGauche().getPointX() >= 950) {
	            joueur.vx = 0;
	            joueur.getTankPointGauche().setPointX(949);
	        }
	        if (joueur.y < 0) {
	            joueur.vy = 0;
	            joueur.y = 0;
	        }
	        if (joueur.y > 990) {
	            joueur.vy = 0;
	            joueur.y = 980;
	        }

	        anim.initialisation_points_tank();
	        anim.initialisation_angles();
	        anim.gestion_pente();
	        anim.repainting();

		}
	}
}
