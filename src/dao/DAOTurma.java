
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Aluno;
import modelo.Turma;


public class DAOTurma {
    
    
    private PreparedStatement comando = null;
    private Connection conexao = null;
    
    private static final String SQLLista = 
            "SELECT * FROM turma";
    private static final String SQLConsulta = 
            "SELECT anoturma, codcurso FROM turma WHERE codturma = ?";
    private static final String SQLAtualiza = 
            "UPDATE turma SET anoturma = ?, codcurso = ? WHERE codturma = ?";
    private static final String SQLRemove = 
            "DELETE FROM turma WHERE codturma = ?";
    private static final String SQLInsere = 
            "INSERT INTO turma (codturma, anoturma, codcurso) VALUES (?, ?, ?)"; 
    
    public DAOTurma(){        
    }
    
    public DAOTurma(Connection conexao){
        this.conexao = conexao;
    }
    
    public void setConnexao(Connection conexao){
        this.conexao = conexao;
    }
    
    public Turma consulta(int codturma) throws SQLException {
        Turma resultado = null;
        comando = conexao.prepareStatement(SQLConsulta);
        comando.setInt(1, codturma);
        ResultSet resultadoConsulta = comando.executeQuery();
        
        if(resultadoConsulta.next()){
            resultado = new Turma();
            resultado.setCodigo(codturma);
            resultado.setAno(resultadoConsulta.getInt("anoturma"));
            resultado.setCurso(resultadoConsulta.getInt("codcurso"));            
        }
        
        return resultado;
    }
    
    public void insere(Turma novoTurma) throws SQLException {
        comando = conexao.prepareStatement(SQLInsere);
        comando.setInt(1, novoTurma.getCodigo());
        comando.setInt(2, novoTurma.getAno()); 
        comando.setInt(3, novoTurma.getCurso()); 
        int linhas = comando.executeUpdate();        
    }
    
    public void atualiza(Turma novoTurma) throws SQLException {
        comando = conexao.prepareStatement(SQLAtualiza);
        comando.setInt(1, novoTurma.getAno());
        comando.setInt(2, novoTurma.getCurso());
        comando.setInt(3, novoTurma.getCodigo());
        comando.executeUpdate();
    }
    
    public void remove(Turma turmaExclusao) throws SQLException {
        comando = conexao.prepareStatement(SQLRemove);
        comando.setInt(1, turmaExclusao.getCodigo());
        comando.executeUpdate();
    }
    
    public List<Turma> lista() throws SQLException {
        ResultSet resultadoConsulta = conexao.createStatement().executeQuery(SQLLista);
        List<Turma> lista = new ArrayList();
        
        while(resultadoConsulta.next()){
            Turma novoTurma = new Turma();
            novoTurma.setCodigo(resultadoConsulta.getInt("codturma"));
            novoTurma.setAno(resultadoConsulta.getInt("anoturma"));
            novoTurma.setCurso(resultadoConsulta.getInt("codcurso"));            
            lista.add(novoTurma);
        }
        
        return lista;
    }
}
