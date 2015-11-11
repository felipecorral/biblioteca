package operacions;
import java.util.ArrayList;


public class Gestion {
	
	
	public static void main (String[] args){
		
		ArrayList<Libro> listaLibros= new ArrayList<Libro>();
		OperacionsBiblioLibro obl = new OperacionsBiblioLibro();
		obl.abrirConexion();
		//Libro lbr1 = new Libro(4,"JJJJJJ","El Jovis","Tol Quien",2001);
		//obl.engadirLibro(lbr1);
		//obl.borrarLibro(2001);
		//obl.modificarLibro(lbr1);
		listaLibros = obl.consultarLibros();
		obl.cerrarConexion();
		System.out.println("Numero de libros: " +listaLibros.size());
		
		
		
		
		ArrayList<Usuario> listaUsuarios= new ArrayList<Usuario>();
		OperacionsBiblioUsuario obu = new OperacionsBiblioUsuario();
		obu.abrirConexion();
		//Usuario usr1 = new Usuario(300,"77554433D","Paco Perez","paco@dametabaco.com");
		//obu.engadirUsuario(usr1);
		//obu.borrarUsuario(3);
		listaUsuarios = obu.consultarUsuarios();
		obu.cerrarConexion();
		System.out.println("Numero de usuarios: " +listaUsuarios.size());
		
		
		ArrayList<Prestamo> listaPrestamos= new ArrayList<Prestamo>();
		OperacionsBiblioPrestamo obp = new OperacionsBiblioPrestamo();
		obp.abrirConexion();
		//Prestamo prs1 = new Prestamo(100,1,1,new Date(),false);
		//obp.engadirPrestamo(prs1);
		//obp.borrarPrestamo(7);
		listaPrestamos = obp.consultarPrestamos();
		obp.cerrarConexion();
		System.out.println("Numero de prestamos: " +listaPrestamos.size());
		
		
		
		
	}
	

}
