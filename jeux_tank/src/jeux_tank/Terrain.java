package jeux_tank;


public class Terrain {
	private Point[] tab;
	private int n = 20;
	private int nbPoint=(int) (Math.pow((int) 2,(int) n)+1);
	private double hauteur= 700.0;
	private double largeur= 1000;
	private double ecartX = largeur/(nbPoint);
	private double increment = ecartX;
	
	public Terrain()
	{
		tab = Point.tabPoint(nbPoint);
		tab[0].setPointY(1*hauteur);
		tab[nbPoint-1].setPointY(0*hauteur);
		
		for(int i=1; i<=n; i++)
		{
			int interval = ((int) (Math.pow((int) 2,(int) (n-i))+1))-1;
			int nb_interval= interval;
			
			for(int p=1; p<=((int) Math.pow(2, i-1)); p++)
			{
				if(p==1)
				{
					tab[nb_interval].setPointY((Math.random()*(tab[nb_interval+interval].distanceY(tab[nb_interval-interval])))+tab[nb_interval+interval].infY(tab[nb_interval-interval]));
				}
				else
				{
					nb_interval = nb_interval + interval*2;
					tab[nb_interval].setPointY((Math.random()*tab[nb_interval+interval].distanceY(tab[nb_interval-interval]))+tab[nb_interval+interval].infY(tab[nb_interval-interval]));
				}
			}
		}
		
		for(int j=0; j<nbPoint; j++)
		{ 
			tab[j].setPointX(increment);
			increment = increment + ecartX;
		}
	}
	
	public void destructionTerrain(int[] tabIndice, int R, Point impact, Point[] tab)
	{
		double x;
		double y;
		double theta;
		double increTheta;
		if(tab[tabIndice.length-1].penteTarrain(impact)){
			theta=impact.angle(tab[tabIndice.length-1]);
			increTheta=2;
			//il faut calculer l'angle entre les point extremaux du groupe de point pour calculer le pas de theta
			for(int i=0; i<tabIndice.length; i++){
				if (i>0 && i<tabIndice.length-1){
					x=impact.getPointX()+R*Math.cos(theta-i*increTheta);
					y=impact.getPointY()+R*Math.sin(theta-i*increTheta);
					tab[i].setPointX(x);
					tab[i].setPointY(y);
				}
			}
		}
		else
		{
			
		}
	}
	
	public Point[] getTab()
	{
		return this.tab;
	}
	
	public int getNbPoint()
	{
		return this.nbPoint;
	}
}
