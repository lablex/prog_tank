package jeux_tank;

import java.awt.Graphics;

import javax.swing.JPanel;

class Paneau extends JPanel{
	Terrain terrain = new Terrain();
	int[] X = new int[terrain.getNbPoint()];
	int[] Y = new int[terrain.getNbPoint()];
	public void paintComponent(Graphics g){
		g.translate(250, 50);
		Point[] tab = terrain.getTab();
		for(int i=0; i<terrain.getNbPoint(); i++){
			X[i]=(int) tab[i].getPointX();
			Y[i]=(int) tab[i].getPointY();
		}
		g.drawPolyline(X, Y, terrain.getNbPoint());
		g.drawRect(0, 0, 1000, 700);
	}
	
}