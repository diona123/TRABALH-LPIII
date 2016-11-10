package modelo;

public class Aluno {
    
    private int codigo;
    private String nome;
    private int turma;
    
    public Aluno(){        
    }
    
    public Aluno(int codaluno, String nomealuno, int codturma){
        this.codigo = codaluno;
        this.nome = nomealuno;
        this.turma = codturma;
    }

    /**
     * @return the codaluno
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codaluno to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nomealuno
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nomealuno to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the codturma
     */
    public int getTurma() {
        return turma;
    }

    /**
     * @param codturma the codturma to set
     */
    public void setTurma(int codturma) {
        this.turma = codturma;
    }

    public String getCurso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
