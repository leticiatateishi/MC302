import java.util.ArrayList;

public class Usuario {

	private int id;
	private String nome;
	private String email;
	private String senha;
	private boolean status;
	private ArrayList<Grupo> grupos;
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

    public static int getGeradorId(){
	    return geradorId;
    }

	public void adicionarGrupo (Grupo grupo){
	    grupos.add(grupo);
	    grupo.adicionarMembro(this);
    }
	
}
