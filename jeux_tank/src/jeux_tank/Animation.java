/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class Animation extends JPanel {

	protected Terrain terrain;
	protected Tank[] tank;
	protected Point[] pointTerrain;
	protected Missile missile1;
	protected Missile missile2;

	// Constructeur de la classe
	public Animation(Tank[] tank, int n, Terrain terrain, Missile missile1, Missile missile2) {
		this.tank = new Tank[n];
		this.missile1 = missile1;
		this.missile2 = missile2;
		this.terrain = terrain;

		for (int i = 0; i < tank.length; i++) {

			this.tank[i] = tank[i];
		}
	}

	// Initialisation des points du tank
	public void initialisation_points_tank() {

		for (int i = 0; i < tank.length; i++) {
			tank[i].setTankPointGaucheX(terrain.getTerrainX(tank[i]
					.getXInt(tank[i].getX())));
			tank[i].setTankPointDroitX(terrain.getTerrainX(tank[i]
					.getXInt(tank[i].getX() + tank[i].getLongueurTank())));
			tank[i].setTankPointGaucheY(terrain.getTerrainY(tank[i]
					.getXInt(tank[i].getX())));
			tank[i].setTankPointDroitY(terrain.getTerrainY(tank[i]
					.getXInt(tank[i].getX() + tank[i].getLongueurTank())));
		}

	}


	public double[] getAngleImage() {
		double[] tab = new double[tank.length];
		for (int i = 0; i < tank.length; i++) {
			tab[i] = -Point.angleBis(tank[i].getTankPointGauche(),
					tank[i].getTankPointDroit());
		}
		return tab;
	}

	public void setAngleTank() {
		double[] tab = new double[tank.length];
		tab = getAngleImage();
		for (int i = 0; i < tank.length; i++) {
			tank[i].setAngleTank(tab[i]);
		}
	}

	public BufferedImage rotationImage(Image image, double degs) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		int a = tank[0].getXInt();
		BufferedImage temp = new BufferedImage(height, width,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2 = temp.createGraphics();
		g2.rotate(degs, width / 2, height / 2); // RÃ©glage de l'angle
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
		return temp;
	}

	public void repainting() {
		repaint();
	}

}
