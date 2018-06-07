import java.util.ArrayList;

public class CaronaPublica extends Carona {

    private ArrayList<GrupoPublico> grupos = new ArrayList<>();


    /*  Construtor semelhantes a classe Carona.  */

    public CaronaPublica(Caronante caronante) {
        super(caronante);
    }


    /*  Verifica se um determinado grupo já pertence ao array list de grupos e, se possível, o adiciona.  */

    public boolean adicionarGrupo(GrupoPublico grupo) {
        if (grupos.contains(grupo) || !grupo.checarPresencaUsuario(getCaronante().getCaronante().getPerfil().getUsuario()))
            return false;
        grupos.add(grupo);
        return true;
    }


    /*  Verifica se há espaço para adição de um novo caroneiro e, se houver, verifica se o caroneiro participa de
     *   algum grupo publico desta carona antes de inseri-lo. */

    public boolean adicionarCaroneiro(Caroneiro caroneiro) {
        if (caroneiros.size() >= getOcupacaoMaxima())
            return false;
        if (grupos.size() == 0){
            CaronaCaroneiro caronaCaroneiro = new CaronaCaroneiro(this, caroneiro);
            caroneiro.adicionarCarona(caronaCaroneiro);
            caroneiros.add(caronaCaroneiro);
            return true;
        }
        for (GrupoPublico i : grupos) {
            System.out.println(i.getNome());
            if (i.checarPresencaUsuario(caroneiro.getPerfil().getUsuario())) {
                CaronaCaroneiro caronaCaroneiro = new CaronaCaroneiro(this, caroneiro);
                caroneiro.adicionarCarona(caronaCaroneiro);
                caroneiros.add(caronaCaroneiro);
                return true;
            }
        }
        return false;
    }


    /*  Verifica se determinado caroneiro participa desta carona e o remove. */

    public boolean removerCaroneiro(Caroneiro caroneiro) {
        for (CaronaCaroneiro i : caroneiros) {
            if (i.getCaroneiro() == caroneiro) {
                caroneiros.remove(i);
                caroneiro.removerCarona(i);
                return true;
            }
        }
        return false;
    }

}
