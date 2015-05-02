/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class Animation extends JPanel implements ActionListener {

    // rafraichissement du Tank tous les 5ms
    Timer tm = new Timer(5, this);

    //Variables concernant le terrain  
    Terrain terrain;
    int[] X;
    int[] Y;
    Point[] tab;
    int reglage_nb_point = 1050;
    int offset_tank_terrain = 50;

    //Variables concernant le tank
    Tank joueur;
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

    // Images du tank
    private static final String IMAGE_PATH_tank = "src/jeux_tank/images/tank.png";
    private static final String IMAGE_PATH_canon = "src/jeux_tank/images/canon.png";
    private final int espace_vide_image = 20;
    private final int taille_image = 75;

    // Constructeur de la classe
    public Animation(Tank joueur, int[] X, int[] Y, Terrain terrain, Point[] tab) {
        this.joueur = joueur;
        this.Y = Y;
        this.X = X;
        this.terrain = terrain;
        this.tab = tab;
        tm.start();
    }

    //Initialisation des points du tank
    public void initialisation_points_tank() {
        joueur.getTankPointGauche().setPointX((int) joueur.getTankPointGauche().getPointX() + joueur.vx);
        joueur.getTankPointDroit().setPointX(joueur.getTankPointGauche().getPointX() + joueur.longueur_tank);
        joueur.getTankPoint113().setPointX(joueur.getTankPointGauche().getPointX() + joueur.longueur_tank - joueur.longueur_tank / 13);
        joueur.getTankPoint56().setPointX(joueur.getTankPointGauche().getPointX() + joueur.longueur_tank - joueur.longueur_tank / 6 * 5);
    }

    //Initialisation des angles
    public void initialisation_angles() {
        angle = Point.getAngle(Math.atan2(tab[(int) joueur.getTankPoint113().getPointX() * reglage_nb_point].getPointY() - tab[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point].getPointY(),
                tab[(int) joueur.getTankPoint113().getPointX() * reglage_nb_point].getPointX() - tab[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point].getPointX()));
    }

    //Gere la rotation du tank sur une pente
    public void gestion_rotation_pente(Point tankPosCenter, double angle_offset_descente, double angle_offset_monte, double gunX_offset2, double gunY_offset2) {
        gunX_offset = dis * Math.cos(Math.toRadians(angle + angle_offset_descente));
        gunY_offset = dis * Math.sin(Math.toRadians(angle + angle_offset_descente));
        positionX = (tankPosCenter.getPointX() - gunX_offset) - gunX_half;
        positionY = (tankPosCenter.getPointY() - gunY_offset) - gunY_half;
        joueur.setAngleTank(angle);

        if (angle > 25) { // Cas des chutes => annulation de la rotation
            joueur.getTankPointGauche().setPointY(Y[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point] - offset_tank_terrain);
            tankPosCenter = new Point(joueur.getTankPointGauche().getPointX() + tankPosCenterX_offset, joueur.getTankPointGauche().getPointY() + tankPosCenterY_offset);
            positionX = (tankPosCenter.getPointX() - gunX_offset2) - gunX_half;
            positionY = (tankPosCenter.getPointY() - gunY_offset2) - gunY_half;
            joueur.setAngleTank(0);
        }
    }

    //Gere la montee/descente du tank sur une pente
    public void gestion_pente() {
        if (Y[(int) joueur.getTankPointDroit().getPointX() * reglage_nb_point] - Y[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point] > 0) {
            joueur.getTankPointGauche().setPointY(Y[(int) joueur.getTankPoint56().getPointX() * reglage_nb_point] - offset_tank_terrain);
        } else if (Y[(int) joueur.getTankPointDroit().getPointX() * reglage_nb_point] - Y[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point] <= 0) {
            joueur.getTankPointGauche().setPointY(Y[(int) joueur.getTankPoint113().getPointX() * reglage_nb_point] - offset_tank_terrain);
        }
        double offsetX_gunPosCenter = 15;
        double offsetY_gunPosCenter = 13;
        Point tankPosCenter = new Point(joueur.getTankPointGauche().getPointX() + tankPosCenterX_offset, joueur.getTankPointGauche().getPointY() + tankPosCenterY_offset);
        Point gunPosCenter = new Point(joueur.getTankPointGauche().getPointX() + offsetX_gunPosCenter, joueur.getTankPointGauche().getPointY() + offsetY_gunPosCenter);
        dis = Point.distance(tankPosCenter, gunPosCenter);
        gestion_rotation_pente(tankPosCenter, 103, 99, -5, 23);

        joueur.getCentreCanon().setPointX(positionX);
        joueur.getCentreCanon().setPointY(positionY);

    }

    public void tir() {
        //Recuperation du point à l'extremité du canon
        double extremite_canon_x;
        double extremite_canon_y;
        double centre_canon_x = joueur.getCentreCanon().getPointX() + gunX_half;
        double centre_canon_y = joueur.getCentreCanon().getPointY() + gunY_half;
        double offsetX_gunPosExtremite = 46;
        double offsetY_gunPosExtremite = 13;
        Point tankPosCenter = new Point(joueur.getTankPointGauche().getPointX() + tankPosCenterX_offset, joueur.getTankPointGauche().getPointY() + tankPosCenterY_offset);
        Point gunPosExtremite = new Point(joueur.getTankPointGauche().getPointX() + offsetX_gunPosExtremite, joueur.getTankPointGauche().getPointY() + offsetY_gunPosExtremite);
        dis = Point.distance(tankPosCenter, gunPosExtremite);
        //distance(tankPosCenter, gunPosExtremite);

        gestion_rotation_pente(tankPosCenter, 144, 140, -36, 23);

        joueur.getTankExtremiteCanon().setPointX(positionX);
        joueur.getTankExtremiteCanon().setPointY(positionY);
        extremite_canon_x = joueur.getTankExtremiteCanon().getPointX() + gunX_half;
        extremite_canon_y = joueur.getTankExtremiteCanon().getPointY() + gunY_half;

        positionX = centre_canon_x + (extremite_canon_x - centre_canon_x) * Math.cos(Math.toRadians(joueur.getAngleCanon())) - (extremite_canon_y - centre_canon_y) * Math.sin(Math.toRadians(joueur.getAngleCanon()));
        positionY = centre_canon_y + (extremite_canon_x - centre_canon_x) * Math.sin(Math.toRadians(joueur.getAngleCanon())) + (extremite_canon_y - centre_canon_y) * Math.cos(Math.toRadians(joueur.getAngleCanon()));
        joueur.getTankExtremiteCanon().setPointX(positionX);
        joueur.getTankExtremiteCanon().setPointY(positionY);
        // Non terminé

    }

    public void actionPerformed(ActionEvent e) {
        if (joueur.getTankPointGauche().getPointX() <= 0) {
            joueur.vx = 0;
            joueur.getTankPointGauche().setPointX(1);
        }
        if (joueur.getTankPointGauche().getPointX() >= 950) {
            joueur.vx = 0;
            joueur.getTankPointGauche().setPointX(949);
        }
        if (joueur.y < 0) {
            joueur.vy = 0;
            joueur.y = 0;
        }
        if (joueur.y > 990) {
            joueur.vy = 0;
            joueur.y = 980;
        }

        initialisation_points_tank();
        initialisation_angles();
        gestion_pente();
        tir();

        repaint();

    }

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
