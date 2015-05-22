/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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

        g.translate(0, 400);
        drawTerrain(g, terrain.getTerrainX(), terrain.getTerrainY());
        drawCiel(g, terrain.getCielX(), terrain.getCielY());
        g.setColor(Color.BLACK);
        drawTimer(g);
        g.setColor(Color.BLACK);
        tabMissile1[Fenetre.getSelectMissile()].drawTrajectoir(g, tabMissile1[Fenetre.getSelectMissile()].getPosition(), "rrr");
        tabMissile2[Fenetre.getSelectMissile()].drawTrajectoir(g, tabMissile2[Fenetre.getSelectMissile()].getPosition(), "rrr");
        
        for (int i = 0; i < tank.length; i++) {
            drawTank(g, tank[i].getPositionTankX(), tank[i].getPositionTankY(), tank[i]);
        }
    }

    

    public void repainting() {
        repaint();
    }

    public void drawTimer(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(Animation.getTime(), 500, -350);
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
