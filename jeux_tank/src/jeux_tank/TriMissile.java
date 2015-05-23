package jeux_tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class TriMissile extends Missile{
	private double hold;
	private double xHold;
	private double yHold;
	private int offset = 1;
	private boolean separation= false;
	private Point pNew= new Point(0, 0);
	private Point pHold= new Point(0, 0);
	private double angleMissile;
	
	public TriMissile(Tank tank, String name) {
		super(tank, name);
	}
	
	public Point setPosition() {
		if(running){
				draw=true;
				i++;
				t=i * 0.005;
				xNew = X0 + V0 * Math.cos(theta) * t;
				pNew.setPointX(xNew);
				pointPosition.setPointX(xNew);
				yNew = +Y0 - 0.5 * g * Math.pow(t, 2) + V0 * Math.sin(theta) * t;
				pNew.setPointY(yNew);
				pointPosition.setPointY(yNew);
				angleMissile=-Point.angleBis(pHold,pNew);
				xHold=xNew;
				yHold=yNew;
				pHold.setPointX(xHold);
				pHold.setPointY(yHold);
			}
			
			if(hold>=getPositionY()){
				separation=true;
				
				
					offset=(int) (i * 0.013);
				
			}
			hold = getPositionY();
			if (-Terrain.getTerrainY((int)getPositionX())>getPositionY()) {
				running = false;
				i = 0;
				offset=0;
				separation=false;
				draw=false;
				Point point=new Point(0,0);
				point.setPointX((int)Terrain.getTerrainX((int)getPositionX()));
				point.setPointY((int)(Terrain.getTerrainY((int)getPositionX())));
				Terrain.destructionTerrain(100, point, (int)getPositionX());
			}

		return pointPosition;
	}
	
	public void drawTrajectoir(Graphics g, Point point, String name) {
		if(draw){
			g.setColor(Color.BLACK);
			g.fillOval((int) point.getPointX(), -(int) point.getPointY(), 20, 20);
	        if(separation){
	        	g.setColor(Color.BLACK);
		       g.fillOval((int)(point.getPointX()-offset*Math.cos(1.5*angleMissile-(3.14159265359/2))), -(int) (point.getPointY()+offset*Math.sin(angleMissile/4)), 20, 20);
		       g.fillOval((int)(point.getPointX()+offset*Math.cos(1.5*angleMissile-(3.14159265359/2))), -(int) (point.getPointY()+offset*Math.sin(angleMissile/4)), 20, 20);
		        
	        }
		}
    }
	public void drawExp(Graphics g, ImageObserver a){
		if (-Terrain.getTerrainY((int)getPositionX())>getPositionY()) {
			Terrain.explosionAffiche(Terrain.getTerrainX((int)getPositionX()), Terrain.getTerrainY((int)getPositionX()), g, a);
		}
	}
}