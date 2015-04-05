package jeux_tank;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class Draw extends Animation {

    private static final String IMAGE_PATH_tank = "src/jeux_tank/images/tank_bas.png";
    private static final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
    private final int contour_image = 30;
    private final int distance_tank = 30;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image tank = new ImageIcon(IMAGE_PATH_tank).getImage();
        Image canon = new ImageIcon(IMAGE_PATH_canon).getImage();
        g.translate(250, 50);
        g.drawPolyline(X, Y, terrain.getNbPoint());
        g.drawRect(0, 0, 1000, 700);
        g.drawImage(tank, joueur.x - contour_image, joueur.y + contour_image, 100, 100, this);
        g.drawImage(joueur.rotationImage(canon, joueur.angle), joueur.x - contour_image, joueur.y + contour_image - distance_tank, 100, 100, this);
    }

}
