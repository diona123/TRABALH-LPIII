package gui;

import dao.DAOCurso;
import dao.DAOTurma;
import modelo.Curso;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Aluno;
import modelo.Turma;
import util.ConectorBD;

public class AlunosPesquisaTableModel extends AbstractTableModel {

    // Atributos ACESSÓRIOS, relacionados ao funcionamento do TableModel
    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int TURMA = 2;
    private static final int CURSO = 3;
    private static final String[] nomeColunas = {"Código", "Nome", "Turma", "Curso"};
    private static final Class[] classeColunas = {Integer.class, String.class, String.class, String.class};

    private List<Aluno> alunos;
    private DAOTurma daoTurma;
    private DAOCurso daoCurso;

    public AlunosPesquisaTableModel() {
        try {
            ConectorBD conexao = new ConectorBD();
            alunos = new ArrayList<>();
            daoTurma = new DAOTurma(conexao.getConexao());
            daoCurso = new DAOCurso(conexao.getConexao());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return nomeColunas[columnIndex];
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return classeColunas[columnIndex];
    }

    @Override
    public int getRowCount() {
        return alunos.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object conteudo = null;
        Aluno aluno = alunos.get(rowIndex);

        Turma turma = null;
        Curso curso = null;
        try {
            int codTurma = aluno.getTurma();
            turma = daoTurma.consulta(codTurma);

            curso = daoCurso.consulta(turma.getCurso());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        switch (columnIndex) {
            case (CODIGO):
                conteudo = (Integer) aluno.getCodigo();
                break;
            case (NOME):
                conteudo = aluno.getNome();
                break;
            case (TURMA):
                if (turma != null) {
                    conteudo = turma.getAno();
                }
                break;
            case (CURSO):
                if (curso != null) {
                    conteudo = curso.getNome();
                }
                break;
        }

        return conteudo;
    }

}
