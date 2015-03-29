/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeux_tank;

/**
 *
 * @author Kevin
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Tank extends JPanel implements ActionListener, KeyListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Timer tm = new Timer(5, this);
    int x = -10, y = -10, vx = 50, vy = 50, angle = 0;
    private static final String IMAGE_PATH_tank_bas = "src/jeux_tank/images/tank_bas.png";
    private static final String IMAGE_PATH_tank_haut = "src/jeux_tank/images/tank_haut.png";

    public Tank() {
        tm.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            //BufferedImage image = ImageIO.read(new File(IMAGE_PATH));
            Image tank = new ImageIcon(IMAGE_PATH_tank_bas).getImage();
            Image canon = new ImageIcon(IMAGE_PATH_tank_haut).getImage();
            g.drawImage(tank, x, (y + 10), 100, 100, this);
            g.drawImage(rotationImage(canon, angle), x, y, 100, 100, this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private BufferedImage rotationImage(Image image, double degs) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage temp = new BufferedImage(height * 2, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = temp.createGraphics();
        g2.rotate(Math.toRadians(degs), height, height / 2); // Réglage de l'angle
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
        return temp;
    }

    public void deplacement_rotation() {
        Tank i = new Tank();
        //Affichage de la fenêtre
        JFrame jf = new JFrame();
        jf.setTitle("Tank");
        jf.setSize(800, 400);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(i);
    }

    public void actionPerformed(ActionEvent e) {
        if (x < 0) {
            vx = 0;
            x = 0;
        }
        if (x > 530) {
            vx = 0;
            x = 530;
        }
        if (y < 0) {
            vy = 0;
            y = 0;
        }
        if (y > 330) {
            vy = 0;
            y = 330;
        }
        x = x + vx;
        y = y + vy;
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            vx = -1;
            vy = 0;
        }
        if (c == KeyEvent.VK_UP) {
            vx = 0;
            vy = -1;
        }
        if (c == KeyEvent.VK_RIGHT) {
            vx = 1;
            vy = 0;
        }
        if (c == KeyEvent.VK_DOWN) {
            vx = 0;
            vy = 1;
        }
        if (c == KeyEvent.VK_S) {
            if (angle != 30) {
                angle = angle + 1;
            }
        }
        if (c == KeyEvent.VK_Z) {
            if (angle != -90) {
                angle = angle - 1;
            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
        vx = 0;
        vy = 0;
    }
}
