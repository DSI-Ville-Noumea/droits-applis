<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/DroitsApplis.java" --%><%-- /jsf:pagecode --%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@page language="java"%>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>DroitsApplis</title>
<meta name="GENERATOR" content="Rational Application Developer">
<!-- <meta http-equiv="X-UA-Compatible" content="chrome=1"> -->
</head>

<%
	if (!nc.mairie.droitsapplis.servlets.DroitsApplisServlet.controlerHabilitation(request)) {
		response.setStatus(javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED); 
		response.setHeader("WWW-Authenticate","BASIC realm=\"Habilitation HTTP pour la Mairie\"");
		javax.servlet.ServletContext sc= getServletContext();
		javax.servlet.RequestDispatcher rd = sc.getRequestDispatcher("ConnectionInsulte.jsp");
		try {
			rd.forward(request,response);
		} catch (Exception e) {
			System.out.println("Impossible de forwarder la JSP : "+e);
		}
	}
	

	nc.mairie.technique.UserAppli aUserAppli = (nc.mairie.technique.UserAppli)nc.mairie.technique.VariableGlobale.recuperer(request,nc.mairie.technique.VariableGlobale.GLOBAL_USER_APPLI);
	
	java.util.ArrayList listeDroits = aUserAppli.getListeDroits();
	
 if (null==listeDroits||listeDroits.size()==0) {%>
<BODY>
<TABLE> 
	<%@ include file="DroitsApplisEmpty.jsp"%>
</TABLE>
</BODY>
<% } else { %>

<frameset rows="13%, *" frameborder="0" border="0" framespacing="0">
	<FRAME src="DroitsTitre.jsp" name="Titre" scrolling="NO" noresize
		marginwidth="0" marginheight="0">
	<FRAME src="DroitsMain.jsp" name="Main" marginwidth="0"
		marginheight="0">
	<NOFRAMES>
	<BODY>
	<P>L'affichage de cette page requiert un navigateur prenant en
	charge les cadres.</P>
	</BODY>
	</NOFRAMES>
</FRAMESET>
<% } %>
</html>
