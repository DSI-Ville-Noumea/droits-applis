<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=UTF-8"%>
<%-- jsf:pagecode language="java" location="/src/pagecode/GestionCompteAvance.java" --%><%-- /jsf:pagecode --%>

<%@page import="nc.mairie.droitsapplis.process.GestionCompteAvance"%>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<LINK href="theme/sigp2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="theme/dynatablo.css" type="text/css"> 
<title>Gestion des Comptes</title>
<meta name="GENERATOR" content="Rational Application Developer">
<!-- <meta http-equiv="X-UA-Compatible" content="chrome=1"> -->

<script type="text/JavaScript" src="js/sortTable.js"></script>

</head>
<jsp:useBean class="nc.mairie.droitsapplis.process.GestionCompteAvance" id="process" scope="session"></jsp:useBean>
	<body>
	<FORM name="formu" method="POST">
<%@ include file="BanniereErreur.jsp" %>
<table cellpadding="0" cellspacing="0" border="0" align="center">
<%if(process.isSuppression){ %>
<tr><td align="center">
<br>
<span class="TitreDroits">Etes-vous sur de vouloir supprimer tous les groupes (droits) associés à ce compte ?
<br>
<span class="color: red;"><%= process.getVAL_ST_NOM_COMPTE() %></span></span>
<br>
<% }else{ %>
<tr><td><div id="TableContainer" class="TableContainer" style="height:250px;width:600px;text-align:center;">
<br>
<span class="TitreDroits">Les Groupes associés au compte <span class="TitreDroitsLbl"><%= process.getVAL_ST_NOM_COMPTE() %></span></span>
<br>
			<TABLE class="sigp2">
				<TR>
					<TD width="40%">
						<FIELDSET><LEGEND class="sigp2Fieldset" align="left">Groupes</LEGEND>
						<TABLE border="0" class="sigp2" width="100%">
							<TR>
								<TD align="left" width="100%">
									<SELECT size="10" name="<%= process.getNOM_LB_GROUPES_GAUCHE() %>"
								class="sigp2-liste" style="text-transform: uppercase; width: 100%"
								ondblclick='executeBouton("<%= process.getNOM_PB_AJOUTER()%>")'>
								<%= process.forComboHTML(process.getVAL_LB_GROUPES_GAUCHE(),process.getVAL_LB_GROUPES_GAUCHE_SELECT()) %>
							</SELECT>
							</TR>
							<tr>
								<td align="center"></td>
							</tr>
						</TABLE>
					</FIELDSET>
					</TD>
					<TD  width="20%"><INPUT type="submit" value="Ajouter&gt;&gt;"
					name="<%=process.getNOM_PB_AJOUTER() %>" class="sigp2-Bouton-100"><BR>
	<%if(!process.isEmptyGroupeDroite){ %>
				<INPUT
					type="submit" value="&lt;&lt;Enlever"
					name="<%=process.getNOM_PB_SUPPRIMER() %>" class="sigp2-Bouton-100"></TD>
	<%} %>
					<TD width="40%">
						<FIELDSET><LEGEND class="sigp2Fieldset" align="left">Groupes associés à ce compte</LEGEND>
						<TABLE border="0" class="sigp2"  width="100%">
							<TR>
								<TD align="left" width="100%">
									<SELECT size="10" name="<%= process.getNOM_LB_GROUPES_DROITE() %>"
								class="sigp2-liste" style="text-transform: uppercase; width: 100%" ondblclick='executeBouton("<%= process.getNOM_PB_SUPPRIMER()%>")'>
								<%= process.forComboHTML(process.getVAL_LB_GROUPES_DROITE(),process.getVAL_LB_GROUPES_DROITE_SELECT()) %>
							</SELECT>
							</TR>
							<TR>
								<TD align="center"></TD>
			
							</TR>
						</TABLE>
			</FIELDSET>
					</TD>
				</TR>
				</TABLE>
		</div></td></tr>
<% } %>
			</TABLE>
<BR>
<TABLE border="0" cellpadding="0" cellspacing="0" class="sigp2" align="center">
		<TR align="center">
				<TD><INPUT type="submit" name="<%= process.getNOM_PB_VALIDER() %>" value="Valider" class="sigp2-Bouton-100"></TD>
				<TD><INPUT type="submit" name="<%= process.getNOM_PB_ANNULER() %>" value="Annuler" class="sigp2-Bouton-100"></TD>				
		</TR>
</TABLE>
		
			<BR>

<INPUT name="JSP" type="hidden" value="<%= process.getJSP() %>">
</FORM>
	</body>
</html>
