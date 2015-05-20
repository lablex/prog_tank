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
	private volatile boolean altern;
	private JPanel pan;
	private Missile missile1;
	private Missile missile2;
	private Tank tank1;
	private Tank tank2;
	private int count;

	public Animation(Missile missile1, Missile missile2, Tank tank1, Tank tank2, JPanel pan) {
		this.missile1 = missile1;
		this.missile2 = missile2;
		this.tank1 = tank1;
		this.tank2 = tank2;
		this.pan=pan;
		
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

	public void run() {

		while (true) {
				try {
					
					count++;
					if(count<10000){
						
						altern=false;
						if(count==9999){
							avance= false;
							tir=false;
							Fenetre.setAvance(true);
							Fenetre.setTir(true);
						}
					}else if(10000<=count && count<20000){
						altern=true;
					}else{
						count=0;
						avance= false;
						tir=false;
						Fenetre.setAvance(true);
						Fenetre.setTir(true);
					}
					
					
					if(altern){
						if(tir && !avance){
							missile1.setMissile(100);
							missile1.setPosition();
							if(!missile1.getRunning()){
								missile1.setRunning(true);
							}
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
						if(tir && !avance){
							missile2.setMissile(100);
							missile2.setPosition();
							if(!missile2.getRunning()){
								missile2.setRunning(true);
							}
						}if(!tir && avance){
							tank2.setPointsTank();
							tank2.setPositionTankX();
							tank2.setPositionTankY();
							tank2.setPositionCanonX();
							tank2.setPositionCanonY();
							tank2.setAngleTank();
						}
					}
					
					pan.repaint();
					Thread.sleep(1);
					
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // Très important de réinterrompre
					break; // Sortie de la boucle infinie

				}
		}

	}
	
	public boolean getAltern(){
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
}
