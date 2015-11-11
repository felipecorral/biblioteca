package operacions;
public class Libro {

	private int idLibro;
	private String ISBN;
	private String titulo;
	private String autores;
	private int ano;

	// Constructor por defecto
	public Libro() {
	}

	// Constructor por parametros
	public Libro(int idLibro, String iSBN, String titulo, String autores,
			int ano) {
		this.idLibro = idLibro;
		ISBN = iSBN;
		this.titulo = titulo;
		this.autores = autores;
		this.ano = ano;
	}

	// getters y setters
	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutores() {
		return autores;
	}

	public void setAutores(String autores) {
		this.autores = autores;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

}
