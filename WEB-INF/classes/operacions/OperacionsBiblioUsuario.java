package operacions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OperacionsBiblioUsuario {
	
	private Connection con=null;
	private String erro="";
	
	
	
//	public OperacionsBiblioUsuario() {
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
//		}finally{
//			if (con!=null){
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// 	TODO Auto-generated catch block
//					erro+="Erro: "+e.getMessage()+"\n";
//				}
//			}
//		}//Finally
	}
	
	
	public ArrayList<Usuario> consultarUsuarios(){
		ArrayList<Usuario> users = new ArrayList<Usuario>();
	Usuario usr=new Usuario();
	ResultSet datos;
	try {
		PreparedStatement sentencia = con.prepareStatement("SELECT * FROM usuario");
		datos=sentencia.executeQuery();
		while(datos.next()){
			usr.setIdUsuario(datos.getInt("idUsuario"));
			usr.setDNI(datos.getString(2));
			usr.setNome(datos.getString(3));
			usr.setCorreoe(datos.getString(4));
			users.add(usr);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		erro+="Error Consulta Usuarios: "+e.getMessage()+"\n";
	}
	return users;
}//Fin LIstar
	
	public void engadirUsuario(Usuario usr){
		PreparedStatement sentenciaParametrizada;
		try {
			sentenciaParametrizada = con.prepareStatement("INSERT INTO usuario (DNI, nome, correoe) VALUES (?,?,?)");
			sentenciaParametrizada.setString(1, usr.getDNI());
			sentenciaParametrizada.setString(2, usr.getNome());
			sentenciaParametrizada.setString(3, usr.getCorreoe());
			sentenciaParametrizada.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			erro+="Error engadir usuario: "+e.getMessage()+"\n";
			System.out.println(erro);
		}
	}//Fin engadir
	
	
	public void modificarUsuario(Usuario u){
		PreparedStatement sentenciaParametrizada;
		try{
			sentenciaParametrizada = con.prepareStatement("UPDATE usuario set DNI=?, nome=?, correoe=? where idUsuario=?");
			sentenciaParametrizada.setString(1, u.getDNI());
			sentenciaParametrizada.setString(2, u.getNome());
			sentenciaParametrizada.setString(3, u.getCorreoe());
			sentenciaParametrizada.setInt(4, u.getIdUsuario());
			sentenciaParametrizada.executeUpdate();
		}catch(SQLException sqe){
			erro+="Error modificando usuario: "+sqe.getMessage();
		}	
	}//Fin Modificar
	
	
	
	public void borrarUsuario(int ide){
		try{
			PreparedStatement sentenciaBorrar = con.prepareStatement("DELETE FROM usuario WHERE idUsuario=?");
			sentenciaBorrar.setInt(1, ide);
			sentenciaBorrar.executeUpdate();
		}catch(SQLException sqe){
			erro+="Error borrando usuario: "+sqe.getMessage();
		}
	}//Fin borrar libro por id
	

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
