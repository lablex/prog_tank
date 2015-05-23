/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import javax.swing.JPanel;


public class Animation  extends Thread {
	private volatile boolean avance= false;
	private volatile boolean tir=false;
	private volatile static boolean altern= false;
	private JPanel pan;
	private Tank tank1;
	private Tank tank2;
	private volatile boolean stop=false;
	private static int count;
	private Missile[] tabMissile1 =new Missile[3];
	private Missile[] tabMissile2 =new Missile[3];

	public Animation(Missile[] tabMissile1, Missile[] tabMissile2,Tank tank1, Tank tank2, JPanel pan) {
		
		for(int i=0; i<tabMissile1.length; i++){
			this.tabMissile1[i] = tabMissile1[i];
			this.tabMissile2[i] = tabMissile2[i];
		}
        
		this.tank1 = tank1;
		this.tank2 = tank2;
		this.pan=pan;
		
		for(int i=0; i<2; i++){
			this.tank1.setPointsTank();
			this.tank1.setPositionTankX();
			this.tank1.setPositionTankY();
			this.tank1.setPositionCanonX();
			this.tank1.setPositionCanonY();
			this.tank1.setAngleTank();
			
			this.tank2.setPointsTank();
			this.tank2.setPositionTankX();
			this.tank2.setPositionTankY();
			this.tank2.setPositionCanonX();
			this.tank2.setPositionCanonY();
			this.tank2.setAngleTank();
		}
	}

	public void run() {

		while (true) {
				try {
					
					count++;
					if (count < 10000) {
	                    if (count == 9999) {
	                        avance = false;
	                        tir = false;
	                        Fenetre.setAvance(true);
	                        Fenetre.setTir(true);
	                        Fenetre.setSelectMissile();
	                    }
	                } else {
	                    if (altern) {
	                        altern = false;
	                    } else {
	                        altern = true;
	                    }
	                    count = 0;
	                    avance = false;
	                    tir = false;
	                    Fenetre.setAvance(true);
	                    Fenetre.setTir(true);
	                    Fenetre.setSelectMissile();
	                }
					
					
					if(altern){
						if(tir && !avance && !stop){
							if(Fenetre.getEnter()){
								tabMissile1[Fenetre.getSelectMissile()].setMissile(Missile.getV0());
							}
							tabMissile1[Fenetre.getSelectMissile()].setPosition();
							tank1.setNbMissile();
					        
							Fenetre.setEnter(false);
							if(!tabMissile1[Fenetre.getSelectMissile()].getRunning()){
								tabMissile1[Fenetre.getSelectMissile()].setRunning(true);
								stop = true;
							}
							
							this.tank1.setPointsTank();
							this.tank1.setPositionTankX();
							this.tank1.setPositionTankY();
							this.tank1.setPositionCanonX();
							this.tank1.setPositionCanonY();
							this.tank1.setAngleTank();
							
							this.tank2.setPointsTank();
							this.tank2.setPositionTankX();
							this.tank2.setPositionTankY();
							this.tank2.setPositionCanonX();
							this.tank2.setPositionCanonY();
							this.tank2.setAngleTank();
						}
						if(!tir && avance){
							tank1.setPointsTank();
							tank1.setPositionTankX();
							tank1.setPositionTankY();
							tank1.setPositionCanonX();
							tank1.setPositionCanonY();
							tank1.setAngleTank();
						}
						
					}else{
						if(tir && !avance && !stop){
							if(Fenetre.getEnter()){
								tabMissile2[Fenetre.getSelectMissile()].setMissile(Missile.getV0());
							}
							tabMissile2[Fenetre.getSelectMissile()].setPosition();
							tank2.setNbMissile();
							Fenetre.setEnter(false);
							if(!tabMissile2[Fenetre.getSelectMissile()].getRunning()){
								tabMissile2[Fenetre.getSelectMissile()].setRunning(true);
								stop = true;
							}
						}if(!tir && avance){
							tank2.setPointsTank();
							tank2.setPositionTankX();
							tank2.setPositionTankY();
							tank2.setPositionCanonX();
							tank2.setPositionCanonY();
							tank2.setAngleTank();
						}
						
						this.tank1.setPointsTank();
						this.tank1.setPositionTankX();
						this.tank1.setPositionTankY();
						this.tank1.setPositionCanonX();
						this.tank1.setPositionCanonY();
						this.tank1.setAngleTank();
						
						this.tank2.setPointsTank();
						this.tank2.setPositionTankX();
						this.tank2.setPositionTankY();
						this.tank2.setPositionCanonX();
						this.tank2.setPositionCanonY();
						this.tank2.setAngleTank();
					}
					
					pan.repaint();
					Thread.sleep(1);
					
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break; 

				}
		}

	}
	
	public boolean getStop(){
		return stop;
	}
	
	public void setStop(boolean stop){
		this.stop = stop;
	}
	
	public static boolean getAltern(){
		return altern;
	}
	
	public boolean getAvance(){
		return avance;
	}
	
	public boolean getTir(){
		return tir;
	}
	
	public void setAvance(boolean avance){
		this.avance=avance;
	}
	
	public void setTir(boolean tir){
		this.tir= tir;
	}

    public static String getTime() {
        String time = String.valueOf(Math.round(count / 1000 * 1000) / 1000);
        return time;
    }

}
