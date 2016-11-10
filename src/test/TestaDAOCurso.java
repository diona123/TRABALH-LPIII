
package test;

import dao.DAOCurso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import modelo.Curso;
import util.ConectorBD;


public class TestaDAOCurso {

    public static void main(String[] args) {        
        ConectorBD conexao = new ConectorBD();
        DAOCurso novoDAO = new DAOCurso(conexao.getConexao());
        
        try {
            Curso novoCurso = novoDAO.consulta(38);            
            System.out.println(novoCurso.getNome());
            
            Curso outroCurso = new Curso(99, "Bale Aquatico");
            novoDAO.remove(outroCurso);
            
            novoCurso.setNome("Ciências da Computação");
            novoDAO.atualiza(novoCurso);
            
            List<Curso> todosCursos = novoDAO.lista();
            
            for(Curso c : todosCursos){
                System.out.println(c.getCodigo() + " - " + c.getNome());
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
