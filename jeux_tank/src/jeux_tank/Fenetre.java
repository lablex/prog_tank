package jeux_tank;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Fenetre extends JFrame implements KeyListener {

	private JPanel pan;
	private double TPRIM = 1000;
	private double ecartXFEN =(int) (Terrain.getNbPoint()/TPRIM)*Terrain.getEcartX();
	private final String IMAGE_PATH_tank = "src/jeux_tank/images/tank.png";
    private final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
    private final String IMAGE_PATH_tank2 = "src/jeux_tank/images/18b.png";
    private Terrain terrain = new Terrain();
    private annimbis test1; 
    private annimbis test2; 
    private boolean actif1=true;
    private boolean actif2=false;
    private int i = 1;
    private int p = 0;
	
	private Tank[] tabJOUEUR = new Tank[2];


    public Fenetre() {

 			tabJOUEUR[1]=new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon);
 			tabJOUEUR[0]=new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon);
 		
       this.setTitle("tank");
       this.setSize(1000, 800);
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       pan = new Draw(tabJOUEUR, 2, terrain);

       getContentPane().add(pan);
       test1 =new annimbis(10, (Draw)pan, actif1);
       test2 =new annimbis(6, (Draw)pan, actif2);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
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
        if (c == KeyEvent.VK_D) {
        	
    		
    		
    		if(i==0){
    			test1.setBool(false);
    			i=1;
    		}else{
    			test1.setBool(true);
    			i=0;
    		}
    		
    		
    		if(p==0){
    			test2.setBool(false);
    			p=1;
    		}else{
    			test2.setBool(true);
    			p=0;
    		}
    	
        }
        if (c == KeyEvent.VK_S) {
        	
        		
                tabJOUEUR[0].setAngleCanon(3.14/180, test1.getBool());
                tabJOUEUR[1].setAngleCanon(3.14/180, test2.getBool());
        	
        }
        if (c == KeyEvent.VK_Z) {
        	
        	 tabJOUEUR[0].setAngleCanon(-3.14/180, test1.getBool());
             tabJOUEUR[1].setAngleCanon(-3.14/180, test2.getBool());
        }
        
        
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    	

    }
}
