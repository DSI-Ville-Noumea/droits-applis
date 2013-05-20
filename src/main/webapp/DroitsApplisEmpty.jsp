<%@page import="nc.mairie.technique.UserAppli"%>
<%@page import="nc.mairie.servlets.Frontale"%>
<%@page contentType="text/html;charset=UTF-8"%>
<BR><BR>
<TABLE width="100%">
<TR><TD class="sigp2-majuscule-titre" style="text-align : center; vertical-align : middle;">
Vous n'avez pas les droits nécessaire pour accéder à l'application de gestion des droits.
<BR>Veuillez contacter la DSI.
<BR><BR>
<% 
nc.mairie.technique.UserAppli toto = (nc.mairie.technique.UserAppli)nc.mairie.technique.VariableGlobale.recuperer(request,nc.mairie.technique.VariableGlobale.GLOBAL_USER_APPLI);
if (null!=toto){ %>
Compte: <FONT color="red"><%=toto.getUserName() %></FONT>
<% } %>
<BR><BR>
</TD></TR>
	<%@ include file="CartoucheQuit.jsp" %>   
</TABLE>


