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

    public Draw(Tank[] joueur, int n) {
		super(joueur, n);
		
	}

	// Images du tank
    private static final String IMAGE_PATH_tank = "src/jeux_tank/images/tank.png";
    private static final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
    private final int espace_vide_image = 20;
    private final int taille_image = 75;
    //variable concernant le terrain se sont des reprise de variable qui sont deja existante dans la classe terrain
    private int nbPoint=1048576;
    private double T = 4*3.14;
	private double w = (2*3.14)/T;
	private double ecartX = T/nbPoint;
	private double incrementy0 = ecartX;
	private double incrementy = 0;
	private double incrementx=0;
	private double incrementx0=ecartX*1000/T;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image tank = new ImageIcon(IMAGE_PATH_tank).getImage();
        Image canon = new ImageIcon(IMAGE_PATH_canon).getImage();
        
        //////////////////  DEBUT:     Dessin Terrain   //////////////////////
        g.translate(0, 500);
  
        //-------- DEBUT création du tableau qui contient les valeur des point qui serviront à la construction du poligone qui forme le ciel ---------//
        int[] X1 = new int[terrain.getNbPoint()];
        int[] Y1 = new int[terrain.getNbPoint()];
        tab = terrain.getTab();
        for (int i = 0; i < terrain.getNbPoint(); i++) {
            X1[i] = (int) tab[i].getPointX();
            Y1[i] = (int) tab[i].getPointY();
        }
        //-------- FIN de la création du tableau qui contient les valeur des points ---------//
        
        //permet de régler la couleur du terrain
        g.setColor(Color.getHSBColor(121, 248, 248));
        g.fillPolygon(X1, Y1, terrain.getNbPoint());
        
        
        //les 4 lines qui suivent redonnent une valeur aux point 0 et 999999
        //pour créer un nouveau poligone mais cette fois ci au dessu du terrain 
        //pour pouvoir le collorier en bleu
			X[0]=0;
			Y[0]=Y[1]-1000;
			X[nbPoint-1]=(int)(incrementx0*(nbPoint-2));
			Y[nbPoint-1]=Y[nbPoint-2]-1000;
		//réglage de la couleur du ciel
		g.setColor(Color.CYAN);
	    g.fillPolygon(X, Y, terrain.getNbPoint());
		
	    //////////////////   FIN:     Dessin Terrain   //////////////////////

	    for(int i=0; i<joueur.length; i++){
	        //ExtremitÃ© du Canon
	        g.drawLine(0, 0, (int) (joueur[i].getTankExtremiteCanon().getPointX()), (int) (joueur[i].getTankExtremiteCanon().getPointY()));
	        //Dessin Tank
	        g.drawImage(rotationImage(tank, joueur[i].getAngleTank()), (int) joueur[i].getTankPointGauche().getPointX() - espace_vide_image, (int) joueur[i].getTankPointGauche().getPointY(), taille_image, taille_image, this);
	        //Dessin Canon
	        g.drawImage(rotationImage(rotationImage(canon, joueur[i].getAngleCanon()), joueur[i].getAngleTank()), (int) joueur[i].getCentreCanon().getPointX(), (int) joueur[i].getCentreCanon().getPointY(), taille_image, taille_image, this);
	    }
    }

}
