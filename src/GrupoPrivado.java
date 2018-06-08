import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GrupoPrivado extends Grupo {

    private ArrayList<CaronaPrivada> caronas;


    public GrupoPrivado(String n, String d, Usuario u) {
        super(n, d, u);
        caronas = new ArrayList<>();
    }


    @Override
    public void salvarParaArquivo() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Grupos.txt", true));
            out.write("" + this + "\n\n");
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /*  Adiciona um novo membro ao grupo. */

    public void adicionarMembro(GrupoUsuario usuario) {
        membros.add(usuario);
    }


    /*  Verifica se determinado usuário participa do grupo e o remove. */

    public void removerMembro(Usuario usuario) {
        for (GrupoUsuario i : membros) {
            if (i.getUsuario() == usuario) {
                membros.remove(i);
            }
        }
    }


    public void adicionarCarona(CaronaPrivada carona) {
        caronas.add(carona);
    }
}
