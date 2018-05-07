public class GrupoPrivado extends Grupo {

    public GrupoPrivado(String n, String d, Usuario u) {
        super(n, d, u);
    }


    /*  Adiciona um novo membro ao grupo. */

    public void adicionarMembro(Usuario usuario) {
        membros.add(new GrupoUsuario(this, usuario));
        usuario.adicionarGrupo(this);
    }


    /*  Verifica se determinado usuário participa do grupo e o remove. */

    public void removerMembro(Usuario usuario) {
        for (GrupoUsuario i : membros) {
            if (i.getUsuario() == usuario) {
                membros.remove(i);
            }
        }
    }
}
