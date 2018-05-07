public class Perfil {

    private char sexo;
    private String dataNascimento;
    private String cidade;
    private String estado;
    private String telefone;
    private boolean fumante;
    private float avaliacao;
    private Caroneiro caroneiro;
    private Caronante caronante;
    private Usuario usuario;


    public Perfil(char sexo, String dataNascimento, String cidade, String estado, String telefone, boolean fumante) {
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.fumante = fumante;
        avaliacao = 0;
    }


    /*  Imprime os dados do perfil. */

    public String toString() {
        String out = "Sexo: " + getSexo() + "\n";
        out += "Data de nascimento: " + getDataNascimento() + "\n";
        out += "Cidade: " + getCidade() + "\n";
        out += "Estado: " + getEstado() + "\n";
        out += "Telefone: " + getTelefone() + "\n";
        out += "Fumante: " + getFumante() + "\n";
        out += "Avaliacao: " + getAvaliacao() + "\n";
        return out;
    }


    /*  Métodos de acesso dos atributos. */

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean getFumante() {
        return fumante;
    }

    public void setFumante(boolean fumante) {
        this.fumante = fumante;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao() {
    }

    public Caroneiro getCaroneiro() {
        return caroneiro;
    }

    public void setCaroneiro(Caroneiro caroneiro) {
        this.caroneiro = caroneiro;
    }

    public Caronante getCaronante() {
        return caronante;
    }

    public void setCaronante(Caronante caronante) {
        this.caronante = caronante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario.getPerfil() != this) {
            usuario.setPerfil(this);
        }
    }
}
