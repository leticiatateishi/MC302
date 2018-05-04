import java.util.ArrayList;

public class Carona {

    private ArrayList<CaronaCaroneiro> caroneiros;
    private CaronaCaronante caronante;
    private double latitudeEncontro;
    private double longitudeEncontro;
    private double latitudeDestino;
    private double longitudeDestino;
    private String horaDiaEncontro;
    private int ocupacaoMaxima=3;
    private float valor;
    private ArrayList<MetodoPagamento> formaPagAceitas;


    public Carona(Caronante c){
        caroneiros = new ArrayList<>();
        formaPagAceitas = new ArrayList<>();
        caronante = new CaronaCaronante(c, this);
    }

    public Carona(Caronante caronante, double latitudeEncontro, double longitudeEncontro, double latitudeDestino,
                  double longitudeDestino, String horaDiaEncontro, int ocupacaoMaxima){
        this(caronante);
        this.latitudeEncontro = latitudeEncontro;
        this.longitudeEncontro = longitudeEncontro;
        this.latitudeDestino = latitudeDestino;
        this.longitudeDestino = longitudeDestino;
        this.horaDiaEncontro = horaDiaEncontro;
        this.ocupacaoMaxima = ocupacaoMaxima;
    }


    /*  Imprime os dados da carona. */

    public String toString(){
        String out = "Dados da carona:\n";
        out += "Latitude de encontro: " +getLatitudeEncontro()+ "\n";
        out += "Longitude de encontro: " +getLongitudeEncontro()+ "\n";
        out += "Latitude do destino: " +getLatitudeDestino()+ "\n";
        out += "Longitude do destino: " +getLongitudeDestino()+ "\n";
        out += "Hora e dia do encontro: " +getHoraDiaEncontro()+ "\n";
        return out;
    }


    /*  Metodos de acesso dos atributos. */

    public final CaronaCaronante getCaronante(){
        return caronante;
    }

    public double getLatitudeEncontro(){
        return latitudeEncontro;
    }

    public void setLatitudeEncontro(double latitude){
        latitudeEncontro = latitude;
    }

    public double getLongitudeEncontro(){
        return longitudeEncontro;
    }

    public void setLongitudeEncontro(double longitude){
        longitudeEncontro = longitude;
    }

    public double getLatitudeDestino(){
        return latitudeDestino;
    }

    public void setLatitudeDestino(double latitude){
        latitudeDestino = latitude;
    }

    public double getLongitudeDestino(){
        return longitudeDestino;
    }

    public void setLongitudeDestino(double longitude){
        longitudeDestino = longitude;
    }

    public String getHoraDiaEncontro(){
        return horaDiaEncontro;
    }

    public void setHoraDiaEncontro(String horaDia){
        horaDiaEncontro = horaDia;
    }

    public int getOcupacaoMaxima(){
        return ocupacaoMaxima;
    }

    public void setOcupacaoMaxima(int ocupacao){
        ocupacaoMaxima = ocupacao;
        caronante.getCaronante().setAssentosDisponiveis(ocupacao);
    }

    public float getValor(){
        return valor;
    }

    public void setValor(float v){
        valor = v;
    }

    public void setCaronante(CaronaCaronante caronante) {
        this.caronante = caronante;
    }

    /*  Verifica se ha assentos disponiveis e, se possivel, adiciona um caroneiro.  */

    public boolean adicionarCaroneiro(Caroneiro caroneiro){
        if (caroneiros.size() < ocupacaoMaxima){
            CaronaCaroneiro caronaCaroneiro = new CaronaCaroneiro(caroneiro, this);
            caroneiros.add(caronaCaroneiro);
            caronante.getCaronante().setAssentosDisponiveis(caronante.getCaronante().getAssentosDisponiveis()-1);
            caroneiro.adicionarCarona(caronaCaroneiro);
            return true;
        }
        else{
            return false;
        }
    }


    /*  Verifica se determinado caroneiro pertence a lista de caroneiros e, se possivel, o remove. */

    public boolean removerCaroneiro(Caroneiro caroneiro) {
        if (caroneiros.contains(caroneiro)){
            caroneiros.remove(caroneiro);
            return true;
        }
        return false;
    }


    /*  Verifica se um metodo de pagamento ja pertence ao array list de formas de pagamento aceitas e, se possivel,
        faz a adicao. Se o novo metodo de pagamento eh GRATIS, o atributo valor da carona eh zerado e todas as outras
        formas de pagamento sao removidas do array list.  */

    public boolean adicionarFormaPagamento(MetodoPagamento mp){
        if (formaPagAceitas.contains(mp))
            return false;
        formaPagAceitas.add(mp);
        if (this.caronaGratuita()){
            setValor(0);
            if (formaPagAceitas.size()>1) {
                formaPagAceitas.clear();
                formaPagAceitas.add(mp);
            }
        }
        return true;
    }


    /*  Tenta remover determinada forma de pagamento. */

    public boolean removerFormaPagamento(MetodoPagamento mp){
        if (formaPagAceitas.contains(mp)) {
            formaPagAceitas.remove(mp);
            return true;
        }
        return false;
    }


    /*  Verifica se determinada forma de pagamento eh valida para essa carona. */

    public boolean checarExistenciaFormaPagamento(MetodoPagamento mp){
        if (formaPagAceitas.contains(mp))
            return true;
        return false;
    }


    /*  Verifica se a carona eh gratis.  */

    public boolean caronaGratuita(){
        if (formaPagAceitas.contains(MetodoPagamento.GRATIS))
            return true;
        return false;
    }


    /*  Indica a ocupacao atual da carona.  */

    public int verificaOcupacao(){
        return caroneiros.size();
    }


    /*  Verifica se a carona esta vazia.  */
    public boolean caronaVazia() {
        if (caroneiros.isEmpty()) {
            return true;
        }
        return false;
    }


    public boolean atribuirNotaCaroneiro(int idUsuario, float avaliacao){
        for (CaronaCaroneiro caroneiro: caroneiros){
            if (caroneiro.getCaroneiro().getPerfil().getUsuario().getId() == idUsuario){
                caroneiro.setAvaliacao(avaliacao);
                if (!caroneiro.getCaroneiro().atualizarAvaliacao(this, avaliacao)){
                    return false;
                }
                return true;
            }
        }
        return false;
    }


    public boolean atribuirNotaCaronante(float avaliacao) {
        caronante.setAvaliacao(avaliacao);
        if (!caronante.getCaronante().atualizarAvaliacao(this, avaliacao)){
            return false;
        }
        return true;
    }

    public CaronaCaroneiro encontrarCaroneiro(Caroneiro caroneiro){
        for (CaronaCaroneiro c: caroneiros){
            if(c.getCaroneiro() == caroneiro){
                return c;
            }
        }
        return null;
    }
}


