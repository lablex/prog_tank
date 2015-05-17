/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 *
 * @author Kevin
 */
public class Draw extends Animation {

	private final int taille_image = 50;
	private final int taille_image2 = 100;
	private final int PLUS = 50 * (106 / 2) / 180;
	private final int PLUS2 = 50 * 106 / 180;
	private final int PLUS2s = 50 * 206 / 180;
	private final int PLUS3 = 100 * 150 / 500;

	public Draw(Tank[] tank, int n, Terrain terrain, Missile missile1, Missile missile2) {
		super(tank, n, terrain, missile1, missile2);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.translate(0, 400);
		drawTerrain(g, terrain.getTerrainX(), terrain.getTerrainY());
		drawCiel(g, terrain.getCielX(), terrain.getCielY());
		g.setColor(Color.RED);
		drawTrajectoir(g, missile2.getPosition(), "missile2");
		drawTrajectoir(g, missile1.getPosition(), "missile1");
		for (int i = 0; i < tank.length; i++) {
			drawTank(g, terrain.getTerrainX(tank[i].getXInt()) - 25
					+ (int) (PLUS * Math.sin(tank[i].getAngleTank())),
					terrain.getTerrainY(tank[i].getXInt()) - 25
					- (int) (PLUS * Math.cos(tank[i].getAngleTank())),
					tank[i]);
		}
		g.drawOval(terrain.getTerrainX(tank[0].getXInt())+ (int) (PLUS2 * Math.sin(tank[0].getAngleTank()))-10,		
				+terrain.getTerrainY(tank[0].getXInt())- (int) (PLUS2 * Math.cos(tank[0].getAngleTank()))-10, 20, 20);

	}

	public void drawTrajectoir(Graphics g, Point point, String name) {
		g.drawOval((int) point.getPointX(), -(int) point.getPointY(), 25, 25);

	}

	public void drawTank(Graphics g, int x, int y, Tank tank) {

		// Dessin Tank
		g.drawImage(rotationImage(tank.getImTank(), tank.getAngleTank()), x, y,
				taille_image, taille_image, this);

		// Dessin Canon
		g.drawImage(
				rotationImage(
						rotationImage(tank.getImCanon(), tank.getAngleCanon()),
						tank.getAngleTank()),
				terrain.getTerrainX(tank.getXInt()) - 50
						+ (int) (PLUS2 * Math.sin(tank.getAngleTank())),
				terrain.getTerrainY(tank.getXInt()) - 50
						- (int) (PLUS2 * Math.cos(tank.getAngleTank())),
				taille_image2, taille_image2, this);
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
