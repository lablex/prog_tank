package jeux_tank;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fenetre extends JFrame {

    public Fenetre() {
        this.setTitle("tank");
        this.setSize(10000, 10000);
        pan = new Draw();
        this.add(pan);
    }

    private JPanel pan;
}
