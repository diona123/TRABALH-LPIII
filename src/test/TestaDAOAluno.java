
package test;

import dao.DAOAluno;
import dao.DAOCurso;
import java.sql.SQLException;
import java.util.List;
import modelo.Aluno;
import modelo.Curso;
import util.ConectorBD;

public class TestaDAOAluno {

    public static void main(String[] args) {
        ConectorBD conexao = new ConectorBD();
        DAOAluno novoDAO = new DAOAluno(conexao.getConexao());
        
        try {
            Aluno novoAluno = novoDAO.consulta(55555);  
            System.out.println(novoAluno.getCodigo() + " - " + novoAluno.getNome());                   
            
            
            Aluno outroAluno = new Aluno(99, "Bale Aquatico", 2);
            novoDAO.remove(outroAluno);
            
            novoAluno.setNome("William");            
            novoDAO.atualiza(novoAluno);
            
            List<Aluno> todosAluno = novoDAO.lista();
            
            for(Aluno c : todosAluno){
                System.out.println(c.getCodigo() + " - " + c.getNome());
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
    }    
}
