package jeux_tank;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Fenetre extends JFrame implements KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel pan;
    private double TPRIM = 1000;
    private double ecartXFEN = (int) (Terrain.getNbPoint() / TPRIM) * Terrain.getEcartX();
    private final String IMAGE_PATH_tank = "src/jeux_tank/images/tank.png";
    private final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
    private Terrain terrain = new Terrain();
    private Tank[] tabJOUEUR = new Tank[2];
    private Missile[] tabMissile1 = new Missile[3];
    private Missile[] tabMissile2 = new Missile[3];
    private Animation anim;
    private volatile static boolean enter;
    private static volatile boolean tir = true;
    private static volatile boolean avance = true;
    protected static volatile boolean missile = true;
    private static int selectMissile;
    private volatile static boolean verticalMissile;
    private volatile static boolean triMissileMissile;

    public Fenetre() {
        this.setEnter(false);
        this.setTitle("tank");
        this.setSize(1000, 800);
        addKeyListener(this);
        this.setBackground(Color.CYAN);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        tabJOUEUR[1] = new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon, 200);
        tabJOUEUR[0] = new Tank(IMAGE_PATH_tank, IMAGE_PATH_canon, 500);

        tabMissile1[0] = new Obus(tabJOUEUR[0], "obus1");
        tabMissile1[1] = new TriMissile(tabJOUEUR[0], "triMissile1");
        tabMissile1[2] = new VerticalMissile(tabJOUEUR[0], "verticalMissile1");

        tabMissile2[0] = new Obus(tabJOUEUR[1], "obus2");
        tabMissile2[1] = new TriMissile(tabJOUEUR[1], "triMissile2");
        tabMissile2[2] = new VerticalMissile(tabJOUEUR[1], "verticalMissile2");

        pan = new Draw(tabJOUEUR, 2, terrain, tabMissile1, tabMissile2);
        anim = new Animation(tabMissile1, tabMissile2, tabJOUEUR[0], tabJOUEUR[1], pan);
        anim.start();
       // timer.start();
        getContentPane().setBackground(Color.BLUE);
        getContentPane().add(pan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (avance) {
            if (c == KeyEvent.VK_LEFT) {
                tir = false;
                anim.setTir(false);
                anim.setAvance(true);
                tabJOUEUR[0].setX(-ecartXFEN, Animation.getAltern());
                tabJOUEUR[1].setX(-ecartXFEN, !Animation.getAltern());
            }
            if (c == KeyEvent.VK_RIGHT) {
                tir = false;
                anim.setTir(false);
                anim.setAvance(true);
                tabJOUEUR[0].setX(ecartXFEN, Animation.getAltern());
                tabJOUEUR[1].setX(ecartXFEN, !Animation.getAltern());
            }
        }
        if (tir) {
            if (c == KeyEvent.VK_ENTER && this.getEnter() == false) {
                Missile.setV0(Missile.getV0() + 5);
                if (Missile.getV0() > 150) {
                    Missile.setV0(150);
                }
                avance = false;
                anim.setAvance(false);
               // Animation.setCount();
            }
        }
        if (missile) {
            if (c == KeyEvent.VK_NUMPAD0) {
                selectMissile = 0;

            }

	        if (c == KeyEvent.VK_NUMPAD1) {
	        	if(tabJOUEUR[0].nbTriMissile>0 && Animation.getAltern()){	
	            selectMissile = 1;
	            triMissileMissile = true;
	        	}else if(tabJOUEUR[1].nbTriMissile>0 && !Animation.getAltern()){
		            selectMissile = 1;
		            triMissileMissile = true;
		        }else{
	        		selectMissile = 0;
	        	}
	        }
        
	        if (c == KeyEvent.VK_NUMPAD2) {
	        	if(tabJOUEUR[0].nbVerticalMissile>0 && Animation.getAltern()){
	        	selectMissile = 2;
	            verticalMissile = true;
	        	}
	        	
	        	else if(tabJOUEUR[1].nbVerticalMissile>0 && !Animation.getAltern()){
		        	selectMissile = 2;
		            verticalMissile = true;
		        	}else{
		        		selectMissile = 0;
		        	}
	        }
        
        
		if (this.getEnter() == false) {
		        if (c == KeyEvent.VK_S) {

                tabJOUEUR[0].setAngleCanon(-3.14 / 180, Animation.getAltern());
                tabJOUEUR[1].setAngleCanon(3.14 / 180, !Animation.getAltern());

            }
            if (c == KeyEvent.VK_Z) {

                tabJOUEUR[0].setAngleCanon(3.14 / 180, Animation.getAltern());
                tabJOUEUR[1].setAngleCanon(-3.14 / 180, !Animation.getAltern());
            }
        }
        }
    }

    public static boolean getVerticalMissile() {
        return verticalMissile;
    }

    public static boolean getTriMissileMissile() {
        return triMissileMissile;
    }

    public static void setVerticalMissile(boolean set) {
        verticalMissile = set;
    }

    public static void setTriMissileMissile(boolean set) {
        triMissileMissile = set;
    }

    public static int getSelectMissile() {
        return selectMissile;
    }

    public static void setSelectMissile() {
        selectMissile = 0;
    }

    public static boolean getEnter() {
        return enter;
    }

    public static void setEnter(boolean enterr) {
        enter = enterr;
    }

    public static void setTir(boolean tire) {
        tir = tire;
    }

    public static void setAvance(boolean avancee) {
        avance = avancee;
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();

        if (avance == false && c == KeyEvent.VK_ENTER) {

            anim.setTir(true);
            this.setEnter(true);
            Missile.setStop(false);
        }
    }
    
}
