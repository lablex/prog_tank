/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author Kevin
 */
public class Animation extends JPanel implements ActionListener, KeyListener {

    Timer tm = new Timer(5, this);
    Tank joueur = new Tank();
    Terrain terrain = new Terrain();
    int[] X;
    int[] Y;
    Point[] tab;
    int reglage_nb_point = 1050;
    int offset_tank_terrain = 68;

    public Animation() {
        tm.start();
        //Initialisation des variables pour la création du terrain
        X = new int[terrain.getNbPoint()];
        Y = new int[terrain.getNbPoint()];
        tab = terrain.getTab();
        for (int i = 0; i < terrain.getNbPoint(); i++) {
            X[i] = (int) tab[i].getPointX();
            Y[i] = (int) tab[i].getPointY();
        }
        //Listeners
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (joueur.getTankPointGauche().getPointX() <= 0) {
            joueur.vx = 0;
            joueur.getTankPointGauche().setPointX(5);
        }
        if (joueur.getTankPointGauche().getPointX() >= 950) {
            joueur.vx = 0;
            joueur.getTankPointGauche().setPointX(945);
        }
        if (joueur.y < 0) {
            joueur.vy = 0;
            joueur.y = 0;
        }
        if (joueur.y > 990) {
            joueur.vy = 0;
            joueur.y = 980;
        }
        gestion_rotation_pente();
        repaint();
    }

    public void gestion_rotation_pente() {
        double dx;
        double dy;
        double dis;
        double gunX_offset;
        double gunY_offset;
        double gunXHalf;
        double gunYHalf;
        double positionX;
        double positionY;
        //Gestion des pentes
        joueur.getTankPointGauche().setPointX((int) joueur.getTankPointGauche().getPointX() + joueur.vx);
        joueur.getTankPointDroit().setPointX(joueur.getTankPointGauche().getPointX() + joueur.longueur_tank);
        double angle = this.getAngle();
        if (Y[(int) joueur.getTankPointDroit().getPointX() * reglage_nb_point] - Y[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point] > 0) {
            joueur.getTankPointGauche().setPointY(Y[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point] - offset_tank_terrain);
        } else if (Y[(int) joueur.getTankPointDroit().getPointX() * reglage_nb_point] - Y[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point] <= 0) {
            joueur.getTankPointGauche().setPointY(Y[(int) joueur.getTankPointDroit().getPointX() * reglage_nb_point] - offset_tank_terrain);
        }
        //Rotation du tank et du canon lors des pentes
        Point tankPosCenter = new Point(joueur.getTankPointGauche().getPointX() + 17.5, joueur.getTankPointGauche().getPointY() + 50);
        Point gunPosCenter = new Point(joueur.getTankPointGauche().getPointX() + 19, joueur.getTankPointGauche().getPointY() + 18);
        dx = tankPosCenter.getPointX() - gunPosCenter.getPointX();
        dy = tankPosCenter.getPointY() - gunPosCenter.getPointY();
        dis = Math.sqrt(dx * dx + dy * dy);
        gunX_offset = dis * Math.cos(Math.toRadians(angle + 100));
        gunY_offset = dis * Math.sin(Math.toRadians(angle + 100));
        gunXHalf = 50;
        gunYHalf = 50;
        //Suppression de la rotation dans les cas de grandes chutes
        if (angle > 25) { // Cas à optimiser en plaçant un nouveau point sur le tank
            positionX = (tankPosCenter.getPointX() - 0) - gunXHalf;
            positionY = (tankPosCenter.getPointY() - 32) - gunYHalf;
            joueur.setAngleTank(0);
        } else {
            positionX = (tankPosCenter.getPointX() - gunX_offset) - gunXHalf;
            positionY = (tankPosCenter.getPointY() - gunY_offset) - gunYHalf;
            joueur.setAngleTank(this.getAngle());
        }
        joueur.getCanon().setPointX(positionX);
        joueur.getCanon().setPointY(positionY);
    }
    
    //Récupère l'angle de rotation du tank
    public double getAngle() {
        double angle1 = Math.atan2(tab[(int) joueur.getTankPointDroit().getPointX() * reglage_nb_point].getPointY() - tab[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point].getPointY(),
                tab[(int) joueur.getTankPointDroit().getPointX() * reglage_nb_point].getPointX() - tab[(int) joueur.getTankPointGauche().getPointX() * reglage_nb_point].getPointX());
        double angle2 = Math.atan2(0, 0);
        return Math.toDegrees(angle1 - angle2);
    }

    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            joueur.vx = -5;
            joueur.vy = 0;
        }
        if (c == KeyEvent.VK_UP) {
            joueur.vx = 0;
            joueur.vy = -10;
        }
        if (c == KeyEvent.VK_RIGHT) {
            joueur.vx = 5;
            joueur.vy = 0;
        }
        if (c == KeyEvent.VK_DOWN) {
            joueur.vx = 0;
            joueur.vy = 10;
        }
        if (c == KeyEvent.VK_S) {
            joueur.setAngleCanon(joueur.getAngleCanon() + 5);

        }
        if (c == KeyEvent.VK_Z) {
            if (joueur.getAngleCanon() != -90) {
                joueur.setAngleCanon(joueur.getAngleCanon() - 5);
            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        joueur.vx = 0;
        joueur.vy = 0;
    }
}
