public class UsuarioNaoPertenceAoGrupo extends SistemaCaronaExcecao {

    public UsuarioNaoPertenceAoGrupo(){
        super("O usuário não pode ser removido do grupo pois não pertence a ele ou é dono do grupo.");
    }
}
