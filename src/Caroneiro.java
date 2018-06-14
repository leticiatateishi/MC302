import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class Caroneiro implements Serializable, Salvavel, Carregavel {

    private static final long serialVersionUID = 1L;
    private String cartaoDeCredito;
    private boolean pagamentoEmDinheiro;
    private Perfil perfil;
    private ArrayList<CaronaCaroneiro> caronas;


    public Caroneiro() {
        caronas = new ArrayList<>();
    }


    public Caroneiro(String cartao) {
        this();
        cartaoDeCredito = cartao;
        pagamentoEmDinheiro = false;
    }


    public Caroneiro(boolean pagamento) {
        this();
        pagamentoEmDinheiro = pagamento;
    }


    @Override
    public void salvarParaArquivo() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Usuarios.txt", true));
            out.write("" + this + "\n***********************************\n\n\n");
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void carregarParaArquivo() {
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Caroneiros.txt"));
            out.writeObject(this);
            out.flush();
        } catch (IOException excepction){
            excepction.printStackTrace();
        }
    }


    /*  Adiciona uma carona ao arraylist de caronas. */

    public boolean adicionarCarona(CaronaCaroneiro c) {
        return caronas.add(c);
    }


    /*  Verifica se o caroneiro participa de determinada carona e o remove. */

    public void removerCarona(CaronaCaroneiro c) {
        for (CaronaCaroneiro i : caronas) {
            if (i == c) caronas.remove(i);
        }
    }


    /*  Tenta adicionar o caroneiro a determinada carona. */

    public boolean pedirCarona(Carona carona) {
        return carona.adicionarCaroneiro(this);
    }


    /*  Imprime os dados do caroneiro. */

    public String toString() {
        String out = "** Dados do caroneiro " + this.getPerfil().getUsuario().getId() + "**\n";
        out += "Cartao de credito: " + getCartaoDeCredito() + "\n";
        out += "Pagamento em dinheiro: " + getPagamentoEmDinheiro() + "\n\n";
        return out;
    }


    /*  Métodos de acesso dos atributos. */

    public String getCartaoDeCredito() {
        return cartaoDeCredito;
    }

    public void setCartaoDeCredito(String cartao) {
        cartaoDeCredito = cartao;
    }

    public boolean getPagamentoEmDinheiro() {
        return pagamentoEmDinheiro;
    }

    public void setPagamentoEmDinheiro(boolean pagamento) {
        pagamentoEmDinheiro = pagamento;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil p) {
        perfil = p;
    }

    public String getCaronas(){
        String out = "";
        for (CaronaCaroneiro i: caronas)
            out += i.getCarona();
        return out;
    }
}
