<%@ page import ="operacions.*" %>
<%@ page import="java.sql.*" %>
<%@ page import ="java.util.*" %>
<%	
	String statCon="nada";
	OperacionsBiblioLibro operacions=new OperacionsBiblioLibro();
	statCon = operacions.abrirConexion();
	try{	
		int numLibs = operacions.contarLibros();	
		Libro libroConsultado = operacions.consultarLibro(numLibs);
%>

<html>

	<head>
		<link rel="stylesheet" type="text/css" href="estilos.css" media="screen" />
		<title>Novo Libro</title>
		
	</head>
	<body>
		<!--Si recibe los parametros, creamos un nuevo libro -->
		<%
		if (request.getParameter("isbn")!=null){
		Libro nuevoLibro = new Libro(1,request.getParameter("isbn"),request.getParameter("titulo"),request.getParameter("autores"),Integer.parseInt(request.getParameter("ano")));
		operacions.engadirLibro(nuevoLibro);
		%>		
		<!--Mostramos un alert, y volvemos a la pagina correspondiente-->
		<script type="text/javascript" >
			alert("Se ha creado el libro \n <%=nuevoLibro.getTitulo()%>");
			location.href = "crearLibro.jsp";
		</script>
		<%		
		}
		%>
		<!--Barra de titulo-->
		<div class="bar_titulo">
			<table class="tabla_titulo">
				<tr>
				<td width="10%">
					<a class="boton" href="index.jsp">Volver</a>
				</td>
				<td width="90%">
					<h3>Novo Libro</h3>
				</td>
				</tr>
			</table>
			
		</div>

		<section><!--tabla -->
		<div id="tabla">
		
		<form class="formulario" action="crearLibro.jsp" method="post">
       		  <fieldset>
		    	<legend>Datos</legend>
			<input type="hidden" id="idLibro" name="idLibro" value=""/>
			<label>
				<span>ISBN :</span>
				<input type="text" id="isbn" name="isbn" value="" placeholder="1234567890123" pattern="(ISBN[-]*(1[03])*[ ]*(: ){0,1})*(([0-9Xx][- ]*){13}|([0-9Xx][- ]*){10})" autofocus required/>
			</label> <br/>
			<label>
				<span>T&iacute;tulo :</span>
				<input type="text" id="titulo" name="titulo del libro" value="" placeholder="titulo" maxlength="200" required/>
			</label> <br/>
			<label>
				<span>Autores :</span>
				<input type="text" id="autor/es del libro" name="autores" value="" placeholder="autores" maxlength="300" required/>
			</label> <br/>	
			<label>
				<span>Ano :</span>
				<input type="number" id="ano" name="ano" value="" placeholder="ano de edici&oacute"  min="868" max="2030" maxlength="4" required/>
			</label> <br/>	
			<input type="submit" class="button" value="Enviar"/>
		  </fieldset>
		</form>
		<hr/>
		<h3>&Uacute;ltimo libro insertado</h3>
		<table border="1" class="tabla_seleccion">
		<tr><th scope="col">ID</th><th scope="col">ISBN</th><th scope="col">T&iacute;tulo</th><th scope="col">Autores</th><th scope="col">Ano</th><th scope="col">Accions</th></tr>
		<tr>
			<td><%=libroConsultado.getIdLibro() %></td>
			<td><%=libroConsultado.getISBN()%></td>
			<td><%=libroConsultado.getTitulo()%></td>
			<td><%=libroConsultado.getAutores()%></td>
			<td><%=libroConsultado.getAno()%></td>
			<td><a class="boton" href="consultarLibro.jsp?id=<%=libroConsultado.getIdLibro()%>">Consultar</a>
			<a class="boton" href="modificarLibro.jsp?id=<%=libroConsultado.getIdLibro()%>">Modificar</a></td>
			<td><a class="boton boton_rojo" href="index.jsp?borrar=<%=libroConsultado.getIdLibro()%>">Borrar</a>
		</td>
		</tr>
		<%

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
