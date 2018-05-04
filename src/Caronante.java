import java.util.ArrayList;

public class Caronante {

    private int tempoHabilitacao;
    private String generoMusicalFavorito;
    private String placaVeiculo;
    private final String cartaMotorista;
    private String marcaVeiculo;
    private String modeloVeiculo;
    private int assentosDisponiveis;
    private Perfil perfil;
    private ArrayList<CaronaCaronante> caronas;


    public Caronante(String carta) {
        cartaMotorista = carta;
        caronas = new ArrayList<>();
    }


    public Caronante(int tempo, String musica, String placa, String carta, String marca, String modelo, int assentos){
        this(carta);
        tempoHabilitacao = tempo;
        generoMusicalFavorito = musica;
        placaVeiculo = placa;
        marcaVeiculo = marca;
        modeloVeiculo = modelo;
        assentosDisponiveis = assentos;
    }


    public String toString() {
        String out = "Dados do caronante:\n";
        out += "Tempo de habilitacao: "+getTempoHabilitacao()+" anos\n";
        out += "Genero musical favorito: "+getGeneroMusicalFavorito()+"\n";
        out += "Placa do veiculo: "+getPlacaVeiculo()+"\n";
        out += "Carta do motorista: "+getCartaMotorista()+"\n";
        out += "Marca do veiculo: "+getMarcaVeiculo()+"\n";
        out += "Modelo do veiculo: "+getModeloVeiculo()+"\n";
        out += "Assentos disponiveis: "+getAssentosDisponiveis()+"\n\n";
        return out;
    }

    public int getTempoHabilitacao() {
        return tempoHabilitacao;
    }

    public void setTempoHabilitacao(int tempo) {
        tempoHabilitacao = tempo;
    }

    public String getGeneroMusicalFavorito() {
        return generoMusicalFavorito;
    }

    public void setGeneroMusicalFavorito(String genero) {
        generoMusicalFavorito = genero;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placa) {
        placaVeiculo = placa;
    }

    public String getCartaMotorista() {
        return cartaMotorista;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marca) {
        marcaVeiculo = marca;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo (String modelo){
        modeloVeiculo = modelo;
    }

    public int getAssentosDisponiveis(){
        return assentosDisponiveis;
    }

    public void setAssentosDisponiveis (int assentos){
        assentosDisponiveis = assentos;
    }

    public Perfil getPerfil() { return perfil; }

    public void setPerfil (Perfil p) { perfil = p; }


    public Carona oferecerCarona(){
        Carona carona = new Carona(this);
        CaronaCaronante caronaCaronante = new CaronaCaronante(this, carona);
        carona.setCaronante(caronaCaronante);
        caronas.add(caronaCaronante);
        return carona;
    }


    public boolean atualizarAvaliacao(Carona carona, float avaliacao){
        for (CaronaCaronante c: caronas){
            if (c.getCarona() == carona){
                c.setAvaliacao(avaliacao);
                return true;
            }
        }
        return false;
    }
}