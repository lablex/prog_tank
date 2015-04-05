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
        //Dessin Terrain
        g.translate(250, 50);
        g.drawPolyline(X, Y, terrain.getNbPoint());
        g.drawRect(0, 0, 1000, 700);
        //Dessin Tank
        g.drawImage(joueur.rotationTank(tank, joueur.getAngleTank()), (int) joueur.getTankPointGauche().getPointX() - espace_vide_image, (int) joueur.getTankPointGauche().getPointY(), taille_image, taille_image, this);
        //Dessin Canon
        g.drawImage(joueur.rotationTank(joueur.rotationTank(canon, joueur.getAngleCanon()), joueur.getAngleTank()), (int) joueur.getCanon().getPointX(), (int) joueur.getCanon().getPointY(), taille_image, taille_image, this);
    }

}
