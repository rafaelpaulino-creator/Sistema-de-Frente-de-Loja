package br.com.logica.model;
import javax.swing.*;
import java.awt.*;

public class ConsultarProduto extends JFrame {
   public ConsultarProduto(){
       setTitle("Buscar Produto");
       setSize(400, 200);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setLayout(new FlowLayout());
       setLocationRelativeTo(null);

       JLabel lblCodigo = new JLabel("Digite o Codigo do Produto:");
       JTextField txtBusca = new JTextField(10);
       JButton btnBuscar = new JButton("Buscar");

       btnBuscar.addActionListener(e -> {
           try {
               int codigoBusca = Integer.parseInt(txtBusca.getText());
               boolean encontrou = false;


               for (Produto a : CadastroProduto.listaDeProdutos) {
                   if (a.getCodigo() == codigoBusca) {
                       JOptionPane.showMessageDialog(this,
                               "Produto Encontrado!\n" +
                                       "Produto: " + a.getNome() +
                                       "\nPreço: " + a.getPreco() +
                                       "\nEstoque: " + a.getEstoque());
                       encontrou = true;
                       break;
                   }
               }

               if (!encontrou) {
                   JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
               }

           } catch (NumberFormatException ex) {
               JOptionPane.showMessageDialog(this, "Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
           }
       });

       add(lblCodigo);
       add(txtBusca);
       add(btnBuscar);

       JButton btnVoltar = new JButton("Voltar");
       btnVoltar.addActionListener(e -> dispose());
       add(btnVoltar);

       setVisible(true);
   }
}
