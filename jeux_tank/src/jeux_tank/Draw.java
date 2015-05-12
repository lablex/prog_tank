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
public class Draw extends Animation{
	
	
	private final int taille_image = 50;
	private final int taille_image2 = 100;
	private final int PLUS = 50*(106/2)/180;
	private final int PLUS2 = 50*106/180;
	private double incrementx0=Terrain.getEcartX()*1000/Terrain.getT();
	
	
    public Draw(Tank[] tank, int n) {
		super(tank, n);
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        //////////////////  DEBUT:     Dessin Terrain   //////////////////////
        g.translate(100, 300);
        g.drawRect(0, 500, 50, 25);
        //-------- DEBUT cr�ation du tableau qui contient les valeur des point qui serviront � la construction du poligone qui forme le ciel ---------//
        int[] X1 = new int[Terrain.getNbPoint()];
        int[] Y1 = new int[Terrain.getNbPoint()];
        pointTerrain = terrain.getTab();
        for (int i = 0; i < Terrain.getNbPoint(); i++) {
            X1[i] = (int) pointTerrain[i].getPointX();
            Y1[i] = (int) pointTerrain[i].getPointY();
        }
        //-------- FIN de la cr�ation du tableau qui contient les valeur des points ---------//
        
        //permet de r�gler la couleur du terrain
        g.setColor(Color.getHSBColor(121, 248, 248));
        g.fillPolygon(X1, Y1, Terrain.getNbPoint());
        
        
        //les 4 lines qui suivent redonnent une valeur aux point 0 et 999999
        //pour cr�er un nouveau poligone mais cette fois ci au dessu du terrain 
        //pour pouvoir le collorier en bleu
			X[0]=0;
			Y[0]=Y[1]-1000;
			X[Terrain.getNbPoint()-1]=(int)(incrementx0*(Terrain.getNbPoint()-2));
			Y[Terrain.getNbPoint()-1]=Y[Terrain.getNbPoint()-2]-1000;
		//r�glage de la couleur du ciel
		g.setColor(Color.CYAN);
	    g.fillPolygon(X, Y, Terrain.getNbPoint());
		
	    //////////////////   FIN:     Dessin Terrain   //////////////////////
	    //for(int i=0; i<tank.length; i++){
	        //Extremité du Canon
	        //g.drawLine(0, 0, (int) (tank[i].getTankExtremiteCanon().getPointX()), (int) (tank[i].getTankExtremiteCanon().getPointY()));
	        //Dessin Tank
	        g.drawImage(rotationImage(tank[0].getImTank(),tank[0].getAngleTank()), X[tank[0].getXInt()]-25+(int)(PLUS*Math.sin(tank[0].getAngleTank())), Y[tank[0].getXInt()]-25-(int)(PLUS*Math.cos(tank[0].getAngleTank())), taille_image, taille_image, this);
	        
	        //Dessin Canon
	        g.drawImage(rotationImage(rotationImage(tank[0].getImCanon(), tank[0].getAngleCanon()), tank[0].getAngleTank()), X[tank[0].getXInt()]-50+(int)(PLUS2*Math.sin(tank[0].getAngleTank())), Y[tank[0].getXInt()]-50-(int)(PLUS2*Math.cos(tank[0].getAngleTank())), taille_image2, taille_image2, this);
	    //}
    }

}
