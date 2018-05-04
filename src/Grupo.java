import java.util.ArrayList;

public class Grupo{

    private int id;
    private String nome;
    private String descricao;
    private ArrayList<Usuario> membros;
    private static int geradorId=0;
    private int testStatic = 1;

    public Grupo(){
        geradorId ++;
        id = geradorId;
        membros = new ArrayList<>();
    }

    public Grupo(String n, String d){
        this();
        nome = n;
        descricao = d;
    }

    public String toString(){
        String out = "Dados do grupo " +getId()+ ":\n";
        out += "Nome: " +getNome()+ "\n";
        out += "Descricao: " +getDescricao()+ "\n";
        out += "Nome e identificacao dos usuarios que participam do grupo: ";
        for (Usuario i: membros){
            out += i.getNome() + "(" + i.getId() + ")\n";
        }
        out += "\n";
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

    public void adicionarMembro(Usuario usuario){
        membros.add(usuario);
    }

    public static int getGeradorId(){
        return geradorId;
    }

    public int getTestStatic(){
        return testStatic;
    }
}