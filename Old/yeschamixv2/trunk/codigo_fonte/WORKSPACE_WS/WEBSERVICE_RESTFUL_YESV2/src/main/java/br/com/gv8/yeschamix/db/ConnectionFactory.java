/**
 * 
 */
package br.com.gv8.yeschamix.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import br.com.gv8.yeschamix.util.Utilidades;

/**
 * @author Bruno Pelogia
 *
 */
public class ConnectionFactory {
	//caminho do banco
	private String DRIVER = "com.mysql.jdbc.Driver";
	private String URL = "jdbc:mysql://mysql.yesturbo.kinghost.net/yesturbo";
//	private String URL = "jdbc:mysql://localhost:3306/yesturbo";
	private String USUARIO = "yesturbo";
	private String SENHA = "y35turbo";
//	private USUARIO = "root";
//	private SENHA = "root";
	
	public Connection criarConexao(String login){
		Connection conexao  = null;
		
		HashMap< String, String > dadosConexao;
        try {
	        dadosConexao = Utilidades.lerPropriedadesBD( login );
        
		
		DRIVER = dadosConexao.get( "driver" );
		URL = dadosConexao.get( "url" );
		USUARIO = dadosConexao.get( "usuario" );
		SENHA = dadosConexao.get( "senha" );
		
		
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		
        } catch ( IOException e1 ) {
	        e1.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao(Connection conexao, PreparedStatement pstmt, ResultSet result){
		try {
			if(conexao != null){
				conexao.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(result != null){
				result.close();
			}
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
}
