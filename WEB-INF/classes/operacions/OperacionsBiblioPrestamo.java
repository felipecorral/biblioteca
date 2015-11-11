package operacions;
import java.sql.*;
import java.util.ArrayList;


public class OperacionsBiblioPrestamo {
	
	
	private Connection con=null;
	private String erro="";
	
	
	
//	 public OperacionsBiblioPrestamo() {
//		// TODO Auto-generated constructor stub
//		try{
//		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
////		    String url="jdbc:mysql://localhost/test";
////			con = DriverManager.getConnection(url,"root","abc123.");
//		    String url="jdbc:mysql://dbalumnos:3312/a14felipecm";
//			con = DriverManager.getConnection(url,"a14felipecm","");
//		}
//		catch(ClassNotFoundException e){
//			erro=erro+"\n\tClase non atopada: "+e.getMessage();
//		}
//		catch(SQLException e){
//			erro=erro+"\n\tError SQL abrindo conexion: "+e.getMessage();
//		}
//	}// FIN
	 
	 
	 public void abrirConexion(){
			try{
				//Creamos el datasource
				com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
				ds.setUser("a14felipecm");
				ds.setPassword("");
				ds.setServerName("dbalumnos");
				ds.setPort(3312);
				ds.setDatabaseName("a14felipecm");
				//Crear ña cpmexion a partir del datasource
				con = ds.getConnection();
			}catch(SQLException sqle){
				//System.out.println("Error SQL"+ sqle.getMessage());
				erro+="Error SQL"+ sqle.getMessage()+"\n";
			}catch(Exception e){
				//System.out.println("Error"+e.getMessage());
				erro+="Error"+e.getMessage()+"\n";
			}
//			}finally{
//				if (con!=null){
//					try {
//						con.close();
//					} catch (SQLException e) {
//						// 	TODO Auto-generated catch block
//						erro+="Erro: "+e.getMessage()+"\n";
//					}
//				}
//			}//Finally
		}

	public ArrayList<Prestamo> consultarPrestamos(){
    		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		Prestamo prestam=new Prestamo();
		ResultSet datos;
		try {
			PreparedStatement sentencia = con.prepareStatement("SELECT * FROM prestamo");
			datos=sentencia.executeQuery();
			while(datos.next()){
			prestam.setIdPrestamo(datos.getInt(1));
			prestam.setIdLibro(datos.getInt(2));
			prestam.setIdUsuario(datos.getInt(3));
			prestam.setData(datos.getDate(4));
			prestam.setDevuelto(datos.getBoolean(5));
			prestamos.add(prestam);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			erro+="Error Consulta: "+e.getMessage()+"\n";
		}
		return prestamos;
	}//Fin
	
	public void engadirPrestamo(Prestamo prest){
		PreparedStatement sentenciaParametrizada;
		try {
			sentenciaParametrizada = con.prepareStatement("INSERT INTO prestamo (idLibro, idUsuario, data, devolto) VALUES (?,?,?,?)");
			//sentenciaParametrizada.setInt(1, prest.getIdPrestamo());
			sentenciaParametrizada.setInt(1, prest.getIdLibro());
			sentenciaParametrizada.setInt(2,prest.getIdUsuario());
			sentenciaParametrizada.setDate(3, new java.sql.Date(prest.getData().getTime()));
			sentenciaParametrizada.setBoolean(4, prest.isDevuelto());
			sentenciaParametrizada.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			erro+="Error: "+e.getMessage()+"\n";
			System.out.println(erro);
		}
	}//Fin engadir
	
	
	public void modificarPrestamo(Prestamo p){
		PreparedStatement sentenciaParametrizada;
		try{
			sentenciaParametrizada = con.prepareStatement("UPDATE prestamo set idLibro=?, idUsuario=?, data=?, devolto=? where idPrestamo=?");
			sentenciaParametrizada.setInt(1, p.getIdLibro());
			sentenciaParametrizada.setInt(2,p.getIdUsuario());
			sentenciaParametrizada.setDate(3, new java.sql.Date(p.getData().getTime()));
			sentenciaParametrizada.setBoolean(4, p.isDevuelto());
			sentenciaParametrizada.setInt(5,p.getIdPrestamo());
			sentenciaParametrizada.executeUpdate();
		}catch(SQLException sqe){
			erro+="Error modificando prestamo: "+sqe.getMessage();
		}	
	}//Fin Modificar
	
	
	public void borrarPrestamo(int ide){
		try{
			PreparedStatement sentenciaBorrar = con.prepareStatement("DELETE FROM prestamo WHERE idPrestamo=?");
			sentenciaBorrar.setInt(1, ide);
			sentenciaBorrar.executeUpdate();
		}catch(SQLException sqe){
			erro+="Error: "+sqe.getMessage();
		}
	}//Fin borrar prestamo por id
	

	public void cerrarConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			erro+="Error: "+e.getMessage()+"\n";
		}
	}//Fin Cerrar Conexion


	
	 public String getErro(){
			return erro;
		}
	
	
	

}
