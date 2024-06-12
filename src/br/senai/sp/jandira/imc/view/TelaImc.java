package br.senai.sp.jandira.imc.view;

import br.senai.sp.jandira.imc.model.Imc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TelaImc extends JFrame {

    private JTextField txtPeso = new JTextField();
    private JTextField txtAltura = new JTextField();
    private JLabel lblPeso = new JLabel("Peso:");
    private JLabel lblAltura = new JLabel("Altura:");
    private JLabel lblTitulo = new JLabel("Calculadora IMC");
    private JLabel lblTituloResultado = new JLabel("Resultado do IMC");
    private JLabel lblResultadoImc = new JLabel("");
    private JLabel lblStatusImc = new JLabel("");
    private JButton btnOk = new JButton("Calcular");
    private JButton btnLimpar = new JButton("Limpar");
    private JPanel panelTitulo = new JPanel();

    private JPanel panelResultado = new JPanel();

    private String imagePath = "../images/";
    private Icon iconBtnOk = new ImageIcon(getClass().getResource(imagePath + "calc2.png"));
    private Icon iconBtnLimpar = new ImageIcon(getClass().getResource(imagePath + "restart24.png"));

    private int peso = 0;
    private double altura = 0.0;

    public TelaImc(){
        setTitle("Calculadora IMC");
        setSize(new Dimension(500, 340));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        // *** TITULO DA TELA
        panelTitulo.setBounds(0, 0, 500, 50);
        panelTitulo.setLayout(null);
        panelTitulo.setBackground(new Color(211, 84, 0));

        lblTitulo.setBounds(10, 10, 300, 30);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);

        // COMPONENTES DA TELA
        lblPeso.setBounds(10, 70, 150, 30);
        txtPeso.setBounds(10, 100, 150, 35);
        txtPeso.setFont(new Font("Arial", Font.PLAIN, 20));

        lblAltura.setBounds(10, 140, 150, 30);
        txtAltura.setBounds(10, 170, 150, 35);
        txtAltura.setFont(new Font("Arial", Font.PLAIN, 20));

        btnOk.setBounds(10, 230, 150, 50);
        btnOk.setIcon(iconBtnOk);
        btnOk.setHorizontalTextPosition(SwingConstants.RIGHT);

        btnLimpar.setBounds(200, 230, 250, 50);
        btnLimpar.setIcon(iconBtnLimpar);
        btnLimpar.setHorizontalTextPosition(SwingConstants.RIGHT);

        lblTituloResultado.setBounds(0, 5, 250, 30);
        lblTituloResultado.setForeground(Color.YELLOW);
        lblTituloResultado.setHorizontalAlignment(JLabel.CENTER);

        lblResultadoImc.setBounds(0, 30, 250, 60);
        lblResultadoImc.setForeground(Color.WHITE);
        lblResultadoImc.setFont(new Font("Arial", Font.BOLD, 48));
        lblResultadoImc.setHorizontalAlignment(JLabel.CENTER);

        lblStatusImc.setBounds(0, 80, 250, 60);
        lblStatusImc.setFont(new Font("Arial", Font.BOLD, 30));
        lblStatusImc.setForeground(Color.WHITE);
        lblStatusImc.setHorizontalAlignment(JLabel.CENTER);

        panelResultado.setBounds(200, 70, 250, 140);
        panelResultado.setBackground(new Color(29, 131, 72));
        panelResultado.setLayout(null);

        panelTitulo.add(lblTitulo);

        panelResultado.add(lblTituloResultado);
        panelResultado.add(lblResultadoImc);
        panelResultado.add(lblStatusImc);

        Container container = getContentPane();
        container.add(panelTitulo);
        container.add(panelResultado);
        container.add(lblPeso);
        container.add(txtPeso);
        container.add(lblAltura);
        container.add(txtAltura);
        container.add(btnOk);
        container.add(btnLimpar);

        // EVENTOS
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularImc();
            }
        });

        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });

        // TORNAR A JANELA VISÍVEL PARA O USUÁRIO
        setVisible(true);
    }

    private void limparTela() {

        txtPeso.setText("");
        txtAltura.setText("");
        lblResultadoImc.setText("");
        lblStatusImc.setText("");
        txtPeso.requestFocus();

    }

    private void calcularImc() {

        if (validarDados()) {
            Imc imc = new Imc();
            imc.setPeso(peso);
            imc.setAltura(altura);

            if (imc.getImc() < 18.5 || imc.getImc() > 25.0){
                panelResultado.setBackground(Color.RED);
            } else {
                panelResultado.setBackground(new Color(29, 131, 72));
            }

            lblResultadoImc.setText(
                    String.format("%.2f", imc.getImc()).replace(".", ",")
            );

            lblStatusImc.setText(imc.getStatus());
        }

    }

    private boolean validarDados() {
        try {
            peso = Integer.parseInt(txtPeso.getText().trim());
        } catch (NumberFormatException erro){
            JOptionPane.showMessageDialog(
                    null,
                    "O peso deve ser um valor numérico!",
                    "Valor inválido",
                    JOptionPane.ERROR_MESSAGE
            );
            txtPeso.requestFocus();
            txtPeso.selectAll();
            return false;
        }

        try {
            altura = Double.parseDouble(txtAltura.getText().replace(",", ".").trim());
        } catch (NumberFormatException erro){
            JOptionPane.showMessageDialog(
                    null,
                    "A altura deve ser um valor numérico!",
                    "Valor inválido",
                    JOptionPane.ERROR_MESSAGE
            );
            txtAltura.requestFocus();
            txtAltura.selectAll();
            return false;
        }

        return true;
    }


}
