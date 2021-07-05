package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import conexao.ModuloConexao;
import javacina.Paciente;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CadastroPaciente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpf;
	private JTextField txtNome;
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	static CadastroPaciente frame = new CadastroPaciente();
	private JTextField txtComborb;
	JComboBox<String> cbmVacina = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
	public CadastroPaciente() {
		conexao = ModuloConexao.conector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(145, 46, 176, 20);
		contentPane.add(txtCpf);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(52, 49, 39, 14);
		contentPane.add(lblCpf);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(54, 83, 46, 14);
		contentPane.add(label_1);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(145, 77, 176, 20);
		contentPane.add(txtNome);
		
		JLabel lblDataNascimento = new JLabel("Data nascimento:");
		lblDataNascimento.setBounds(13, 108, 104, 14);
		contentPane.add(lblDataNascimento);
		
		JFormattedTextField txtDataNascimento = new JFormattedTextField((Format) null);
		txtDataNascimento.setText("10/06/2021");
		txtDataNascimento.setColumns(10);
		txtDataNascimento.setBounds(145, 108, 176, 20);
		contentPane.add(txtDataNascimento);
		
		
		
		JButton button_1 = new JButton("Enviar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Paciente p1 = new Paciente();
				p1.setNome(txtNome.getText());
				p1.setCpf(lblCpf.getText());
				
				String dataNascimento = txtDataNascimento.getText();
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
				Date dataN;
				try {
					dataN = formatador.parse(dataNascimento);
					p1.setDataNascimento(dataN);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				

				if (p1.calculoIdade() < 70) {
					JOptionPane.showMessageDialog(null,"PACIENTE NÃO TEM IDADE PARA VACINAÇÃO. ");
					return;

				}else {
					
					String sql = "insert into javacina.paciente(nome,cpf,datanascimento,comorbidade,vacina) values(?,?,?,?,?)";

					try {
						pst = conexao.prepareStatement(sql);
						pst.setString(1, txtNome.getText());
						pst.setString(2, txtCpf.getText());
						pst.setString(3, txtDataNascimento.getText());	
						pst.setString(4, txtComborb.getText());	
						if(cbmVacina.getSelectedIndex()==0) {
							pst.setString(5, "ASTRAZENICA");	
						}else {
							pst.setString(5, "CORONAVAC");
						}
						

						if (txtNome.getText().isEmpty() || txtCpf.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
						} else {
							int adicionado = pst.executeUpdate();
							if (adicionado > 0) {
								frame.setVisible(false);
								JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
								TelaPrincipal principal = new TelaPrincipal();
								principal.setVisible(true);
								//TelaPrincipal.lblUsuario.setText("Bem vindo ");
								principal.setLocationRelativeTo(null);
								
								
								
							}

						}

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
					
					
				}
			}
		});
		button_1.setBounds(183, 266, 89, 23);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("Comorbidades:");
		lblNewLabel.setBounds(24, 152, 81, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnCardio = new JButton("Cardiovascular");
		btnCardio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComborb.setText(txtComborb.getText() + ";" + "Cardiovascular");
				btnCardio.setEnabled(false);
			}
		});
		btnCardio.setBounds(490, 77, 167, 23);
		contentPane.add(btnCardio);
		
		JLabel lblCliqueAquiPara = new JLabel("Clique nas comorbidades que o paciente possui");
		lblCliqueAquiPara.setBounds(449, 49, 244, 14);
		contentPane.add(lblCliqueAquiPara);
		
		JButton btnRespiratoria = new JButton("Respirat\u00F3ria");
		btnRespiratoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComborb.setText(txtComborb.getText() + ";" + "Respiratória");
				btnRespiratoria.setEnabled(false);
			}
		});
		btnRespiratoria.setBounds(490, 114, 167, 23);
		contentPane.add(btnRespiratoria);
		
		JButton btnDiabete = new JButton("Diabetes");
		btnDiabete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComborb.setText(txtComborb.getText() + ";" + "Diabetes");
				btnDiabete.setEnabled(false);
			}
		});
		btnDiabete.setBounds(490, 148, 167, 23);
		contentPane.add(btnDiabete);
		
		JButton btnCancer = new JButton("Cancer");
		btnCancer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComborb.setText(txtComborb.getText() + ";" + "Cancer");
				btnCancer.setEnabled(false);
			}
		});
		btnCancer.setBounds(490, 182, 167, 23);
		contentPane.add(btnCancer);
		
		JButton btnRenal = new JButton("Renal");
		btnRenal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComborb.setText(txtComborb.getText() + ";" + "Renal");
				btnRenal.setEnabled(false);
			}
		});
		btnRenal.setBounds(490, 220, 167, 23);
		contentPane.add(btnRenal);
		
		JButton btnOrgao = new JButton("Transplantados org\u00E3os");
		btnOrgao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComborb.setText(txtComborb.getText() + ";" + "OrgãoSolidos");
				btnOrgao.setEnabled(false);
			}
		});
		btnOrgao.setBounds(490, 254, 167, 23);
		contentPane.add(btnOrgao);
		
		JButton btnAnemiaFalciforme = new JButton("Anemia falciforme");
		btnAnemiaFalciforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComborb.setText(txtComborb.getText() + ";" + "Anemia");
				btnAnemiaFalciforme.setEnabled(false);
			}
		});
		btnAnemiaFalciforme.setBounds(490, 288, 167, 23);
		contentPane.add(btnAnemiaFalciforme);
		
		JButton btnObesidade = new JButton("Obesidade");
		btnObesidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtComborb.setText(txtComborb.getText() + ";" + "Obesidade");
				btnObesidade.setEnabled(false);
			}
		});
		btnObesidade.setBounds(490, 322, 167, 23);
		contentPane.add(btnObesidade);
		
		txtComborb = new JTextField();
		txtComborb.setBounds(101, 149, 347, 20);
		contentPane.add(txtComborb);
		txtComborb.setColumns(50);
		
		JButton button = new JButton("Apagar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtCpf.setText("");
				txtNome.setText("");
				txtComborb.setText("");
				btnAnemiaFalciforme.setEnabled(true);
				btnCancer.setEnabled(true);
				btnCardio.setEnabled(true);
				btnDiabete.setEnabled(true);
				btnObesidade.setEnabled(true);
				btnOrgao.setEnabled(true);
				btnRenal.setEnabled(true);
				btnRespiratoria.setEnabled(true);
				
				return;
			}
		});
		button.setBounds(59, 266, 114, 23);
		contentPane.add(button);
		
		
		cbmVacina.setModel(new DefaultComboBoxModel<String>(new String[] {"ASTRAZENICA", "CORONAVAC"}));
		cbmVacina.setBounds(101, 201, 141, 20);
		contentPane.add(cbmVacina);
		
		
		JLabel lblVacina = new JLabel("Vacina:");
		lblVacina.setBounds(13, 204, 81, 14);
		contentPane.add(lblVacina);
	}
}
