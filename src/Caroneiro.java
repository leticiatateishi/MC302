import java.util.ArrayList;

public class Caroneiro {

    private String cartaoDeCredito;
    private boolean pagamentoEmDinheiro;
    private Perfil perfil;
    private ArrayList<CaronaCaroneiro> caronas;


    public Caroneiro(){
        caronas = new ArrayList<>();
    }


    public Caroneiro (String cartao){
        this();
        cartaoDeCredito = cartao;
        pagamentoEmDinheiro = false;
    }


    public Caroneiro (boolean pagamento){
        this();
        pagamentoEmDinheiro = pagamento;
    }


    public String toString(){
        String out = "Cartao de credito: " +getCartaoDeCredito()+ "\n";
        out += "Pagamento em dinheiro: " +getPagamentoEmDinheiro()+ "\n\n";
        return out;
    }

    public String getCartaoDeCredito() {
        return cartaoDeCredito;
    }

    public void setCartaoDeCredito (String cartao) {
        cartaoDeCredito = cartao;
    }

    public boolean getPagamentoEmDinheiro(){
        return pagamentoEmDinheiro;
    }

    public void setPagamentoEmDinheiro (boolean pagamento){
        pagamentoEmDinheiro = pagamento;
    }

    public Perfil getPerfil() { return perfil; }

    public void setPerfil (Perfil p) { perfil = p; }


    public void adicionarCarona(CaronaCaroneiro c){
        caronas.add(c);
    }


    public boolean atualizarAvaliacao(Carona carona, float avaliacao){
        for (CaronaCaroneiro c: caronas){
            if (c.getCarona() == carona){
                c.setAvaliacao(avaliacao);
                return true;
            }
        }
        return false;
    }


}
