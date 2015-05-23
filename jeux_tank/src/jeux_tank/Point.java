package jeux_tank;

import java.util.ArrayList;

public class Point {

    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point a) {
        double dist = Math.sqrt(Math.pow(a.getPointX() - this.getPointX(), 2) + (Math.pow(a.getPointY() - this.getPointY(), 2)));
        return dist;
    }

    //Distance entre deux points
    public static double distance(Point point1, Point point2) {
        double dx = point1.getPointX() - point2.getPointX();
        double dy = point1.getPointY() - point2.getPointY();
        double dis = Math.sqrt(dx * dx + dy * dy);
        return dis;
    }
    
    public static double triAngle(Point a, Point b, Point c){
    	double angle;
    	angle=Math.acos((Math.pow(distance(a,c), 2)-Math.pow(distance(c,b), 2)-Math.pow(distance(a,b), 2))/(-2*distance(c,b)*distance(a,b)));
    	return angle;
    }

    public double distanceX(Point a) {
        double dist = Math.abs(a.getPointX() - getPointX());
        return dist;
    }
    
    public static double distanceX(Point a, Point b) {
        double dist = a.getPointX() - b.getPointX();
        return dist;
    }

    public double distanceY(Point a) {
        double dist = Math.abs(a.getPointY() - getPointY());
        return dist;
    }
    
    public static double distanceY(Point a, Point b) {
        double dist = a.getPointY() - b.getPointY();
        return dist;
    }

    public double infY(Point a) {
        if (a.getPointY() <= this.getPointY()) {
            return a.getPointY();
        } else {
            return this.getPointY();
        }
    }

    public Point pointMillieu(Point a, Point b) {
        Point c = new Point(0, 0);
        c.setPointX((a.getPointX() - b.getPointX()) / 2);
        c.setPointY((a.getPointY() - b.getPointY()) / 2);
        return c;
    }

    public static Point[] tabPoint(int n) {
        Point[] c = new Point[n];
        for (int i = 0; i < n; i++) {
            c[i] = new Point(0, 0);
        }
        return c;
    }

    public double angle(Point a) {
        double theta = 0;
        theta = Math.atan((Math.abs(a.getPointX() - this.getPointX())) / (Math.abs(a.getPointY() - this.getPointY())));
        return theta;
    }
    
    public static double angleBis(Point a, Point b){
		Point oxi = new Point(0,0);
    	oxi.setPointX(b.getPointX());
    	oxi.setPointY(a.getPointY());
    	double dist1 = -distanceY(oxi, b);
    	double dist2 = distanceX(a, oxi);
    	double div = dist1/dist2;
		return Math.atan(div);
    	
    }
    
        //Angle entre deux points
    public static double getAngle(double angle1) {
        double angle2 = Math.atan2(0, 0);
        return Math.toDegrees(angle1 - angle2);
    }

    public boolean penteTarrain(Point impacte) {
        double pente = 0;
        pente = (impacte.getPointY() - this.getPointY()) / (impacte.getPointX() - this.getPointX());

        if (pente > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int indiceTabPoint(double y, double x, Point[] tab, int tailleTab) {
        for (int i = 0; i < tailleTab; i++) {
            if (tab[i].getPointY() == y && tab[i].getPointX() == x) {
                return i;
            }
        }

        return 0;
    }
    public static ArrayList<Point> portionDestruction(Point[] tabPointTerrain, Point pointImpact, int R){
    	double distance;
    	ArrayList<Point> tabPoint = new ArrayList<Point>();
    	for (int i = 0; i < tabPointTerrain.length; i++) {
    		distance = pointImpact.distance(tabPointTerrain[i]);
    		if (distance <= R) {
                tabPoint.add(tabPointTerrain[i]);
            }
    	}
    	return tabPoint;
    	
    }
    public static int[] groupPointIndice(int indice, int R, Point[] tab, int tailleTab) {
        double rayon;
        ArrayList<Point> tabPoint = new ArrayList<Point>();
        for (int i = 0; i < tailleTab; i++) {
            rayon = tab[indice].distance(tab[i]);
            if (rayon <= R) {
                tabPoint.add(tab[i]);
            }
        }
        int[] tabPointIndiceOut = new int[tabPoint.size()];
        for (int i = 0; i < tabPoint.size(); i++) {
            tabPointIndiceOut[i] = i;
        }
        return tabPointIndiceOut;

    }

    public void setPointX(double x) {
        this.x = x;
    }

    public double getPointX() {
        return this.x;
    }

	public void setPointY(double y) {
        this.y = y;
    }

    public double getPointY() {
        return this.y;
    }
}
