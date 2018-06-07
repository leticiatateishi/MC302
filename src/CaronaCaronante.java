public class CaronaCaronante {

    private Caronante caronante;
    private Carona carona;
    private float avaliacao;


    public CaronaCaronante(Caronante caronante, Carona carona) {
        this.caronante = caronante;
        this.carona = carona;
    }


    /*  Métodos de acesso dos atributos. */

    public Caronante getCaronante() {
        return caronante;
    }

    public void setCaronante(Caronante c) {
        caronante = c;
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
