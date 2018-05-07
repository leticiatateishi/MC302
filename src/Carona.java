import java.util.ArrayList;

public abstract class Carona {

    protected ArrayList<CaronaCaroneiro> caroneiros;
    private CaronaCaronante caronante;
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


    /*  Verifica se um metodo de pagamento ja pertence ao array list de formas de pagamento aceitas e, se possivel,
    faz a adicao. Se o novo metodo de pagamento eh GRATIS, o atributo valor da carona é zerado e todas as outras
    formas de pagamento sao removidas do array list.  */

    public boolean adicionarFormaPagamento(MetodoPagamento mp) {
        if (formaPagAceitas.contains(mp))
            return false;
        formaPagAceitas.add(mp);
        if (this.caronaGratuita()) {
            setValor(0);
            if (formaPagAceitas.size() > 1) {
                formaPagAceitas.clear();
                formaPagAceitas.add(mp);
            }
        }
        return true;
    }


    /*  Tenta remover determinada forma de pagamento. */

    public boolean removerFormaPagamento(MetodoPagamento mp) {
        if (formaPagAceitas.contains(mp)) {
            formaPagAceitas.remove(mp);
            return true;
        }
        return false;
    }


    /*  Verifica se determinada forma de pagamento é valida para essa carona. */

    public boolean checarExistenciaFormaPagamento(MetodoPagamento mp) {
        if (formaPagAceitas.contains(mp))
            return true;
        return false;
    }


    /*  Verifica se a carona é gratis.  */

    public boolean caronaGratuita() {
        return formaPagAceitas.contains(MetodoPagamento.GRATIS);
    }


    /*  Indica a ocupacao atual da carona.  */

    public int verificaOcupacao() {
        return caroneiros.size();
    }


    /*  Verifica se a carona esta vazia.  */

    public boolean caronaVazia() {
        return caroneiros.isEmpty();
    }


    /*  Procura o caroneiro no arraylist de caroneiro e atribui uma avaliação para este caroneiro nesta carona. */

    public boolean atribuirNotaCaroneiro(int idUsuario, float avaliacao) {
        for (CaronaCaroneiro c : caroneiros) {
            if (c.getCaroneiro().getPerfil().getUsuario().getId() == idUsuario) {
                c.setAvaliacao(avaliacao);
                return true;
            }
        }
        return false;
    }


    /*  Atribui nota para o caronante nesta carona. */

    public boolean atribuirNotaCaronante(float avaliacao) {
        caronante.setAvaliacao(avaliacao);
        return true;
    }


    /*  Procura um caroneiro no arraylist de caroneiros. */

    public CaronaCaroneiro encontrarCaroneiro(Caroneiro caroneiro) {
        for (CaronaCaroneiro c : caroneiros) {
            if (c.getCaroneiro() == caroneiro) {
                return c;
            }
        }
        return null;
    }


    /*  Verifica se ha assentos disponiveis e, se possivel, adiciona um caroneiro.  */

    public abstract boolean adicionarCaroneiro(Caroneiro caroneiro);


    /*  Verifica se determinado caroneiro pertence a lista de caroneiros e, se possivel, o remove. */

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

    public void setCaronante(CaronaCaronante caronante) {
        this.caronante = caronante;
    }


    public Caroneiro getCaroneiro(int id) {
        for (CaronaCaroneiro i : caroneiros) {
            if (i.getCaroneiro().getPerfil().getUsuario().getId() == id) {
                return i.getCaroneiro();
            }
        }
        return null;
    }
}


