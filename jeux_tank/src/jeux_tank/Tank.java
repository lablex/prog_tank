/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 *
 * @author Kevin
 */
public class Tank {

	private double x = 0;
	private Point tankGauche;
	private Point tankDroit;
	private double angleTank;
	private double angleCanon;
	private Image immTank;
	private Image immCanon;
	private static int width = 50 * (106 / 2) / 180;
	private final double longueurTank;
	private static int PXimage = 1000;
	static int countInstance = 0;
	protected Missile missile;

	
	
	private double TPRIM = 1000;
	private double ecartXFEN = (int) (Terrain.getNbPoint() / TPRIM)
			* Terrain.getEcartX();

	public Tank(String IMAGE_PATH_tank, String IMAGE_PATH_canon, int emplacement) {
		immTank = new ImageIcon(IMAGE_PATH_tank).getImage();
		immCanon = new ImageIcon(IMAGE_PATH_canon).getImage();
		tankGauche = new Point(0, 0);
		tankDroit = new Point(0, 0);
		angleCanon = 0;
		this.x=emplacement*ecartXFEN;
		longueurTank = (Terrain.getNbPoint() * width / PXimage)
				* Terrain.getEcartX();
		countInstance += 1;
	}

	public Missile getMissile() {
		return this.missile;
	}
	
	public Image getImTank() {
		return immTank;

	}

	public Image getImCanon() {
		return immCanon;

	}

	public void setAngleCanon(double angleCanon, boolean test) {
		if (test) {
			this.angleCanon = this.angleCanon + angleCanon;
		}
	}

	public double getAngleCanon() {
		return this.angleCanon;
	}

	public double getLongueurTank() {
		return this.longueurTank;
	}

	public void setX(double x, boolean test) {
		if (test) {
			this.x = this.x + x;
		}
	}

	public static int getWidth() {
		return width;
	}

	public static int getPXimage() {
		return PXimage;
	}

	public double getVX() {
		return this.x;
	}

	public double getX() {
		return this.x;
	}

	public int getXInt(double x) {
		return (int) (x / Terrain.getEcartX());
	}

	public int getXInt() {
		return (int) (this.x / Terrain.getEcartX());
	}

	public void setAngleTank(double angleTank) {
		this.angleTank = angleTank;
	}

	public double getAngleTank() {
		return this.angleTank;
	}

	public Point getTankPointGauche() {
		return this.tankGauche;
	}

	public double getTankPointGaucheX() {
		return this.tankGauche.getPointX();
	}

	public double getTankPointGaucheY() {
		return this.tankGauche.getPointY();
	}

	public void setTankPointGaucheX(double x) {
		this.tankGauche.setPointX(x);
	}

	public void setTankPointGaucheY(double y) {
		this.tankGauche.setPointY(y);
	}

	public void setTankPointDroitX(double x) {
		this.tankDroit.setPointX(x);
	}

	public void setTankPointDroitY(double y) {
		this.tankDroit.setPointY(y);
	}

	public Point getTankPointDroit() {
		return this.tankDroit;
	}

	public double getTankPointDroitX() {
		return this.tankDroit.getPointX();
	}

	public double getTankPointDroitY() {
		return this.tankDroit.getPointY();
	}

}
