import java.util.ArrayList;

public class CaronaPublica extends Carona {

    private ArrayList<GrupoPublico> grupos;


    /*  Construtores semelhantes a classe Carona.  */

    public CaronaPublica(Caronante caronante){
        super(caronante);
    }


    /*  Verifica se um determinado grupo ja pertence a array list de grupos e, se possivel, o adiciona.  */

    public boolean adicionarGrupo(GrupoPublico grupo){
        if (grupos.contains(grupo))
            return false;
        grupos.add(grupo);
        return true;
    }

    public boolean adicionarCaroneiro(Caroneiro caroneiro){
        if(caroneiros.size() >= getOcupacaoMaxima()){
            return false;
        }
        CaronaCaroneiro caronaCaroneiro = new CaronaCaroneiro(this, caroneiro);
        caroneiro.adicionarCarona(caronaCaroneiro);
        caroneiros.add(caronaCaroneiro);
        return true;
    }

    public boolean removerCaroneiro(Caroneiro caroneiro){
        for (CaronaCaroneiro i: caroneiros){
            if (i.getCaroneiro() == caroneiro){
                caroneiros.remove(i);
                caroneiro.removerCarona(i);
                return true;
            }
        }
        return false;
    }
}
