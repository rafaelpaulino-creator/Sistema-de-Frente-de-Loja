package br.com.logica.view;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JDialog {

    private boolean loginComSucesso = false;


    public TelaLogin(JFrame parent) {
        super(parent, "Login do Operador", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2, 10, 10));


        JLabel lblUsuario = new JLabel("Usuário:", SwingConstants.RIGHT);
        JTextField txtUsuario = new JTextField();

        JLabel lblSenha = new JLabel("Senha:", SwingConstants.RIGHT);
        JPasswordField txtSenha = new JPasswordField();

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCancelar = new JButton("Cancelar");


        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();

            String senha = new String(txtSenha.getPassword());


            if (usuario.equals("adm") && senha.equals("123")) {
                loginComSucesso = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Usuário ou senha incorretos!",
                        "Erro de Login",
                        JOptionPane.ERROR_MESSAGE);
                txtSenha.setText("");
            }
        });


        btnCancelar.addActionListener(e -> {
            loginComSucesso = false;
            dispose();
        });

        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        add(lblUsuario);
        add(txtUsuario);
        add(lblSenha);
        add(txtSenha);
        add(btnEntrar);
        add(btnCancelar);
    }


    public boolean isLoginComSucesso() {
        return loginComSucesso;
    }
}