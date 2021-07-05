package telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import conexao.ModuloConexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RelatorioPaciente extends JFrame {
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable tblCarros;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RelatorioPaciente frame = new RelatorioPaciente();
					frame.setSize(800, 400);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public void preenche() {
		String sql = "SELECT * FROM javacina.paciente;";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			tblCarros.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Atenção", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Create the frame.
	 */
	public RelatorioPaciente() {
		conexao = ModuloConexao.conector();
		setType(Type.POPUP);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 26, 474, 222);
		getContentPane().add(scrollPane);

		tblCarros = new JTable();
		scrollPane.setViewportView(tblCarros);
		// baseTabela.add(tblCarros);
		tblCarros.setModel(new DefaultTableModel(new Object[][] { { null, "", "", null, null }, },
				new String[] { "idpaciente", "Nome", "Cpf", "dataNascimento" }));
		tblCarros.setEnabled(false);
		tblCarros.setColumnSelectionAllowed(true);
		
		preenche();
	}
}
