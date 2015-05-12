package jeux_tank;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Fenetre extends JFrame implements KeyListener {

	private JPanel pan;
	private double TPRIM = 1000;
	private double ecartXFEN =(int) (Terrain.getNbPoint()/TPRIM)*Terrain.getEcartX();
	private static final String IMAGE_PATH_tank = "src/jeux_tank/images/tank.png";
    private static final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";

	
	private Tank[] tabJOUEUR = new Tank[1];
	{
		for(int i=0; i<tabJOUEUR.length; i++){
			tabJOUEUR[i]=new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon);
		}
	}

    public Fenetre() {

       this.setTitle("tank");
       this.setSize(1000, 800);
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       pan = new Draw(tabJOUEUR, 1);
       getContentPane().add(pan);
       new annimbis(5, tabJOUEUR[0], (Draw)pan);
       //new annimbis(100, tabJOUEUR[1], (Draw)pan);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
        	for(int i=0; i<tabJOUEUR.length; i++){
        		
        		tabJOUEUR[i].setX(-ecartXFEN);
        	}
        }
        if (c == KeyEvent.VK_RIGHT) {
        	for(int i=0; i<tabJOUEUR.length; i++){
        		
        		tabJOUEUR[i].setX(ecartXFEN);
        	}
        }
        if (c == KeyEvent.VK_S) {
        	for(int i=0; i<tabJOUEUR.length; i++){
        		
                tabJOUEUR[i].setAngleCanon(3.14/180);
        	}
        }
        if (c == KeyEvent.VK_Z) {
        	for(int i=0; i<tabJOUEUR.length; i++){
        		
                tabJOUEUR[i].setAngleCanon(-3.14/180);
            }
        }
        
        
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    	for(int i=0; i<tabJOUEUR.length; i++){
    		tabJOUEUR[i].setX(0);
    	}

    }
}
