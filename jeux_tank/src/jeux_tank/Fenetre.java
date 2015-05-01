package jeux_tank;

import javax.swing.*;

public class Fenetre extends JFrame {

    Tank joueur = new Tank();
    Tank IA = new Tank();
    Terrain terrain = new Terrain();
    int[] X;
    int[] Y;
    Point[] tab;

    public Fenetre() {
        //Initialisation du terrain
        X = new int[terrain.getNbPoint()];
        Y = new int[terrain.getNbPoint()];
        tab = terrain.getTab();
        for (int i = 0; i < terrain.getNbPoint(); i++) {
            X[i] = (int) tab[i].getPointX();
            Y[i] = (int) tab[i].getPointY();
        }
        this.setTitle("tank");
        this.setSize(10000, 10000);
        //Ajout des listeners
        addKeyListener(new TankKeyListener(joueur));
        addKeyListener(new TankKeyListener(IA));
        pan = new TankActionListener(joueur, X, Y, terrain, tab);
        pan = new TankActionListener(IA, X, Y, terrain, tab);
        this.add(pan);

        //Listeners
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    private JPanel pan;
}
