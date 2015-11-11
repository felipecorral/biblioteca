package operacions;
import java.util.Date;

public class Prestamo {

	private int idPrestamo;
	private int idLibro;
	private int idUsuario;
	private Date data;
	private boolean devuelto;
	
	
	//Constructor por defecto
	public Prestamo() {
	}
	
	//Constructor por parametros
	public Prestamo(int idPrestamo, int idLibro, int idUsuario, Date data,
			boolean devuelto) {
		this.idPrestamo = idPrestamo;
		this.idLibro = idLibro;
		this.idUsuario = idUsuario;
		this.data = data;
		this.devuelto = devuelto;
	}
	//Getters y Setters
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public int getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public boolean isDevuelto() {
		return devuelto;
	}
	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}
	
	
	
	
	
	

}
