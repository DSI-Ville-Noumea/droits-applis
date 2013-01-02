<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/MessageInformation.java" --%><%-- /jsf:pagecode --%>

<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>MessageInformation</title>
<meta http-equiv="Content-Type" content="text/html">
<meta name="GENERATOR" content="Rational Application Developer">
</head>
<BODY bgcolor="#FFFFFF" BGPROPERTIES="FIXED" background="images/fond.jpg" class="sigp2-BODY">
<jsp:useBean class="nc.mairie.commun.process.MessageInformation" id="process" scope="session"></jsp:useBean>
<%String message = process.getVAL_ST_MESSAGE();%>
<TABLE border="0" width="100%" height="100%" style="text-align : center;">
  <TBODY>
    <TR>
      <TD align="center">
			<FORM name="formu" method="POST">
      <BR>
			<TABLE width="590" border="0">
				<TBODY>
					<TR>
						<TD align="center"><FIELDSET style="background-color: #d8e4f8"><i><b><% if (message.toUpperCase().startsWith("INF")) { %> <FONT
							size="6">Message d'information</FONT> <%} else { %> <FONT
							size="6">Message d'erreur</FONT> <%}%></b></i></FIELDSET>
						<FIELDSET style="background-color: #d8e4f8"><BR>
						<TABLE
							border="0">
							<TBODY>
								<TR>
									<TD width="32"><b><% if (message.toUpperCase().startsWith("INF")) { %>
									<IMG src="images/info.gif" width="32" height="32" border="0"> <%} else { %>
									<IMG src="images/stop.gif" width="32" height="32" border="0"> <%}%></b></TD>
									<TD width="30"></TD>
									<TD valign="middle"><%=message%></TD>
									<TD valign="middle"></TD>
								</TR>
							</TBODY>
						</TABLE>
						<BR>
						</FIELDSET>
						<FIELDSET style="background-color: #d8e4f8">
							<INPUT type="submit" name="<%=process.getNOM_PB_OK()%>"
							value="OK" class="sigp2-Bouton-100">
						</FIELDSET></TD>
					</TR>
				</TBODY>
			</TABLE>
<INPUT name="JSP" type="hidden" value="<%= process.getJSP() %>">
			</FORM>
      </TD>
    </TR>
  </TBODY>
</TABLE>
</BODY>

</html>
