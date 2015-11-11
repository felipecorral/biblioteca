<%@ page import ="operacions.*" %>
<%@ page import="java.sql.*" %>
<%@ page import ="java.util.*" %>

<!--Creo el objeto operacions, hago la conexión y lleno el arraylist de libros -->
<%	
	String statCon="nada";
	OperacionsBiblioLibro operacions=new OperacionsBiblioLibro();
	statCon = operacions.abrirConexion();
	ArrayList<Libro> datos = new ArrayList<Libro>();
	try{
		datos = operacions.consultarLibros();
%>

<html>

	<head>
		<link rel="stylesheet" type="text/css" href="estilos.css" media="screen" />
		<title>Gesti&oacute;n Biblioteca</title>
		
	</head>
	<body>
		<!--Si recibe el parametro borrar, borramos el libro con el id correspondiente -->
		<%
			if (request.getParameter("borrar")!=null){
				
				operacions.borrarLibro(Integer.parseInt(request.getParameter("borrar")));
			%>
		<!--Mostramos un alert, y volvemos a la pagina correspondiente-->
		<script type="text/javascript" >
			alert("Se ha borrado el libro");
			location.href = "index.jsp";
		</script>	

		<%
			}
 		%>
		<!--Barra de titulo-->
		<div class="bar_titulo">
			<table class="tabla_titulo">
				<tr>

				<td colspan="2">
					<h3>Gesti&oacute;n de Libros</h3>
				</td>
				<td>
					<a class="boton" href="crearLibro.jsp">Nuevo</a>
				</td>
				</tr>
			</table>
			
		</div>

		<section><!--tabla -->
		<div id="tabla">
		<table border="1" class="tabla_seleccion">
		<tr><th scope="col">ID</th><th scope="col">ISBN</th><th scope="col">T&iacute;tulo</th><th scope="col">Autores</th><th scope="col">Ano</th><th scope="col">Acci&oacute;ns</th><th scope="col">Eliminar</th></tr>
		
		<!--Recorremos el arraylist y vamos metiendo filas en la tabla -->		
		<%
		for (int i=0; i<datos.size(); i++){
		%>
		<tr>
		<td><%=datos.get(i).getIdLibro()%></td>
		<td><%=datos.get(i).getISBN()%></td>
		<td><%=datos.get(i).getTitulo()%></td>
		<td><%=datos.get(i).getAutores()%></td>
		<td><%=datos.get(i).getAno()%></td>
		<td><a class="boton" href="consultarLibro.jsp?id=<%=datos.get(i).getIdLibro()%>">Consultar</a>
		<a class="boton" href="modificarLibro.jsp?id=<%=datos.get(i).getIdLibro()%>">Modificar</a></td>
		<td><a class="boton boton_rojo" href="index.jsp?borrar=<%=datos.get(i).getIdLibro()%>">Borrar</a></td></tr>
		<!--Cerramos la conexión, y manejamos la excepción -->
		<%
		}
		operacions.cerrarConexion();
		}catch(Exception e){
			out.println("Error"+e.getMessage());
		}
		
	%>
</table>
	</div>
	</section><!--Fin tabla -->		

		
	</body>
	<!--Muestro los errores con getErro en el pie de la pagina-->
	<footer class="pie"><!--Pie-->
		<div >
		<span>JDBC version:<%=statCon%> ||</span><span style="color:#FF0000">Consola: <%=operacions.getErro()%></span>
		</div>	

	</footer>
</html>
