package javacina;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Paciente {

	private String nome;
	private String cpf;
	private String sexo;
	private Date dataNascimento;
	private int codigo;
	private VacinaTipo vacina;

	private static List<ComorbidadesPaciente> comorbidades = new ArrayList<>();

	public void adicioneComorbidade(ComorbidadesPaciente comorbidade) {
		comorbidades.add(comorbidade);
	}
	
	public List<ComorbidadesPaciente> getComorbidades(){
		return comorbidades;
	}
	

	public int calculoIdade() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataNascimento);
		return Period.between(
				LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY)+1, cal.get(Calendar.DAY_OF_MONTH)),
				LocalDate.now()).getYears();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public VacinaTipo getVacina() {
		return vacina;
	}

	public void setVacina(VacinaTipo vacina) {
		this.vacina = vacina;
	}

}
