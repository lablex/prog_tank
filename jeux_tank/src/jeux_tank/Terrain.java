package jeux_tank;

public class Terrain {
	private Point[] tab;
	private static int TerrainX[];
	private static int TerrainY[];
	private static int CielX[];
	private static int CielY[];
	private static int nbPoint = 1000;
	private static double T = 4 * 3.14;
	private double w = (2 * 3.14) / T;
	private static double ecartX = T / nbPoint;
	private double incrementy0 = ecartX;
	private double coef1 = -70 * Math.random();
	private double coef0 = -70 * Math.random();
	private double coef2 = -70 * Math.random();
	private double coef3 = -70 * Math.random();

	public Terrain() {
		tab = Point.tabPoint(nbPoint);
		CielX = new int[nbPoint];
		CielY = new int[nbPoint];
		TerrainX = new int[nbPoint];
		TerrainY = new int[nbPoint];
		double incrementy = 0;
		double incrementx = 0;
		double incrementx0 = ecartX * 1000 / T;

		for (int i = 1; i < nbPoint - 1; i++) {

			tab[i].setPointX(incrementx);
			TerrainX[i] = (int) tab[i].getPointX();
			CielX[i] = (int) tab[i].getPointX();
			tab[i].setPointY(coef0 * Math.sin(1 * w * incrementy) + coef1
					* Math.sin(2 * w * incrementy) + 60
					* Math.sin(3 * w * incrementy) + 15
					* Math.sin(4 * w * incrementy) + 10
					* Math.sin(5 * w * incrementy) + 3
					* Math.sin(6 * w * incrementy) + coef2
					* Math.cos(1 * w * incrementy) + coef3
					* Math.cos(2 * w * incrementy) + 20
					* Math.cos(3 * w * incrementy) + 5
					* Math.cos(4 * w * incrementy) + 8
					* Math.cos(5 * w * incrementy) + 5
					* Math.cos(6 * w * incrementy));
			TerrainY[i] = (int) tab[i].getPointY();
			CielY[i] = (int) tab[i].getPointY();

			if (i == 1) {
				tab[0].setPointX(0);

				tab[0].setPointY(tab[1].getPointY() + 1000);
				TerrainX[0] = (int) tab[0].getPointX();
				TerrainY[0] = (int) tab[0].getPointY();

				tab[0].setPointY(tab[1].getPointY() - 1000);
				CielX[0] = (int) tab[0].getPointX();
				CielY[0] = (int) tab[0].getPointY();

			}
			if (i == nbPoint - 2) {
				tab[nbPoint - 1].setPointX(incrementx);

				tab[nbPoint - 1].setPointY(tab[nbPoint - 2].getPointY() + 1000);
				TerrainX[nbPoint - 1] = (int) tab[nbPoint - 1].getPointX();
				TerrainY[nbPoint - 1] = (int) tab[nbPoint - 1].getPointY();

				tab[nbPoint - 1].setPointY(tab[nbPoint - 2].getPointY() - 1000);
				CielX[nbPoint - 1] = (int) tab[nbPoint - 1].getPointX();
				CielY[nbPoint - 1] = (int) tab[nbPoint - 1].getPointY();
			}


			incrementy = incrementy + incrementy0;
			incrementx = incrementx + incrementx0;
		}

	}

	public void destructionTerrain(int[] tabIndice, int R, Point impact,
			Point[] tab) {
		double x;
		double y;
		double theta;
		double increTheta;
		if (tab[tabIndice.length - 1].penteTarrain(impact)) {
			theta = impact.angle(tab[tabIndice.length - 1]);
			increTheta = 2;

			for (int i = 0; i < tabIndice.length; i++) {
				if (i > 0 && i < tabIndice.length - 1) {
					x = impact.getPointX() + R
							* Math.cos(theta - i * increTheta);
					y = impact.getPointY() + R
							* Math.sin(theta - i * increTheta);
					tab[i].setPointX(x);
					tab[i].setPointY(y);
				}
			}
		} else {

		}
	}

	public static int getTerrainX(int i) {
		return TerrainX[i];
	}

	public static int getTerrainY(int i) {
		try{
			return TerrainY[i];
			}
			catch(ArrayIndexOutOfBoundsException e){
				return -1;
			}
	}

	public int[] getTerrainX() {
		return TerrainX;
	}

	public int[] getTerrainY() {
		return TerrainY;
	}

	public int[] getCielX() {
		return CielX;
	}

	public int[] getCielY() {
		return CielY;
	}

	public int getT() {
		return (int) T;
	}

	public Point[] getTab() {
		return this.tab;
	}

	public static double getEcartX() {
		return ecartX;
	}

	public static int getNbPoint() {
		return nbPoint;
	}
}
