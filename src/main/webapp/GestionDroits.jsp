<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/GestionDroits.java" --%><%-- /jsf:pagecode --%>

<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<LINK href="theme/sigp2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="theme/dynatablo.css" type="text/css"> 
<title>Gestion des Droits</title>
<meta http-equiv="Content-Type" content="text/html">
<meta name="GENERATOR" content="Rational Application Developer">
<script type="text/JavaScript" src="js/sortTable.js"></script>
<script type="text/JavaScript" src="js/dynatablo.js"></script>
</head>
<jsp:useBean class="nc.mairie.droitsapplis.process.GestionDroits" id="process" scope="session"></jsp:useBean>
	<body>
	<FORM name="formu" method="POST">
<%@ include file="BanniereErreur.jsp" %>
<table cellpadding="0" cellspacing="0" border="0" align="center"><tr><td class="TitreDroits">Un Droit appartient à toto une application et est associé à plusieurs Groupes.</td></tr></table>
<table cellpadding="0" cellspacing="0" border="0" align="center"><tr><td><div id="TableContainer" class="TableContainer" style="overflow: auto;height:500px;width:900px;">
<%= process.generateTABLO_DROITS() %> 
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
