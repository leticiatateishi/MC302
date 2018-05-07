import java.util.ArrayList;

public abstract class Grupo {

    private int id;
    private String nome;
    private String descricao;
    protected ArrayList<GrupoUsuario> membros;
    private Usuario dono;
    private static int geradorId = 0;

    public Grupo() {
        geradorId++;
        id = geradorId;
        membros = new ArrayList<>();
    }

    public Grupo(String n, String d, Usuario u) {
        this();
        nome = n;
        descricao = d;
        dono = u;
        membros.add(new GrupoUsuario(this, u));
        u.adicionarGrupo(this);
    }


    /*  Altera o dono do grupo. */

    public void alterarDono(Usuario dono, Usuario donoNovo) {
        if (getDono() == dono) {
            this.dono = donoNovo;
        }
    }


    /*  Adiciona um novo membro ao grupo. */

    public abstract void adicionarMembro(Usuario usuario);


    /*  Verifica se determinado usuário participa do grupo e o remove. */

    public abstract void removerMembro(Usuario usuario);


    /*  Imprime os dados do grupo. */

    public String toString() {
        String out = "Dados do grupo " + getId() + ":\n";
        out += "\t*Nome: " + getNome() + "\n";
        out += "\t*Descricao: " + getDescricao() + "\n";
        out += "\t*Nome do dono: " + getDono().getNome() + "\n";
        out += "\t*Membros do grupo:\n";
        for (GrupoUsuario i : membros) {
            out += "\t\t" + i.getUsuario().getNome() + "\n";
        }
        return out;
    }


    /*  Métodos de acesso dos atributos. */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getDono() {
        return dono;
    }
}