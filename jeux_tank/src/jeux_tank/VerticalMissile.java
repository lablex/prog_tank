package jeux_tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class VerticalMissile extends Missile{

	private double hold= getPositionY()-5;
	private boolean stopp=false;
	private Point pointImpact=new Point(0,0);
	private volatile boolean test;
	private boolean drawExp;
	
	
	public VerticalMissile(Tank tank, String name) {
		super(tank, name);
	}
	
	public Point setPosition() {
		if(running){
			drawExp = false;
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
		test =-Terrain.getTerrainY((int)getPositionX())>getPositionY();
		if (-Terrain.getTerrainY((int)getPositionX())>getPositionY()) {
			draw=false;
			drawExp = true;
			running = false;
			i = 0;
			stopp=false;
			pointImpact.setPointX((int)Terrain.getTerrainX((int)getPositionX()));
			pointImpact.setPointY((int)(Terrain.getTerrainY((int)getPositionX())));
			Terrain.destructionTerrain(100, pointImpact, (int)getPositionX());
		}

		return pointPosition;
	}
	
	public void drawTrajectoir(Graphics g, Point point, String name) {
		if(draw){
			g.setColor(Color.BLACK);
			g.fillOval((int) point.getPointX(), -(int) point.getPointY(), 20, 20);
		}

    }
	
public void drawExp(Graphics g, ImageObserver a){
		
		if (drawExp) {
			Terrain.explosionAffiche(Terrain.getTerrainX((int)getPositionX())-50, Terrain.getTerrainY((int)getPositionX())-50, g, a);
		}
	}

	public Point getPointImpact(){
		return pointImpact;
	}
	
	public void setConditionImpacte(){
		this.test=false;
	}
	
	public boolean getConditionImpacte(){
		
		
		return test;
	}
}

