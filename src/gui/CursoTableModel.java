package gui;

import modelo.Curso;
import dao.DAOCurso;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CursoTableModel extends AbstractTableModel {
    
    // Atributos ESSENCIAIS, relacionados aos dados
    private DAOCurso dao;
    private List<Curso> lista;
    
    // Atributos ACESSÓRIOS, relacionados ao funcionamento do TableModel
    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final String[] nomeColunas = {"codcurso", "nomecurso"};
    private static final Class[] classeColunas = {Integer.class, String.class};
    
    public CursoTableModel(DAOCurso dao) throws SQLException {
        this.dao = dao;
        this.lista = dao.lista();
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return nomeColunas[columnIndex];
    }
    
    @Override
    public Class getColumnClass(int columnIndex){
        return classeColunas[columnIndex];
    }
    
    @Override
    public int getRowCount(){
        return lista.size();
    }
    
    @Override
    public int getColumnCount(){
        return nomeColunas.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Object conteudo = null;
        Curso objeto = lista.get(rowIndex);
        
        switch(columnIndex){
            case(CODIGO):
                conteudo = (Integer) objeto.getCodigo();
                break;
            case(NOME):
                conteudo = objeto.getNome();
                break;
        }
        
        return conteudo;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        Curso objeto = lista.get(rowIndex);
        
        switch (columnIndex){
            case CODIGO:
                objeto.setCodigo((Integer) value);
                break;
            case NOME:
                objeto.setNome((String) value);
                break;
        }
        
        try {
            dao.atualiza(objeto);
        } catch (SQLException exc){
            exc.printStackTrace();
        }
        
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    
}
