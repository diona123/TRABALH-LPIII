
package modelo;

public class Turma {
    
    private int codigo;
    private int curso;
    private int ano;

    /**
     * @return the codturma
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codturma to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codcurso
     */
    public int getCurso() {
        return curso;
    }

    /**
     * @param curso the codcurso to set
     */
    public void setCurso(int curso) {
        this.curso = curso;
    }

    /**
     * @return the anoturma
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the anoturma to set
     */
    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public Turma(){        
    }
    
    public Turma(int codturma, int anoturma, int codcurso){
        this.codigo = codturma;
        this.ano = anoturma;
        this.curso = codcurso;
    }
}
