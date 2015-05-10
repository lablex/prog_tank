package jeux_tank;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Fenetre extends JFrame implements KeyListener {

	private JPanel pan;
	private Tank[] tabJOUEUR = new Tank[2];
	{
		for(int i=0; i<tabJOUEUR.length; i++){
			tabJOUEUR[i]=new Tank();
		}
	}

    public Fenetre() {

        this.setTitle("tank");
        this.setSize(1000, 1000);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
       pan = new Draw(tabJOUEUR, 2);
       getContentPane().add(pan);
       new annimbis(5, tabJOUEUR[0], (Draw)pan);
       new annimbis(100, tabJOUEUR[1], (Draw)pan);
        
    }
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            tabJOUEUR[0].vx = -1;
            tabJOUEUR[0].vy = 0;
            tabJOUEUR[1].vx = -1;
            tabJOUEUR[1].vy = 0;
            System.out.println("a");
        }
        if (c == KeyEvent.VK_UP) {
            tabJOUEUR[0].vx = 0;
            tabJOUEUR[0].vy = -10;
            tabJOUEUR[1].vx = 0;
            tabJOUEUR[1].vy = -10;
            System.out.println("b");
        }
        if (c == KeyEvent.VK_RIGHT) {
            tabJOUEUR[0].vx = 1;
            tabJOUEUR[0].vy = 0;
            tabJOUEUR[1].vx = 1;
            tabJOUEUR[1].vy = 0;
            System.out.println("c");
        }
        if (c == KeyEvent.VK_DOWN) {
            tabJOUEUR[0].vx = 0;
            tabJOUEUR[0].vy = 10;
            tabJOUEUR[1].vx = 0;
            tabJOUEUR[1].vy = 10;
            System.out.println("d");
        }
        if (c == KeyEvent.VK_S) {
            if (tabJOUEUR[0].getAngleCanon() != 30) {
                tabJOUEUR[0].setAngleCanon(tabJOUEUR[0].getAngleCanon() + 5);
                tabJOUEUR[1].setAngleCanon(tabJOUEUR[1].getAngleCanon() + 5);
            }
        }
        if (c == KeyEvent.VK_Z) {
            if (tabJOUEUR[0].getAngleCanon() != -50) {
                tabJOUEUR[0].setAngleCanon(tabJOUEUR[0].getAngleCanon() - 5);
                tabJOUEUR[1].setAngleCanon(tabJOUEUR[1].getAngleCanon() - 5);
            }
        }
        
        
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        tabJOUEUR[0].vx = 0;
        tabJOUEUR[0].vy = 0;
        tabJOUEUR[1].vx = 0;
        tabJOUEUR[1].vy = 0;
    }
}
