package br.com.logica.model;
import br.com.logica.controller.VendaController;
import javax.swing.*;
import java.awt.*;

public class MenuOperador extends JFrame {

    public MenuOperador() {

        setTitle("Menu Operador");

        setSize(320, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());


        JLabel lblTitulo = new JLabel("Menu Operador", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        JPanel painelBotoes = new JPanel(new GridLayout(7, 1, 10, 15));

        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 40, 30, 40));


        JButton btnCadastrar = new JButton("Cadastro de Produtos");
        btnCadastrar.addActionListener(e -> new CadastroProduto().setVisible(true));


        JButton btnListar = new JButton("Listar Produtos");
        btnListar.addActionListener(e -> new ListarProdutos().setVisible(true));


        JButton btnPesquisar = new JButton("Consultar Produto");
        btnPesquisar.addActionListener(e -> new ConsultarProduto().setVisible(true));


        JButton btnRemover = new JButton("Remover Produto");
        btnRemover.addActionListener(e -> new RemoverProduto().setVisible(true));


        JButton btnAlterar = new JButton("Alterar Produto");
        btnAlterar.addActionListener(e -> new AlterarProduto().setVisible(true));

        JButton btnVendas = new JButton("Historico de Vendas");
        btnVendas.addActionListener(e -> new VendaController().setVisible(true));


        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());


        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnListar);
        painelBotoes.add(btnPesquisar);
        painelBotoes.add(btnVendas);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnVoltar);


        add(lblTitulo, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);

        setVisible(true);
    }
}

