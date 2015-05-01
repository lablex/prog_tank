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
public class Tank {

    private static final long serialVersionUID = 1L;

    int x = 0, y = 0, vx = 1, vy = 1;
    Point tank_gauche;
    Point tank_droit;
    Point canon;
    Point tank_56; // Point à 5/6 (axe des X) du tank
    Point tank_113; // Point à 1/13 (axe des X) du tank 
    Point extremite_canon; // Point à 1/15 (axe des X) du tank 
    double angle_tank, angle_canon = 0;
    protected final int longueur_tank;

    public Tank() {
        tank_gauche = new Point(0, 0);
        tank_droit = new Point(0, 0);
        tank_56 = new Point(0, 0);
        tank_113 = new Point(0, 0);
        canon = new Point(0, 0);
        extremite_canon = new Point(0, 0);
        longueur_tank = 30;
    }

    public void setAngleCanon(double angle_canon) {
        this.angle_canon = angle_canon;
    }

    public double getAngleCanon() {
        return this.angle_canon;
    }

    public void setAngleTank(double angle_tank) {
        this.angle_tank = angle_tank;
    }

    public double getAngleTank() {
        return this.angle_tank;
    }

    public Point getTankPointGauche() {
        return this.tank_gauche;
    }

    public Point getTankPointDroit() {
        return this.tank_droit;
    }

    public Point getCentreCanon() {
        return this.canon;
    }

    public Point getTankPoint56() {
        return this.tank_56;
    }

    public Point getTankPoint113() {
        return this.tank_113;
    }

    public Point getTankExtremiteCanon() {
        return this.extremite_canon;
    }

}
