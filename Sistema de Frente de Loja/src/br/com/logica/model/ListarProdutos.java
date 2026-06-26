package br.com.logica.model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

public class ListarProdutos extends JFrame {
    public ListarProdutos(){
        setTitle("LISTA DE PRODUTOS");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"CODIGO", "PRODUTO", "PREÇO", "ESTOQUE"};

        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Produto a : CadastroProduto.listaDeProdutos) {
            model.addRow(new Object[]{a.getCodigo(), a.getNome(), a.getPreco(), a.getEstoque()});
        }



        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());
        add(btnVoltar, BorderLayout.SOUTH);

        setVisible(true);

    }
}
