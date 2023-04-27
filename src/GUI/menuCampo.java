package GUI;

import ChildLogic.Basket;
import ChildLogic.GUIInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class menuCampo extends JFrame implements ActionListener
{
    final int PANEL_WIDTH = 960;
    final int PANEL_HEIGHT = 540;
    public static JPanel pnlPrincipal;
    private JButton btnCriarCrianca;
    private JButton btnEncerrar;
    public GUIInterface guiInterface;
    ArrayList<Integer> childrenX;
    ArrayList<Integer> childrenY;
    ArrayList<Semaphore> animationMutexList;
    ArrayList<GUI.Action> actionsList;
    Image child;
    Image backgroundImage;
    Image basket;
    Timer timer;
    int velocity = 2;
    //public GUIInterface guiInterface;
    public menuCampo(int qntBallsBasket)
    {
        Basket cesto = new Basket(qntBallsBasket);
        childrenX = new ArrayList<>();
        childrenY = new ArrayList<>();
        animationMutexList = new ArrayList<>();
        actionsList = new ArrayList<>();

        implementGUIInterface();

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));

        child = new ImageIcon("assets/kid-removebg-preview.png").getImage();
        child = child.getScaledInstance(100,200, Image.SCALE_SMOOTH);

        backgroundImage = new ImageIcon("assets/backGroundPark.jpg").getImage();
        backgroundImage = backgroundImage.getScaledInstance(960,540, Image.SCALE_SMOOTH);

        basket = new ImageIcon("assets/basket.png").getImage();
        basket = basket.getScaledInstance(100,100, Image.SCALE_SMOOTH);

        timer = new Timer(10, this::timer);
        timer.start();

        //JFrame menuCampo
        setTitle("Campo de futebol do Fortaleza");
        setResizable(false); /*não pode ser redimensionado*/
        setSize(800,600); /*Tamanho: 800x600*/
        //setLocationRelativeTo(null); /*O frame é posicionado no centro*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /*Botão "X" fecha a execução*/
        setLayout(null);
        setVisible(true);

        //JButton btnCriarCrianca
        btnCriarCrianca = new JButton("Chamar Criança");
        btnCriarCrianca.setBounds(465,525,150,25);
        btnCriarCrianca.setFont(new Font("Arial", Font.PLAIN, 12));
        btnCriarCrianca.addActionListener(this);

        //JButton btnEncerrar
        btnEncerrar = new JButton("Encerrar");
        btnEncerrar.setBounds(625,525,150,25);
        btnEncerrar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnEncerrar.addActionListener(this::closePanel);

        //Adição dos componentes
//        add(lblCapacidadeCesto);
//        add(txtCapacidadeCesto);
        add(btnCriarCrianca);
        add(btnEncerrar);
    }
    public static menuCampo getPanel() {return menuCampo.getPanel();}
    private void closePanel(ActionEvent e){System.exit(0);} /*Fecha o programa*/

    public void doAction(GUI.Action action, int index) {
        actionsList.set(index, action);
    }

    private void goToXY(int x, int y, int index) {
        int currentX = childrenX.get(index);

        if(currentX < x) {
            childrenX.set(index, currentX + velocity);
        } else if(currentX > x) {
            childrenX.set(index, currentX - velocity);
        }

        int currentY = childrenY.get(index);
        if(currentY < y) {
            childrenY.set(index, currentY + velocity);
        } else if(currentY > y) {
            childrenY.set(index, currentY - velocity);
        }

        if(currentY == y && currentX == x) {
            actionsList.set(index, null);
            animationMutexList.get(index).release();
        }

        repaint();
    }

    private void goToPlay(int index) {
        goToXY(450, 180, index);
    }

    private void goToBasket(int index) {
        goToXY(450, 540, index);
    }

    private void goToRest(int index) {
        goToXY(100, 100, index);
    }

    public void timer(ActionEvent e) {
        for (int i = 0; i < actionsList.size(); i++) {
            var action = actionsList.get(i);

            if (action == Action.NEW_CHILD) {
                repaint();
            }

            if (action == Action.GETBALL) {
                goToBasket(i);
            }

            if (action == Action.PUTBALL) {
                goToBasket(i);
            }

            if (action == Action.PLAY) {
                goToPlay(i);
            }

            if (action == Action.REST) {
                goToRest(i);
            }
        }
    }

    int getThreadIndexById(String id) {
        var children = Basket.children;
        for(int index = 0; index < children.size(); index++) {
            if(children.get(index).getIdChild().equals(id)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) /*Cria o buffer basket, com o parâmetro capacidade dado em txtCapacidadeCesto*/
    {
        new menuCriarCrianca();
    }

    void implementGUIInterface() {
        guiInterface = new GUIInterface() {
            @Override
            public void newChildAnimation() {
                actionsList.add(null);
                animationMutexList.add(new Semaphore(1));
                childrenX.add(0);
                childrenY.add(540);
                doAction(Action.NEW_CHILD, actionsList.size() - 1);
            }

            @Override
            public void getBall(String id) {
                var index = getThreadIndexById(id);
                try {
                    animationMutexList.get(index).acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                doAction(Action.GETBALL, index);
            }

            @Override
            public void putBall(String id) {
                var index = getThreadIndexById(id);
                try {
                    animationMutexList.get(index).acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                doAction(Action.PUTBALL, index);
            }

            @Override
            public void play(String id) {
                var index = getThreadIndexById(id);
                try {
                    animationMutexList.get(index).acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                doAction(Action.PLAY, index);
            }

            @Override
            public void rest(String id) {
                var index = getThreadIndexById(id);
                try {
                    animationMutexList.get(index).acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                doAction(Action.REST, index);
            }
        };
    }
}

