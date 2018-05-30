public class InsercaoEmGrupoPrivado extends SistemaCaronaExcecao {

    public InsercaoEmGrupoPrivado(){
        super("Apenas o dono de um grupo privado pode adicionar novos membros.");
    }
}
