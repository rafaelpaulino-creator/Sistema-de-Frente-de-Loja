package br.com.logica.model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CadastrarUsuario extends JFrame {
    public static int qtdUsuario = 0;
    public static List<Usuario> listaUsuario = new ArrayList<>();

    public CadastrarUsuario() {
        setTitle("Cadastro de Usuario");
        setLayout(new FlowLayout());
        setSize(260, 430);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblNome = new JLabel("Nome: ");
        JTextField txtNome = new JTextField(20);

        JLabel lblTelefone = new JLabel("Telefone: ");
        JTextField txtTelefone = new JTextField(20);

        JLabel lblEmail = new JLabel("Email: ");
        JTextField txtEmail = new JTextField(20);

        JLabel lblSenha = new JLabel("Senha: ");
        JPasswordField txtSenha = new JPasswordField(20);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String telefone = txtTelefone.getText();
            String email = txtEmail.getText();
            String senha = new String(txtSenha.getPassword());

            qtdUsuario++;
            int registro = qtdUsuario;


            Usuario novoUsuario = new Usuario(registro, nome, telefone, email, senha);
            listaUsuario.add(novoUsuario);

            JOptionPane.showMessageDialog(null, "Usuario Cadastrado com sucesso!\nNome: " + nome);


            txtNome.setText("");
            txtTelefone.setText("");
            txtEmail.setText("");
            txtSenha.setText("");
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e3 -> dispose());

        add(lblNome);
        add(txtNome);
        add(lblTelefone);
        add(txtTelefone);
        add(lblEmail);
        add(txtEmail);
        add(lblSenha);
        add(txtSenha);

        add(btnSalvar);
        add(btnVoltar);
        setVisible(true);
    }
}