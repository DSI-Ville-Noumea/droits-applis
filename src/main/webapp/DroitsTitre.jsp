<!-- Sample JSP file -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page import="nc.mairie.droitsapplis.client.CheckDroits"%>
<%@page import="nc.mairie.technique.Transaction"%>
<%@page import="java.util.ArrayList"%>
<%@page import="nc.mairie.droitsapplis.process.DroitsTitre"%>
<%@page import="nc.mairie.droitsapplis.servlets.DroitsApplisServlet"%>
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.3 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<TITLE>
Applications
</TITLE>
<SCRIPT language="javascript" src="js/GestionBoutonDroit.js"></SCRIPT>
<SCRIPT language="javascript" src="js/GestionMenu.js"></SCRIPT>
<%

	nc.mairie.technique.UserAppli aUserAppli = (nc.mairie.technique.UserAppli)nc.mairie.technique.VariableGlobale.recuperer(request,nc.mairie.technique.VariableGlobale.GLOBAL_USER_APPLI);
	
	java.util.ArrayList listeDroits = aUserAppli.getListeDroits();
	
String res = 	"<script language=\"javascript\">\n"+
		"var listeDroits = new Array(\n";
		res+="   \"construction\",\n";

for (int i = 0; i < listeDroits.size(); i++){
	res+= "   \""+(String)listeDroits.get(i)+"\"";
	if (i+1 < listeDroits.size())
		res+=",\n";
	else	res+="\n";
}

res+=")</script>";
%>
<%=res%>
<LINK rel="stylesheet" href="theme/menu.css" type="text/css">
<LINK href="theme/sigp2.css" rel="stylesheet" type="text/css">
<script language="javascript">
var defaultTitre = "Gestion des droits sur les applications";
var titre = defaultTitre;

function init()
{
  B.setExpression("innerText","titre.toString()");
}

	
function changerTitre(chaine) {
	titre=chaine;
//	document.write(titre)
	document.recalc();
}
</script>
</HEAD>
<!-- background="images/fond_menu.jpg" -->
<BODY bgcolor="#ffffff" text="#000000" style="text-align : center;"><BASEFONT FACE="Arial" SIZE=2
onload='changerTitre(window.parent.frames("Titre").defaultTitre);'>
<table width="100%"><tr><td valign="top" align="left">
<IMG src="images/DSI03.gif" width="139" height="87" border="0" align="left">
</td><td valign="middle" align="center">

<FORM name="leForm" method="POST" target="Main" action="DroitsApplisServlet"><INPUT type="hidden" name="ACTIVITE" value="">
<SCRIPT>
var menu = new Menu(); 
<% if (listeDroits.contains("GestionDroits")){ %>
menu.ajouterFils(new Lien("GestionDroits", "Gestion des Droits", "Gestion des Droits pour les Applications", true));
<% } 
if (listeDroits.contains("GestionGroupes")){%>
menu.ajouterFils(new Lien("GestionGroupes", "Gestion des Groupes", "Gestion des Groupes d'utilisateurs pour les Applications", true));
<%}
if (listeDroits.contains("GestionComptes")){%>
menu.ajouterFils(new Lien("GestionComptes", "Gestion des Comptes", "Gestion des Comptes des utilisateurs", true));
<% } %>
document.write(menu.afficherhoriz()); 
</SCRIPT>
</FORM>
</td></tr></table>
</BODY>
</HTML>
