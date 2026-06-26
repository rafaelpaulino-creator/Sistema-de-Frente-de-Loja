package br.com.logica.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroProduto extends JFrame {

        public static int qtdProduto = 0;
        public static List<Produto> listaDeProdutos = new ArrayList<>();

        public CadastroProduto() {
            setTitle("Cadastrar Produtos");
            setLayout(new BorderLayout());
            setSize(350, 250);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);


            JPanel painelForm = new JPanel(new GridLayout(3, 2, 10, 10));
            painelForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel lblProduto = new JLabel("Produto: ");
            JTextField txtProduto = new JTextField(20);

            JLabel lblPreco = new JLabel("Preço: ");
            JTextField txtPreco = new JTextField(20);

            JLabel lblEstoque = new JLabel("Estoque: ");
            JTextField txtEstoque = new JTextField(20);


            painelForm.add(lblProduto);
            painelForm.add(txtProduto);
            painelForm.add(lblPreco);
            painelForm.add(txtPreco);
            painelForm.add(lblEstoque);
            painelForm.add(txtEstoque);


            JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.addActionListener(e -> {
                String nome = txtProduto.getText();

                try {
                    double preco = Double.parseDouble(txtPreco.getText());
                    int estoque = Integer.parseInt(txtEstoque.getText());

                    qtdProduto++;
                    int codigo = qtdProduto;


                    Produto novoProduto = new Produto(codigo, nome, preco, estoque);


                    listaDeProdutos.add(novoProduto);

                    System.out.println("Codigo: " + codigo + " Produto: " + nome + " | Preço: " + preco + " | Estoque: " + estoque);

                    javax.swing.JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");


                    txtProduto.setText("");
                    txtPreco.setText("");
                    txtEstoque.setText("");
                    txtProduto.requestFocus();

                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null,
                            "Por favor, insira valores válidos para Preço e Estoque.",
                            "Erro de Validação",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            });



            JButton btnVoltar3 = new JButton("Voltar");
            btnVoltar3.addActionListener(e3 -> dispose());


            painelBotoes.add(btnSalvar);
            painelBotoes.add(btnVoltar3);


            add(painelForm, BorderLayout.CENTER);
            add(painelBotoes, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

