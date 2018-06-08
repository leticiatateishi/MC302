import java.io.Serializable;

public class CaronaCaroneiro implements Serializable {

    private Caroneiro caroneiro;
    private Carona carona;
    private float avaliacao;


    public CaronaCaroneiro(Carona carona, Caroneiro caroneiro) {
        this.caroneiro = caroneiro;
        this.carona = carona;
    }


    /*  Métodos de acesso dos atributos. */

    public Caroneiro getCaroneiro() {
        return caroneiro;
    }

    public void setCaroneiro(Caroneiro c) {
        caroneiro = c;
    }

    public Carona getCarona() {
        return carona;
    }

    public void setCarona(Carona c) {
        carona = c;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float a) {
        avaliacao = a;
    }
}
