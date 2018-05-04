import java.util.ArrayList;

public abstract class Grupo{

    private int id;
    private String nome;
    private String descricao;
    protected ArrayList<GrupoUsuario> membros;
    private Usuario dono;
    private static int geradorId=0;

    public Grupo(){
        geradorId ++;
        id = geradorId;
        membros = new ArrayList<>();
    }

    public Grupo(String n, String d, Usuario u){
        this();
        nome = n;
        descricao = d;
        dono = u;
    }

    public String toString(){
        String out = "Dados do grupo " +getId()+ ":\n";
        out += "Nome: " +getNome()+ "\n";
        out += "Descricao: " +getDescricao()+ "\n";
        return out;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Usuario getDono(){
        return dono;
    }

    // VERIFICAR SE O NOVO DONO PERTENCE AO GRUPO?
    public void alterarDono(Usuario dono, Usuario donoNovo){
        if (getDono() == dono){
            this.dono = donoNovo;
        }
    }

    public abstract void adicionarMembro(Usuario usuario);

    public abstract void removerMembro(Usuario usuario);
}