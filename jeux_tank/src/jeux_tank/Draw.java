/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class Draw extends JPanel {

    private final int taille_image = 50;
    private final int taille_image2 = 100;

    protected Terrain terrain;
    protected Tank[] tank;
    protected Point[] pointTerrain;
    protected Missile missile1;
    protected Missile missile2;

    public Draw(Tank[] tank, int n, Terrain terrain, Missile missile1, Missile missile2) {
        this.tank = new Tank[n];
        this.missile1 = missile1;
        this.missile2 = missile2;
        this.terrain = terrain;

        for (int i = 0; i < tank.length; i++) {

            this.tank[i] = tank[i];
        }
    }

    public void paintComponent(Graphics g) {

        g.translate(0, 400);
        drawTerrain(g, terrain.getTerrainX(), terrain.getTerrainY());
        drawCiel(g, terrain.getCielX(), terrain.getCielY());
        g.setColor(Color.RED);
        drawTimer(g);
        drawTrajectoir(g, missile2.getPosition(), "missile2");
        drawTrajectoir(g, missile1.getPosition(), "missile1");
        for (int i = 0; i < tank.length; i++) {
            drawTank(g, tank[i].getPositionTankX(), tank[i].getPositionTankY(), tank[i]);
        }
    }

    public void drawTrajectoir(Graphics g, Point point, String name) {
        g.drawOval((int) point.getPointX(), -(int) point.getPointY(), 25, 25);

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
