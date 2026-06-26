package br.com.logica.model;

import javax.swing.*;
import java.awt.*;

public class MenuUsuario extends JFrame {
    public MenuUsuario(){
        setTitle("Menu Usuario");

        setSize(320, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());


        JLabel lblTitulo = new JLabel("Menu do Usuario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        JPanel painelBotoes = new JPanel(new GridLayout(6, 1, 10, 15));

        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 40, 30, 40));


        JButton btnListar = new JButton("Pesquisar Produtos");
        btnListar.addActionListener(e -> new ListarProdutos().setVisible(true));


        JButton btnComprar = new JButton("Comprar Produtos ");
        btnComprar.addActionListener(e -> new Carrinho().setVisible(true));





        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());



        painelBotoes.add(btnListar);
        painelBotoes.add(btnComprar);
        painelBotoes.add(btnVoltar);


        add(lblTitulo, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);

        setVisible(true);
    }
}

