import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Usuario implements Serializable, Salvavel, Carregavel {

    private static final long serialVersionUID = 1L;
    private int id;
    private String nome;
    private String email;
    private String senha;
    private boolean status;
    private ArrayList<GrupoUsuario> grupos;
    private Perfil perfil;
    private static int geradorId = 0;


    public Usuario(String nome, String email, String senha, boolean status, Perfil perfil) {
        id = geradorId;
        geradorId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.status = status;
        this.perfil = perfil;
        perfil.setUsuario(this);
        grupos = new ArrayList<>();
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
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Usuario.txt"));
            out.writeObject(this);
            out.flush();
        } catch (IOException excepction){
            excepction.printStackTrace();
        }
    }


    /*  Adiciona o usuário a um grupo público. */

    public void adicionarGrupo(GrupoPublico grupo) throws GrupoInexistente {
        if (grupo != null) {
            GrupoUsuario gu = new GrupoUsuario(grupo, this);
            grupos.add(gu);
            grupo.adicionarMembro(gu);
        } else throw new GrupoInexistente();
    }


    /*  Adiciona um GrupoUsuario a um grupo privado. Esse método é apenas auxiliar ao método adicionarUsuarioAUmGrupo
     *  e NÃO deve ser chamado diretamente. */

    public void adicionarGrupoPrivado(GrupoUsuario grupoUsuario) {
        grupos.add(grupoUsuario);
    }


    /*  Adiciona um usuário a um grupo privado apenas se o usuário que fizer a inserção (this) for o dono do
     *  grupo privado. */

    public void adicionarUsuarioAUmGrupo(Usuario usuario, GrupoPrivado grupo) throws InsercaoEmGrupoPrivado {

        if (grupo.getDono() == this) {
            GrupoUsuario grupoUsuario = new GrupoUsuario(grupo, usuario);
            grupo.adicionarMembro(grupoUsuario);
            usuario.adicionarGrupoPrivado(grupoUsuario);
            return;
        }
        throw new InsercaoEmGrupoPrivado();
    }


    /*  Verifica se o usuário pertence a determinado grupo porém não é o dono, e o remove. */

    public boolean removerGrupo(Grupo grupo) throws UsuarioNaoPertenceAoGrupo {

        for (GrupoUsuario i : grupos) {
            if (i.getGrupo() == grupo && i.getGrupo().getDono() != this) {
                i.getGrupo().removerMembro(this);
                grupos.remove(i);
                return true;
            }
        }
        throw new UsuarioNaoPertenceAoGrupo();
    }


    /*  Verifica se o usuário pertence a determinado grupo porém não é o dono, e o remove. */

    public boolean removerGrupo(int idGrupo) throws UsuarioNaoPertenceAoGrupo {

        for (GrupoUsuario i : grupos) {
            if (i.getGrupo().getId() == idGrupo && !(i.getGrupo().getDono() == this)) {
                i.getGrupo().removerMembro(this);
                grupos.remove(i);
                return true;
            }
        }
        throw new UsuarioNaoPertenceAoGrupo();
    }


    /*  Verifica se o usuário é dono do grupo e atualiza o nome e a descrição do grupo. */

    public void atualizarGrupo(Usuario dono, int idGrupo, String nome, String descricao) {
        for (GrupoUsuario i : grupos) {
            if (i.getGrupo().getId() == idGrupo && this == dono) {
                i.getGrupo().setNome(nome);
                i.getGrupo().setDescricao(descricao);
            }
        }
    }


    /*  Verifica se o usuário é dono do grupo e atualiza a descrição do grupo. */

    public void atualizarGrupo(Usuario dono, int idGrupo, String descricao) {
        for (GrupoUsuario i : grupos) {
            if (i.getGrupo().getId() == idGrupo && this == dono) i.getGrupo().setDescricao(descricao);
        }
    }


    /*  Cria um grupo público no qual este usuário é o dono. */

    public GrupoPublico criarGrupoPublico(String nome, String descricao) {
        GrupoPublico grupo = new GrupoPublico(nome, descricao, this);
        grupos.add(new GrupoUsuario(grupo, this));
        return grupo;
    }


    /*  Cria um grupo privado no qual este usuário é o dono. */

    public GrupoPrivado criarGrupoPrivado(String nome, String descricao) {
        GrupoPrivado grupo = new GrupoPrivado(nome, descricao, this);
        grupos.add(new GrupoUsuario(grupo, this));
        return grupo;
    }


    /*  Imprime os dados do usuário. */

    public String toString() {
        String out = "** Dados do usuário " + id + "**\n";
        out += "Nome: " + getNome() + " (" + getId() + ")\n";
        out += "Email: " + getEmail() + "\n";
        out += "Senha: " + getSenha() + "\n";
        out += "Status: " + getStatus() + "\n";
        out += "Grupos:\n";
        for (GrupoUsuario i : grupos) {
            out += "\t" + i.getGrupo().getNome() + "\n";
        }
        out += "\n";
        return out;
    }


    /*  Métodos de acesso dos atributos. */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
        if (perfil.getUsuario() != this) {
            perfil.setUsuario(this);
        }
    }

}
