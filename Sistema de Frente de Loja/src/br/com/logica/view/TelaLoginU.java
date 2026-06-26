package br.com.logica.view;
import br.com.logica.model.CadastrarUsuario;
import br.com.logica.model.Usuario;
import javax.swing.*;
import java.awt.*;

public class TelaLoginU extends JDialog {

    private boolean loginComSucesso = false;

    public TelaLoginU(JFrame parent) {
        super(parent, "Login do Usuário", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Nome:", SwingConstants.RIGHT);
        JTextField txtUsuario = new JTextField();

        JLabel lblSenha = new JLabel("Senha:", SwingConstants.RIGHT);
        JPasswordField txtSenha = new JPasswordField();

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCancelar = new JButton("Cancelar");

        btnEntrar.addActionListener(e -> {
            String nomeDigitado = txtUsuario.getText();
            String senhaDigitada = new String(txtSenha.getPassword());
            boolean encontrou = false;


            for (Usuario u : CadastrarUsuario.listaUsuario) {

                if (u.getNome().equalsIgnoreCase(nomeDigitado) && u.getSenha().equals(senhaDigitada)) {
                    encontrou = true;
                    break;
                }
            }

            if (encontrou) {
                loginComSucesso = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Nome ou senha incorretos! Verifique se você já possui cadastro.",
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