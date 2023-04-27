import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class menuCampo extends JFrame implements ActionListener
{
    private JPanel pnlPrincipal;
    private JButton btnCriarCrianca;
    private JButton btnEncerrar;
//    private JTextField txtCapacidadeCesto;
//    private JLabel lblCapacidadeCesto;

    public menuCampo()
    {
        //JFrame menuCampo
        setTitle("Campo de futebol do Fortaleza");
        setResizable(false); /*não pode ser redimensionado*/
        setSize(800,600); /*Tamanho: 800x600*/
        setLocationRelativeTo(null); /*O frame é posicionado no centro*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /*Botão "X" fecha a execução*/
        setLayout(null);
        setVisible(true);

//        //JLabel lblCapacidadeCesto
//        lblCapacidadeCesto = new JLabel("Capacidade do cesto:");
//        lblCapacidadeCesto.setVisible(true);
//        lblCapacidadeCesto.setBounds(40,100,150,25);
//        lblCapacidadeCesto.setFont(new Font("Arial", Font.PLAIN, 12));
//
//        //JTextField
//        txtCapacidadeCesto = new JTextField();
//        txtCapacidadeCesto.setBounds(180,100,180,25);
//        txtCapacidadeCesto.setFont(new Font("Arial", Font.PLAIN, 12));

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

    private void closePanel(ActionEvent e){System.exit(0);} /*Fecha o programa*/

    @Override
    public void actionPerformed(ActionEvent e) /*Cria o buffer basket, com o parâmetro capacidade dado em txtCapacidadeCesto*/
    {
        new menuCriarCrianca();
    }
}
