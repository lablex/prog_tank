package jeux_tank;

import java.awt.Graphics;

public class Obus extends Missile{
	
	public Obus(Tank tank, String name) {
		super(tank, name);
	}
	
	public Point setPosition() {
		if(running){	
			draw=true;
			i++;
			t=i * 0.005;
			xNew = X0 + V0 * Math.cos(theta) * t;
			pointPosition.setPointX(xNew);
			yNew = +Y0 - 0.5 * g * Math.pow(t, 2) + V0 * Math.sin(theta) * t;
			pointPosition.setPointY(yNew);
		}
		if (-Terrain.getTerrainY((int)getPositionX())>getPositionY()) {
			running = false;
			i = 0;
			draw=false;
		}

		return pointPosition;
	}
	
	public void drawTrajectoir(Graphics g, Point point, String name) {
		if(draw){
			g.drawOval((int) point.getPointX(), -(int) point.getPointY(), 25, 25);
		}
    }
}
