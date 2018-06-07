import java.util.ArrayList;

public abstract class Carona {

    protected ArrayList<CaronaCaroneiro> caroneiros;
    private final CaronaCaronante caronante;
    private double latitudeEncontro;
    private double longitudeEncontro;
    private double latitudeDestino;
    private double longitudeDestino;
    private String horaDiaEncontro;
    private int ocupacaoMaxima = 3;
    private float valor;
    private ArrayList<MetodoPagamento> formaPagAceitas;


    public Carona(Caronante c) {
        caroneiros = new ArrayList<>();
        formaPagAceitas = new ArrayList<>();
        caronante = new CaronaCaronante(c, this);
    }


    /*  Verifica se um metodo de pagamento já pertence ao array list de formas de pagamento aceitas e, se possível,
     *  faz a adição. Se o novo metodo de pagamento é GRATIS, o atributo valor da carona é zerado e todas as outras
     *  formas de pagamento são removidas do array list. */

    public boolean adicionarFormaPagamento(MetodoPagamento mp) {

        if (formaPagAceitas.contains(mp))
            return false;

        if (mp == MetodoPagamento.GRATIS) {
            setValor(0);
            if (formaPagAceitas.size() >= 1)
                formaPagAceitas.clear();
        }

        formaPagAceitas.add(mp);
        return true;
    }


    /*  Tenta remover determinada forma de pagamento. /

    public boolean removerFormaPagamento(MetodoPagamento mp) {
        return formaPagAceitas.remove(mp);
    }


    /*  Verifica se determinada forma de pagamento é valida para essa carona. */

    public boolean checarExistenciaFormaPagamento(MetodoPagamento mp) {
        return formaPagAceitas.contains(mp);
    }


    /*  Verifica se a carona é gratuita. */

    public boolean caronaGratuita() {
        return formaPagAceitas.contains(MetodoPagamento.GRATIS);
    }


    /*  Indica a ocupação atual da carona. */

    public int verificaOcupacao() {
        return caroneiros.size();
    }


    /*  Verifica se a carona está vazia (se não possui caroneiros). */

    public boolean caronaVazia() {
        return caroneiros.isEmpty();
    }


    /*  Um usuário com id passado por parâmetro atribui uma nota a esta carona. Essa nota é utilizada para calcular
     *  a avaliação dos outros participantes da carona (do caronante e dos outros caroneiros, se existirem). */

    public boolean atribuirNotaCaroneiro(int idUsuario, float avaliacao) {

        for (CaronaCaroneiro c : caroneiros) {
            if (c.getCaroneiro().getPerfil().getUsuario().getId() != idUsuario)
                c.getCaroneiro().getPerfil().setAvaliacao(avaliacao);
            else
                c.setAvaliacao(avaliacao);
            caronante.getCaronante().getPerfil().setAvaliacao(avaliacao);
        }

        return true;
    }


    /*  O caronante atribui uma nota a essa carona. Essa nota é utilizada para calcular a avaliação dos caroneiros
     *  participantes da carona. */

    public boolean atribuirNotaCaronante(float avaliacao) {

        if (caronante == null) return false;

        for (CaronaCaroneiro c : caroneiros)
            c.getCaroneiro().getPerfil().setAvaliacao(avaliacao);

        getCaronante().setAvaliacao(avaliacao);
        return true;
    }


    /*  Verifica se há assentos disponiveis e, se possível, adiciona um caroneiro.  */

    public abstract boolean adicionarCaroneiro(Caroneiro caroneiro);


    /*  Verifica se determinado caroneiro pertence à lista de caroneiros e, se possível, o remove. */

    public abstract boolean removerCaroneiro(Caroneiro caroneiro);


    /*  Imprime os dados da carona. */

    public String toString() {
        String out = "Dados da carona:\n";
        out += "Latitude de encontro: " + getLatitudeEncontro() + "\n";
        out += "Longitude de encontro: " + getLongitudeEncontro() + "\n";
        out += "Latitude do destino: " + getLatitudeDestino() + "\n";
        out += "Longitude do destino: " + getLongitudeDestino() + "\n";
        out += "Hora e dia do encontro: " + getHoraDiaEncontro() + "\n";
        return out;
    }


    /*  Metodos de acesso dos atributos. */

    public final CaronaCaronante getCaronante() {
        return caronante;
    }

    public double getLatitudeEncontro() {
        return latitudeEncontro;
    }

    public void setLatitudeEncontro(double latitude) {
        latitudeEncontro = latitude;
    }

    public double getLongitudeEncontro() {
        return longitudeEncontro;
    }

    public void setLongitudeEncontro(double longitude) {
        longitudeEncontro = longitude;
    }

    public double getLatitudeDestino() {
        return latitudeDestino;
    }

    public void setLatitudeDestino(double latitude) {
        latitudeDestino = latitude;
    }

    public double getLongitudeDestino() {
        return longitudeDestino;
    }

    public void setLongitudeDestino(double longitude) {
        longitudeDestino = longitude;
    }

    public String getHoraDiaEncontro() {
        return horaDiaEncontro;
    }

    public void setHoraDiaEncontro(String horaDia) {
        horaDiaEncontro = horaDia;
    }

    public int getOcupacaoMaxima() {
        return ocupacaoMaxima;
    }

    public void setOcupacaoMaxima(int ocupacao) {
        ocupacaoMaxima = ocupacao;
        caronante.getCaronante().setAssentosDisponiveis(ocupacao);
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float v) {
        valor = v;
    }

}


