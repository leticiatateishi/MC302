import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GrupoUsuario implements Salvavel {

    private int id;
    private Grupo grupo;
    private Usuario usuario;
    private static int geradorId = 0;

    public GrupoUsuario(Grupo grupo, Usuario usuario) {
        geradorId++;
        id = geradorId;
        this.grupo = grupo;
        this.usuario = usuario;
    }


    @Override
    public void salvarParaArquivo() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Grupos.txt", true));
            out.write("" + this + "\n\n");
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /*  Imprime os dados do GrupoUsuario. */

    public String toString() {
        String out = "GrupoUsuario " + getId() + ":  ";
        out += "Grupo " + getGrupo().getNome() + " (" + getGrupo().getId() + "); ";
        out += "Usuario: " + getUsuario().getNome() + " (" + getUsuario().getId() + ")";
        return out;
    }


    /*  Métodos de acesso dos atributos. */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
