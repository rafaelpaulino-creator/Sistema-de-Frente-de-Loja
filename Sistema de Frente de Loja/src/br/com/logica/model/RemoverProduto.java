package br.com.logica.model;
import javax.swing.*;
import java.awt.*;

public class RemoverProduto extends JFrame {
    public RemoverProduto(){
        setTitle("Remover Produto");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel lblCodigo = new JLabel("Digite o codigo do Produto:");
        JTextField txtBusca = new JTextField(10);
        JButton btnRemover = new JButton("Remover");
        JButton btnVoltar = new JButton("Voltar");


        btnRemover.addActionListener(e -> {
            try {
                int codigoBuscado = Integer.parseInt(txtBusca.getText());
                Produto ProdutoRemover = null;


                for (Produto a : CadastroProduto.listaDeProdutos) {
                    if (a.getCodigo() == codigoBuscado) {
                        ProdutoRemover = a;
                        break;
                    }
                }


                if (ProdutoRemover != null) {


                    int resposta = JOptionPane.showConfirmDialog(this,
                            "Tem certeza que deseja remover esse Produto: " + ProdutoRemover.getNome() + "?",
                            "Confirmar Remoção",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);


                    if (resposta == JOptionPane.YES_OPTION) {


                        CadastroProduto.listaDeProdutos.remove(ProdutoRemover);


                        JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
                        txtBusca.setText("");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número de codigo válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnVoltar.addActionListener(e -> dispose());


        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBusca.add(lblCodigo);
        painelBusca.add(txtBusca);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnVoltar);


        add(painelBusca);
        add(painelBotoes);

        setVisible(true);
    }
}
