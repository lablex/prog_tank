/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

/**
 *
 * @author Kevin
 */
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Tank {

    private static final long serialVersionUID = 1L;

    int x = 0, y = 0, vx = 1, vy = 1;
    Point tank_gauche;
    Point tank_droite;
    Point canon;
    double angle_tank,angle_canon=0;
    protected final int longueur_tank;
    protected final int distance_tank_canon;

    public Tank() {
        tank_gauche = new Point(0, 0);
        tank_droite = new Point(0, 0);
        canon = new Point(0, 0);
        longueur_tank = 35;
        distance_tank_canon = 30;
    }

    public BufferedImage rotationTank(Image image, double degs) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage temp = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = temp.createGraphics();
        g2.rotate(Math.toRadians(degs), height / 2, height / 2); // Réglage de l'angle
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return temp;
    }
    
    public void setAngleCanon(double angle_canon){
        this.angle_canon = angle_canon;
    }
    
    public double getAngleCanon(){
        return this.angle_canon;
    }
    
    public void setAngleTank(double angle_tank) {
        this.angle_tank = angle_tank;
    }
    
    public double getAngleTank(){
        return this.angle_tank;
    }

}
