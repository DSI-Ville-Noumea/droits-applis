<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/GestionGroupeAvance.java" --%><%-- /jsf:pagecode --%>

<%@page import="nc.mairie.droitsapplis.process.GestionGroupes"%>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<LINK href="theme/sigp2.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="theme/dynatablo.css" type="text/css"> 
<title>Gestion des Groupes</title>
<meta http-equiv="Content-Type" content="text/html">
<meta name="GENERATOR" content="Rational Application Developer">

<script type="text/JavaScript" src="js/sortTable.js"></script>

</head>
<jsp:useBean class="nc.mairie.droitsapplis.process.GestionGroupeAvance" id="process" scope="session"></jsp:useBean>
	<body>
	<FORM name="formu" method="POST">
<%@ include file="BanniereErreur.jsp" %>
<table cellpadding="0" cellspacing="0" border="0" align="center">
<%
			if (process.getSActionGroupeCourant().equals(
			GestionGroupes.SUPPRIMER_GROUPE)) {
%>
	<tr>
		<td align="center"><br>
		<span class="TitreDroits">Etes-vous sur de vouloir supprimer le groupe <span class="TitreDroitsLbl"><%=process.getVAL_ST_NOM()%></span>
		ainsi que toutes ses associations (comptes et droits) ? <br>
		</span>
		<br>
		<%
		} else {
		%>
		
	
	<tr>
		<td>
		<div id="TableContainer" class="TableContainer"
			style="height:550px;width:600px;text-align:center;"><br>
		<span class="TitreDroits">
		<%
				if (process.getSActionGroupeCourant().equals(
				GestionGroupes.AJOUTER_GROUPE)) {
		%>
		Ajouter un groupe <%
				} else if (process.getSActionGroupeCourant().equals(
				GestionGroupes.MODIFIER_GROUPE)) {
		%>
		Modifier le groupe <span class="TitreDroitsLbl"><%=process.getVAL_ST_NOM()%></span> <%
 }
 %>
		</span>
		<br>
		<TABLE>
			<TR>
				<TD width="119" valign="bottom" class="sigp2">Nom :</TD>
				<TD><INPUT class="sigp2-saisie" maxlength="50"
					name="<%= process.getNOM_EF_NOM() %>" size="50" type="text"
					value="<%= process.getVAL_EF_NOM() %>"></TD>
			</TR>
			<TR>
				<TD width="119" valign="bottom" class="sigp2">Description :</TD>
				<TD><INPUT class="sigp2-saisie" maxlength="50"
					name="<%= process.getNOM_EF_DESCRIPTION() %>" size="50" type="text"
					value="<%= process.getVAL_EF_DESCRIPTION() %>"></TD>
			</TR>
		</TABLE>
		<br>
		<span class="TitreDroits">Les Comptes associés au groupe <span class="TitreDroitsLbl"><%=process.getVAL_ST_NOM()%></span></span>
		<br>
		<TABLE class="sigp2">
			<TR>
				<TD width="40%">
				<FIELDSET><LEGEND class="sigp2Fieldset" align="left">Comptes</LEGEND>
				<TABLE border="0" class="sigp2" width="100%">
					<TR>
						<TD align="left" width="100%"><SELECT size="10"
							name="<%= process.getNOM_LB_GROUPES_GAUCHE_COMPTE() %>"
							class="sigp2-liste"
							style="text-transform: uppercase; width: 100%"
							ondblclick='executeBouton("<%= process.getNOM_PB_AJOUTER_COMPTE()%>")'>
							<%=process.forComboHTML(process
								.getVAL_LB_GROUPES_GAUCHE_COMPTE(), process
								.getVAL_LB_GROUPES_GAUCHE_COMPTE_SELECT())%>
						</SELECT>
					</TR>
					<tr>
						<td align="center"></td>
					</tr>
				</TABLE>
				</FIELDSET>
				</TD>
				<TD width="20%"><INPUT type="submit" value="Ajouter&gt;&gt;"
					name="<%=process.getNOM_PB_AJOUTER_COMPTE() %>" class="sigp2-Bouton-100"><BR>
				<%
				if (!process.isEmptyGroupeDroiteCompte) {
				%> <INPUT type="submit" value="&lt;&lt;Enlever"
					name="<%=process.getNOM_PB_SUPPRIMER_COMPTE() %>" class="sigp2-Bouton-100"></TD>
				<%
				}
				%>
				<TD width="40%">
				<FIELDSET><LEGEND class="sigp2Fieldset" align="left">Comptes
				associés à ce groupe</LEGEND>
				<TABLE border="0" class="sigp2" width="100%">
					<TR>
						<TD align="left" width="100%"><SELECT size="10"
							name="<%= process.getNOM_LB_GROUPES_DROITE_COMPTE() %>"
							class="sigp2-liste"
							style="text-transform: uppercase; width: 100%"
							ondblclick='executeBouton("<%= process.getNOM_PB_SUPPRIMER_COMPTE()%>")'>
							<%=process.forComboHTML(process
								.getVAL_LB_GROUPES_DROITE_COMPTE(), process
								.getVAL_LB_GROUPES_DROITE_COMPTE_SELECT())%>
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
		
		<BR>
		
		<span class="TitreDroits">Les Droits associés au groupe <span class="TitreDroitsLbl"><%=process.getVAL_ST_NOM()%></span></span>
		<br>
		<TABLE class="sigp2">
			<TR>
				<TD width="40%">
				<FIELDSET><LEGEND class="sigp2Fieldset" align="left">Droits</LEGEND>
				<TABLE border="0" class="sigp2" width="100%">
					<TR>
						<TD align="left" width="100%"><SELECT size="10"
							name="<%= process.getNOM_LB_GROUPES_GAUCHE_DROIT() %>"
							class="sigp2-liste"
							style="text-transform: uppercase; width: 100%"
							ondblclick='executeBouton("<%= process.getNOM_PB_AJOUTER_DROIT()%>")'>
							<%=process.forComboHTML(process
								.getVAL_LB_GROUPES_GAUCHE_DROIT(), process
								.getVAL_LB_GROUPES_GAUCHE_DROIT_SELECT())%>
						</SELECT>
					</TR>
					<tr>
						<td align="center"></td>
					</tr>
				</TABLE>
				</FIELDSET>
				</TD>
				<TD width="20%"><INPUT type="submit" value="Ajouter&gt;&gt;"
					name="<%=process.getNOM_PB_AJOUTER_DROIT() %>" class="sigp2-Bouton-100"><BR>
				<%
				if (!process.isEmptyGroupeDroiteDroit) {
				%> <INPUT type="submit" value="&lt;&lt;Enlever"
					name="<%=process.getNOM_PB_SUPPRIMER_DROIT() %>" class="sigp2-Bouton-100"></TD>
				<%
				}
				%>
				<TD width="40%">
				<FIELDSET><LEGEND class="sigp2Fieldset" align="left">Droits
				associés à ce groupe</LEGEND>
				<TABLE border="0" class="sigp2" width="100%">
					<TR>
						<TD align="left" width="100%"><SELECT size="10"
							name="<%= process.getNOM_LB_GROUPES_DROITE_DROIT() %>"
							class="sigp2-liste"
							style="text-transform: uppercase; width: 100%"
							ondblclick='executeBouton("<%= process.getNOM_PB_SUPPRIMER_DROIT()%>")'>
							<%=process.forComboHTML(process
								.getVAL_LB_GROUPES_DROITE_DROIT(), process
								.getVAL_LB_GROUPES_DROITE_DROIT_SELECT())%>
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
		
			<BR>
		
		
		</div></td></tr>
	
		<%
		}
		%>
		
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
