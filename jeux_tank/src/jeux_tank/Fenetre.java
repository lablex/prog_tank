package jeux_tank;
import javax.swing.*;
  
public class Fenetre extends JFrame {
  
        public Fenetre(){   
        	this.setTitle("tank");
        	this.setSize(10000, 10000);
        	pan = new Paneau();
        	getContentPane().add(pan);
        }
        private JPanel pan;
}

