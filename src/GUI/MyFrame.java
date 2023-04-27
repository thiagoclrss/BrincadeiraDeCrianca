package GUI;


import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    //MyPanel panel;
    menuInicial panel;

    public MyFrame() {
        //panel = new MyPanel();
        panel = new menuInicial();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    //public MyPanel getPanel() {return panel;}
    public menuInicial getPanel() {return panel;}

    //public void setPanel(MyPanel panel) {this.panel = panel;}
    public void setPanel(menuInicial panel) {this.panel = panel;}
}
