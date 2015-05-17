package jeux_tank;

public class annimbis extends Thread {
	Animation anim;
	volatile boolean bool;
	long mseconds;
	boolean biginig = true;
	static int countInstance = 0;
	String nom;

	public annimbis(int mseconds, Animation anim, boolean bool, String nom) {
		this.anim = anim;
		this.bool = bool;
		this.mseconds = mseconds;
		this.nom = nom;
		countInstance++;
	}

	public boolean getBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}

	public void run() {

		while (true) {
			if (bool) {
				try {
					anim.initialisation_points_tank();
					anim.setAngleTank();
					anim.repainting();
					Thread.sleep(mseconds);

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // Très important de réinterrompre
					break; // Sortie de la boucle infinie

				}

			}
		}

	}
}
