package br.com.logica.controller;

import br.com.logica.model.Venda;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VendaController extends JFrame {


    public static List<Venda> historicoVendas = new ArrayList<>();

    private DefaultTableModel modeloTabela;

    public VendaController() {
        setTitle("Histórico de Vendas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        JLabel lblTitulo = new JLabel("Vendas Realizadas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);


        String[] colunas = {"Cód. Venda", "Quantidade de Itens", "Total com Desconto"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabelaHistorico = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaHistorico);
        add(scrollTabela, BorderLayout.CENTER);


        JPanel painelBaixo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());
        painelBaixo.add(btnVoltar);
        add(painelBaixo, BorderLayout.SOUTH);


        preencherTabela();

        setVisible(true);
    }

    private void preencherTabela() {
        int contador = 1;

        for (Venda v : historicoVendas) {

            double total = v.calcularTotal();
            if (total >= 500) total *= 0.85;
            else if (total >= 200) total *= 0.90;
            else if (total >= 100) total *= 0.95;

            modeloTabela.addRow(new Object[]{
                    "Venda #" + contador,
                    v.getItens().size() + " item(ns)",
                    String.format("R$ %.2f", total)
            });
            contador++;
        }
    }
}