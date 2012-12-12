package nc.mairie.droitsapplis.process;

import java.util.ArrayList;

import nc.mairie.technique.*;
/**
 * Process SeatIndex
 * Date de cr�ation : (24/08/07 07:53:07)
 * @author : G�n�rateur de process
*/
public class DroitsMain extends nc.mairie.technique.BasicProcess {
	private String focus = null;
	public String dpt = "toto";
/**
 * Initialisation des zones � afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la m�thode addZone(getNOMxxx, String);
 * Date de cr�ation : (24/08/07 07:53:07)
 * @author : G�n�rateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{
	//System.out.println("PASS initZone DroitsMain");
	UserAppli aUser = (UserAppli)nc.mairie.technique.VariableGlobale.recuperer(request,nc.mairie.technique.VariableGlobale.GLOBAL_USER_APPLI);
	java.util.Hashtable h = nc.mairie.technique.MairieLDAP.chercherUserLDAPAttributs(aUser.getUserName());
	dpt = (String)h.get("department");
	
	//ArrayList listDroits = 
	/*
	ArrayList listService = Service.chercherListServiceAccro(getTransaction(),dpt);
	if(getTransaction().isErreur()){
		return;
	}
	if(listService.size()>0){
		Service unService = (Service)listService.get(0);
		VariableGlobale.ajouter(request,"SERVICE",unService);
	}
*/
}
/**
 * M�thode appel�e par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de cr�ation : (24/08/07 07:53:07)
 * @author : G�n�rateur de process
 */
public boolean recupererStatut(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si on arrive de la JSP alors on traite le get
	if (request.getParameter("JSP")!=null && request.getParameter("JSP").equals(getJSP())) {

	}
	//Si TAG INPUT non g�r� par le process
	setStatut(STATUT_MEME_PROCESS);
	return true;
}
/**
 * Constructeur du process SeatIndex.
 * Date de cr�ation : (24/08/07 07:53:07)
 * @author : G�n�rateur de process
 */
public DroitsMain() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone � utiliser dans un champ cach� dans chaque formulaire de la JSP.
 * Date de cr�ation : (24/08/07 07:53:07)
 * @author : G�n�rateur de process
 */
public String getJSP() {
	return "DroitsMain.jsp";
}
}
