package jeux_tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Obus extends Missile {

    
    private volatile boolean test;
    protected volatile boolean drawExp;

    public Obus(Tank tank, String name) {
        super(tank, name);
    }

    public Point setPosition() {
        if (running) {
            //destruction=false;
            detection = false;
            detection2 = false;
            drawExp = false;
            draw = true;
            i++;
            t = i * 0.005;
            xNew = X0 + V0 * Math.cos(theta) * t;
            pointPosition.setPointX(xNew);
            yNew = +Y0 - 0.5 * g * Math.pow(t, 2) + V0 * Math.sin(theta) * t;
            pointPosition.setPointY(yNew);
        }
        test = -Terrain.getTerrainY((int) getPositionX()) > getPositionY();
        if (-Terrain.getTerrainY((int) getPositionX()) > getPositionY() || getPositionX() > 1000) {
            Fenetre.setEnter(false);
            draw = false;     
            drawExp = true;
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
            g.fillOval((int) point.getPointX(), -(int) point.getPointY(), 15, 15);

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

    public void drawExp(Graphics g, ImageObserver a, int with, int eight) {

        if (drawExp) {
            Terrain.explosionAffiche(Terrain.getTerrainX((int) getPositionX()) -with+20, Terrain.getTerrainY((int) getPositionX()) -eight+20, g, a,with, eight);
        }
    }

    public boolean getDrawExp() {
        return drawExp;
    }

    public void setDrawExp(boolean drawExp) {
        this.drawExp = drawExp;
    }

}
