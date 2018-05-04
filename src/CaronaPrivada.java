import java.util.ArrayList;

public class CaronaPrivada extends Carona {

    private ArrayList<GrupoPrivado> grupos;


    /*  Construtores semelhantes a classe Carona.  */

    public CaronaPrivada(Caronante caronante){
        super(caronante);
    }


    /*  Verifica se um determinado grupo ja pertence a array list de grupos e, se possivel, o adiciona.  */
    public boolean adicionarGrupo(GrupoPrivado grupo){
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

//    public boolean pertenceAoGrupo(Grupo grupo){
//        for(GrupoPrivado i: grupos){
//            if(i == grupo){
//                return true;
//            }
//        }
//        return false;
//    }
}
