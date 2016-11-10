package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.ConectorBD;

public class TestaCurso {
    
    public static void main(String[] args) {
        String consulta = "select nomecurso from curso";
        
        ConectorBD conexao = new ConectorBD();
        Statement comando = conexao.getDBCommand();
        
        try {
            ResultSet resultado = comando.executeQuery(consulta);
            
            while(resultado.next()){
                String nome = resultado.getString("nomecurso");
                System.out.println(nome);
            }
        
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
