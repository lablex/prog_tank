package jeux_tank;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Fenetre extends JFrame implements KeyListener {

	private JPanel pan;
	private double TPRIM = 1000;
	private double ecartXFEN = (int) (Terrain.getNbPoint() / TPRIM)* Terrain.getEcartX();
	private final String IMAGE_PATH_tank = "src/jeux_tank/images/tank.png";
	private final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
	private Terrain terrain = new Terrain();
	private Tank[] tabJOUEUR = new Tank[2];
	private Missile missile1 ;
	private Missile missile2 ;
	private Animation anim;
	private static boolean enter;
	private static volatile boolean tir = true;
	private static volatile boolean avance = true;

	

	public Fenetre() {

		this.setTitle("tank");
		this.setSize(1000, 800);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		tabJOUEUR[1] = new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon, 200);
		tabJOUEUR[0] = new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon, 500);
		missile1 = new Missile(tabJOUEUR[0], "missile1");
		missile2 = new Missile(tabJOUEUR[1], "missile2");
		pan = new Draw(tabJOUEUR, 2, terrain, missile1, missile2);
		anim = new Animation(missile1, missile2,tabJOUEUR[0], tabJOUEUR[1], pan);
		anim.start();
		getContentPane().add(pan);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(avance){
			if (c == KeyEvent.VK_LEFT) {
				tir=false;
				anim.setTir(false);
				anim.setAvance(true);
				tabJOUEUR[0].setX(-ecartXFEN, anim.getAltern());
				tabJOUEUR[1].setX(-ecartXFEN, !anim.getAltern());
			}
			if (c == KeyEvent.VK_RIGHT) {
				tir=false;
				anim.setTir(false);
				anim.setAvance(true);
				tabJOUEUR[0].setX(ecartXFEN, anim.getAltern());
				tabJOUEUR[1].setX(ecartXFEN, !anim.getAltern());
			}
		}
		if(tir){
			if (c == KeyEvent.VK_ENTER) {
					anim.setStop(false);
					enter=true;
					Missile.setV0(Missile.getV0() + 5);
					avance=false;
					//anim.setTir(true);
					anim.setAvance(false);
				}
		}

		if (c == KeyEvent.VK_S) {

			tabJOUEUR[0].setAngleCanon(3.14 / 180, anim.getAltern());
			tabJOUEUR[1].setAngleCanon(3.14 / 180, !anim.getAltern());

		}
		if (c == KeyEvent.VK_Z) {

			tabJOUEUR[0].setAngleCanon(-3.14 / 180, anim.getAltern());
			tabJOUEUR[1].setAngleCanon(-3.14 / 180, !anim.getAltern());
		}

	}
	
	public static boolean getEnter(){
		return enter;
	}
	public static void setEnter(boolean enterr){
		enter=enterr;
	}
	public static void setTir(boolean tire){
		tir=tire;
	}
	public static void setAvance(boolean avancee){
		avance=avancee;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
            if(Missile.getV0() != 0 && avance == false){
                System.out.println(Missile.getV0());
                 anim.setTir(true);
            }
	}
}
