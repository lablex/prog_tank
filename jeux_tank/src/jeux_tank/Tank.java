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
	private int positionTankX;
	private int positionTankY;
	private int positionCanonY;
	private int positionCanonX;
	private final int PLUS = 50 * (106 / 2) / 180;
	private final int PLUS2 = 50 * 106 / 180;
	
	
	private double TPRIM = 1000;
	private double ecartXFEN = (int) (Terrain.getNbPoint() / TPRIM)* Terrain.getEcartX();

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
	
	public void setPointsTank() {

			setTankPointGaucheX(Terrain.getTerrainX(getXInt(getX())));
			setTankPointDroitX(Terrain.getTerrainX(getXInt(getX() + getLongueurTank())));
			setTankPointGaucheY(Terrain.getTerrainY(getXInt(getX())));
			setTankPointDroitY(Terrain.getTerrainY(getXInt(getX() + getLongueurTank())));

	}
	
	public void setPositionTankX() {

		positionTankX = Terrain.getTerrainX(getXInt()) - 25 + (int) (PLUS * Math.sin(getAngleTank()));

	}
	
	public void setPositionTankY() {

		positionTankY = Terrain.getTerrainY(getXInt()) - 25 - (int) (PLUS * Math.cos(getAngleTank()));

	}
	
	public int getPositionTankX() {

		return positionTankX ;

	}
	
	public int getPositionTankY() {

		return positionTankY ;

	}
	
	public void setPositionCanonX() {

		positionCanonX = Terrain.getTerrainX(getXInt()) - 50 + (int) (PLUS2 * Math.sin(getAngleTank()));
		//System.out.println(PLUS2 * Math.sin(getAngleTank()));

	}
	
	public void setPositionCanonY() {

		positionCanonY = Terrain.getTerrainY(getXInt()) - 50 - (int) (PLUS2 * Math.cos(getAngleTank()));
		//System.out.println("test"+positionCanonY);

	}
	
	public int getPositionCanonX() {

		return positionCanonX ;

	}
	
	public int getPositionCanonY() {

		return positionCanonY ;

	}


	public void setAngleTank() {
		angleTank=-Point.angleBis(getTankPointGauche(),getTankPointDroit());
	}

	public BufferedImage rotationImageTank(Image image, double degs) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		int a = getXInt();
		BufferedImage temp = new BufferedImage(height, width,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2 = temp.createGraphics();
		g2.rotate(degs, width / 2, height / 2); // RÃ©glage de l'angle
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
		return temp;
	}
	
	public BufferedImage rotationImageCanon(Image image, double degs) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		int a = getXInt();
		BufferedImage temp = new BufferedImage(height, width,
				BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2 = temp.createGraphics();
		g2.rotate(degs, width / 2, height / 2); // RÃ©glage de l'angle
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
		return temp;
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
		return angleTank;
	}

	public Point getTankPointGauche() {
		return tankGauche;
		
	}

	public double getTankPointGaucheX() {
		return tankGauche.getPointX();
	}

	public double getTankPointGaucheY() {
		return tankGauche.getPointY();
	}

	public void setTankPointGaucheX(double x) {
		tankGauche.setPointX(x);
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
