package jeux_tank;

import java.util.Timer;
import java.util.TimerTask;

public class annimbis {
	Timer timer;
	Animation anim;
	boolean bool;
	 
	public annimbis(int mseconds, Animation anim, boolean bool) {
		timer = new Timer();
		this.anim = anim;
		timer.schedule(new task(), 0, mseconds);
		this.bool = bool;
	}
	public boolean getBool(){
		return bool;
	}
	
	public void setBool(boolean bool){
		this.bool=bool;
	}
 
	class task extends TimerTask {
		public void run() {
			if(bool){
		        anim.initialisation_points_tank();
		        anim.setAngleTank();
		        anim.repainting();
			}

		}
	}
}
