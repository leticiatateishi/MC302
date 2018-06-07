import java.util.ArrayList;

public class Caroneiro {

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


    /*  Adiciona uma carona ao arraylist de caronas. */

    public boolean adicionarCarona(CaronaCaroneiro c) {
        return caronas.add(c);
    }


    /*  Verifica se o caroneiro participa de determinada carona e o remove. */

    public void removerCarona(CaronaCaroneiro c) {
        for (CaronaCaroneiro i : caronas) {
            if (i == c) {
                caronas.remove(i);
            }
        }
    }


    /*  Tenta adicionar o caroneiro a determinada carona. */

    public boolean pedirCarona(Carona carona) {
        return carona.adicionarCaroneiro(this);
    }


    /*  Retorna a avaliação do caroneiro em determinada carona. */

    public float getAvalicao(Carona carona) {
        for (CaronaCaroneiro i : caronas) {
            if (i.getCarona() == carona) {
                return i.getAvaliacao();
            }
        }
        return 0.0f;
    }


    /*  Imprime os dados do caroneiro. */

    public String toString() {
        String out = "Cartao de credito: " + getCartaoDeCredito() + "\n";
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
}
