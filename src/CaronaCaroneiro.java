public class CaronaCaroneiro {

    private Caroneiro caroneiro;
    private Carona carona;
    private float avaliacao;

    public CaronaCaroneiro(Caroneiro caroneiro, Carona carona){
        this.caroneiro = caroneiro;
        this.carona = carona;
    }

    public Caroneiro getCaroneiro(){
        return caroneiro;
    }

    public void setCaroneiro(Caroneiro c){
        caroneiro = c;
    }

    public Carona getCarona(){
        return carona;
    }

    public void setCarona(Carona c){
        carona = c;
    }

    public float getAvaliacao(){
        return avaliacao;
    }

    public void setAvaliacao(float a){
        avaliacao = a;
    }
}
