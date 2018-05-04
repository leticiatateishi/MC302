public class GrupoUsuario {

    private int id;
    private Grupo grupo;
    private Usuario usuario;
    private static int geradorId = 0;

    public GrupoUsuario(Grupo grupo, Usuario usuario){
        geradorId ++;
        this.grupo = grupo;
        this.usuario = usuario;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Grupo getGrupo(){
        return grupo;
    }

    public void setGrupo(Grupo grupo){
        this.grupo = grupo;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
}
