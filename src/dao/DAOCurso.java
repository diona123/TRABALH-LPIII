package dao;

import modelo.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOCurso {
    
    private PreparedStatement comando = null;
    private Connection conexao = null;
    
    private static final String SQLLista = 
            "SELECT * FROM curso";
    private static final String SQLConsulta = 
            "SELECT nomecurso FROM curso WHERE codcurso = ?";
    private static final String SQLAtualiza = 
            "UPDATE curso SET nomecurso = ? WHERE codcurso = ?";
    private static final String SQLRemove = 
            "DELETE FROM curso WHERE codcurso = ?";
    private static final String SQLInsere = 
            "INSERT INTO curso (codcurso, nomecurso) VALUES (?, ?)"; 
    
    public DAOCurso(){        
    }
    
    public DAOCurso(Connection conexao){
        this.conexao = conexao;
    }
    
    public void setConnexao(Connection conexao){
        this.conexao = conexao;
    }
    
    public Curso consulta(int codcurso) throws SQLException {
        Curso resultado = null;
        comando = conexao.prepareStatement(SQLConsulta);
        comando.setInt(1, codcurso);
        ResultSet resultadoConsulta = comando.executeQuery();
        
        if(resultadoConsulta.next()){
            resultado = new Curso();
            String nome = resultadoConsulta.getString("nomecurso");
            resultado.setNome(nome);
            resultado.setCodigo(codcurso);
        }
        
        return resultado;
    }
    
    public void insere(Curso novoCurso) throws SQLException {
        comando = conexao.prepareStatement(SQLInsere);
        comando.setInt(1, novoCurso.getCodigo());
        comando.setString(2, novoCurso.getNome());        
        int linhas = comando.executeUpdate();        
    }
    
    
    public void atualiza(Curso novoCurso) throws SQLException {
        comando = conexao.prepareStatement(SQLAtualiza);
        comando.setString(1, novoCurso.getNome());
        comando.setInt(2, novoCurso.getCodigo());
        comando.executeUpdate();
    }
    
    public void remove(Curso cursoExclusao) throws SQLException {
        comando = conexao.prepareStatement(SQLRemove);
        comando.setInt(1, cursoExclusao.getCodigo());
        comando.executeUpdate();
    }
    
    public List<Curso> lista() throws SQLException {
        ResultSet resultadoConsulta = conexao.createStatement().executeQuery(SQLLista);
        List<Curso> lista = new ArrayList();
        
        while(resultadoConsulta.next()){
            Curso novoCurso = new Curso();
            novoCurso.setCodigo(resultadoConsulta.getInt("codcurso"));
            novoCurso.setNome(resultadoConsulta.getString("nomecurso"));
            lista.add(novoCurso);
        }
        
        return lista;
    }
}
