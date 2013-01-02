package nc.mairie.droitsapplis.process;

import nc.mairie.technique.*;
/**
 * Process Accueil
 * Date de création : (13/05/09 12:35:45)
 * @author : Générateur de process
*/
public class Accueil extends nc.mairie.technique.BasicProcess {
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (13/05/09 12:35:45)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{
	//System.out.println("PASS initZone Accueil");
}
/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (13/05/09 12:35:45)
 * @author : Générateur de process
 */
public boolean recupererStatut(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si on arrive de la JSP alors on traite le get
	if (request.getParameter("JSP")!=null && request.getParameter("JSP").equals(getJSP())) {

		//Si clic sur le bouton PB_CONNEXION
		if (testerParametre(request, getNOM_PB_CONNEXION())) {
			return performPB_CONNEXION(request);
		}

	}
	//Si TAG INPUT non géré par le process
	setStatut(STATUT_MEME_PROCESS);
	return true;
}
/**
 * Constructeur du process Accueil.
 * Date de création : (13/05/09 12:35:45)
 * @author : Générateur de process
 */
public Accueil() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (13/05/09 12:35:45)
 * @author : Générateur de process
 */
public String getJSP() {
	return "Accueil.jsp";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_CONNEXION
 * Date de création : (13/05/09 12:35:45)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_CONNEXION() {
	return "NOM_PB_CONNEXION";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (13/05/09 12:35:45)
 * @author : Générateur de process
 */
public boolean performPB_CONNEXION(javax.servlet.http.HttpServletRequest request) throws Exception {
	return true;
}
}
