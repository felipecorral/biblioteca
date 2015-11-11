package operacions;
public class Usuario {

	private int idUsuario;
	private String DNI;
	private String nome;
	private String correoe;

	// Constructor por defecto
	public Usuario() {
	}

	// Constructor por parametros
	public Usuario(int idUsuario, String dNI, String nome, String correoe) {
		this.idUsuario = idUsuario;
		DNI = dNI;
		this.nome = nome;
		this.correoe = correoe;
	}

	// Getters y Setters
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCorreoe() {
		return correoe;
	}

	public void setCorreoe(String correoe) {
		this.correoe = correoe;
	}

}
