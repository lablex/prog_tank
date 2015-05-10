/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class Animation extends JPanel {

    // rafraichissement du Tank tous les 5ms
    Tank[] joueur;

    //Variables concernant le terrain  
    Terrain terrain = new Terrain();
    int[] X;
    int[] Y;
    Point[] tab;
    int reglage_nb_point = 1050;
    int offset_tank_terrain = 50;

    //Variables concernant le tank
    double angle;
    double dx;
    double dy;
    double dis;
    double gunX_offset;
    double gunY_offset;
    final double gunX_half = 38;
    final double gunY_half = 36;
    final double tankPosCenterX_offset = 15;
    final double tankPosCenterY_offset = 37;
    double positionX;
    double positionY;

    // Constructeur de la classe
    public Animation(Tank[] joueur, int n) {
    	this.joueur = new Tank[n];
    	
    	for(int i=0; i<joueur.length; i++){
    		
    		this.joueur[i] = joueur[i];
    	}
        //Initialisation des variables pour la création du terrain
        X = new int[terrain.getNbPoint()];
        Y = new int[terrain.getNbPoint()];
        tab = terrain.getTab();
        for (int i = 0; i < terrain.getNbPoint(); i++) {
            X[i] = (int) tab[i].getPointX();
            Y[i] = (int) tab[i].getPointY();
        }

    }

    //Initialisation des points du tank
    public void initialisation_points_tank() {
    	for(int i=0; i<joueur.length; i++){
        joueur[i].getTankPointGauche().setPointX((int) joueur[i].getTankPointGauche().getPointX() + joueur[i].vx);
        joueur[i].getTankPointDroit().setPointX(joueur[i].getTankPointGauche().getPointX() + joueur[i].longueur_tank);
        joueur[i].getTankPoint46().setPointX(joueur[i].getTankPointGauche().getPointX() + joueur[i].longueur_tank*4/6);
        joueur[i].getTankPoint113().setPointX(joueur[i].getTankPointGauche().getPointX() + joueur[i].longueur_tank - joueur[i].longueur_tank / 15);
        joueur[i].getTankPoint56().setPointX(joueur[i].getTankPointGauche().getPointX() + joueur[i].longueur_tank - joueur[i].longueur_tank / 6 * 5);
    
    	}
    }

    //Initialisation des angles
    public void initialisation_angles() {
    	for(int i=0; i<joueur.length; i++){
    		angle = Point.getAngle(Math.atan2(tab[(int) joueur[i].getTankPoint113().getPointX() * reglage_nb_point].getPointY() 
    											- tab[(int) joueur[i].getTankPoint46().getPointX() * reglage_nb_point].getPointY(),
    											tab[(int) joueur[i].getTankPoint113().getPointX() * reglage_nb_point].getPointX() 
    											- tab[(int) joueur[i].getTankPoint46().getPointX() * reglage_nb_point].getPointX()));
    	}
    }

    //Gere la rotation du tank sur une pente
    public void gestion_rotation_pente(Point tankPosCenter, double angle_offset_descente, double angle_offset_monte, double gunX_offset2, double gunY_offset2) {
        gunX_offset = dis * Math.cos(Math.toRadians(angle + angle_offset_descente));
        gunY_offset = dis * Math.sin(Math.toRadians(angle + angle_offset_descente));
        positionX = (tankPosCenter.getPointX() - gunX_offset) - gunX_half;
        positionY = (tankPosCenter.getPointY() - gunY_offset) - gunY_half;
        for(int i=0; i<joueur.length; i++){
        	joueur[i].setAngleTank(angle);
        }
        if (angle > 90) { // Cas des chutes => annulation de la rotation
        	for(int i=0; i<joueur.length; i++){
	        	joueur[i].getTankPointGauche().setPointY(Y[(int) joueur[i].getTankPointGauche().getPointX() * reglage_nb_point] - offset_tank_terrain);
	            tankPosCenter = new Point(joueur[i].getTankPointGauche().getPointX() + tankPosCenterX_offset, joueur[i].getTankPointGauche().getPointY() + tankPosCenterY_offset);
	            joueur[i].setAngleTank(0);
        	}
            positionX = (tankPosCenter.getPointX() - gunX_offset2) - gunX_half;
            positionY = (tankPosCenter.getPointY() - gunY_offset2) - gunY_half;
            
        }
    }

    //Gere la montee/descente du tank sur une pente
    public void gestion_pente() {
    	for(int i=0; i<joueur.length; i++){
	    	if (Y[(int) joueur[i].getTankPointDroit().getPointX() * reglage_nb_point] - Y[(int) joueur[i].getTankPointGauche().getPointX() * reglage_nb_point] > 0) {
	            joueur[i].getTankPointGauche().setPointY(Y[(int) joueur[i].getTankPoint56().getPointX() * reglage_nb_point] - offset_tank_terrain);
	        } else if (Y[(int) joueur[i].getTankPointDroit().getPointX() * reglage_nb_point] - Y[(int) joueur[i].getTankPointGauche().getPointX() * reglage_nb_point] <= 0) {
	            joueur[i].getTankPointGauche().setPointY(Y[(int) joueur[i].getTankPoint113().getPointX() * reglage_nb_point] - offset_tank_terrain);
	        }
    	}
        double offsetX_gunPosCenter = 15;
        double offsetY_gunPosCenter = 13;
        for(int i=0; i<joueur.length; i++){
	        Point tankPosCenter = new Point(joueur[i].getTankPointGauche().getPointX() + tankPosCenterX_offset, joueur[i].getTankPointGauche().getPointY() + tankPosCenterY_offset);
	        Point gunPosCenter = new Point(joueur[i].getTankPointGauche().getPointX() + offsetX_gunPosCenter, joueur[i].getTankPointGauche().getPointY() + offsetY_gunPosCenter);
	        dis = Point.distance(tankPosCenter, gunPosCenter);
	        gestion_rotation_pente(tankPosCenter, 103, 99, -5, 23);
	        joueur[i].getCentreCanon().setPointX(positionX);
	        joueur[i].getCentreCanon().setPointY(positionY);
        }

    }
    public void repainting() {
    	 repaint();
    }
    public void tir() {
        //Recuperation du point à l'extremité du canon
        double extremite_canon_x;
        double extremite_canon_y;
        double offsetX_gunPosExtremite = 46;
        double offsetY_gunPosExtremite = 13;
        
        for(int i=0; i<joueur.length; i++){
	        double centre_canon_x = joueur[i].getCentreCanon().getPointX() + gunX_half;
	        double centre_canon_y = joueur[i].getCentreCanon().getPointY() + gunY_half;
	        Point tankPosCenter = new Point(joueur[i].getTankPointGauche().getPointX() + tankPosCenterX_offset, joueur[i].getTankPointGauche().getPointY() + tankPosCenterY_offset);
	        Point gunPosExtremite = new Point(joueur[i].getTankPointGauche().getPointX() + offsetX_gunPosExtremite, joueur[i].getTankPointGauche().getPointY() + offsetY_gunPosExtremite);
	        dis = Point.distance(tankPosCenter, gunPosExtremite);
	
	        gestion_rotation_pente(tankPosCenter, 144, 140, -36, 23);
	
	        joueur[i].getTankExtremiteCanon().setPointX(positionX);
	        joueur[i].getTankExtremiteCanon().setPointY(positionY);
	        extremite_canon_x = joueur[i].getTankExtremiteCanon().getPointX() + gunX_half;
	        extremite_canon_y = joueur[i].getTankExtremiteCanon().getPointY() + gunY_half;
	
	        positionX = centre_canon_x + (extremite_canon_x - centre_canon_x) * Math.cos(Math.toRadians(joueur[i].getAngleCanon())) - (extremite_canon_y - centre_canon_y) * Math.sin(Math.toRadians(joueur[i].getAngleCanon()));
	        positionY = centre_canon_y + (extremite_canon_x - centre_canon_x) * Math.sin(Math.toRadians(joueur[i].getAngleCanon())) + (extremite_canon_y - centre_canon_y) * Math.cos(Math.toRadians(joueur[i].getAngleCanon()));
	        joueur[i].getTankExtremiteCanon().setPointX(positionX);
	        joueur[i].getTankExtremiteCanon().setPointY(positionY);
        }
        // Non terminé

    }

    
    public BufferedImage rotationImage(Image image, double degs) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage temp = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = temp.createGraphics();
        g2.rotate(Math.toRadians(degs), height / 2, height / 2); // Réglage de l'angle
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return temp;
    }
    
}
