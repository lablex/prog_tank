package jeux_tank;


public class Terrain {
	private Point[] tab;
	private int nbPoint=1000000;
	//période des sinus/cosinus
	private double T = 4*3.14;
	//pulsation des sinus/cosinus
	private double w = (2*3.14)/T;
	//distance entre deux abscisses
	private double ecartX = T/1000000;
	//varible qui va permettre d'incrémenter la variable incrementy. le nom de cette variable est trompeur car ce n'est pas y que l'on incrémente
	//mais la valeur de x qui se trouve dans les sinus/cossinus qui eux permettent de donner la valeur de y
	private double incrementy0 = ecartX;
	//coeficient de fourier aléatoire
	private double coef1=150*Math.random();
	private double coef0=150*Math.random();
	private double coef2=150*Math.random();
	private double coef3=150*Math.random();
	
	public Terrain()
	{
		tab = Point.tabPoint(nbPoint);
		
		//valeur de la corrdonné y a chaque nouveaux tour de boucle
		double incrementy = 0;
		
		//valeur de la corrdonné x a chaque nouveaux tour de boucle
		double incrementx=0;
		
		//valeur permétant d'incrémenter la vriable incrementx le calcule qui donne la valeur de incrementx0
		//est un produit en croit cela permet de dilater l'axe des x est ainsi agrandire la courbe sinn celle ci serait invisible
		double incrementx0=ecartX*1000/T;
		
		
		for(int i=1; i<999999; i++){
			
			//régla de la valeur x en incrémentant la variable incrementx de la valeur de la variable incrémentx0 à chaque nouveau tour de de boucle
			tab[i].setPointX(incrementx);
			//réglade la valeur y avec l'aide d'une série de fourier cette série est alléatoir car elle possède des coef alléatoire
			tab[i].setPointY(coef0*Math.sin(1*w*incrementy)+coef1*Math.sin(2*w*incrementy)+60*Math.sin(3*w*incrementy)+15*Math.sin(4*w*incrementy)+10*Math.sin(5*w*incrementy)+5*Math.sin(6*w*incrementy)+
							 coef2*Math.cos(1*w*incrementy)+coef3*Math.cos(2*w*incrementy)+20*Math.cos(3*w*incrementy)+5*Math.cos(4*w*incrementy)+8*Math.cos(5*w*incrementy)+5*Math.cos(6*w*incrementy));
			//ces deux if permettent de placer le premier point et le dernier point à des ordonné beaucoup plus basses mais à la mêmme abscisse 
			//le dexieme et avant dernier point qui comment la courbe ce placement sert a former un poligone qui à 3 coté en forme de droite et 
			//un présentant notre courbe. ce poligonne est important car il nous permettra par la suite de colloré le terrain
			if(i==1){
				tab[0].setPointX(0);
				tab[0].setPointY(tab[1].getPointY()+200);
			}
			if(i==999998){
				tab[999999].setPointX(incrementx);
				tab[999999].setPointY(tab[999998].getPointY()+200);
			}
			
			//incrémentation des variable
			incrementy=incrementy+incrementy0;
			incrementx=incrementx+incrementx0;
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
