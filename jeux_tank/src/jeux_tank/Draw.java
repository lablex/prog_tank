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
	
	
    public Draw(Tank[] tank, int n, Terrain terrain) {
		super(tank, n, terrain);
	}

    public void paintComponent(Graphics g) {
    	
    	
    	
        super.paintComponent(g);
        g.translate(0, 400);
        drawTerrain(g, terrain.getTerrainX(), terrain.getTerrainY());
        drawCiel(g, terrain.getCielX(), terrain.getCielY());
	    drawTank(g);
	    
	    
    }
    
    
    
    
    public void drawTank(Graphics g){
    	 
    	for (int i = 0; i < tank.length; i++) {
	    	//Dessin Tank
	        g.drawImage(rotationImage(tank[i].getImTank(),tank[i].getAngleTank()), terrain.getTerrainX(tank[i].getXInt())-25+(int)(PLUS*Math.sin(tank[i].getAngleTank())), terrain.getTerrainY(tank[i].getXInt())-25-(int)(PLUS*Math.cos(tank[i].getAngleTank())), taille_image, taille_image, this);
	        
	        //Dessin Canon
	        g.drawImage(rotationImage(rotationImage(tank[i].getImCanon(), tank[i].getAngleCanon()), tank[i].getAngleTank()), terrain.getTerrainX(tank[i].getXInt())-50+(int)(PLUS2*Math.sin(tank[i].getAngleTank())), terrain.getTerrainY(tank[i].getXInt())-50-(int)(PLUS2*Math.cos(tank[i].getAngleTank())), taille_image2, taille_image2, this);
    	}
    }
    
    public void drawTerrain(Graphics g, int[] terrainX,int[] terrainY){
    	int[] X1 = new int[Terrain.getNbPoint()];
        int[] Y1 = new int[Terrain.getNbPoint()];
        pointTerrain = terrain.getTab();
        
        for (int i = 0; i < Terrain.getNbPoint(); i++) {
            X1[i] = (int) pointTerrain[i].getPointX();
            Y1[i] = (int) pointTerrain[i].getPointY();
        }

        g.setColor(Color.getHSBColor(121, 248, 248));
	    g.fillPolygon(terrainX, terrainY, Terrain.getNbPoint());

    }
    
    public void drawCiel(Graphics g, int[] cielX,int[] cielY){
    	int[] X1 = new int[Terrain.getNbPoint()];
        int[] Y1 = new int[Terrain.getNbPoint()];
        pointTerrain = terrain.getTab();
        
        for (int i = 0; i < Terrain.getNbPoint(); i++) {
            X1[i] = (int) pointTerrain[i].getPointX();
            Y1[i] = (int) pointTerrain[i].getPointY();
        }
	    
	    g.setColor(Color.CYAN);
        g.fillPolygon(cielX, cielY, Terrain.getNbPoint());
    }

}
