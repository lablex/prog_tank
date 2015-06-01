package jeux_tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class TriMissile extends Missile {

    private double hold;
    private double xHold;
    private double yHold;
    private int offset = 1;
    private boolean separation = false;
    private Point pNew = new Point(0, 0);
    private Point pHold = new Point(0, 0);
    private double angleMissile;
    private volatile boolean test;
    private boolean drawExp;

    public TriMissile(Tank tank, String name) {
        super(tank, name);
    }

    public Point setPosition() {
        if (running) {
            draw = true;
            drawExp = false;
            i++;
            t = i * 0.005;
            xNew = X0 + V0 * Math.cos(theta) * t;
            pNew.setPointX(xNew);
            pointPosition.setPointX(xNew);
            yNew = +Y0 - 0.5 * g * Math.pow(t, 2) + V0 * Math.sin(theta) * t;
            pNew.setPointY(yNew);
            pointPosition.setPointY(yNew);
            angleMissile = -Point.angleBis(pHold, pNew);
            xHold = xNew;
            yHold = yNew;
            pHold.setPointX(xHold);
            pHold.setPointY(yHold);
        }

        if (hold >= getPositionY()) {
            separation = true;
            offset = (int) (i * 0.013);

        }
        hold = getPositionY();
        test = -Terrain.getTerrainY((int) getPositionX()) > getPositionY();
        if (-Terrain.getTerrainY((int) getPositionX()) > getPositionY() || getPositionX() > 1000) {
            Fenetre.setEnter(false);
           // Missile.setV0(75);
            draw = false;
            drawExp = true;
            offset = 0;
            separation = false;

            pointImpact.setPointX((int) Terrain.getTerrainX((int) getPositionX()));
            pointImpact.setPointY((int) (Terrain.getTerrainY((int) getPositionX())));
            if (destruction == false) {
                Terrain.destructionTerrain(100, pointImpact, (int) getPositionX());
                destruction = true;
            }
        }

        return pointPosition;
    }

    public void drawTrajectoir(Graphics g, Point point, String name) {
        if (draw) {
            g.setColor(Color.BLACK);
            g.fillOval((int) point.getPointX(), -(int) point.getPointY(), 20, 20);
            if (separation) {
                g.setColor(Color.BLACK);
                g.fillOval((int) (point.getPointX() - offset * Math.cos(1.5 * angleMissile - (3.14159265359 / 2))), -(int) (point.getPointY() + offset * Math.sin(angleMissile / 4)), 20, 20);
                g.fillOval((int) (point.getPointX() + offset * Math.cos(1.5 * angleMissile - (3.14159265359 / 2))), -(int) (point.getPointY() + offset * Math.sin(angleMissile / 4)), 20, 20);

            }
        }
    }

    public void drawExp(Graphics g, ImageObserver a) {

        if (drawExp) {
            Terrain.explosionAffiche(Terrain.getTerrainX((int) getPositionX()) - 50, Terrain.getTerrainY((int) getPositionX()) - 50, g, a);
        }
    }

    public Point getPointImpact() {
        return pointImpact;
    }

    public void setConditionImpacte() {
        this.test = false;
    }

    public boolean getConditionImpacte() {

        return test;
    }

}
