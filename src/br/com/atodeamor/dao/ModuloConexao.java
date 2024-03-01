
package br.com.atodeamor.dao;
import java.sql.*;

public class ModuloConexao {
    //metodo de conexão com o BD
    public static Connection conector(){
        java.sql.Connection conexao = null;
        //linha abaixo vai chamar o driver importada da biblioteca
        String driver = "com.mysql.jdbc.Driver";
        //Armazenando info. referentes ao BD
        String url="jdbc:mysql://localhost:3306/rs_sistemas";
        String user="root";
        String password = "ramon";
        // estabelecendo a conexão com o BD
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
            
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
