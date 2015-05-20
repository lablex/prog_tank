package jeux_tank;

public class Terrain {
	private Point[] tab;
	private static int TerrainX[];
	private static int TerrainY[];
	private static int CielX[];
	private static int CielY[];
	private static int nbPoint = 1000;
	// p�riode des sinus/cosinus
	private static double T = 4 * 3.14;
	// pulsation des sinus/cosinus
	private double w = (2 * 3.14) / T;
	// distance entre deux abscisses
	private static double ecartX = T / nbPoint;
	// varible qui va permettre d'incr�menter la variable incrementy. le nom de
	// cette variable est trompeur car ce n'est pas y que l'on incr�mente
	// mais la valeur de x qui se trouve dans les sinus/cossinus qui eux
	// permettent de donner la valeur de y
	private double incrementy0 = ecartX;
	// coeficient de fourier al�atoire
	private double coef1 = 150 * Math.random();
	private double coef0 = 150 * Math.random();
	private double coef2 = 150 * Math.random();
	private double coef3 = 150 * Math.random();

	public Terrain() {
		tab = Point.tabPoint(nbPoint);
		CielX = new int[nbPoint];
		CielY = new int[nbPoint];
		TerrainX = new int[nbPoint];
		TerrainY = new int[nbPoint];
		// valeur de la corrdonn� y a chaque nouveaux tour de boucle
		double incrementy = 0;

		// valeur de la corrdonn� x a chaque nouveaux tour de boucle
		double incrementx = 0;

		// valeur perm�tant d'incr�menter la vriable incrementx le calcule qui
		// donne la valeur de incrementx0
		// est un produit en croit cela permet de dilater l'axe des x est ainsi
		// agrandire la courbe sinn celle ci serait invisible
		double incrementx0 = ecartX * 1000 / T;

		for (int i = 1; i < nbPoint - 1; i++) {

			// r�gla de la valeur x en incr�mentant la variable incrementx de la
			// valeur de la variable incr�mentx0 � chaque nouveau tour de de
			// boucle
			tab[i].setPointX(incrementx);
			TerrainX[i] = (int) tab[i].getPointX();
			CielX[i] = (int) tab[i].getPointX();
			// r�glade la valeur y avec l'aide d'une s�rie de fourier cette
			// s�rie est all�atoir car elle poss�de des coef all�atoire
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
			// ces deux if permettent de placer le premier point et le dernier
			// point � des ordonn� beaucoup plus basses mais � la m�mme abscisse
			// le dexieme et avant dernier point qui comment la courbe ce
			// placement sert a former un poligone qui � 3 cot� en forme de
			// droite et
			// un pr�sentant notre courbe. ce poligonne est important car il
			// nous permettra par la suite de collor� le terrain
			if (i == 1) {
				tab[0].setPointX(0);

				tab[0].setPointY(tab[1].getPointY() + 200);
				TerrainX[0] = (int) tab[0].getPointX();
				TerrainY[0] = (int) tab[0].getPointY();

				tab[0].setPointY(tab[1].getPointY() - 1000);
				CielX[0] = (int) tab[0].getPointX();
				CielY[0] = (int) tab[0].getPointY();

			}
			if (i == nbPoint - 2) {
				tab[nbPoint - 1].setPointX(incrementx);

				tab[nbPoint - 1].setPointY(tab[nbPoint - 2].getPointY() + 200);
				TerrainX[nbPoint - 1] = (int) tab[nbPoint - 1].getPointX();
				TerrainY[nbPoint - 1] = (int) tab[nbPoint - 1].getPointY();

				tab[nbPoint - 1].setPointY(tab[nbPoint - 2].getPointY() - 1000);
				CielX[nbPoint - 1] = (int) tab[nbPoint - 1].getPointX();
				CielY[nbPoint - 1] = (int) tab[nbPoint - 1].getPointY();
			}

			// incr�mentation des variable
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
			// il faut calculer l'angle entre les point extremaux du groupe de
			// point pour calculer le pas de theta
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
