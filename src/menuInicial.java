import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class menuInicial extends JFrame implements ActionListener
{
    private JPanel pnlPrincipal;
    private JButton btnOK;
    private JButton btnCancelar;
    private JTextField txtCapacidadeCesto;
    private JLabel lblCapacidadeCesto;

    public menuInicial()
    {
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
        new menuCampo();
        dispose();
    }
}
