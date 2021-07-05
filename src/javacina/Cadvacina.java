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

		// login e senha que ser� o cpf (Sem ponto nem espa�o)
		System.out.println("Informe seus dados.");
		System.out.print("Login: ");
		String login = sc.nextLine();
		// O cpf � a senha de acesso
		System.out.print("Senha: ");
		String senha = sc.nextLine();

		// System.out.println("Usu�rio logado com SUCESSO! ");

		System.out.println("****************************");

		// Menu de op��es do usu�rio para cadastrar pacientes.
		int opcao1;
		//Caso idade limite mude .
		//System.out.println("Informe a idade limite para vacina: ");
		//idadeLimite = sc.nextInt();
		
		do {
			System.out.println("ESCOLHA UMA OP��O: ");
			System.out.println(" '1' : CADASTRO DE PACIENTE");
			System.out.println(" '2' : EMISS�O DE RELAT�RIO");
			System.out.println(" '3' : SAIR");

			System.out.println("Informe a op��o:  ");
			opcao1 = sc.nextInt();

			switch (opcao1) {

			case 1:
				System.out.println("CADASTRO DE PACIENTE \n");
				cadastroPaciente();
				break;

			case 2:
				System.out.println("EMISS�O DE RELAT�RIO \n");
				imprimirRelatorio();
				break;

			case 3:
				break;
			default:
				System.err.println("OP��O INV�LIDA");

			}

		} while (opcao1 != 3);
		System.out.println("SAIR  \n");

		System.out.println("****************************");

		sc.close();
	}

	private static List<Paciente> pacientes = new ArrayList<>();

	public static void cadastroPaciente() throws ParseException { // marca m�todo como exce�a�

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

		// System.out.println("C�DIGO: ");
		// int codigo = sc.nextInt();

		Paciente p1 = new Paciente();
		p1.setNome(nome);
		p1.setCpf(cpf);
		p1.setSexo(sexo);
		p1.setDataNascimento(dataN);
		if (p1.calculoIdade() < idadeLimite) {
			System.err.println("PACIENTE N�O TEM IDADE PARA VACINA��O. ");
			return;

		}
		pacientes.add(p1);

		System.out.println("****************************");

		int opcao1;
		do {
			System.out.println("INFORME COMORBIDADE(S) : ");
			System.out.println(" '0' : SAIR");
			System.out.println(" '1' : DOEN�AS CARDIOVASCULARES");
			System.out.println(" '2' : DOEN�AS RESPIRATORIAS CR�NICAS");
			System.out.println(" '3' : DIABETES");
			System.out.println(" '4' : C�NCER");
			System.out.println(" '5' : DOEN�A RENAL");
			System.out.println(" '6' : TRANSPLANTADOS ORG�OS S�LIDOS");
			System.out.println(" '7' : ANEMIA FALCIFORME");
			System.out.println(" '8' : OBESIDADE");
			System.out.println("Informe as op��es:  ");
			opcao1 = sc.nextInt();

			switch (opcao1) {

			case 0:
				System.out.println(" SAIR");
				break;

			case 1:
				System.out.println("DOEN�AS CARDIOVASCULARES");
				p1.adicioneComorbidade(ComorbidadesPaciente.DOENCAS_CARDIOVASCULARES);
				break;

			case 2:
				System.out.println("DOEN�AS RESPIRATORIAS CR�NICAS");
				p1.adicioneComorbidade(ComorbidadesPaciente.DOENCAS_RESPIRATORIAS_CRONICAS);
				break;

			case 3:
				System.out.println("DIABETES");
				p1.adicioneComorbidade(ComorbidadesPaciente.DIABETES);
				break;

			case 4:
				System.out.println("C�NCER");
				p1.adicioneComorbidade(ComorbidadesPaciente.CANCER);
				break;

			case 5:
				System.out.println("DOEN�A RENAL");
				p1.adicioneComorbidade(ComorbidadesPaciente.DOENCA_RENAL);
				break;

			case 6:
				System.out.println("TRANSPLANTADOS ORG�OS S�LIDOS");
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
				System.err.println("OP��O INV�LIDA");
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
				System.err.println("OP��O INV�LIDA");
			}

		} while (opcaoV != 1 && opcaoV != 2);

		System.out.println("****************************");

	}

	private static void imprimirRelatorio() {

		System.out.println("RELAT�RIO DE PACIENTE: ");

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
