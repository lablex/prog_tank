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

	protected Tank[] tank;
    protected Terrain terrain = new Terrain();
    protected int[] X;
    protected int[] Y;
    protected Point[] pointTerrain;


    // Constructeur de la classe
    public Animation(Tank[] tank, int n) {
    	this.tank = new Tank[n];
    	
    	for(int i=0; i<tank.length; i++){
    		
    		this.tank[i] = tank[i];
    	}
    	
        //Initialisation des variables pour la crÃ©ation du terrain
        X = new int[Terrain.getNbPoint()];
        Y = new int[Terrain.getNbPoint()];
        pointTerrain = terrain.getTab();
        for (int i = 0; i < Terrain.getNbPoint(); i++) {
            X[i] = (int) pointTerrain[i].getPointX();
            Y[i] = (int) pointTerrain[i].getPointY();
        }

    }
    
    //Initialisation des points du tank
   public void initialisation_points_tank() {
    	for(int i=0; i<tank.length; i++){
	        tank[i].setTankPointGaucheX(X[Tank.getXInt(tank[i].getX())]);
	        tank[i].setTankPointDroitX(X[Tank.getXInt(tank[i].getX()+tank[i].getLongueurTank())]);
	        tank[i].setTankPointGaucheY(Y[tank[i].getXInt()]);
	        tank[i].setTankPointDroitY(Y[Tank.getXInt(tank[i].getX()+tank[i].getLongueurTank())]);

    		
	        System.out.println("Xg: "+X[Tank.getXInt(tank[i].getX())]);
	        System.out.println("Xd: "+X[Tank.getXInt(tank[i].getX()+tank[i].getLongueurTank())]);
	        System.out.println("Yg: "+Y[tank[i].getXInt()]);
	        System.out.println("X: "+X[tank[i].getXInt()]);
	        System.out.println("getXInt+getLongueur_tank: "+tank[i].getXInt()+tank[i].getXInt(tank[i].getLongueurTank()));
	        System.out.println("getXIn: "+tank[i].getXInt());
	        System.out.println("getTankPointDroitY: "+tank[i].getTankPointDroitY());
	        System.out.println("getTankPointGaucheY: "+tank[i].getTankPointGaucheY());
	        System.out.println("getTankPointGaucheX: "+tank[i].getTankPointGaucheX());
	        System.out.println("getTankPointDroitX: "+tank[i].getTankPointDroitX());
	        System.out.println("getTankPointDroitY: "+Tank.getWidth());
	        System.out.println("");
    	}

    }
    
    public double[] getAngleImage(){
    	double[] tab = new double[tank.length];
    	for(int i=0; i<tank.length; i++){
    		tab[i]=-Point.angleBis(tank[i].getTankPointGauche(), tank[i].getTankPointDroit());
    		System.out.println("angle: "+tab[i]);
    		 System.out.println("");
    	}
    	return tab;
    }
    
    public void setAngleTank(){
    	double[] tab = new double[tank.length];
    	tab=getAngleImage();
    	for(int i=0; i<tank.length; i++){
    		tank[i].setAngleTank(tab[i]);
    	}
    }
    
    public BufferedImage rotationImage(Image image, double degs) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int a = tank[0].getXInt();
        BufferedImage temp = new BufferedImage(height, width, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = temp.createGraphics();
        g2.rotate(degs, width/2, height/2); // RÃ©glage de l'angle
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return temp;
    }

    public void repainting() {
    	 repaint();
    }
      
}
