package nc.mairie.droitsapplis.process;

import java.util.ArrayList;

import nc.mairie.technique.*;
import nc.mairie.droitsapplis.metier.Compte;
/**
 * Process OeAGENTSelection
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
*/
public class OeAGENTSelection extends nc.mairie.technique.BasicProcess {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7190310296680234950L;
	public static final int STATUT_EDITCOMPTE=1;
	public static final int STATUT_LISTES_COMPTES=2;
	
	private java.lang.String[] LB_AGENT;
	private java.util.ArrayList<Compte> listeCompte;
	public String focus = null;
	private boolean first = true;
	private Compte theCompte;
/**
 * Constructeur du process OeAGENTSelection.
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
public OeAGENTSelection() {
	super();
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_AGENT
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
private String [] getLB_AGENT() {
	if (LB_AGENT == null)
		LB_AGENT = initialiseLazyLB();
	return LB_AGENT;
}
/**
 * Insérez la description de la méthode ici.
 *  Date de création : (01/01/2003 09:51:40)
 * @return java.util.ArrayList
 */
private java.util.ArrayList<Compte> getListeCompte() {
	if (listeCompte==null){
		listeCompte = new java.util.ArrayList<Compte>();
	}
	return listeCompte;
}
/**
 * Retourne le nom d'une zone de saisie pour la JSP :
 * EF_NOM_AGENT
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_EF_NOM_AGENT() {
	return "NOM_EF_NOM_AGENT";
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_AGENT
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_AGENT() {
	return "NOM_LB_AGENT";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_AGENT_SELECT
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_AGENT_SELECT() {
	return "NOM_LB_AGENT_SELECT";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_ANNULER
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_ANNULER() {
	return "NOM_PB_ANNULER";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_OK
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_OK() {
	return "NOM_PB_OK";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_RECHERCHER
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_RECHERCHER() {
	return "NOM_PB_RECHERCHER";
}
/**
 * Retourne la valeur à afficher par la JSP pour la zone de saisie  :
 * EF_NOM_AGENT
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_EF_NOM_AGENT() {
	return getZone(getNOM_EF_NOM_AGENT());
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_AGENT
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String []getVAL_LB_AGENT() {
	return getLB_AGENT();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_AGENT
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_LB_AGENT_SELECT() {
	return getZone(getNOM_LB_AGENT_SELECT());
}
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Récup de l'agent activité, s'il existe
	Compte compte = (Compte)VariableActivite.recuperer(this, VariableActivite.ACTIVITE_AGENT_MAIRIE);
	if (compte != null) {
		setCompteEnCours(compte);
		VariableActivite.enlever(this, VariableActivite.ACTIVITE_AGENT_MAIRIE);
	}
	if(isFirst()){
		addZone(getNOM_RG_RECHERCHE(),getNOM_RB_RECH_NOM());
		addZone(getNOM_RG_TRI(),getNOM_RB_TRI_NOM());
		setFirst(false);
	}

}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_ANNULER(javax.servlet.http.HttpServletRequest request) throws Exception {
	setStatut(STATUT_PROCESS_APPELANT);
	return true;
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_OK(javax.servlet.http.HttpServletRequest request) throws Exception {
	//Indice sélectionné dans la liste
	int indice = (Services.estNumerique(getZone(getNOM_LB_AGENT_SELECT())) ? Integer.parseInt(getZone(getNOM_LB_AGENT_SELECT())) : -1);

	//Si pas d'élément sélectionné
	if (indice == -1 || getListeCompte() == null || getListeCompte().size() == 0) {
		setStatut(STATUT_MEME_PROCESS,true,MairieMessages.getMessage("ERR997","comptes"));
		return false;
	}

	//VariableGlobale.ajouter(request, VariableGlobale.GLOBAL_AGENT_MAIRIE, getListeAgent().get(indice));
	VariableActivite.ajouter(this, GestionComptes.COMPTE_NOM, getListeCompte().get(indice).getCdidut());
	VariableActivite.ajouter(this, GestionComptes.ACTION_COMPTE, GestionComptes.AJOUTER_COMPTE);
	setStatut(STATUT_EDITCOMPTE);
	return true;
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_RECHERCHER(javax.servlet.http.HttpServletRequest request) throws Exception {

	String zone = getZone(getNOM_EF_NOM_AGENT());
	java.util.ArrayList<Compte> aListe = new java.util.ArrayList<Compte>();
	Compte unCompte=null;

	//System.out.println("ZONE="+zone);
	//On recherche par compte exact puis par compte approché, puis par nom approché

	//Si rien de saisi, recherche de tous les comptes
	if (zone.length() == 0) {
		aListe = Compte.listerCompte(getTransaction());
		//Sinon, selon la taille
	} else if (zone.length()<8) {
		unCompte = Compte.chercherCompte(getTransaction(), zone.toUpperCase());
		if (null==unCompte || null==unCompte.getCdidut()){
			aListe = Compte.listerCompteLikeCompte(getTransaction(), zone.toUpperCase());
		}
	}
	if ((null==aListe || !(aListe.size()>0))){
		if (null==unCompte || null==unCompte.getCdidut()){
			aListe = Compte.listerCompteLikeNom(getTransaction(), zone.toUpperCase());
		}else{
			aListe = new java.util.ArrayList<Compte>();
			aListe.add(unCompte);
		}
	}


	//Si erreur alors pas trouvé. On traite
	if (getTransaction().isErreur())  {
		getTransaction().traiterErreur();
	} 

	//Si la liste est vide alors erreur	
	if (null==aListe || aListe.size() == 0 || ( aListe.size()== 1 && (aListe.get(0)==null || aListe.get(0).getCdidut()==null))){
		setStatut(STATUT_MEME_PROCESS,false,MairieMessages.getMessage("ERR998"));
		return false;
	}

	setListeCompte(aListe);

	//Remplissage de la liste
	int [] tailles = {10,60};
	String [] attr = {"cdidut","liidut"};
	FormateListe aFormateListe = new FormateListe(tailles, getListeCompte(), attr);
	setLB_AGENT(aFormateListe.getListeFormatee());

	return true;
}
/**
 * Insérez la description de la méthode ici.
 *  Date de création : (28/03/2003 08:50:20)
 * @param newAgentActivite nc.mairie.salairerappels.metier.Agent
 */
private void setCompteEnCours(nc.mairie.droitsapplis.metier.Compte newCompte) {
	theCompte = newCompte;
}
@SuppressWarnings("unused")
private Compte getCompteEnCours() {
	return theCompte;
}
/**
 * Setter de la liste:
 * LB_AGENT
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
private void setLB_AGENT(java.lang.String[] newLB_AGENT) {
	LB_AGENT = newLB_AGENT;
}
/**
 * Insérez la description de la méthode ici.
 *  Date de création : (01/01/2003 09:51:40)
 * @param newListeAgent java.util.ArrayList
 */
private void setListeCompte(java.util.ArrayList<Compte> newListeCompte) {
	listeCompte = newListeCompte;
}
/**
 * @return Renvoie focus.
 */
public String getFocus() {
	if (focus == null) {
		focus=getDefaultFocus();
	}
	return focus;
}
/**
 * @return String
 */
public String getDefaultFocus() {
	return getNOM_EF_NOM_AGENT();
}
/**
 * @param focus focus à définir.
 */
public void setFocus(String focus) {
	this.focus = focus;
}
/**
 * Retourne le nom du groupe de radio boutons coché pour la JSP :
 * RG_RECHERCHE
 * Date de création : (08/10/08 13:07:23)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RG_RECHERCHE() {
	return "NOM_RG_RECHERCHE";
}
/**
 * Retourne la valeur du radio bouton (RB_) coché dans la JSP :
 * RG_RECHERCHE
 * Date de création : (08/10/08 13:07:23)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_RG_RECHERCHE() {
	return getZone(getNOM_RG_RECHERCHE());
}
/**
 * Retourne le nom du groupe de radio boutons coché pour la JSP :
 * RG_TRI
 * Date de création : (08/10/08 13:07:23)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RG_TRI() {
	return "NOM_RG_TRI";
}
/**
 * Retourne la valeur du radio bouton (RB_) coché dans la JSP :
 * RG_TRI
 * Date de création : (08/10/08 13:07:23)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_RG_TRI() {
	return getZone(getNOM_RG_TRI());
}
/**
 * Retourne le nom du radio bouton pour la JSP :
 * RB_RECH_NOM
 * Date de création : (08/10/08 13:07:23)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RB_RECH_NOM() {
	return "NOM_RB_RECH_NOM";
}
/**
 * Retourne le nom du radio bouton pour la JSP :
 * RB_RECH_PRENOM
 * Date de création : (08/10/08 13:07:23)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RB_RECH_PRENOM() {
	return "NOM_RB_RECH_PRENOM";
}
/**
 * Retourne le nom du radio bouton pour la JSP :
 * RB_TRI_NOM
 * Date de création : (08/10/08 13:07:23)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RB_TRI_NOM() {
	return "NOM_RB_TRI_NOM";
}
/**
 * Retourne le nom du radio bouton pour la JSP :
 * RB_TRI_NOMATR
 * Date de création : (08/10/08 13:07:23)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RB_TRI_NOCOMPTE() {
	return "NOM_RB_TRI_NOCOMPTE";
}

private boolean isFirst(){
	return first;
}
private void setFirst(boolean newFirst){
	first =  newFirst;
}

/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (01/01/03 09:35:10)
 * @author : Générateur de process
 */
public boolean recupererStatut(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si on arrive de la JSP alors on traite le get
	if (request.getParameter("JSP")!=null && request.getParameter("JSP").equals(getJSP())) {

		//Si clic sur le bouton PB_TRI
		if (testerParametre(request, getNOM_PB_TRI())) {
			return performPB_TRI(request);
		}

		//Si clic sur le bouton PB_ANNULER
		if (testerParametre(request, getNOM_PB_ANNULER())) {
			return performPB_ANNULER(request);
		}

		//Si clic sur le bouton PB_OK
		if (testerParametre(request, getNOM_PB_OK())) {
			return performPB_OK(request);
		}

		//Si clic sur le bouton PB_RECHERCHER
		if (testerParametre(request, getNOM_PB_RECHERCHER())) {
			return performPB_RECHERCHER(request);
		}

	}
	//Si pas de retour définit
	
	//setStatut(STATUT_PROCESS_APPELANT,false,"Erreur : TAG INPUT non géré par le process");
	
	setStatut(STATUT_MEME_PROCESS,false,"Erreur : TAG INPUT non géré par le process");
	return false;
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (08/10/08 14:13:26)
 * @author : Générateur de process
 */
public String getJSP() {
	return "OeAGENTSelection.jsp";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_TRI
 * Date de création : (08/10/08 14:13:26)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_TRI() {
	return "NOM_PB_TRI";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (08/10/08 14:13:26)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_TRI(javax.servlet.http.HttpServletRequest request) throws Exception {
	String tri = "cdidut";
	if(getVAL_RG_TRI().equals(getNOM_RB_TRI_NOM())){
		tri="liidut";
	}else if(getVAL_RG_TRI().equals(getNOM_RB_TRI_NOCOMPTE())){
		tri = "cdidut";
	}
	
	//Remplissage de la liste
	int [] tailles = {10,60};
	String [] attr = {"cdidut","liidut"};
	String [] colonnes = {tri};
	boolean []ordres = {true};
	ArrayList<Compte> a = Services.trier(getListeCompte(),colonnes,ordres);
	FormateListe aFormateListe = new FormateListe(tailles, a, attr);
	setLB_AGENT(aFormateListe.getListeFormatee());
	return true;
}
}
