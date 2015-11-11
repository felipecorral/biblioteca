<%@ page import ="operacions.*" %>
<%@ page import="java.sql.*" %>
<%@ page import ="java.util.*" %>
<!--Cargo operacions, y un entero que me dice si el libro está prestado o no -->
<%	
	String statCon="nada";
	OperacionsBiblioLibro operacions=new OperacionsBiblioLibro();
	statCon = operacions.abrirConexion();
	String id=request.getParameter("id");
	try{
		int ide = Integer.parseInt(id);		
		Libro libroConsultado = operacions.consultarLibro(ide);
		int prestado = operacions.contarPrestamo(ide);
%>

<html>

	<head>
		<link rel="stylesheet" type="text/css" href="estilos.css" media="screen" />
		<title><%=libroConsultado.getTitulo()%></title>
		
	</head>
	<body>
		<!--Barra de titulo-->
		<div class="bar_titulo">
			<table class="tabla_titulo">
				<tr>
				<td width="10%">
					<a class="boton" href="index.jsp">Volver</a>
				</td>
				<td width="90%">
					<h3><%=libroConsultado.getTitulo()%></h3>
				</td>
				</tr>
			</table>
			
		</div>

		<section><!--tabla -->
		<div id="tabla">
		<table border="1" class="tabla_seleccion">
		<tr><th scope="col">ID</th><th scope="col">ISBN</th><th scope="col">T&iacute;tulo</th><th scope="col">Autores</th><th scope="col">Ano</th><th scope="col">Prestado</th><th scope="col">Acci&oacute;ns</th><th scope="col">Eliminar</th></tr>
		
		<tr>
			<td><%=libroConsultado.getIdLibro() %></td>
			<td><%=libroConsultado.getISBN()%></td>
			<td><%=libroConsultado.getTitulo()%></td>
			<td><%=libroConsultado.getAutores()%></td>
			<td><%=libroConsultado.getAno()%></td>
			<td>
			<!--Si el libro está prestado, prestamo tiene valor 1 -->
			<%
				if (prestado == 1) {
			%>
				<span style="background:#FF0000"> Prestado </span>	
			<%
				} else {
			%>
				<span style="background:#00FF00"> Disponible </span>
			<%
				}
			%>
			</td>
			<td>
			<a class="boton" href="modificarLibro.jsp?id=<%=libroConsultado.getIdLibro()%>">Modificar</a></td>
			<td><a class="boton boton_rojo" href="index.jsp?borrar=<%=libroConsultado.getIdLibro()%>">Borrar</a>
		</tr>
		<%
		operacions.cerrarConexion();
		}catch(Exception e){
			out.println("Error"+e.getMessage());
		}
	%>
	</table>
	</div>
		
	</body>
	<!--Muestro los errores con getErro en el pie de la pagina-->
	<footer class="pie"><!--Pie-->
		<div >
		<span>JDBC version:<%=statCon%> ||</span><span style="color:#FF0000">Consola: <%=operacions.getErro()%></span>
		</div>	

	</footer>

</html>
