<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/GestionGroupes.java" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<LINK href="theme/sigp2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="theme/dynatablo.css" type="text/css"> 
<title>Gestion des Groupes</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
<script type="text/JavaScript" src="js/sortTable.js"></script>
<script type="text/JavaScript" src="js/dynatablo.js"></script>
</head>
<jsp:useBean class="nc.mairie.droitsapplis.process.GestionGroupes" id="process" scope="session"></jsp:useBean>
	<body>
	<FORM name="formu" method="POST">
<%@ include file="BanniereErreur.jsp" %>
<table cellpadding="0" cellspacing="0" border="0" align="center"><tr><td class="TitreDroits">Un Groupe lie les Droits des applications aux Comptes utilisateurs.</td></tr></table>
<table cellpadding="0" cellspacing="0" border="0" align="center"><tr><td><div id="TableContainer" class="TableContainer" style="overflow: auto;height:500px;width:auto;">
<%= process.generateTABLO_GROUPES() %> 
</div></td></tr></table>

<TABLE border="0" cellpadding="0" cellspacing="0" class="sigp2" align="center">
		<TR align="center">
				<TD><INPUT type="submit" name="<%= process.getNOM_PB_AJOUTER() %>" value="Ajouter" class="sigp2-Bouton-100"></TD>
				<TD><INPUT type="submit" name="<%= process.getNOM_PB_MODIFIER() %>" value="Modifier" class="sigp2-Bouton-100"></TD>
				<TD><INPUT type="submit" name="<%= process.getNOM_PB_SUPPRIMER() %>" value="Supprimer" class="sigp2-Bouton-100"></TD>				
		</TR>
</TABLE>

<TABLE border="0" cellpadding="0" cellspacing="0" class="sigp2" align="center">
		<TR align="center">
				<TD><INPUT type="submit" name="<%= process.getNOM_PB_ANNULER() %>" value="Retour" class="sigp2-Bouton-100"></TD>
		</TR>
</TABLE>
<INPUT name="JSP" type="hidden" value="<%= process.getJSP() %>">
</FORM>
	</body>
</html>
