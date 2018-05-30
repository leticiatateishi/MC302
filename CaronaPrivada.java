import java.util.ArrayList;
import java.util.List;

public class CaronaPrivada extends Carona {

    private ArrayList<GrupoPrivado> grupos = new ArrayList<>();


    /*  Construtor semelhantes a classe Carona.  */

    public CaronaPrivada(Caronante caronante) {
        super(caronante);
    }


    /*  Verifica se um determinado grupo já pertence ao arraylist de grupos e, se possível, o adiciona.  */

    public boolean adicionarGrupo(GrupoPrivado grupo) throws UsuarioNaoPertenceAoGrupoPrivado{
        if (grupo.checarPresencaUsuario(getCaronante().getCaronante().getPerfil().getUsuario())) {
            grupos.add(grupo);
            return true;
        }
        throw new UsuarioNaoPertenceAoGrupoPrivado();
    }


    /*  Verifica se há espaço para adição de um novo caroneiro e, se houver, verifica se o caroneiro participa de
    *   algum grupo privado desta carona antes de inseri-lo. */

    public boolean adicionarCaroneiro(Caroneiro caroneiro) {

        if (caroneiros.size() >= getOcupacaoMaxima()) {
            return false;
        }

        for (GrupoPrivado i : grupos) {
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
