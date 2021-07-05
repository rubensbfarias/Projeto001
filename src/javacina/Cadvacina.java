package javacina;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Cadvacina {
	private static int idadeLimite = 70;
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws ParseException {

		// login e senha que será o cpf (Sem ponto nem espaço)
		System.out.println("Informe seus dados.");
		System.out.print("Login: ");
		String login = sc.nextLine();
		// O cpf é a senha de acesso
		System.out.print("Senha: ");
		String senha = sc.nextLine();

		// System.out.println("Usuário logado com SUCESSO! ");

		System.out.println("****************************");

		// Menu de opções do usuário para cadastrar pacientes.
		int opcao1;
		//Caso idade limite mude .
		//System.out.println("Informe a idade limite para vacina: ");
		//idadeLimite = sc.nextInt();
		
		do {
			System.out.println("ESCOLHA UMA OPÇÃO: ");
			System.out.println(" '1' : CADASTRO DE PACIENTE");
			System.out.println(" '2' : EMISSÃO DE RELATÓRIO");
			System.out.println(" '3' : SAIR");

			System.out.println("Informe a opção:  ");
			opcao1 = sc.nextInt();

			switch (opcao1) {

			case 1:
				System.out.println("CADASTRO DE PACIENTE \n");
				cadastroPaciente();
				break;

			case 2:
				System.out.println("EMISSÃO DE RELATÓRIO \n");
				imprimirRelatorio();
				break;

			case 3:
				break;
			default:
				System.err.println("OPÇÃO INVÁLIDA");

			}

		} while (opcao1 != 3);
		System.out.println("SAIR  \n");

		System.out.println("****************************");

		sc.close();
	}

	private static List<Paciente> pacientes = new ArrayList<>();

	public static void cadastroPaciente() throws ParseException { // marca método como exceçaõ

		System.out.println("Informe NOME do paciente: ");
		String nome = sc.next();

		System.out.println("Informe CPF do paciente: ");
		String cpf = sc.next();

		System.out.println("Informe SEXO do paciente: ");
		String sexo = sc.next();

		System.out.println("Informe DATA DE NASCIMENTO- dd/mm/aaaa: ");
		String dataNascimento = sc.next();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		Date dataN = formatador.parse(dataNascimento); // Ler e transformar em date

		// System.out.println("CÓDIGO: ");
		// int codigo = sc.nextInt();

		Paciente p1 = new Paciente();
		p1.setNome(nome);
		p1.setCpf(cpf);
		p1.setSexo(sexo);
		p1.setDataNascimento(dataN);
		if (p1.calculoIdade() < idadeLimite) {
			System.err.println("PACIENTE NÃO TEM IDADE PARA VACINAÇÃO. ");
			return;

		}
		pacientes.add(p1);

		System.out.println("****************************");

		int opcao1;
		do {
			System.out.println("INFORME COMORBIDADE(S) : ");
			System.out.println(" '0' : SAIR");
			System.out.println(" '1' : DOENÇAS CARDIOVASCULARES");
			System.out.println(" '2' : DOENÇAS RESPIRATORIAS CRÔNICAS");
			System.out.println(" '3' : DIABETES");
			System.out.println(" '4' : CÂNCER");
			System.out.println(" '5' : DOENÇA RENAL");
			System.out.println(" '6' : TRANSPLANTADOS ORGÃOS SÓLIDOS");
			System.out.println(" '7' : ANEMIA FALCIFORME");
			System.out.println(" '8' : OBESIDADE");
			System.out.println("Informe as opções:  ");
			opcao1 = sc.nextInt();

			switch (opcao1) {

			case 0:
				System.out.println(" SAIR");
				break;

			case 1:
				System.out.println("DOENÇAS CARDIOVASCULARES");
				p1.adicioneComorbidade(ComorbidadesPaciente.DOENCAS_CARDIOVASCULARES);
				break;

			case 2:
				System.out.println("DOENÇAS RESPIRATORIAS CRÔNICAS");
				p1.adicioneComorbidade(ComorbidadesPaciente.DOENCAS_RESPIRATORIAS_CRONICAS);
				break;

			case 3:
				System.out.println("DIABETES");
				p1.adicioneComorbidade(ComorbidadesPaciente.DIABETES);
				break;

			case 4:
				System.out.println("CÂNCER");
				p1.adicioneComorbidade(ComorbidadesPaciente.CANCER);
				break;

			case 5:
				System.out.println("DOENÇA RENAL");
				p1.adicioneComorbidade(ComorbidadesPaciente.DOENCA_RENAL);
				break;

			case 6:
				System.out.println("TRANSPLANTADOS ORGÃOS SÓLIDOS");
				p1.adicioneComorbidade(ComorbidadesPaciente.TRANSPLANTADOS_ORGAOS_SOLIDOS);
				break;

			case 7:
				System.out.println(" ANEMIA FALCIFORME");
				p1.adicioneComorbidade(ComorbidadesPaciente.ANEMIA_FALCIFORME);
				break;

			case 8:
				System.out.println(" OBESIDADE ");
				p1.adicioneComorbidade(ComorbidadesPaciente.ANEMIA_FALCIFORME);
				break;
			default:
				System.err.println("OPÇÃO INVÁLIDA");
			}

		} while (opcao1 != 0);

		System.out.println("****************************");

		int opcaoV;
		System.out.println("INFORME O NOME DA VACINA: ");
		System.out.println(" '1' : ASTRAZENICA");
		System.out.println(" '2' : CORONAVAC");

		do {
			System.out.println("Nome da vacina:  ");
			opcaoV = sc.nextInt();

			switch (opcaoV) {

			case 1:
				System.out.println("ASTRAZENICA");
				break;
			case 2:
				System.out.println("CORONAVAC");
				break;
			default:
				System.err.println("OPÇÃO INVÁLIDA");
			}

		} while (opcaoV != 1 && opcaoV != 2);

		System.out.println("****************************");

	}

	private static void imprimirRelatorio() {

		System.out.println("RELATÓRIO DE PACIENTE: ");

		for (Paciente p1 : pacientes) {
			System.out.printf(" NOME: ");
			System.out.println(p1.getNome());
			System.out.printf(" CPF: ");
			System.out.println(p1.getCpf());
			System.out.printf(" IDADE: ");
			System.out.println(p1.calculoIdade());
			System.out.printf("COMORBIDADES:");
			System.out.println(p1.getComorbidades());

		}
		
		System.out.println("****************************");


	}

}
