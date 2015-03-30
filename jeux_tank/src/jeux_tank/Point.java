package jeux_tank;

import java.util.ArrayList;

public class Point
{
	double x;
	double y;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double distance(Point a)
	{
		double dist = Math.sqrt(Math.pow(a.getPointX()-getPointX(), 2)+(Math.pow(a.getPointY()-getPointY(), 2)));
		return dist;
	}
	
	public double distanceX(Point a)
	{
		double dist =Math.abs(a.getPointX()-getPointX());
		return dist;
	}
	
	public double distanceY(Point a)
	{
		double dist =Math.abs(a.getPointY()-getPointY());
		return dist;
	}
	
	public double infY(Point a)
	{
		if(a.getPointY() <= this.getPointY())
		{
			return a.getPointY();
		}
		else
		{
			return this.getPointY();
		}
	}
	
	public static Point pointMillieu(Point a, Point b)
	{
		Point c = new Point(0, 0);
		c.setPointX((a.getPointX()-b.getPointX())/2); 
		c.setPointY((a.getPointY()-b.getPointY())/2); 
		return c;
	}
	
	public static Point[] tabPoint(int n)
	{
		Point[] c = new Point[n];
		for(int i=0; i<n; i++)
		{
			c[i]= new Point(0, 0);
		}
		return c;
	}
	
	public double angle(Point a)
	{
		double theta=0;
		theta=Math.acos((Math.abs(a.getPointX()-this.getPointX()))/(this.distance(a)));
		return theta;
	}
	
	public boolean penteTarrain(Point impacte)
	{
		double pente=0;
		pente=(impacte.getPointY()-this.getPointY())/(impacte.getPointX()-this.getPointX());
		
		if(pente > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static int indiceTabPoint(double y, double x, Point[] tab, int tailleTab)
	{
		for(int i=0; i<tailleTab; i++){
			if(tab[i].getPointY()==y && tab[i].getPointX()==x){
				return i;
			}
		}
		
		return 0;
	}
	
	public static int[] groupPointIndice(int indice, int R, Point[] tab, int tailleTab)
	{	
		double rayon;
		ArrayList<Point> tabPoint = new  ArrayList<Point> ();
		for(int i=0; i<tailleTab; i++){
			rayon=tab[indice].distance(tab[i]);
			if(rayon<=R){
				tabPoint.add(tab[i]);
			}
		}
		int[] tabPointIndiceOut = new int[tabPoint.size()];
		for(int i=0; i<tabPoint.size(); i++){
			tabPointIndiceOut[i] = i;
		}
		return tabPointIndiceOut;

	}
	
	public void setPointX(double x)
	{
		this.x= x;
	}
	public double getPointX()
	{
		return this.x;
	}
	public void setPointY(double y)
	{
		this.y= y;
	}
	public double getPointY()
	{
		return this.y;
	}
}