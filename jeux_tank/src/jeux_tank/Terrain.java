package jeux_tank;


public class Terrain {
	Point[] tab;
	int nbPoint;
	
	
	public Terrain(){
		int n = 20;
		double hauteur= 700.0;
		nbPoint = (int) (Math.pow((int) 2,(int) n)+1);
		double largeur= 1000;
		tab = Point.tabPoint(nbPoint);
		tab[0].setPointY(Math.random()*hauteur);
		tab[nbPoint-1].setPointY(Math.random()*hauteur);
		for(int i=1; i<=n; i++){
			int interval = ((int) (Math.pow((int) 2,(int) (n-i))+1))-1;
			/*System.out.println("interval:"+interval);
			System.out.println("indice 'i':"+i);*/
			int nb_interval= interval;
			for(int p=1; p<=((int) Math.pow(2, i-1)); p++){
				//System.out.println("indice 'p':"+p);
				if(p==1){
					tab[nb_interval].setPointY((Math.random()*(tab[nb_interval+interval].distanceY(tab[nb_interval-interval])))+tab[nb_interval+interval].infY(tab[nb_interval-interval]));
					/*double e = tab[nb_interval+interval].distanceY(tab[nb_interval-interval]);
					double f = tab[nb_interval+interval].infY(tab[nb_interval-interval]);
					double g = tab[nb_interval+interval].getPointY();
					double h = tab[nb_interval-interval].getPointY();;
					System.out.println("distanceY:"+e);
					System.out.println("nb_interval+interval:"+g);
					System.out.println("nb_interval-interval:"+h);
					System.out.println("supYl:"+f);*/
				}else{
					nb_interval = nb_interval + interval*2;
					tab[nb_interval].setPointY((Math.random()*tab[nb_interval+interval].distanceY(tab[nb_interval-interval]))+tab[nb_interval+interval].infY(tab[nb_interval-interval]));
					/*double e = tab[nb_interval+interval].distanceY(tab[nb_interval-interval]);
					double f = tab[nb_interval+interval].infY(tab[nb_interval-interval]);
					double g = tab[nb_interval+interval].getPointY();
					double h = tab[nb_interval-interval].getPointY();;
					System.out.println("distanceY:"+e);
					System.out.println("nb_interval+interval:"+g);
					System.out.println("nb_interval-interval:"+h);
					System.out.println("supYl:"+f);*/
				}
			}
			//System.out.println();
		}
		double ecartX = largeur/(nbPoint);
		double increment = ecartX;
		for(int j=0; j<nbPoint; j++){ 
			tab[j].setPointX(increment);
			increment = increment + ecartX;
			
		}
	}
	
	public Point[] getTab(){
		return this.tab;
	}
	
	public int getNbPoint(){
		return this.nbPoint;
	}
}
