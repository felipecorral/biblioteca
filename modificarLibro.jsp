<%@ page import ="operacions.*" %>
<%@ page import="java.sql.*" %>
<%@ page import ="java.util.*" %>
<%	
	String statCon="nada";
	OperacionsBiblioLibro operacions=new OperacionsBiblioLibro();
	statCon = operacions.abrirConexion();
	try{	
		int numLibs = operacions.contarLibros();	
		Libro libroModificado=null;
%>

<html>

	<head>
		<link rel="stylesheet" type="text/css" href="estilos.css" media="screen" />
		<title>Modificar Libro</title>
		
	</head>
	<body>
		<!--Si recibe el parametro modificar,modificamos el libro -->
		<%
		if (request.getParameter("modificar")!=null){
		libroModificado = new Libro(Integer.parseInt(request.getParameter("idLibro")),request.getParameter("isbn"),request.getParameter("titulo"),request.getParameter("autores"),Integer.parseInt(request.getParameter("ano")));
		operacions.modificarLibro(libroModificado);
		%>		
		<!--Mostramos un alert, y volvemos a la pagina correspondiente-->
		<script type="text/javascript" >
			alert("Se ha modificado el libro \n <%=libroModificado.getTitulo()%>");
			location.href = "modificarLibro.jsp?id=<%=libroModificado.getIdLibro()%>";
		</script>
		<!--Si no recibe parametro, cargamos el libro que queriamos modificar-->
		<%		
		}else{
			libroModificado = operacions.consultarLibro(Integer.parseInt(request.getParameter("id")));
			
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
					<h3>Modificar: <%=libroModificado.getTitulo()%></h3>
				</td>
				</tr>
			</table>
			
		</div>		



		<section><!--tabla -->
		<div id="tabla">
		
		<form class="formulario" action="modificarLibro.jsp" method="post">
       		  <fieldset>
		    	<legend>Datos</legend>
			<input type="hidden" id="idLibro" name="idLibro" value="<%=libroModificado.getIdLibro()%>"/>
			<label>
				<span>ISBN :</span>
				<input type="text" id="isbn" name="isbn" value="<%=libroModificado.getISBN()%>" placeholder="isbn" autofocus required/>
			</label> <br/>
			<label>
				<span>T&iacute;tulo :</span>
				<input type="text" id="titulo" name="titulo" value="<%=libroModificado.getTitulo()%>" placeholder="titulo de la obra" maxlength="200" required/>
			</label> <br/>
			<label>
				<span>Autores :</span>
				<input type="text" id="autores" name="autores" value="<%=libroModificado.getAutores()%>" placeholder="autor/es del libro" maxlength="300" required/>
			</label> <br/>	
			<label>
				<span>Ano :</span>
				<input type="date" id="ano" name="ano" value="<%=libroModificado.getAno()%>" placeholder="ano de edici&oacute;" min="868" max="2030" maxlength="4" required/>
			</label> <br/>	
			<input type="hidden" id="modificar" name="modificar" value="true"/>
			<input type="submit" class="button" value="Enviar"/>
		  </fieldset>
		</form>
		<hr/>
		<h3>Datos Libro Modificado</h3>
		<table border="1" class="tabla_seleccion">
		<tr><th scope="col">ID</th><th scope="col">ISBN</th><th scope="col">T$iacute;tulo</th><th scope="col">Autores</th><th scope="col">Ano</th><th scope="col">Accions</th></tr>
		<tr>
			<td><%=libroModificado.getIdLibro() %></td>
			<td><%=libroModificado.getISBN()%></td>
			<td><%=libroModificado.getTitulo()%></td>
			<td><%=libroModificado.getAutores()%></td>
			<td><%=libroModificado.getAno()%></td>
			<td><a class="boton" href="consultarLibro.jsp?id=<%=libroModificado.getIdLibro()%>">Consultar</a>
			</td>
			<td><a class="boton boton_rojo" href="index.jsp?borrar=<%=libroModificado.getIdLibro()%>">Borrar</a>
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
