import java.util.ArrayList;
import java.util.List;

public class CaronaPrivada extends Carona {

    private ArrayList<GrupoPrivado> grupos = new ArrayList<>();


    /*  Construtor semelhantes a classe Carona.  */

    public CaronaPrivada(Caronante caronante) {
        super(caronante);
    }


    /*  Verifica se um determinado grupo já pertence ao arraylist de grupos e, se possível, o adiciona.  */

    public boolean adicionarGrupo(GrupoPrivado grupo) {
        if (grupos.contains(grupo))
            return false;
        grupos.add(grupo);
        return true;
    }


    /*  Verifica se há espaço para adição de um novo caroneiro e, se houver, verifica se o caroneiro participa de
    *   algum grupo privado desta carona antes de inseri-lo. */

    public boolean adicionarCaroneiro(Caroneiro caroneiro) {
        if (caroneiros.size() >= getOcupacaoMaxima()) {
            return false;
        }
        for (GrupoPrivado i : grupos) {
            if (caroneiro.getPerfil().getUsuario().pertenceAoGrupo(i)) {
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
