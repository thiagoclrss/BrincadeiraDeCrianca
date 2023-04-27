package GUI;

import ChildLogic.GUIInterface;
import ChildLogic.ThreadChild;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuCriarCrianca extends JFrame implements ActionListener
{
    private JPanel pnlPrincipal;
    private JLabel lblNome;
    private JTextField txtNome;
    private JLabel lblBola;
    private JCheckBox chkBola;
    private JLabel lblBrinca;
    private JTextField txtBrinca;
    private JLabel lblDescansa;
    private JTextField txtDescansa;
    private JButton btnCriarCrianca;
    private JButton btnCancelar;

    public menuCriarCrianca()
    {
        //JFrame menuCriarCriança
        setTitle("Chamar Criança");
        setResizable(false); /*Não pode ser redimensionado*/
        setSize(400,250); /*Tamanho: 400x250*/
        setLocationRelativeTo(null); /*O frame é posicionado no centro*/
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); /*Botão "X" fecha a janela atual*/
        setLayout(null);
        setVisible(true);

        //JLabel lblNome
        lblNome = new JLabel("Nome da criança");
        lblNome.setVisible(true);
        lblNome.setBounds(20,20,150,25);
        lblNome.setFont(new Font("Arial", Font.PLAIN, 12));

        //JTextField txtNome
        txtNome = new JTextField();
        txtNome.setBounds(230,20,150,25);
        txtNome.setFont(new Font("Arial", Font.PLAIN, 12));

        //JLabel lblBola
        lblBola = new JLabel("Está com uma bola?");
        lblBola.setVisible(true);
        lblBola.setBounds(20,50,150,25);
        lblBola.setFont(new Font("Arial", Font.PLAIN, 12));

        //JCheckBox chkBola
        chkBola = new JCheckBox();
        chkBola.setBounds(350,50,50,25);

        //JLabel lblBrinca
        lblBrinca = new JLabel("Tempo que brinca");
        lblBrinca.setVisible(true);
        lblBrinca.setBounds(20,80,150,25);
        lblBrinca.setFont(new Font("Arial", Font.PLAIN, 12));


        //JTextField txtBrinca
        txtBrinca = new JTextField();
        txtBrinca.setBounds(230,80,150,25);
        txtBrinca.setFont(new Font("Arial", Font.PLAIN, 12));


        //JLabel lblDescansa
        lblDescansa = new JLabel("Tempo que descansa");
        lblDescansa.setVisible(true);
        lblDescansa.setBounds(20,110,150,25);
        lblDescansa.setFont(new Font("Arial", Font.PLAIN, 12));

        add(lblDescansa);

        //JTextField txtDescansa
        txtDescansa = new JTextField();
        txtDescansa.setBounds(230,110,150,25);
        txtDescansa.setFont(new Font("Arial", Font.PLAIN, 12));


        //JButton btnCriarCrianca
        btnCriarCrianca = new JButton("OK");
        btnCriarCrianca.setBounds(80,160,100,25);
        btnCriarCrianca.setFont(new Font("Arial", Font.PLAIN, 12));
        btnCriarCrianca.addActionListener(this);


        //JButton btnCancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(220,160,100,25);
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnCancelar.addActionListener(this::closePanel);

        //Adição dos componentes
        add(lblNome);
        add(txtNome);
        add(lblBola);
        add(chkBola);
        add(lblBrinca);
        add(txtBrinca);
        add(txtDescansa);
        add(btnCriarCrianca);
        add(btnCancelar);
    }

    private void closePanel(ActionEvent e){dispose();} /*Fecha o programa*/

    @Override
    public void actionPerformed(ActionEvent e) /*Cria o buffer basket, com o parâmetro capacidade dado em txtCapacidadeCesto*/
    {
        String idThread = txtNome.getText();
        boolean ball = chkBola.isSelected(); //Define se a criança vai ser produtora ou consumidora
        int playingTime = Integer.parseInt(txtBrinca.getText());
        int quietTime = Integer.parseInt(txtDescansa.getText());
        ThreadChild addChild = new ThreadChild(
                idThread, ball, playingTime, quietTime, menuCampo.getPanel().guiInterface);
        addChild.run();
        dispose();
    }
}
