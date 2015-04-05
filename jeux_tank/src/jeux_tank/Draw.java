package jeux_tank;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

class Draw extends Animation {

    private static final String IMAGE_PATH_tank = "src/jeux_tank/images/tank_bas.png";
    private static final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
    private final int espace_vide_image = 30;
    private final int distance_tank_canon = 30;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image tank = new ImageIcon(IMAGE_PATH_tank).getImage();
        Image canon = new ImageIcon(IMAGE_PATH_canon).getImage();
        g.translate(250, 50);
        g.drawPolyline(X, Y, terrain.getNbPoint());
        g.drawRect(0, 0, 1000, 700);
        g.drawImage(tank, (int)joueur.tank_gauche.getPointX()-espace_vide_image,(int) joueur.tank_gauche.getPointY() + espace_vide_image, 100, 100, this);
        g.drawImage(joueur.rotationImage(canon, joueur.angle), (int)joueur.tank_gauche.getPointX() - espace_vide_image,(int) joueur.tank_gauche.getPointY() + espace_vide_image - distance_tank_canon, 100, 100, this);
    }

}
