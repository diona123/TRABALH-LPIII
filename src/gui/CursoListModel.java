package gui;

import modelo.Curso;
import dao.DAOCurso;
import java.sql.SQLException;
import java.util.List;
import javax.swing.AbstractListModel;

public class CursoListModel extends AbstractListModel {
    
    private DAOCurso dao;
    private List<Curso> lista;
    
    public CursoListModel(DAOCurso dao) throws SQLException {
        this.dao = dao;
        this.lista = dao.lista();
    }
    
    @Override
    public int getSize(){
        return lista.size();
    }
    
    @Override
    public Curso getElementAt(int index){
        return lista.get(index);
    }
}
