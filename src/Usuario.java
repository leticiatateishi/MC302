import java.util.ArrayList;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private boolean status;
	private ArrayList<GrupoUsuario> grupos;
    private Perfil perfil;
    private static int geradorId=0;


	public Usuario (String nome, String email, String senha, boolean status, Perfil perfil) {
		geradorId ++;
		id = geradorId;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.status = status;
		this.perfil = perfil;
		perfil.setUsuario(this);
        grupos = new ArrayList<>();
	}


	public String toString () {
		String out = "Nome: "+getNome()+" ("+getId()+")\n";
			out += "Email: "+getEmail()+"\n";
			out += "Senha: "+getSenha()+"\n";
			out += "Status: "+getStatus()+"\n";
            out += "\n";
		return out;
	}
	
	public int getId () {
		return id;
	}
	
	public void setId (int id) {
		this.id = id;
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getEmail () {
		return email;
	}
	
	public void setEmail (String email) {
	    this.email = email;
	}
	
	public String getSenha () {
		return senha;
	}
	
	public void setSenha (String senha) {
		this.senha = senha;
	}
	
	public boolean getStatus () {
		return status;
	}
	
	public void setStatus (boolean status) {
		this.status = status;
	}

	public Perfil getPerfil(){
	    return perfil;
    }

    public void setPerfil (Perfil perfil){
	    this.perfil = perfil;
	    if(perfil.getUsuario() != this){
	        perfil.setUsuario(this);
        }
    }

	public void adicionarGrupo (Grupo grupo){
		grupo.adicionarMembro(this);
		grupos.add(new GrupoUsuario(grupo, this));
    }

    public boolean removerGrupo(Grupo grupo){
        for (GrupoUsuario i: grupos){
            if (i.getGrupo() == grupo){
                i.getGrupo().removerMembro(this);
                grupos.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean removerGrupo(int idGrupo){
        for (GrupoUsuario i: grupos){
            if (i.getGrupo().getId() == idGrupo){
                i.getGrupo().removerMembro(this);
                grupos.remove(i);
                return true;
            }
        }
        return false;
    }


    public void atualizarGrupo(Usuario dono, int idGrupo, String nome, String descricao){
        for (GrupoUsuario i: grupos){
            if(i.getGrupo().getId() == idGrupo && this == dono){
                i.getGrupo().setNome(nome);
                i.getGrupo().setDescricao(descricao);
            }
        }
    }


    public void atualizarGrupo(Usuario dono, int idGrupo, String descricao){
        for (GrupoUsuario i: grupos){
            if(i.getGrupo().getId() == idGrupo && this == dono){
                i.getGrupo().setDescricao(descricao);
            }
        }
    }

    public boolean pertenceAoGrupo(Grupo grupo){
	    for(GrupoUsuario i: grupos){
	        if(i.getGrupo() == grupo){
	            return true;
            }
        }
        return false;
    }
	
}
