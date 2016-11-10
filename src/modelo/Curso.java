package modelo;


public class Curso {
    
    // Atributos => representam colunas na tabela
    private int codigo;
    private String nome;
    
    // => Métodos => operam sobre os atributos
    
    public Curso(){
    }
    
    public Curso(int codigo, String nome){
        setCodigo(codigo);
        setNome(nome);
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        if(codigo < 0){
            throw new RuntimeException("Codigo de curso invalido");
        } else {
            this.codigo = codigo;
        }
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        if(nome == null){
            throw new RuntimeException("Nome de curso invalido");
        } else {
            this.nome = nome;
        }
    }
}
