package nc.mairie.droitsapplis.process;

import java.util.ArrayList;

import nc.mairie.droitsapplis.metier.DroitsApp;
import nc.mairie.droitsapplis.metier.Groupe;
import nc.mairie.droitsapplis.metier.GroupeDroits;
import nc.mairie.metier.Utils;
import nc.mairie.technique.Services;
import nc.mairie.technique.VariableActivite;
/**
 * Process GestionDroitAvance
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
*/
public class GestionDroitAvance extends nc.mairie.technique.BasicProcess {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1681404872573609214L;
	private java.lang.String[] LB_APPLICATION;
	private java.lang.String[] LB_GROUPES_DROITE;
	private java.lang.String[] LB_GROUPES_GAUCHE;
	
	
	public boolean isEmptyGroupeDroite=true;
	
	private ArrayList<Groupe> listGroupeDroiteInit;
	private ArrayList<Groupe> listGroupeGauche;
	private ArrayList<Groupe> listGroupeDroite;
	private ArrayList<String> listApplications;
	
	private DroitsApp daDroitCourant;
	private String sDroitIdCourant="";
	private String sActionDroitCourant="";
	public boolean isSuppression = false;
	
	private boolean isfirst=true;
	
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{
	//System.out.println("PASS initZone GestionDroitAvance first="+isfirst);
	//System.out.println("APP DROIT ID  ="+VariableActivite.recuperer(this, GestionDroits.DROIT_ID));
	if (isfirst){
		if (null!=VariableActivite.recuperer(this, GestionDroits.DROIT_ID)&&!"".equals(VariableActivite.recuperer(this, GestionDroits.DROIT_ID))){
			sDroitIdCourant=(String)VariableActivite.recuperer(this, GestionDroits.DROIT_ID);
			daDroitCourant=DroitsApp.chercherDroitsApp(getTransaction(), sDroitIdCourant);
		}
		if (null!=VariableActivite.recuperer(this, GestionDroits.ACTION_DROIT)){
			sActionDroitCourant=(String)VariableActivite.recuperer(this, GestionDroits.ACTION_DROIT);
		}

		if (sDroitIdCourant!=null&&!sDroitIdCourant.equals("")&&!sActionDroitCourant.equals(GestionDroits.AJOUTER_DROIT)){
			setDaDroitCourant(DroitsApp.chercherDroitsApp(getTransaction(), sDroitIdCourant));
			addZone(getNOM_ST_NOM(), getDaDroitCourant().getDroit());
			addZone(getNOM_EF_NOM(), getDaDroitCourant().getDroit());
		}
		
		if (sActionDroitCourant.equals(GestionDroits.AJOUTER_DROIT)){
			sDroitIdCourant="";
			daDroitCourant=null;
			addZone(getNOM_ST_NOM(), "");
			addZone(getNOM_EF_NOM(), "");
		}
		
		if (!sActionDroitCourant.equals(GestionDroits.SUPPRIMER_DROIT)){
			try{
				setListGroupeGauche(Groupe.listerGroupe(getTransaction()));
				if (sDroitIdCourant.length()>0){
					setListGroupeDroiteInit(Groupe.listerGroupefromDroit(getTransaction(), sDroitIdCourant));
					setListGroupeDroite(new ArrayList<Groupe>(getListGroupeDroiteInit()));
				}
				setListGroupeGauche(new ArrayList<Groupe>(nc.mairie.metier.Utils.Elim_doubure_Groupe(getListGroupeGauche(), getListGroupeDroite())));
				setListApplications(nc.mairie.droitsapplis.metier.DroitsApp.listerApplications(getTransaction()));
			}catch (Exception e){
				System.out.println("ARF ERREUR"+e);
				System.out.println(e.getMessage());
				getTransaction().declarerErreur("Erreur sur les groupes...\n"+e);
				return ;
			}

			
				String[] lignesApplication=null;
				if(null!=getListApplications()){
					lignesApplication=new String[getListApplications().size()];
					for (int i=0;i<getListApplications().size();i++){
						lignesApplication[i]=(String)getListApplications().get(i);
						//System.out.println("lignesapplication["+i+"]="+lignesApplication[i]);
						if (sActionDroitCourant.equals(GestionDroits.MODIFIER_DROIT)){
							if (lignesApplication[i].equals(daDroitCourant.getApplication()))
								addZone(getNOM_LB_APPLICATION_SELECT(),String.valueOf(i));	
						}
					}
				}
				setLB_APPLICATION(lignesApplication);
		
		}else{
			isSuppression=true;
		}
		
		
		isfirst=false;
	}
	
	initialiseGroupesSides();
	
}

public void initialiseGroupesSides(){


	String[] lignesGauche=null;
	String[] lignesDroite=null;

	if(null!=getListGroupeGauche()){
		lignesGauche=new String[getListGroupeGauche().size()];
		for (int i=0;i<getListGroupeGauche().size();i++){
			lignesGauche[i]=getListGroupeGauche().get(i).getLigrou();
		}
	}
	if(null!=getListGroupeDroite()){
		lignesDroite=new String[getListGroupeDroite().size()];
		for (int i=0;i<getListGroupeDroite().size();i++){
			lignesDroite[i]=getListGroupeDroite().get(i).getLigrou();
		}
		if (getListGroupeDroite().size()>0)
			isEmptyGroupeDroite=false;
	}

	setLB_GROUPES_GAUCHE(lignesGauche);
	setLB_GROUPES_DROITE(lignesDroite);

}
/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public boolean recupererStatut(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si on arrive de la JSP alors on traite le get
	if (request.getParameter("JSP")!=null && request.getParameter("JSP").equals(getJSP())) {

		//Si clic sur le bouton PB_AJOUTER
		if (testerParametre(request, getNOM_PB_AJOUTER())) {
			return performPB_AJOUTER(request);
		}

		//Si clic sur le bouton PB_ANNULER
		if (testerParametre(request, getNOM_PB_ANNULER())) {
			return performPB_ANNULER(request);
		}

		//Si clic sur le bouton PB_SUPPRIMER
		if (testerParametre(request, getNOM_PB_SUPPRIMER())) {
			return performPB_SUPPRIMER(request);
		}

		//Si clic sur le bouton PB_VALIDER
		if (testerParametre(request, getNOM_PB_VALIDER())) {
			return performPB_VALIDER(request);
		}

	}
	//Si TAG INPUT non géré par le process
	setStatut(STATUT_MEME_PROCESS);
	return true;
}
/**
 * Constructeur du process GestionDroitAvance.
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public GestionDroitAvance() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public String getJSP() {
	return "GestionDroitAvance.jsp";
}

/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_AJOUTER
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_AJOUTER() {
	return "NOM_PB_AJOUTER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public boolean performPB_AJOUTER(javax.servlet.http.HttpServletRequest request) throws Exception {
	// on ajoute le groupe à la liste des groupes pour le compte
	int indice = (Services.estNumerique(getVAL_LB_GROUPES_GAUCHE_SELECT()) ? Integer.parseInt(getVAL_LB_GROUPES_GAUCHE_SELECT()): -1); 
	if (indice == -1) {
		getTransaction().declarerErreur("Vous devez sélectionner un élement");
		return false;
	}
	Groupe unGroupe = (Groupe)getListGroupeGauche().get(indice);
	// on ajoute à la liste
	getListGroupeDroite().add(unGroupe);
	getListGroupeGauche().remove(indice);
	return true;
}

/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_SUPPRIMER
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_SUPPRIMER() {
	return "NOM_PB_SUPPRIMER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public boolean performPB_SUPPRIMER(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	// on ajoute le groupe à la liste des groupes pour le compte
	int indice = (Services.estNumerique(getVAL_LB_GROUPES_DROITE_SELECT()) ? Integer.parseInt(getVAL_LB_GROUPES_DROITE_SELECT()): -1); 
	if (indice == -1) {
		getTransaction().declarerErreur("Vous devez sélectionner un élement");
		return false;
	}
	Groupe unGroupe = (Groupe)getListGroupeDroite().get(indice);
	// on ajoute à la liste
	getListGroupeGauche().add(unGroupe);
	getListGroupeDroite().remove(indice);
	return true;
}


/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_ANNULER
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_ANNULER() {
	return "NOM_PB_ANNULER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public boolean performPB_ANNULER(javax.servlet.http.HttpServletRequest request) throws Exception {
	setStatut(STATUT_PROCESS_APPELANT);
	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_VALIDER
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_VALIDER() {
	return "NOM_PB_VALIDER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public boolean performPB_VALIDER(javax.servlet.http.HttpServletRequest request) throws Exception {

	GroupeDroits gd;


	
	
	//Cas on supprime
	if (isSuppression){
		ArrayList<GroupeDroits> listgd=GroupeDroits.listerGroupeDroits(getTransaction(), sDroitIdCourant);
		if (null!=listgd){
			for (int i=0;i<listgd.size(); i++){
				gd=listgd.get(i);
				gd.supprimerGroupeDroits(getTransaction());
			}
		}
		
		if (null!=daDroitCourant){
			daDroitCourant.supprimerDroitsApp(getTransaction());
		}
		
	//Cas on modifie ou on cree
	}else{
		if (null==daDroitCourant){
			daDroitCourant=new DroitsApp();
		}
		if (null==daDroitCourant.getCddrap()){
			daDroitCourant.setCddrap(String.valueOf(daDroitCourant.nouvId(getTransaction())));
		}
		
		daDroitCourant.setDroit(getVAL_EF_NOM());
		if (getVAL_EF_APPLICATION()!=null&&getVAL_EF_APPLICATION().length()>0){
			daDroitCourant.setApplication(getVAL_EF_APPLICATION());
		}else{
			int indice = (Services.estNumerique(getVAL_LB_APPLICATION_SELECT()) ? Integer.parseInt(getVAL_LB_APPLICATION_SELECT()): -1);
			//System.out.println("INDICE SELECTIONNE="+indice);
			if (indice == -1) {
				getTransaction().declarerErreur("Vous devez sélectionner une application pour ce droit");
				return false;
			}
			daDroitCourant.setApplication(getListApplications().get(indice));
		}

		if (sActionDroitCourant.equals(GestionDroits.AJOUTER_DROIT)){
			//Cas création
			daDroitCourant.creerDroitsApp(getTransaction());
		}else if (sActionDroitCourant.equals(GestionDroits.MODIFIER_DROIT)){
			daDroitCourant.modifierDroitsApp(getTransaction());
		}
		
		
		if (daDroitCourant.getCddrap()!=null && !daDroitCourant.getCddrap().equals("")){
			//System.out.println("passDroiteInit="+getListGroupeDroiteInit());
			//System.out.println("passDroite="+getListGroupeDroite());
			//System.out.println("passGauche"+getListGroupeGauche());
			ArrayList<Groupe> listAAjouter=new ArrayList<Groupe>();
			ArrayList<Groupe> listAEnlever=new ArrayList<Groupe>();
			if (getListGroupeDroiteInit().size()>0){
				listAAjouter=Utils.Elim_doubure_Groupe(getListGroupeDroite(), getListGroupeDroiteInit());
				listAEnlever=Utils.Elim_doubure_Groupe(getListGroupeDroiteInit(), getListGroupeDroite());
			}else{
				listAAjouter=listGroupeDroite;
			}

			//AEnlever
			if (listAEnlever.size()>0){
				for (int i=0; i<listAEnlever.size(); i++){
					gd=GroupeDroits.chercherGroupeDroits(getTransaction(), daDroitCourant.getCddrap(), listAEnlever.get(i).getCdgrou());
					if (null!=gd)
						gd.supprimerGroupeDroits(getTransaction());
				}
			}

			//AAjouter
			if (listAAjouter.size()>0){
				for (int i=0; i<listAAjouter.size(); i++){
					gd=new GroupeDroits();
					gd.setCddrap(daDroitCourant.getCddrap());
					gd.setCdgrou(listAAjouter.get(i).getCdgrou());
					gd.creerGroupeDroits(getTransaction());
				}
			}

		}

	}
	//si erreur
	if(getTransaction().isErreur()){
		return false;
	}
	//tout s'est bien passé
	commitTransaction();

	//on vide les zones
	setLB_GROUPES_GAUCHE(LBVide);
	setLB_GROUPES_DROITE(LBVide);
	VariableActivite.enlever(this,GestionDroits.DROIT_ID);
	VariableActivite.enlever(this,GestionDroits.ACTION_DROIT);
	setStatut(STATUT_PROCESS_APPELANT);
	return true;
}
/**
 * Retourne pour la JSP le nom de la zone statique :
 * ST_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_ST_APPLICATION() {
	return "NOM_ST_APPLICATION";
}
/**
 * Retourne la valeur à afficher par la JSP  pour la zone :
 * ST_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getVAL_ST_APPLICATION() {
	return getZone(getNOM_ST_APPLICATION());
}
/**
 * Retourne pour la JSP le nom de la zone statique :
 * ST_GROUPE
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_ST_GROUPE() {
	return "NOM_ST_GROUPE";
}
/**
 * Retourne la valeur à afficher par la JSP  pour la zone :
 * ST_GROUPE
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getVAL_ST_GROUPE() {
	return getZone(getNOM_ST_GROUPE());
}
/**
 * Retourne pour la JSP le nom de la zone statique :
 * ST_NOM
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_ST_NOM() {
	return "NOM_ST_NOM";
}
/**
 * Retourne la valeur à afficher par la JSP  pour la zone :
 * ST_NOM
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getVAL_ST_NOM() {
	return getZone(getNOM_ST_NOM());
}
/**
 * Retourne le nom d'une zone de saisie pour la JSP :
 * EF_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_EF_APPLICATION() {
	return "NOM_EF_APPLICATION";
}
/**
 * Retourne la valeur à afficher par la JSP pour la zone de saisie  :
 * EF_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getVAL_EF_APPLICATION() {
	return getZone(getNOM_EF_APPLICATION());
}
/**
 * Retourne le nom d'une zone de saisie pour la JSP :
 * EF_NOM
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_EF_NOM() {
	return "NOM_EF_NOM";
}
/**
 * Retourne la valeur à afficher par la JSP pour la zone de saisie  :
 * EF_NOM
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getVAL_EF_NOM() {
	return getZone(getNOM_EF_NOM());
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
private String [] getLB_APPLICATION() {
	if (LB_APPLICATION == null)
		LB_APPLICATION = initialiseLazyLB();
	return LB_APPLICATION;
}
/**
 * Setter de la liste:
 * LB_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
private void setLB_APPLICATION(java.lang.String[] newLB_APPLICATION) {
	LB_APPLICATION = newLB_APPLICATION;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_APPLICATION() {
	return "NOM_LB_APPLICATION";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_APPLICATION_SELECT
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_APPLICATION_SELECT() {
	return "NOM_LB_APPLICATION_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String [] getVAL_LB_APPLICATION() {
	return getLB_APPLICATION();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_APPLICATION
 * Date de création : (30/04/09 15:21:25)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_APPLICATION_SELECT() {
	return getZone(getNOM_LB_APPLICATION_SELECT());
}

/**
 * Getter de la liste avec un lazy initialize :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private String [] getLB_GROUPES_DROITE() {
	if (LB_GROUPES_DROITE == null)
		LB_GROUPES_DROITE = initialiseLazyLB();
	return LB_GROUPES_DROITE;
}
/**
 * Setter de la liste:
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private void setLB_GROUPES_DROITE(java.lang.String[] newLB_GROUPES_DROITE) {
	LB_GROUPES_DROITE = newLB_GROUPES_DROITE;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_GROUPES_DROITE() {
	return "NOM_LB_GROUPES_DROITE";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_GROUPES_DROITE_SELECT
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_GROUPES_DROITE_SELECT() {
	return "NOM_LB_GROUPES_DROITE_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String [] getVAL_LB_GROUPES_DROITE() {
	return getLB_GROUPES_DROITE();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_GROUPES_DROITE_SELECT() {
	return getZone(getNOM_LB_GROUPES_DROITE_SELECT());
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private String [] getLB_GROUPES_GAUCHE() {
	if (LB_GROUPES_GAUCHE == null)
		LB_GROUPES_GAUCHE = initialiseLazyLB();
	return LB_GROUPES_GAUCHE;
}
/**
 * Setter de la liste:
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private void setLB_GROUPES_GAUCHE(java.lang.String[] newLB_GROUPES_GAUCHE) {
	LB_GROUPES_GAUCHE = newLB_GROUPES_GAUCHE;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_GROUPES_GAUCHE() {
	return "NOM_LB_GROUPES_GAUCHE";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_GROUPES_GAUCHE_SELECT
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getNOM_LB_GROUPES_GAUCHE_SELECT() {
	return "NOM_LB_GROUPES_GAUCHE_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String [] getVAL_LB_GROUPES_GAUCHE() {
	return getLB_GROUPES_GAUCHE();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getVAL_LB_GROUPES_GAUCHE_SELECT() {
	return getZone(getNOM_LB_GROUPES_GAUCHE_SELECT());
}

public ArrayList<Groupe> getListGroupeDroite() {
	if (null==listGroupeDroite)
		listGroupeDroite=new ArrayList<Groupe>();
	return listGroupeDroite;
}

public void setListGroupeDroite(ArrayList<Groupe> listGroupeDroite) {
	this.listGroupeDroite = listGroupeDroite;
}

public ArrayList<Groupe> getListGroupeGauche() {
	if (null==listGroupeGauche)
		listGroupeGauche=new ArrayList<Groupe>();
	return listGroupeGauche;
}

public void setListGroupeGauche(ArrayList<Groupe> listGroupeGauche) {
	this.listGroupeGauche = listGroupeGauche;
}

public ArrayList<Groupe> getListGroupeDroiteInit() {
	if (null==listGroupeDroiteInit)
		listGroupeDroiteInit=new ArrayList<Groupe>();
	return listGroupeDroiteInit;
}

public void setListGroupeDroiteInit(ArrayList<Groupe> listGroupeDroiteInit) {
	this.listGroupeDroiteInit = listGroupeDroiteInit;
}

public ArrayList<String> getListApplications() {
	if (null==listApplications)
		listApplications=new ArrayList<String>();
	return listApplications;
}

public void setListApplications(ArrayList<String> listApplications) {
	this.listApplications = listApplications;
}

public DroitsApp getDaDroitCourant() {
	return daDroitCourant;
}

public void setDaDroitCourant(DroitsApp daDroitCourant) {
	this.daDroitCourant = daDroitCourant;
}

public String getSActionDroitCourant() {
	return sActionDroitCourant;
}

public void setSActionDroitCourant(String actionDroitCourant) {
	sActionDroitCourant = actionDroitCourant;
}

}
