package jeux_tank;

import java.awt.Color;
import java.awt.Graphics;

public class VerticalMissile extends Missile{

	private double hold= getPositionY()-5;
	private boolean stopp=false;
	
	
	public VerticalMissile(Tank tank, String name) {
		super(tank, name);
	}
	
	public Point setPosition() {
		if(running){
			draw=true;
			if(!stopp){
				i++;
				t=i * 0.005;
				xNew = X0 + V0 * Math.cos(theta) * t;
				pointPosition.setPointX(xNew);
				yNew = +Y0 - 0.5 * g * Math.pow(t, 2) + V0 * Math.sin(theta) * t;
				pointPosition.setPointY(yNew);
			}else{
				pointPosition.setPointX(xNew);
				yNew=yNew-i * 0.0009;
				pointPosition.setPointY(yNew);
			}
		}
		if(hold>=getPositionY()){
			stopp=true;		
		}
		hold = getPositionY();
		if (-Terrain.getTerrainY((int)getPositionX())>getPositionY()) {
			draw=false;
			running = false;
			i = 0;
			stopp=false;
			Point point=new Point(0,0);
			point.setPointX((int)Terrain.getTerrainX((int)getPositionX()));
			point.setPointY((int)(Terrain.getTerrainY((int)getPositionX())));
			Terrain.destructionTerrain(150, point, (int)getPositionX());
		}

		return pointPosition;
	}
	
	public void drawTrajectoir(Graphics g, Point point, String name) {
		if(draw){
			g.setColor(Color.BLACK);
			g.fillOval((int) point.getPointX(), -(int) point.getPointY(), 20, 20);
		}

    }
}

