/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class Draw extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int taille_image = 50;
    private final int taille_image2 = 100;
    protected Terrain terrain;
    protected Tank[] tank;
    protected Point[] pointTerrain;
    private Missile[] tabMissile1=new Missile[3] ;
    private Missile[] tabMissile2=new Missile[3] ;

    public Draw(Tank[] tank, int n, Terrain terrain, Missile[] tabMissile1, Missile[] tabMissile2) {
        
    	this.tank = new Tank[n];
        this.terrain = terrain;
        
        for(int i=0; i<tabMissile1.length; i++){
			this.tabMissile1[i] = tabMissile1[i];
			this.tabMissile2[i] = tabMissile2[i];
		}
        for (int i = 0; i < tank.length; i++) {
            this.tank[i] = tank[i];
        }
    }

    public void paintComponent(Graphics g) {
    	g.setColor(Color.BLACK);
        g.translate(0, 500);
       drawTerrain(g, terrain.getTerrainX(), terrain.getTerrainY());
       drawCiel(g, terrain.getCielX(), terrain.getCielY());
       g.setColor(Color.WHITE);
       drawTimer(g);
       g.setColor(Color.gray);
       tabMissile1[Fenetre.getSelectMissile()].drawTrajectoir(g, tabMissile1[Fenetre.getSelectMissile()].getPosition(), "rrr");
       tabMissile2[Fenetre.getSelectMissile()].drawTrajectoir(g, tabMissile2[Fenetre.getSelectMissile()].getPosition(), "rrr");
       tabMissile1[Fenetre.getSelectMissile()].drawExp(g, this);
       tabMissile2[Fenetre.getSelectMissile()].drawExp(g, this);
        
        for (int i = 0; i < tank.length; i++) {
            drawTank(g, tank[i].getPositionTankX(), tank[i].getPositionTankY(), tank[i]);
        }
        
        if(Animation.getAltern()){
        	tank[1].viewAtribu(g);
        }else{
        	tank[0].viewAtribu(g);
        }
        
        tank[0].setTankVie(tabMissile2[Fenetre.getSelectMissile()], 30);
        tank[1].setTankVie(tabMissile1[Fenetre.getSelectMissile()], 30);
        
        
        g.setColor(Color.YELLOW);
		g.fillOval(850, -550, 200, 200);
    }

    

    public void repainting() {
        repaint();
    }

    public void drawTimer(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString(Animation.getTime(), 500, -450);
    }

    public void drawTank(Graphics g, int x, int y, Tank tank) {

        // Dessin Tank
        g.drawImage(tank.rotationImageTank(tank.getImTank(), tank.getAngleTank()), x, y, taille_image, taille_image, this);

        // Dessin Canon
        g.drawImage(
                tank.rotationImageTank(tank.rotationImageCanon(tank.getImCanon(), tank.getAngleCanon()), tank.getAngleTank()), tank.getPositionCanonX(), tank.getPositionCanonY(), taille_image2, taille_image2, this);
    }

    public void drawTerrain(Graphics g, int[] terrainX, int[] terrainY) {

        int[] X1 = new int[Terrain.getNbPoint()];
        int[] Y1 = new int[Terrain.getNbPoint()];
        pointTerrain = terrain.getTab();

        for (int i = 0; i < Terrain.getNbPoint(); i++) {
            X1[i] = (int) pointTerrain[i].getPointX();
            Y1[i] = (int) pointTerrain[i].getPointY();
        }

        g.setColor(Color.getHSBColor(121, 248, 248));
        g.fillPolygon(terrainX, terrainY, Terrain.getNbPoint());

    }

    public void drawCiel(Graphics g, int[] cielX, int[] cielY) {

        int[] X1 = new int[Terrain.getNbPoint()];
        int[] Y1 = new int[Terrain.getNbPoint()];
        pointTerrain = terrain.getTab();

        for (int i = 0; i < Terrain.getNbPoint(); i++) {
            X1[i] = (int) pointTerrain[i].getPointX();
            Y1[i] = (int) pointTerrain[i].getPointY();
        }

        g.setColor(Color.CYAN);
        g.fillPolygon(cielX, cielY, Terrain.getNbPoint());
    }

}
