package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.ConectorBD;

public class TestaAluno {
    
    public static void main(String[] args) {
        String consulta = "select nomealuno from aluno";
        
        ConectorBD conexao = new ConectorBD();
        Statement comando = conexao.getDBCommand();
        
        try {
            ResultSet resultado = comando.executeQuery(consulta);
            
            while(resultado.next()){
                String nome = resultado.getString("nomealuno");
                System.out.println(nome);
            }
        
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
