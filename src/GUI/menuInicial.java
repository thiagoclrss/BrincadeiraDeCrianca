package GUI;

import ChildLogic.Basket;
import ChildLogic.GUIInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class menuInicial extends JFrame implements ActionListener
{
    private JPanel pnlPrincipal;
    private JButton btnOK;
    private JButton btnCancelar;
    private JTextField txtCapacidadeCesto;
    private JLabel lblCapacidadeCesto;
    public GUIInterface guiInterface;
    ArrayList<Integer> childrenX;
    ArrayList<Integer> childrenY;
    ArrayList<Semaphore> animationMutexList;
    ArrayList<GUI.Action> actionsList;

    public menuInicial()
    {
        implementGUIInterface();

        //JFrame menuInicial
        setTitle("Brincadeira de Criança");
        setResizable(false); /*não pode ser redimensionado*/
        setSize(400,300); /*Tamanho: 400x300*/
        setLocationRelativeTo(null); /*O frame é posicionado no centro*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /*Botão "X" fecha a execução*/
        setLayout(null);
        setVisible(true);

        //JLabel lblCapacidadeCesto
        lblCapacidadeCesto = new JLabel("Capacidade do cesto:");
        lblCapacidadeCesto.setVisible(true);
        lblCapacidadeCesto.setBounds(40,100,150,25);
        lblCapacidadeCesto.setFont(new Font("Arial", Font.PLAIN, 12));

        //JTextField
        txtCapacidadeCesto = new JTextField();
        txtCapacidadeCesto.setBounds(180,100,180,25);
        txtCapacidadeCesto.setFont(new Font("Arial", Font.PLAIN, 12));

        //JButton btnOK
        btnOK = new JButton("OK");
        btnOK.setBounds(80,225,100,25);
        btnOK.setFont(new Font("Arial", Font.PLAIN, 12));
        btnOK.addActionListener(this);

        //JButton btnCancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(220,225,100,25);
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnCancelar.addActionListener(this::closePanel);

        //Adição dos componentes
        add(lblCapacidadeCesto);
        add(txtCapacidadeCesto);
        add(btnOK);
        add(btnCancelar);
    }

    private void closePanel(ActionEvent e){dispose();} /*Fecha o painel atual*/

    @Override
    public void actionPerformed(ActionEvent e) /*Cria o buffer basket, com o parâmetro capacidade dado em txtCapacidadeCesto*/
    {
        int capacidadeCesto = Integer.parseInt(txtCapacidadeCesto.getText());
        Basket basket = new Basket(capacidadeCesto);
        System.out.println("Cesto com capacidade " + capacidadeCesto);
        new menuCampo(capacidadeCesto);
        dispose();
    }

    public void doAction(GUI.Action action, int index) {
        actionsList.set(index, action);
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
