import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class Caronante implements Serializable, Salvavel, Carregavel {

    private static final long serialVersionUID = 1L;
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


    public Caronante(String placa, String carta, String marca, String modelo, int assentos) {
        this(carta);
        placaVeiculo = placa;
        marcaVeiculo = marca;
        modeloVeiculo = modelo;
        assentosDisponiveis = assentos;
    }


    @Override
    public void salvarParaArquivo() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Usuarios.txt", true));
            out.write("" + this);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void carregarParaArquivo() {
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Caronante.txt"));
            out.writeObject(this);
            out.flush();
        } catch (IOException excepction){
            excepction.printStackTrace();
        }
    }


    /*  Cria uma carona pública. */

    public CaronaPublica oferecerCaronaPublica() {
        CaronaPublica carona = new CaronaPublica(this);
        caronas.add(carona.getCaronante());
        return carona;
    }


    /*  Cria uma carona privada. */

    public CaronaPrivada oferecerCaronaPrivada() {
        CaronaPrivada carona = new CaronaPrivada(this);
        caronas.add(carona.getCaronante());
        return carona;
    }


    /*  Imprime os dados do caronante. */

    public String toString() {
        String out = "** Dados do caronante " + this.getPerfil().getUsuario().getId() + "**\n";
        out += "Tempo de habilitacao: " + getTempoHabilitacao() + " anos\n";
        out += "Genero musical favorito: " + getGeneroMusicalFavorito() + "\n";
        out += "Placa do veiculo: " + getPlacaVeiculo() + "\n";
        out += "Carta do motorista: " + getCartaMotorista() + "\n";
        out += "Marca do veiculo: " + getMarcaVeiculo() + "\n";
        out += "Modelo do veiculo: " + getModeloVeiculo() + "\n";
        out += "Assentos disponiveis: " + getAssentosDisponiveis() + "\n\n";
        return out;
    }


    /*  Metodos de acesso dos atributos. */

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

    public void setModeloVeiculo(String modelo) {
        modeloVeiculo = modelo;
    }

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void setAssentosDisponiveis(int assentos) {
        assentosDisponiveis = assentos;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil p) {
        perfil = p;
    }
}