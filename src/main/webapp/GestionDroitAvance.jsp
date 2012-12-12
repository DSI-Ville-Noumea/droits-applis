<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/GestionDroitAvance.java" --%><%-- /jsf:pagecode --%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="nc.mairie.droitsapplis.process.GestionDroitAvance"%>
<%@page import="nc.mairie.droitsapplis.process.GestionDroits"%>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<LINK href="theme/sigp2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="theme/dynatablo.css" type="text/css"> 
<title>Gestion des Droits</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="GENERATOR" content="Rational Application Developer">
<script type="text/JavaScript" src="js/sortTable.js"></script>

</head>
<jsp:useBean class="nc.mairie.droitsapplis.process.GestionDroitAvance" id="process" scope="session"></jsp:useBean>
<body>
<FORM name="formu" method="POST"><%@ include
	file="BanniereErreur.jsp"%>
<table cellpadding="0" cellspacing="0" border="0" align="center">
	<%
	if (process.isSuppression) {
	%>
	<tr>
		<td align="center"><br>
		<span class="TitreDroits">Etes-vous sur de vouloir supprimer le droit <span class="TitreDroitsLbl"><%=process.getVAL_ST_NOM()%></span>
		ainsi que tous ses groupes associés ? <br>
		</span>
		<br>
		<%
		} else {
		%>
		
	<tr>
		<td>
		<div id="TableContainer" class="TableContainer"
			style="height:350px;width:600px;text-align:center;"><br>
		<span class="TitreDroits">
		<%
				if (process.getSActionDroitCourant().equals(
				GestionDroits.AJOUTER_DROIT)) {
		%> Ajouter un droit <%
				} else if (process.getSActionDroitCourant().equals(
				GestionDroits.MODIFIER_DROIT)) {
		%> Modifier le droit <span class="TitreDroitsLbl"><%=process.getVAL_ST_NOM()%></span> <%
 }
 %>
		</span>
		<br>
		<TABLE>
			<TR>
				<TD width="119" valign="bottom" class="sigp2">Nom (identique à celui défini dans l'application) :</TD>
				<TD><INPUT class="sigp2-saisie" maxlength="50"
					name="<%= process.getNOM_EF_NOM() %>" size="50" type="text"
					value="<%= process.getVAL_EF_NOM() %>"></TD>
			</TR>
			<TR>
				<TD width="119" valign="bottom" class="sigp2">Choisir une Application :</TD>
				<TD><SELECT class="sigp2-saisie"
					name="<%= process.getNOM_LB_APPLICATION() %>">
					<%=process.forComboHTML(process.getVAL_LB_APPLICATION(),
								process.getVAL_LB_APPLICATION_SELECT())%>
				</SELECT> <span class="sigp2">&nbsp; ou en définir une nouvelle :&nbsp;</span> <INPUT class="sigp2-saisie"
					maxlength="50" name="<%= process.getNOM_EF_APPLICATION() %>"
					size="25" type="text"
					value="<%= process.getVAL_EF_APPLICATION() %>"></TD>
			</TR>
		</TABLE>
		<br>
		<span class="TitreDroits">Les Groupes associés au droit <span class="TitreDroitsLbl"><%=process.getVAL_ST_NOM()%></span></span>
		<br>
		<TABLE class="sigp2">
			<TR>
				<TD width="40%">
				<FIELDSET><LEGEND class="sigp2Fieldset" align="left">Groupes</LEGEND>
				<TABLE border="0" class="sigp2" width="100%">
					<TR>
						<TD align="left" width="100%"><SELECT size="10"
							name="<%= process.getNOM_LB_GROUPES_GAUCHE() %>"
							class="sigp2-liste"
							style="text-transform: uppercase; width: 100%"
							ondblclick='executeBouton("<%= process.getNOM_PB_AJOUTER()%>")'>
							<%=process.forComboHTML(process
								.getVAL_LB_GROUPES_GAUCHE(), process
								.getVAL_LB_GROUPES_GAUCHE_SELECT())%>
						</SELECT>
					</TR>
					<tr>
						<td align="center"></td>
					</tr>
				</TABLE>
				</FIELDSET>
				</TD>
				<TD width="20%"><INPUT type="submit" value="Ajouter&gt;&gt;"
					name="<%=process.getNOM_PB_AJOUTER() %>" class="sigp2-Bouton-100"><BR>
				<%
				if (!process.isEmptyGroupeDroite) {
				%> <INPUT type="submit" value="&lt;&lt;Enlever"
					name="<%=process.getNOM_PB_SUPPRIMER() %>" class="sigp2-Bouton-100"></TD>
				<%
				}
				%>
				<TD width="40%">
				<FIELDSET><LEGEND class="sigp2Fieldset" align="left">Groupes
				associés à ce droit</LEGEND>
				<TABLE border="0" class="sigp2" width="100%">
					<TR>
						<TD align="left" width="100%"><SELECT size="10"
							name="<%= process.getNOM_LB_GROUPES_DROITE() %>"
							class="sigp2-liste"
							style="text-transform: uppercase; width: 100%"
							ondblclick='executeBouton("<%= process.getNOM_PB_SUPPRIMER()%>")'>
							<%=process.forComboHTML(process
								.getVAL_LB_GROUPES_DROITE(), process
								.getVAL_LB_GROUPES_DROITE_SELECT())%>
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
		<%
			}
			%>
		
</TABLE>
<BR>
<TABLE border="0" cellpadding="0" cellspacing="0" class="sigp2"
	align="center">
	<TR align="center">
		<TD><INPUT type="submit"
			name="<%= process.getNOM_PB_VALIDER() %>" value="Valider"
			class="sigp2-Bouton-100"></TD>
		<TD><INPUT type="submit"
			name="<%= process.getNOM_PB_ANNULER() %>" value="Annuler"
			class="sigp2-Bouton-100"></TD>
	</TR>
</TABLE>

<BR>

<INPUT name="JSP" type="hidden" value="<%= process.getJSP() %>">
</FORM>
</body>
</html>
