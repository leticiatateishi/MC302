public class GrupoPrivado extends Grupo {

    public GrupoPrivado(String n, String d, Usuario u){
        super(n, d, u);
    }

    public void adicionarMembro(Usuario usuario){
        membros.add(new GrupoUsuario(this, usuario));
    }

    public void removerMembro(Usuario usuario){
        for(GrupoUsuario i: membros){
            if (i.getUsuario() == usuario){
                membros.remove(i);
            }
        }
    }
}
