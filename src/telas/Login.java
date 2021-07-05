package telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import conexao.ModuloConexao;


public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordUser;
	private JLabel lblNewLabel;
	JProgressBar barraProgresso = new JProgressBar();
	private JLabel lblStatusCarrega;
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private static Login frame = new Login();
	private JSeparator separator_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void logar() {

		// logica principal para pesquisar no banco de dados
		String sql = "select * from javacina.usuario where login = ? and senha = ?";
		try {
//as linhas abaixo preparam a consulta em função do que foi 
//digitado nas caixas de texto. O ? é substituído pelo conteúdo
//das variáveis que são armazenadas em pst.setString
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtUsuario.getText());
			pst.setString(2, passwordUser.getText());
			// a linha abaixo executa a query(consulta)
			rs = pst.executeQuery();
			// se existir um usuário e senha correspondente
			if (rs.next()) {

				TelaPrincipal principal = new TelaPrincipal();
				principal.setSize(500, 500);
				principal.setVisible(true);
				//TelaPrincipal.lblUsuario.setText("Bem vindo ");
				principal.setLocationRelativeTo(null);
				frame.setVisible(false);

			} else {
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}


	public Login() {
		conexao = ModuloConexao.conector();
		setResizable(false);
		setTitle("Login - Sistema");
		//setIconImage(
				//.getDefaultToolkit().getImage(Login.class.getResource("/icones/vacina.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 245);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlightText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));

		txtUsuario = new JTextField();
		txtUsuario.setText("joao");
		txtUsuario.setColumns(10);
		passwordUser = new JPasswordField();
		passwordUser.setText("123");
		passwordUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					logar();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair do login?", "Atenção",
							JOptionPane.YES_NO_OPTION);
					if (sair == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				}
			}
		});

		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/icones/vacina.png")));

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnConectar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		btnConectar.setBackground(SystemColor.window);
		btnConectar.setIcon(new ImageIcon(Login.class.getResource("/icones/icon conect login.png")));

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		btnCancelar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 11));
		btnCancelar.setBackground(SystemColor.window);
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/icones/icone x login.png")));
		barraProgresso.setVisible(false);
		barraProgresso.setForeground(Color.BLUE);
		barraProgresso.setBackground(SystemColor.window);

		lblStatusCarrega = new JLabel("");
		lblStatusCarrega.setForeground(Color.BLUE);
		lblStatusCarrega.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));

		new JSeparator();

		separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.controlHighlight);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE).addGap(10)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblUsurio).addGap(4)
								.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(6).addComponent(lblSenha).addGap(4)
								.addComponent(passwordUser, GroupLayout.PREFERRED_SIZE, 146,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addGap(29).addComponent(btnConectar)))
				.addGap(47))
				.addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap(229, Short.MAX_VALUE)
								.addComponent(barraProgresso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(93))
				.addGroup(Alignment.TRAILING,
						gl_contentPane.createSequentialGroup().addContainerGap(285, Short.MAX_VALUE)
								.addComponent(lblStatusCarrega).addGap(145))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(2)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
						.createSequentialGroup().addGap(34)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(2).addComponent(lblUsurio))
								.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup().addGap(2).addComponent(lblSenha))
								.addComponent(passwordUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(29)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(btnCancelar)
								.addComponent(btnConectar)))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(24).addComponent(lblNewLabel)))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(barraProgresso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(lblStatusCarrega, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)));
		contentPane.setLayout(gl_contentPane);
	}
}