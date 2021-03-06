package jeux_tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import javax.swing.JFrame;
import javax.swing.Timer;

abstract class Missile extends JFrame {

    protected volatile double X0;
    protected volatile double Y0;
    protected static volatile double V0;
    protected Point pointImpact = new Point(0, 0);
    protected volatile double xNew = 0;
    protected volatile double yNew = 0;
    protected double theta;
    protected boolean stopp = false;
    protected double g = 10.0;
    protected volatile boolean bool = true;
    protected volatile static boolean running;
    protected Point pointPosition;
    protected String name;
    protected Tank tank;
    protected final int PLUS = 50 * (106 / 2) / 180;
    protected final int PLUS2 = 50 * 106 / 180;
    protected final int PLUS2s = 50 * 206 / 180;
    protected final int PLUS3 = 100 * 150 / 500;
    protected static int i = 1;
    protected double t = 0;
    protected boolean draw = false;
    protected boolean drawExp;
    public static volatile boolean stop = false;
    public static boolean destruction = false;
   

    public static volatile boolean detection;
    public static volatile boolean detection2 = true;

    public Missile(Tank tank, String name) {
        V0 = 75;
        this.tank = tank;
        this.name = name;
        pointPosition = new Point(0, 0);
    }

    abstract Point setPosition();

    abstract void drawTrajectoir(Graphics g, Point point, String name);

    public void setMissile(double V) {
        pointPosition = new Point(0, 0);
        V0 = V;
        this.X0 = Terrain.getTerrainX(tank.getXInt()) + (int) (PLUS2 * Math.sin(tank.getAngleTank())) - 10;
        this.Y0 = -Terrain.getTerrainY(tank.getXInt()) + (int) (PLUS2 * Math.cos(tank.getAngleTank())) + 10;
        this.theta = -tank.getAngleCanon() - tank.getAngleTank();
        yNew = 0;
        xNew = 0;
    }

    public abstract void drawExp(Graphics g, ImageObserver a, int with, int eight);

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Point getPosition() {
        return pointPosition;
    }

    public double getPositionX() {
        return pointPosition.getPointX();
    }

    abstract boolean getConditionImpacte();

    abstract void setConditionImpacte();

    abstract Point getPointImpact();

    public double getPositionY() {
        return pointPosition.getPointY();
    }

    public void setPositionX(double positionX) {
        this.pointPosition.setPointX(positionX);
    }

    public void setPositionY(double positionY) {
        this.pointPosition.setPointY(positionY);
    }

    public static void setV0(double V1) {
        V0 = V1;
    }

    public static double getV0() {
        return V0;
    }

    public static boolean getStop() {
        return stop;
    }

    public static void setStop(boolean stop1) {
        stop = stop1;
    }

    public int getI() {
        return i;
    }
    
    public abstract boolean getDrawExp();

    public abstract void setDrawExp(boolean drawExp);

    public void verification() {
        if (detection) {
            Animation.choix_missile2 = false;
        } else if (!detection) {
            Animation.choix_missile2 = true;
        }

        if (-Terrain.getTerrainY((int) getPositionX()) > getPositionY() || getPositionX() > 1000) {

            running = false;
            stop = true;
            stopp = false;
            //i = 0;

            if (Animation.getAltern() == true) {
                detection = true;
                detection2 = false;
            } else if (Animation.getAltern() == false) {
                detection = false;
                detection2 = true;
            }

            if (destruction) {
                Missile.setV0(75);
                i = 0;
                draw = false;
                destruction = false;
            }

            Fenetre.missile = true;

        }
    }

}
