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
        X = new int[terrain.getNbPoint()];
        Y = new int[terrain.getNbPoint()];
        tab = terrain.getTab();
        for (int i = 0; i < terrain.getNbPoint(); i++) {
            X[i] = (int) tab[i].getPointX();
            Y[i] = (int) tab[i].getPointY();
        }
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (joueur.tank_gauche.getPointX() <= 0) {
            joueur.vx = 0;
            joueur.tank_gauche.setPointX(5);
        }
        if (joueur.tank_gauche.getPointX() >= 950) {
            joueur.vx = 0;
            joueur.tank_gauche.setPointX(945);
        }
        if (joueur.y < 0) {
            joueur.vy = 0;
            joueur.y = 0;
        }
        if (joueur.y > 700) {
            joueur.vy = 0;
            joueur.y = 700;
        }
        joueur.tank_gauche.setPointX((int) joueur.tank_gauche.getPointX() + joueur.vx);
        joueur.tank_droite.setPointX(joueur.tank_gauche.getPointX() + joueur.longueur_tank);

        //Gestion des pentes
        if (Y[(int) joueur.tank_droite.getPointX() * reglage_nb_point] - Y[(int) joueur.tank_gauche.getPointX() * reglage_nb_point] > 0) {
            joueur.tank_gauche.setPointY(Y[(int) joueur.tank_gauche.getPointX() * reglage_nb_point] - offset_tank_terrain);
        } else if (Y[(int) joueur.tank_droite.getPointX() * reglage_nb_point] - Y[(int) joueur.tank_gauche.getPointX() * reglage_nb_point] <= 0) {
            joueur.tank_gauche.setPointY(Y[(int) joueur.tank_droite.getPointX() * reglage_nb_point] - offset_tank_terrain);
        }
        //Rotation lors des pentes
        joueur.setAngleTank(this.getAngle());
        joueur.canon.setPointX(joueur.tank_gauche.getPointX() - 25 + joueur.angle_tank * 0.4); //coefficients déterminés expérimentalement
        joueur.canon.setPointY(joueur.tank_gauche.getPointY() - 30 - joueur.angle_tank * 0.15); //coefficients déterminés expérimentalement
        repaint();
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
            if (joueur.getAngleCanon() != 30) {
                joueur.setAngleCanon(joueur.getAngleCanon() + 5);
            }

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

    public double getAngle() {
        double angle1 = Math.atan2(tab[(int) joueur.tank_droite.getPointX() * reglage_nb_point].getPointY() - tab[(int) joueur.tank_gauche.getPointX() * reglage_nb_point].getPointY(),
                tab[(int) joueur.tank_droite.getPointX() * reglage_nb_point].getPointX() - tab[(int) joueur.tank_gauche.getPointX() * reglage_nb_point].getPointX());
        double angle2 = Math.atan2(0, 0);
        return Math.toDegrees(angle1 - angle2);
    }

}
