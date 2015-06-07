package jeux_tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class VerticalMissile extends Missile {

    private double hold = getPositionY() - 5000;
    private double xHold;
    private double yHold;
    protected volatile boolean drawExp;
    private volatile boolean test;

    public VerticalMissile(Tank tank, String name) {
        super(tank, name);
    }

    public Point setPosition() {
        if (running) {
            drawExp = false;
            draw = true;
            if (!stopp) {
                i++;
                t = i * 0.005;
                xHold = X0 + V0 * Math.cos(theta) * t;
                pointPosition.setPointX(xHold);
                yHold = Y0 - 0.5 * g * Math.pow(t, 2) + V0 * Math.sin(theta) * t;
                pointPosition.setPointY(yHold);
            } else {
                pointPosition.setPointX(xHold);
                yHold = yHold - i * 0.0009;
                pointPosition.setPointY(yHold);
            }
        }
        if (hold >= getPositionY() && i>10) {
            stopp = true;
        }
        hold = getPositionY();
        System.out.println(hold);
        if (-Terrain.getTerrainY((int) getPositionX()) > getPositionY() || getPositionX() > 1000) {
            Fenetre.setEnter(false);
            //Missile.setV0(75);
            drawExp = true;
            draw = false;
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
        }

    }

    public void drawExp(Graphics g, ImageObserver a, int with, int eight) {

        if (drawExp) {
        	Terrain.explosionAffiche(Terrain.getTerrainX((int) getPositionX()) -with+20, Terrain.getTerrainY((int) getPositionX()) -eight+20, g, a,with, eight);
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
    
    public boolean getDrawExp() {
        return drawExp;
    }

    public void setDrawExp(boolean drawExp) {
        this.drawExp = drawExp;
    }
}
