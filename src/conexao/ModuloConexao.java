package conexao;

	import java.sql.*;

	public class ModuloConexao {
	    //criando um m�todo respons�vel por estabelecer
	    //uma conex�o com o banco

	    public static Connection conector() {
	        //criando uma vari�vel especial para 
	        //estabelecer uma conex�o com o banco
	        java.sql.Connection conexao = null;
	        //carregando o driver correspondente
	        //ao banco (n�o esque�a de importar ele
	        // em libraries 
	        String driver = "com.mysql.cj.jdbc.Driver";
	        //armazenando informa��es referente ao 
	        //banco de dados
	        String url = "jdbc:mysql://localhost:3306/javacina";
	        String user = "admin";
	        String password = "admin";
	        //estabelecendo a conex�o com o banco
	        try {
	            Class.forName(driver);
	            conexao = DriverManager.getConnection(url, user, password);
	            System.out.println("Conectou");
	            return conexao;

	        } catch (Exception e) {
	            //a linha abaixo serve de apoio para esclarecer o erro
	            System.out.println("Erro: " + e);
	            return null;
	        }

	    }
	
}
