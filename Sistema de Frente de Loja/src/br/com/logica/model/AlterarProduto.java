package br.com.logica.model;

import javax.swing.*;
import java.awt.*;

public class AlterarProduto extends JFrame {

    public AlterarProduto() {
        setTitle("Alterar Produto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel lblCodigo = new JLabel("Digite o codigo do Produto:");
        JTextField txtBusca = new JTextField(10);

        // Mudei o nome da variável para btnAlterar para fazer mais sentido
        JButton btnAlterar = new JButton("Alterar");
        JButton btnVoltar = new JButton("Voltar");

        btnAlterar.addActionListener(e -> {
            try {
                int codigoBuscado = Integer.parseInt(txtBusca.getText());
                Produto ProdutoAlterar = null;


                for (Produto a : CadastroProduto.listaDeProdutos) {
                    if (a.getCodigo() == codigoBuscado) {
                        ProdutoAlterar = a;
                        break;
                    }
                }

                if (ProdutoAlterar != null) {

                    String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", ProdutoAlterar.getNome());

                    if (novoNome != null && !novoNome.trim().isEmpty()) {
                        String precoStr = JOptionPane.showInputDialog(this, "Novo preço:", ProdutoAlterar.getPreco());
                        String estoqueStr = JOptionPane.showInputDialog(this, "Novo estoque:", ProdutoAlterar.getEstoque());

                        ProdutoAlterar.setNome(novoNome);
                        // O replace(",", ".") é uma ótima ideia para evitar erros de digitação!
                        ProdutoAlterar.setPreco(Double.parseDouble(precoStr.replace(",", ".")));
                        ProdutoAlterar.setEstoque(Integer.parseInt(estoqueStr));

                        JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!");

                        txtBusca.setText("");
                    }
                } else {

                    JOptionPane.showMessageDialog(this,
                            "Produto com código " + codigoBuscado + " não encontrado.",
                            "Aviso",
                            JOptionPane.WARNING_MESSAGE);
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Por favor, insira valores numéricos válidos.",
                        "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
            }
        });


        btnVoltar.addActionListener(e -> dispose());


        add(lblCodigo);
        add(txtBusca);
        add(btnAlterar);
        add(btnVoltar);


        setVisible(true);
    }
}