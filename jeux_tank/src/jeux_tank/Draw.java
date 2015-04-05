package jeux_tank;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

class Draw extends Animation {

    private static final String IMAGE_PATH_tank = "src/jeux_tank/images/tank_bas.png";
    private static final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
    private final int espace_vide_image = 30;
    private final int taille_image = 100;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image tank = new ImageIcon(IMAGE_PATH_tank).getImage();
        Image canon = new ImageIcon(IMAGE_PATH_canon).getImage();
        g.translate(250, 50);
        g.drawPolyline(X, Y, terrain.getNbPoint());
        g.drawRect(0, 0, 1000, 700);
        g.drawImage(joueur.rotationTank(tank, joueur.angle_tank), (int)joueur.tank_gauche.getPointX()-espace_vide_image,(int) joueur.tank_gauche.getPointY() + 37, taille_image, taille_image, this);
        g.drawImage(joueur.rotationTank(canon, joueur.angle), (int)joueur.tank_gauche.getPointX() - espace_vide_image,(int) joueur.tank_gauche.getPointY() + 37 - joueur.distance_tank_canon, taille_image, taille_image, this);
    }

}
