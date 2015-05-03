/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Kevin
 */
public class Draw extends Animation{

    // Images du tank
    private static final String IMAGE_PATH_tank = "src/jeux_tank/images/tank.png";
    private static final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
    private final int espace_vide_image = 20;
    private final int taille_image = 75;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image tank = new ImageIcon(IMAGE_PATH_tank).getImage();
        Image canon = new ImageIcon(IMAGE_PATH_canon).getImage();
        
        //Dessin Terrain
        g.translate(250, 50);
        g.drawPolyline(X, Y, terrain.getNbPoint());
        g.drawRect(0, 0, 1000, 700);

        //Extremité du Canon
        g.drawLine(0, 0, (int) (joueur.getTankExtremiteCanon().getPointX()), (int) (joueur.getTankExtremiteCanon().getPointY()));
        //Dessin Tank
        g.drawImage(rotationImage(tank, joueur.getAngleTank()), (int) joueur.getTankPointGauche().getPointX() - espace_vide_image, (int) joueur.getTankPointGauche().getPointY(), taille_image, taille_image, this);
        //Dessin Canon
        g.drawImage(rotationImage(rotationImage(canon, joueur.getAngleCanon()), joueur.getAngleTank()), (int) joueur.getCentreCanon().getPointX(), (int) joueur.getCentreCanon().getPointY(), taille_image, taille_image, this);

    }

}
