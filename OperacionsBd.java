package operacions;

import java.sql.*;
import java.util.ArrayList;

public class OperacionsBd {
	
	
	private Connection con=null;
	private String erro="";
	

	public OperacionsBd(){
		try{
		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    String url="jdbc:mysql://localhost/test";
			con = DriverManager.getConnection(url,"root","abc123.");
		}
		catch(ClassNotFoundException e){
			erro=erro+"\n\tClase non atopada: "+e.getMessage();
		}
		catch(SQLException e){
			erro=erro+"\n\tError SQL abrindo conexion: "+e.getMessage();
		}
	}// FIN




	public String getErro(){
		return erro;
	}
	
	public void abrirConexion(){
		try{
			//Creamos el datasource
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setUser("root");
			ds.setPassword("abc123.");
			ds.setServerName("localhost");
			ds.setPort(3312);
			ds.setDatabaseName("test");
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
	
	public void abrirConexion(String user,String pass,String server,int port,String dbName){
		try{
			//Creamos el datasource
			com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
			ds.setUser(user);
			ds.setPassword(pass);
			ds.setServerName(server);
			ds.setPort(port);
			ds.setDatabaseName(dbName);
			//Crear ña cpmexion a partir del datasource
			//String url = "jdbc:mysql://dbalumnos:3312/a14felipecm";
			//con = DriverManager.getConnection(url,"a14felipecm","");
			con = ds.getConnection();
			DatabaseMetaData meta = con.getMetaData();
		}catch(SQLException sqle){
			erro+="Error SQL"+ sqle.getMessage()+"\n";
		}catch(Exception e){
			erro+="Error"+e.getMessage()+"\n";
		}
//		}finally{
//			if (con!=null){
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// 	TODO Auto-generated catch block
//					erro+="Error: "+e.getMessage()+"\n";
//				}
//			}
//		}//Finally
	}//Fin AbrirConexion
	
	public void cerrarConexion(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			erro+="Error: "+e.getMessage()+"\n";
		}
	}//Fin Cerrar Conexion
	
	public void engadeAlumno(Alumno alu){
		PreparedStatement sentenciaParametrizada;
		try {
			sentenciaParametrizada = con.prepareStatement("insert into alumno(DNI, nome, apelidos, idade) values (?,?,?,?)");
			sentenciaParametrizada.setString(1, alu.getDNI());
			sentenciaParametrizada.setString(2, alu.getNome());
			sentenciaParametrizada.setString(3,alu.getApelidos());
			sentenciaParametrizada.setInt(4, alu.getIdade());
			sentenciaParametrizada.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			erro+="Error: "+e.getMessage()+"\n";
		}
	}
	public Alumno consultaAlumno(String denei){
		Alumno aluno = new Alumno();
		ResultSet datos;
		try {
			PreparedStatement sentencia = con.prepareStatement("select * from alumno where DNI=?");
			sentencia.setString(1, denei);
			datos=sentencia.executeQuery();
			if(datos.next()){
			aluno.setDNI(datos.getString(1));
			aluno.setNome(datos.getString(2));
			aluno.setApelidos(datos.getString(3));
			aluno.setIdade(datos.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			erro+="Error Consulta: "+e.getMessage()+"\n";
		}
		return aluno;
	}//Fin ConsultarAlumno
	
	public ArrayList<Alumno> listadoAlumnos(){
		ResultSet datos;
		Statement sentencia;
		
		ArrayList<Alumno> alunos= new ArrayList<Alumno>();
		try {
			sentencia = con.createStatement();
			datos=sentencia.executeQuery("select * from alumno");
		while(datos.next()){
			Alumno aluno = new Alumno();
			aluno.setDNI(datos.getString("DNI"));
			aluno.setNome(datos.getString("nome"));
			aluno.setApelidos(datos.getString("apelidos"));
			aluno.setIdade(datos.getInt("idade"));
			alunos.add(aluno);
			//System.out.println("DNI: "+aluno.DNI+" | "+"Nome: "+aluno.nome);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			erro+="Error: "+e.getMessage()+"\n";
		}
		return alunos;
	}//Fin LIstadoAlumnos
	
	public void modificaAlumno(Alumno al){
		try{
			PreparedStatement sentenciaMod = con.prepareStatement("update alumno set DNI=?,nome=?,apelidos=?,idade=? where DNI=?");
			sentenciaMod.setString(1,al.getDNI());
			sentenciaMod.setString(2, al.getNome());
			sentenciaMod.setString(3, al.getApelidos());
			sentenciaMod.setInt(4, al.getIdade());
			sentenciaMod.setString(5, al.getDNI());
			sentenciaMod.executeUpdate();
		}catch(SQLException sqe){
			erro+="Error: "+ sqe.getMessage()+"\n";
		}	
	}//Fin Modificar
	
	public void borrarAlumno(String denei){
		try{
			PreparedStatement sentenciaBorrar = con.prepareStatement("delete from alumno where DNI=?");
			sentenciaBorrar.setString(1, denei);
			sentenciaBorrar.executeUpdate();
		}catch(SQLException sqe){
			erro+="Error: "+sqe.getMessage();
		}
	}
}
