<BR><BR>
<TABLE width="100%">
<TR><TD class="sigp2-majuscule-titre" style="text-align : center; vertical-align : middle;">
Vous n'avez pas les droits n�cessaire pour acc�der � l'application de gestion des droits.
<BR>Veuillez contacter la DSI.
<BR><BR>
<% if (null!=aUserAppli){ %>
Compte: <FONT color="red"><%=aUserAppli.getUserName() %></FONT>
<% } %>
<BR><BR>
</TD></TR>
	<%@ include file="CartoucheQuit.jsp" %>   
</TABLE>


