<!-- Sample HTML file --> <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@page contentType="text/html;charset=UTF-8"%>
<HTML>
<HEAD>
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.3 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<!-- <meta http-equiv="X-UA-Compatible" content="chrome=1"> -->
<TITLE>Gestion des Droits des Applications</TITLE>
<SCRIPT language="JavaScript"> 
function simpleDialog(msg) { 
features = 
   'toolbar=no,location=no,directories=no,status=no,menubar=no,' +
   'scrollbars=no,resizable=no,width=200,height=100' 
dlg = window.open ("","DroitsAppLittle",features) 
dlg.document.write ("<BODY bgColor='black' text='white'>") 
dlg.document.write ("<H2><CENTER>",msg,"</CENTER></H2>") 
dlg.document.write ("<FORM><CENTER>") 
dlg.document.write ("<INPUT type='button' value='OK' onClick = 'self.close()'>") 
dlg.document.write ("</CENTER></FORM>") 
} 

function principal(fenetre) { 
features = 
   'toolbar=no,location=no,directories=no,status=no,menubar=no,' +
   'scrollbars=yes,resizable=yes,width=1024,height=768,screenX=0,screenY=0,left=0,top=0' 
dlg = window.open (fenetre,"_self",features) 
//opener=self;

//window.close();

//var frm = window.frames;
//var res=''
//for (i=0; i < frm.length; i++) 
//	res += i;

//res=window.frames.length
//liste.innerText=res

} 
function executeBouton(nom)
{
document.formu.elements[nom].click();
}

</SCRIPT></HEAD>
<BODY bgcolor="#FFFFFF"><P></P>Cliquer sur le bouton pour vous connecter
<FORM name="leForm" method="POST" target="_self" action="DroitsApplisServlet"><INPUT type="hidden" name="ACTIVITE" value="DroitsApplis">
<BR>
<INPUT type="submit" name="Connexion" value="Connexion" /><BR>
<BR>
<span id = "liste"></span>

</FORM>
</BODY>
</HTML>