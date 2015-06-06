/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

import javax.swing.JPanel;

public class Animation extends Thread {

    private volatile boolean avance = false;
    private volatile boolean tir = false;
    private volatile static boolean altern = false;
    private JPanel pan;
    private Tank tank1;
    private Tank tank2;
    private static int count;
    private Missile[] tabMissile1 = new Missile[3];
    private Missile[] tabMissile2 = new Missile[3];
    public static boolean choix_missile2 = true;
    public static boolean continu = false;
    public static boolean tir_missile2 = false;
    public static boolean tir_missile1 = false;

    public Animation(Missile[] tabMissile1, Missile[] tabMissile2, Tank tank1, Tank tank2, JPanel pan) {

        for (int i = 0; i < tabMissile1.length; i++) {
            this.tabMissile1[i] = tabMissile1[i];
            this.tabMissile2[i] = tabMissile2[i];
        }

        this.tank1 = tank1;
        this.tank2 = tank2;
        this.pan = pan;

        for (int i = 0; i < 2; i++) {
            this.tank1.setPointsTank();
            this.tank1.setPositionTankX();
            this.tank1.setPositionTankY();
            this.tank1.setPositionCanonX();
            this.tank1.setPositionCanonY();
            this.tank1.setAngleTank();
           // this.tank1.setAngleCanon(30,true);
            
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

                if (count < 9000) {
                } else {
                    if (altern) {
                        altern = false;
                    } else {
                        altern = true;
                    }
                    count = 0;
                    if (Missile.getStop()) {
                        avance = false;
                        tir = false;
                    }
                    Fenetre.setAvance(true);
                    Fenetre.setTir(true);
                    if(!tir_missile2 && !tir_missile1)
                        Fenetre.setSelectMissile();
                }
                // Gestion alternance des tanks
                if (tir_missile2) {
                    if (!Missile.detection && altern) {
                        Fenetre.setAvance(false);
                        tir_missile(tabMissile2, tank2);
                        continu = true;
                        count = 0;
                    } else if (Missile.detection && altern) {
                        Missile.setV0(75);
                        Fenetre.setTir(true);
                        Fenetre.setAvance(true);
                        Fenetre.setSelectMissile();
                        continu = false;
                        choix_missile2 = false;
                        tir_missile2 = false;
                        tir = false;
                        count = 0;
                        Fenetre.setEnter(false);

                    } else if (altern && Missile.getStop()) {
                        choix_missile2 = false;
                        tir_missile2 = false;
                    }

                } else if (altern && !tir_missile2) {
                    choix_missile2 = false;
                }
                if (tir_missile1) {
                    if (!Missile.detection2 && !altern) {
                        Fenetre.setAvance(false);
                        tir_missile(tabMissile1, tank1);
                        continu = true;
                        count = 0;
                    } else if (Missile.detection2 && !altern) {
                        Missile.setV0(75);
                        Fenetre.setTir(true);
                        Fenetre.setAvance(true);
                        Fenetre.setSelectMissile();
                        continu = false;
                        choix_missile2 = true;
                        tir_missile1 = false;
                        tir = false;
                        count = 0;
                        Fenetre.setEnter(false);
                    } else if (!altern  && Missile.getStop()) {
                        choix_missile2 = true;
                        tir_missile1 = false;
                    }

                } else if (!altern && !tir_missile1) {
                    choix_missile2 = true;
                }

                if (tir) {
                    if (!altern && !continu && choix_missile2 == true) {
                        Fenetre.setAvance(false);
                        //Fenetre.setSelectMissile();
                        tir_missile(tabMissile2, tank2);
                        tir_missile2 = true;
                    } else if (altern && !continu && choix_missile2 == false) {
                        Fenetre.setAvance(false);
                        //Fenetre.setSelectMissile();
                        tir_missile(tabMissile1, tank1);
                        tir_missile1 = true;
                    }
                } else if (avance) {
                    if (altern) {
                        Fenetre.setAvance(true);
                        avance(tank1);
                    }
                    if (!altern) {
                        Fenetre.setAvance(true);
                        avance(tank2);
                    }
                }
                init(tank1);
                init(tank2);
                pan.repaint();
                Thread.sleep(1);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;

            }
        }

    }

    public static boolean getAltern() {
        return altern;
    }

    public boolean getAvance() {
        return avance;
    }

    public boolean getTir() {
        return tir;
    }

    public void setAvance(boolean avance) {
        this.avance = avance;
    }

    public void setTir(boolean tir) {
        this.tir = tir;
    }

    public static String getTime() {
        String time = String.valueOf(Math.round(count / 1000 * 1000) / 1000);
        return time;
    }

    public void tir(Missile[] tabMissile1) {
        if (tir && !avance && Missile.getStop() == false) {
            tabMissile1[Fenetre.getSelectMissile()].setMissile(Missile.getV0());
            tabMissile1[Fenetre.getSelectMissile()].setPosition();
            if (!tabMissile1[Fenetre.getSelectMissile()].getRunning()) {
                tabMissile1[Fenetre.getSelectMissile()].setRunning(true);
            }
        }
        tabMissile1[Fenetre.getSelectMissile()].verification();

    }

    public void tir_missile(Missile[] tabMissile1, Tank tank) {
        if (tir && !avance && Missile.getStop() == false) {
            tabMissile1[Fenetre.getSelectMissile()].setMissile(Missile.getV0());
            tabMissile1[Fenetre.getSelectMissile()].setPosition();
            tank.setNbMissile();
            if (!tabMissile1[Fenetre.getSelectMissile()].getRunning()) {
                tabMissile1[Fenetre.getSelectMissile()].setRunning(true);
            }
        }
        Fenetre.missile = false;
        tabMissile1[Fenetre.getSelectMissile()].verification();

    }

    public void avance(Tank tank) {
        if (!tir && avance) {
            tank.setPointsTank();
            tank.setPositionTankX();
            tank.setPositionTankY();
            tank.setPositionCanonX();
            tank.setPositionCanonY();
            tank.setAngleTank();
        }

    }

    public void init(Tank tank) {

        tank.setPointsTank();
        tank.setPositionTankX();
        tank.setPositionTankY();
        tank.setPositionCanonX();
        tank.setPositionCanonY();
        tank.setAngleTank();

    }
    
}
