package jeux_tank;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Fenetre extends JFrame implements KeyListener {

	public static boolean test = false;
	private JPanel pan;
	private double TPRIM = 1000;
	private double ecartXFEN = (int) (Terrain.getNbPoint() / TPRIM)
			* Terrain.getEcartX();
	private final String IMAGE_PATH_tank = "src/jeux_tank/images/tank.png";
	private final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
	private final String IMAGE_PATH_tank2 = "src/jeux_tank/images/18b.png";
	private Terrain terrain = new Terrain();
	private final int PLUS = 50 * (106 / 2) / 180;
	private final int PLUS2 = 50 * 106 / 180;
	private final int PLUS2s = 50 * 206 / 180;
	private final int PLUS3 = 100 * 150 / 500;
	private Tank[] tabJOUEUR = new Tank[2];
	private Missile missile1 ;
	private Missile missile2 ;
	private volatile static annimbis test1;
	private volatile static annimbis test2;
	private boolean actif1 = true;
	private boolean actif2 = false;
	private int i = 0;
	private int p = 1;
	private int t = 0;
	private int f = 1;

	

	public Fenetre() {

		tabJOUEUR[1] = new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon, 100);
		tabJOUEUR[0] = new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon, 800);

		this.setTitle("tank");
		this.setSize(1000, 800);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		missile1 = new Missile(100, terrain.getTerrainX(tabJOUEUR[0].getXInt())+ (int) (PLUS2 * Math.sin(tabJOUEUR[0].getAngleTank()))-10,		
								-terrain.getTerrainY(tabJOUEUR[0].getXInt())+ (int) (PLUS2 * Math.cos(tabJOUEUR[0].getAngleTank()))+10, 
								tabJOUEUR[0].getAngleCanon()+tabJOUEUR[0].getAngleTank(), "missile1");
		
		
		missile2 = new Missile(100, terrain.getTerrainX(tabJOUEUR[1].getXInt())+ (int) (PLUS2 * Math.sin(tabJOUEUR[1].getAngleTank()))-10,		
								-terrain.getTerrainY(tabJOUEUR[1].getXInt())+ (int) (PLUS2 * Math.cos(tabJOUEUR[1].getAngleTank()))+10, 
								tabJOUEUR[1].getAngleCanon()+tabJOUEUR[1].getAngleTank(), "missile2");
		
		
		pan = new Draw(tabJOUEUR, 2, terrain, missile1, missile2);

		getContentPane().add(pan);
		Fenetre.test1 = new annimbis(100, (Draw) pan, actif1, "aaaaaa");
		Fenetre.test2 = new annimbis(100, (Draw) pan, actif2, "bbbbb");

		test1.start();
		test2.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	int i1 = 0;

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_LEFT) {
			tabJOUEUR[0].setX(-ecartXFEN, test1.getBool());
			tabJOUEUR[1].setX(-ecartXFEN, test2.getBool());
		}
		if (c == KeyEvent.VK_RIGHT) {
			tabJOUEUR[0].setX(ecartXFEN, test1.getBool());
			tabJOUEUR[1].setX(ecartXFEN, test2.getBool());
		}

		if (c == KeyEvent.VK_ENTER) {
			if (i1 == 0) {
				missile1.start();
				missile2.start();
				this.i1++;
			} else {

				
				if (t == 0) {
					missile1.bool = false;
					missile2.bool = true;
					t = 1;
				} else {
					missile1.bool = true;
					missile2.bool = false;
					t = 0;
				}

				if (f == 0) {
					missile2.bool = false;
					missile1.bool = true;
					f = 1;
				} else {

					missile2.bool = true;
					missile1.bool = false;
					f = 0;
				}
				
				missile2.setMissile(100, 
									terrain.getTerrainX(tabJOUEUR[0].getXInt())+ (int) (PLUS2 * Math.sin(tabJOUEUR[0].getAngleTank()))-10,		
									-terrain.getTerrainY(tabJOUEUR[0].getXInt())+ (int) (PLUS2 * Math.cos(tabJOUEUR[0].getAngleTank()))+10, 
									tabJOUEUR[0].getAngleCanon()+tabJOUEUR[0].getAngleTank());
				
				
				missile1.setMissile(100, 
									terrain.getTerrainX(tabJOUEUR[1].getXInt())+ (int) (PLUS2 * Math.sin(tabJOUEUR[1].getAngleTank()))-10,		
									-terrain.getTerrainY(tabJOUEUR[1].getXInt())+ (int) (PLUS2 * Math.cos(tabJOUEUR[1].getAngleTank()))+10, 
									tabJOUEUR[1].getAngleCanon()+tabJOUEUR[1].getAngleTank());
				
				


			}

		}
		if (c == KeyEvent.VK_D) {

			if (i == 0) {
				test1.setBool(false);
				i = 1;
			} else {
				test1.setBool(true);
				i = 0;
			}

			if (p == 0) {
				test2.setBool(false);
				p = 1;
			} else {
				test2.setBool(true);
				p = 0;
			}

		}
		if (c == KeyEvent.VK_S) {

			tabJOUEUR[0].setAngleCanon(3.14 / 180, test1.getBool());
			tabJOUEUR[1].setAngleCanon(3.14 / 180, test2.getBool());

		}
		if (c == KeyEvent.VK_Z) {

			tabJOUEUR[0].setAngleCanon(-3.14 / 180, test1.getBool());
			tabJOUEUR[1].setAngleCanon(-3.14 / 180, test2.getBool());
		}

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {

	}
}
