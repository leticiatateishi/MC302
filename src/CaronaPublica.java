import java.util.ArrayList;

public class CaronaPublica extends Carona {

    private ArrayList<GrupoPublico> grupos;


    /*  Construtores semelhantes a classe Carona.  */

    public CaronaPublica(Caronante caronante){
        super(caronante);
    }

    public CaronaPublica(Caronante caronante, double latitudeEncontro, double longitudeEncontro, double latitudeDestino,
                         double longitudeDestino, String horaDiaEncontro, int ocupacaoMaxima){
        super(caronante, latitudeEncontro, longitudeEncontro, latitudeDestino, longitudeDestino, horaDiaEncontro,
                ocupacaoMaxima);
    }


    /*  Metodo semelhante a classe Carona.  */

    public boolean adicionarCaroneiro(Caroneiro caroneiro){
        if (super.adicionarCaroneiro(caroneiro))
            return true;
        return false;
    }


    /*  Verifica se um determinado grupo ja pertence a array list de grupos e, se possivel, o adiciona.  */

    public boolean adicionarGrupo(GrupoPublico grupo){
        if (grupos.contains(grupo))
            return false;
        grupos.add(grupo);
        return true;
    }
}
