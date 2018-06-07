public class GrupoPublico extends Grupo {

    public GrupoPublico(String n, String d, Usuario u) {
        super(n, d, u);
    }


    /*  Adiciona um novo membro ao grupo. */

    public void adicionarMembro(GrupoUsuario usuario) {
        membros.add(usuario);
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