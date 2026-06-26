import br.com.logica.model.CadastrarUsuario;
import br.com.logica.model.MenuUsuario;
import br.com.logica.view.TelaLogin;
import br.com.logica.model.MenuOperador;
import br.com.logica.view.TelaLoginU;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame janelaPrincipal = new JFrame("Sistema de Frente de Loja");
        janelaPrincipal.setSize(350, 400);
        janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setLayout(new BorderLayout());


        JLabel lblTitulo = new JLabel("Sistema de Frente de Loja", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 10, 15));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));


        JButton btnOperador = new JButton("Entrar como Administrador");
        btnOperador.setFont(new Font("Arial", Font.PLAIN, 14));
        btnOperador.addActionListener(e -> {
            TelaLogin dialogLogin = new TelaLogin(janelaPrincipal);
            dialogLogin.setVisible(true);


            if (dialogLogin.isLoginComSucesso()) {
                new MenuOperador().setVisible(true);
                JOptionPane.showMessageDialog(janelaPrincipal, "Bem-vindo, ADMINISTRADOR!");
            }
        });



        JButton btnEntrarUsuario = new JButton("Entrar como Usuário");
        btnEntrarUsuario.setFont(new Font("Arial", Font.PLAIN, 14));

        btnEntrarUsuario.addActionListener(e -> {
            TelaLoginU loginDialog = new TelaLoginU(janelaPrincipal);
            loginDialog.setVisible(true);

            if (loginDialog.isLoginComSucesso()) {

                new MenuUsuario().setVisible(true);
            }
        });

        JButton btnCadUsuario = new JButton("->Novo Usuario Cadastrar<-");
        btnCadUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        btnCadUsuario.addActionListener(e -> new CadastrarUsuario().setVisible(true));


        painelBotoes.add(btnOperador);
       painelBotoes.add(btnEntrarUsuario);
       painelBotoes.add(btnCadUsuario);


        janelaPrincipal.add(lblTitulo, BorderLayout.NORTH);
        janelaPrincipal.add(painelBotoes, BorderLayout.CENTER);


        janelaPrincipal.setVisible(true);
    }
}