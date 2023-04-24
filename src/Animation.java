import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animation extends JPanel implements ActionListener {

    private Timer timer;
    private Image child;
    private Image basket;

    public Animation() {
        child = new ImageIcon("assets/kid-removebg-preview.png").getImage();

        basket = new ImageIcon("assets/basket.png").getImage();
        timer = new Timer(25, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d= (Graphics2D)g;

        Image backGround = new ImageIcon("assets/backGroundPark.jpg").getImage();
        //desenha background
        g2d.drawImage(backGround,0, 0, getWidth(), getHeight(), null);

        //desenha criança
        g2d.drawImage(child,100,400, 80,80,null);

        //desenha cesto
        g2d.drawImage(basket, getWidth()/2 -50,400, 80, 80, null );


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}