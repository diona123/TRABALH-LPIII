package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Aluno;

public class DAOAluno {    
    
    private PreparedStatement comando = null;
    private Connection conexao = null;
    
    private static final String SQLLista = 
            "SELECT * FROM aluno";
    private static final String SQLConsulta = 
            "SELECT nomealuno, codturma FROM aluno WHERE codaluno = ?";
    private static final String SQLConsultaPorNome = 
            "SELECT * FROM aluno WHERE upper(nomealuno) like upper(?)";
    private static final String SQLAtualiza =
            "UPDATE aluno SET nomealuno = ?, codturma = ? WHERE codaluno = ?";
    private static final String SQLRemove = 
            "DELETE FROM aluno WHERE codaluno = ?";
    private static final String SQLInsere = 
            "INSERT INTO aluno (codaluno, nomealuno, codturma) VALUES (?, ?, ?)"; 
    
    public DAOAluno(){        
    }
    
    public DAOAluno(Connection conexao){
        this.conexao = conexao;
    }
    
    public void setConnexao(Connection conexao){
        this.conexao = conexao;
    }
    
    public List<Aluno> consultaPorNome(String nomeAluno) throws SQLException{
        
        List<Aluno> alunos = new ArrayList<>();
        comando = conexao.prepareStatement(SQLConsultaPorNome);
        comando.setString(1, "%" + nomeAluno + "%");
        ResultSet resultadoConsulta = comando.executeQuery();
        
        while(resultadoConsulta.next()){
            Aluno aluno = new Aluno();
            aluno.setCodigo(resultadoConsulta.getInt("codaluno"));
            aluno.setNome(resultadoConsulta.getString("nomealuno"));
            aluno.setTurma(resultadoConsulta.getInt("codturma"));
            alunos.add(aluno);
        }
        
        return alunos;
    }
    
    public Aluno consulta(int codaluno) throws SQLException {
        Aluno resultado = null;
        comando = conexao.prepareStatement(SQLConsulta);
        comando.setInt(1, codaluno);
        ResultSet resultadoConsulta = comando.executeQuery();
        
        if(resultadoConsulta.next()){
            resultado = new Aluno();
            resultado.setCodigo(codaluno);
            resultado.setNome(resultadoConsulta.getString("nomealuno"));
            resultado.setTurma(resultadoConsulta.getInt("codturma"));            
        }
        
        return resultado;
    }
    
    public void insere(Aluno novoAluno) throws SQLException {
        comando = conexao.prepareStatement(SQLInsere);
        comando.setInt(1, novoAluno.getCodigo());
        comando.setString(2, novoAluno.getNome()); 
        comando.setInt(3, novoAluno.getTurma()); 
        int linhas = comando.executeUpdate();        
    }
    
    public void atualiza(Aluno novoAluno) throws SQLException {
        comando = conexao.prepareStatement(SQLAtualiza);
        comando.setString(1, novoAluno.getNome());
        comando.setInt(2, novoAluno.getTurma());
        comando.setInt(3, novoAluno.getCodigo());
        comando.executeUpdate();
    }
    
    public void remove(Aluno alunoExclusao) throws SQLException {
        comando = conexao.prepareStatement(SQLRemove);
        comando.setInt(1, alunoExclusao.getCodigo());
        comando.executeUpdate();
    }
    
    public List<Aluno> lista() throws SQLException {
        ResultSet resultadoConsulta = conexao.createStatement().executeQuery(SQLLista);
        List<Aluno> lista = new ArrayList();
        
        while(resultadoConsulta.next()){
            Aluno novoCurso = new Aluno();
            novoCurso.setCodigo(resultadoConsulta.getInt("codaluno"));
            novoCurso.setNome(resultadoConsulta.getString("nomealuno"));
            novoCurso.setTurma(resultadoConsulta.getInt("codturma"));            
            lista.add(novoCurso);
        }
        
        return lista;
    }
}
