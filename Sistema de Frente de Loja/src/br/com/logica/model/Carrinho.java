package br.com.logica.model;

import br.com.logica.service.Caixa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Carrinho extends JFrame {

    private Venda carrinhoAtual = new Venda();
    private DefaultTableModel modeloTabela;
    private JLabel lblTotalFinal;

    public Carrinho() {
        setTitle("Frente de Caixa - Carrinho de Compras");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);


        JPanel painelTopo = new JPanel(new FlowLayout());
        JTextField txtCodigo = new JTextField(5);
        JTextField txtQuantidade = new JTextField(3);
        JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
        JButton btnVerProdutos = new JButton("Ver Produtos");

        btnVerProdutos.addActionListener(e -> new ListarProdutos());

        painelTopo.add(btnVerProdutos);
        painelTopo.add(new JLabel("Código:"));
        painelTopo.add(txtCodigo);
        painelTopo.add(new JLabel("Qtd:"));
        painelTopo.add(txtQuantidade);
        painelTopo.add(btnAdicionar);


        String[] colunas = {"Produto", "Preço Unit.", "Qtd", "Subtotal"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        JTable tabelaCarrinho = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaCarrinho);


        JPanel painelBaixo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnVoltar = new JButton("Voltar");
        JButton btnRemover = new JButton("Remover Item");

        lblTotalFinal = new JLabel("Total: R$ 0.00");
        lblTotalFinal.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnFinalizar = new JButton("Finalizar Compra");

        painelBaixo.add(btnVoltar);
        painelBaixo.add(btnRemover);
        painelBaixo.add(Box.createHorizontalStrut(15)); // Espaçamento
        painelBaixo.add(lblTotalFinal);
        painelBaixo.add(btnFinalizar);


        btnAdicionar.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCodigo.getText());
                int qtd = Integer.parseInt(txtQuantidade.getText());
                boolean encontrou = false;

                for (Produto p : CadastroProduto.listaDeProdutos) {
                    if (p.getCodigo() == codigo) {
                        encontrou = true;

                        int qtdJaNoCarrinho = 0;
                        int linhaExistente = -1;


                        for (int i = 0; i < carrinhoAtual.getItens().size(); i++) {
                            ItemVenda item = carrinhoAtual.getItens().get(i);
                            if (item.getProduto().getCodigo() == codigo) {
                                qtdJaNoCarrinho = item.getQuantidade();
                                linhaExistente = i;
                                break;
                            }
                        }


                        if (qtd > 0 && (qtd + qtdJaNoCarrinho) <= p.getEstoque()) {

                            if (linhaExistente != -1) {

                                ItemVenda itemAtualizado = new ItemVenda(p, qtdJaNoCarrinho + qtd);
                                carrinhoAtual.getItens().set(linhaExistente, itemAtualizado);


                                modeloTabela.setValueAt(itemAtualizado.getQuantidade(), linhaExistente, 2);
                                modeloTabela.setValueAt(String.format("%.2f", itemAtualizado.getSubtotal()), linhaExistente, 3);
                            } else {

                                ItemVenda novoItem = new ItemVenda(p, qtd);
                                carrinhoAtual.adicionarItem(novoItem);

                                modeloTabela.addRow(new Object[]{
                                        p.getNome(),
                                        String.format("%.2f", p.getPreco()),
                                        qtd,
                                        String.format("%.2f", novoItem.getSubtotal())
                                });
                            }

                            atualizarTotalNaTela();
                            txtCodigo.setText("");
                            txtQuantidade.setText("");
                        } else {
                            JOptionPane.showMessageDialog(this,
                                    "Estoque insuficiente! Você já tem " + qtdJaNoCarrinho + " unidade(s) deste item no carrinho.",
                                    "Limite de Estoque",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    }
                }
                if (!encontrou) JOptionPane.showMessageDialog(this, "Produto não encontrado!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Digite números válidos!");
            }
        });


        btnRemover.addActionListener(e -> {
            int linhaSelecionada = tabelaCarrinho.getSelectedRow();

            if (linhaSelecionada != -1) {
                carrinhoAtual.getItens().remove(linhaSelecionada);
                modeloTabela.removeRow(linhaSelecionada);
                atualizarTotalNaTela();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Selecione um item na tabela primeiro para removê-lo.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        });


        btnVoltar.addActionListener(e -> dispose());


        btnFinalizar.addActionListener(e -> {
            if (carrinhoAtual.getItens().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O carrinho está vazio!");
                return;
            }

            double totalOriginal = carrinhoAtual.calcularTotal();
            double totalComDesconto = totalOriginal;

            if (totalOriginal >= 500) totalComDesconto *= 0.85;
            else if (totalOriginal >= 200) totalComDesconto *= 0.90;
            else if (totalOriginal >= 100) totalComDesconto *= 0.95;


            double valorDesconto = totalOriginal - totalComDesconto;

            String valorPagoStr = JOptionPane.showInputDialog(this,
                    "Total a pagar: R$ " + String.format("%.2f", totalComDesconto) + "Desconto de : R$ " + String.format("%.2f",valorDesconto) +"\nDigite o valor pago:");

            if (valorPagoStr != null && !valorPagoStr.isEmpty()) {
                try {
                    double valorPago = Double.parseDouble(valorPagoStr.replace(",", "."));

                    if (valorPago >= totalComDesconto) {

                        Caixa caixa = new Caixa();
                        double troco = caixa.calcularTroco(valorPago, totalComDesconto);

                        for (ItemVenda item : carrinhoAtual.getItens()) {
                            item.getProduto().baixarEstoque(item.getQuantidade());
                        }


                        String recibo = String.format(
                                "Venda Finalizada com Sucesso!\n\n" +
                                        "Subtotal: R$ %.2f\n" +
                                        "Desconto: R$ %.2f\n" +
                                        "Total: R$ %.2f\n" +
                                        "Pagamento: R$ %.2f\n" +
                                        "Troco: R$ %.2f",
                                totalOriginal, valorDesconto, totalComDesconto, valorPago, troco
                        );


                        JOptionPane.showMessageDialog(this, recibo, "Cupom Fiscal", JOptionPane.INFORMATION_MESSAGE);

                        br.com.logica.controller.VendaController.historicoVendas.add(carrinhoAtual);

                        carrinhoAtual = new Venda();
                        modeloTabela.setRowCount(0);
                        atualizarTotalNaTela();

                    } else {
                        JOptionPane.showMessageDialog(this, "Dinheiro insuficiente!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Valor inválido!");
                }
            }
        });


        add(painelTopo, BorderLayout.NORTH);
        add(scrollTabela, BorderLayout.CENTER);
        add(painelBaixo, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void atualizarTotalNaTela() {
        lblTotalFinal.setText("Total: R$ " + String.format("%.2f", carrinhoAtual.calcularTotal()));
    }
}