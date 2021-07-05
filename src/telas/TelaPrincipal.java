package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastroPaciente = new JButton("Cadastro Paciente");
		btnCadastroPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroPaciente principal = new CadastroPaciente();
				principal.setVisible(true);
				//TelaPrincipal.lblUsuario.setText("Bem vindo ");
				principal.setLocationRelativeTo(null);
				//frame.setVisible(false);
				
			}
		});
		btnCadastroPaciente.setBounds(37, 56, 166, 23);
		contentPane.add(btnCadastroPaciente);
		
		JButton btnRelatorioPaciente = new JButton("Relat\u00F3rio paciente");
		btnRelatorioPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelatorioPaciente principal = new RelatorioPaciente();
				principal.setSize(800, 800);
				principal.setVisible(true);
				//TelaPrincipal.lblUsuario.setText("Bem vindo ");
				principal.setLocationRelativeTo(null);
				//frame.setVisible(false);
			}
		});
		btnRelatorioPaciente.setBounds(245, 56, 188, 23);
		contentPane.add(btnRelatorioPaciente);
	}
}
