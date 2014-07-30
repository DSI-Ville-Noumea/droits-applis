package nc.mairie.droitsapplis.process;

import java.util.ArrayList;

import nc.mairie.droitsapplis.metier.Compte;
import nc.mairie.droitsapplis.metier.CompteGroupe;
import nc.mairie.droitsapplis.metier.DroitsApp;
import nc.mairie.droitsapplis.metier.Groupe;
import nc.mairie.droitsapplis.metier.GroupeDroits;
import nc.mairie.metier.Utils;
import nc.mairie.technique.*;
/**
 * Process GestionGroupeAvance
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
*/
public class GestionGroupeAvance extends nc.mairie.technique.BasicProcess {
	/**
	 * 
	 */
	private static final long serialVersionUID = -318398992548773858L;
	private java.lang.String[] LB_GROUPES_DROITE_COMPTE;
	private java.lang.String[] LB_GROUPES_GAUCHE_COMPTE;
	private java.lang.String[] LB_GROUPES_DROITE_DROIT;
	private java.lang.String[] LB_GROUPES_GAUCHE_DROIT;
	
	public boolean isEmptyGroupeDroiteCompte=true;
	public boolean isEmptyGroupeDroiteDroit=true;
	
	private ArrayList<DroitsApp> listGroupeDroiteInitDroit;
	private ArrayList<DroitsApp> listGroupeGaucheDroit;
	private ArrayList<DroitsApp> listGroupeDroiteDroit;
	private ArrayList<Compte> listGroupeDroiteInitCompte;
	private ArrayList<Compte> listGroupeGaucheCompte;
	private ArrayList<Compte> listGroupeDroiteCompte;
	
	private Groupe gGroupeCourant;
	private String sGroupeIdCourant="";
	private String sActionGroupeCourant="";

	private boolean isfirst=true;
	
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{
	//System.out.println("PASS initZone GestionGroupeAvance isfirst="+isfirst);
	
	if (isfirst){
		if (null!=VariableActivite.recuperer(this, GestionGroupes.GROUPE_ID)&&!"".equals(VariableActivite.recuperer(this, GestionGroupes.GROUPE_ID))){
			sGroupeIdCourant=(String)VariableActivite.recuperer(this, GestionGroupes.GROUPE_ID);
			gGroupeCourant=Groupe.chercherGroupe(getTransaction(), sGroupeIdCourant);
		}
		if (null!=VariableActivite.recuperer(this, GestionGroupes.ACTION_GROUPE)){
			sActionGroupeCourant=(String)VariableActivite.recuperer(this, GestionGroupes.ACTION_GROUPE);
		}

		if (gGroupeCourant!=null&&gGroupeCourant.getCdgrou()!=null&&!sActionGroupeCourant.equals(GestionGroupes.AJOUTER_GROUPE)){
			addZone(getNOM_ST_NOM(), getGGroupeCourant().getLigrou());
			addZone(getNOM_EF_NOM(), getGGroupeCourant().getLigrou());
			addZone(getNOM_ST_DESCRIPTION(), getGGroupeCourant().getDefinition());
			addZone(getNOM_EF_DESCRIPTION(), getGGroupeCourant().getDefinition());
		}
		
		if (!sActionGroupeCourant.equals(GestionGroupes.SUPPRIMER_GROUPE)){
			if (sGroupeIdCourant.length()<1)
				sGroupeIdCourant="-1";
			
			try{
				setListGroupeGaucheCompte(Compte.listerCompteOrderByCode(getTransaction()));
				setListGroupeDroiteInitCompte(Compte.listerComptesfromGroupeOrderByCode(getTransaction(), sGroupeIdCourant));
				setListGroupeDroiteCompte(new ArrayList<Compte>(getListGroupeDroiteInitCompte()));
				setListGroupeGaucheCompte(new ArrayList<Compte>(nc.mairie.metier.Utils.Elim_doubure_Compte(getListGroupeGaucheCompte(), getListGroupeDroiteCompte())));

				setListGroupeGaucheDroit(DroitsApp.listerDroitsApp(getTransaction()));
				setListGroupeDroiteInitDroit(DroitsApp.listerDroitsAppfromGroupe(getTransaction(), sGroupeIdCourant));
				setListGroupeDroiteDroit(new ArrayList<DroitsApp>(getListGroupeDroiteInitDroit()));
				setListGroupeGaucheDroit(new ArrayList<DroitsApp>(nc.mairie.metier.Utils.Elim_doubure_Droit(getListGroupeGaucheDroit(), getListGroupeDroiteDroit())));
			}catch (Exception e){
				System.out.println("ERREUR"+e);
				System.out.println(e.getMessage());
				getTransaction().declarerErreur("Erreur sur les groupes...\n"+e);
				return ;
			}
		}
		
		isfirst=false;
	}
	initialiseGroupesSides();

}

public void initialiseGroupesSides(){

	String[] lignesGaucheDroit=null;
	String[] lignesDroiteDroit=null;
	String[] lignesGaucheCompte=null;
	String[] lignesDroiteCompte=null;

	//Gestion tableaux des comptes
	if(null!=getListGroupeGaucheCompte()){
		lignesGaucheCompte=new String[getListGroupeGaucheCompte().size()];
		for (int i=0;i<getListGroupeGaucheCompte().size();i++){
			lignesGaucheCompte[i]=getListGroupeGaucheCompte().get(i).getCdidut();
		}
	}
	if(null!=getListGroupeDroiteCompte()){
		lignesDroiteCompte=new String[getListGroupeDroiteCompte().size()];
		for (int i=0;i<getListGroupeDroiteCompte().size();i++){
			lignesDroiteCompte[i]=getListGroupeDroiteCompte().get(i).getCdidut();
		}
		if (getListGroupeDroiteCompte().size()>0)
			isEmptyGroupeDroiteCompte=false;
	}

	setLB_GROUPES_GAUCHE_COMPTE(lignesGaucheCompte);
	setLB_GROUPES_DROITE_COMPTE(lignesDroiteCompte);
	
	//Gestion tableaux des droits
	if(null!=getListGroupeGaucheDroit()){
		lignesGaucheDroit=new String[getListGroupeGaucheDroit().size()];
		for (int i=0;i<getListGroupeGaucheDroit().size();i++){
			lignesGaucheDroit[i]=getListGroupeGaucheDroit().get(i).getDroit();
		}
	}
	if(null!=getListGroupeDroiteDroit()){
		lignesDroiteDroit=new String[getListGroupeDroiteDroit().size()];
		for (int i=0;i<getListGroupeDroiteDroit().size();i++){
			lignesDroiteDroit[i]=getListGroupeDroiteDroit().get(i).getDroit();
		}
		if (getListGroupeDroiteDroit().size()>0)
			isEmptyGroupeDroiteDroit=false;
	}

	setLB_GROUPES_GAUCHE_DROIT(lignesGaucheDroit);
	setLB_GROUPES_DROITE_DROIT(lignesDroiteDroit);
}

/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
public boolean recupererStatut(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si on arrive de la JSP alors on traite le get
	if (request.getParameter("JSP")!=null && request.getParameter("JSP").equals(getJSP())) {

		//Si clic sur le bouton PB_ANNULER
		if (testerParametre(request, getNOM_PB_ANNULER())) {
			return performPB_ANNULER(request);
		}

		//Si clic sur le bouton PB_VALIDER
		if (testerParametre(request, getNOM_PB_VALIDER())) {
			return performPB_VALIDER(request);
		}
		
		//Si clic sur le bouton PB_AJOUTER
		if (testerParametre(request, getNOM_PB_AJOUTER_COMPTE())) {
			return performPB_AJOUTER_COMPTE(request);
		}

		//Si clic sur le bouton PB_SUPPRIMER
		if (testerParametre(request, getNOM_PB_SUPPRIMER_COMPTE())) {
			return performPB_SUPPRIMER_COMPTE(request);
		}

		//Si clic sur le bouton PB_AJOUTER
		if (testerParametre(request, getNOM_PB_AJOUTER_DROIT())) {
			return performPB_AJOUTER_DROIT(request);
		}

		//Si clic sur le bouton PB_SUPPRIMER
		if (testerParametre(request, getNOM_PB_SUPPRIMER_DROIT())) {
			return performPB_SUPPRIMER_DROIT(request);
		}


	}
	//Si TAG INPUT non géré par le process
	setStatut(STATUT_MEME_PROCESS);
	return true;
}
/**
 * Constructeur du process GestionGroupeAvance.
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
public GestionGroupeAvance() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
public String getJSP() {
	return "GestionGroupeAvance.jsp";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_ANNULER
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_ANNULER() {
	return "NOM_PB_ANNULER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (07/05/09 10:16:09)
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
 * Retourne le nom d'un bouton pour la JSP :
 * PB_VALIDER
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_VALIDER() {
	return "NOM_PB_VALIDER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_VALIDER(javax.servlet.http.HttpServletRequest request) throws Exception {
	GroupeDroits gd;
	CompteGroupe cg;
	
	//cas suppression
	if (sActionGroupeCourant.equals(GestionGroupes.SUPPRIMER_GROUPE)){
		if (null!=gGroupeCourant){
			
			ArrayList<GroupeDroits> listgd=GroupeDroits.listerGroupeDroitsfromGroupe(getTransaction(), gGroupeCourant.getCdgrou());
			if (null!=listgd){
				for (int i=0;i<listgd.size(); i++){
					gd=listgd.get(i);
					gd.supprimerGroupeDroits(getTransaction());
				}
			}

				ArrayList<CompteGroupe> listcg=CompteGroupe.listerCompteGroupefromGroupe(getTransaction(), gGroupeCourant.getCdgrou());
				if (null!=listcg){
					for (int i=0;i<listcg.size(); i++){
						cg=listcg.get(i);
						cg.supprimerCompteGroupe(getTransaction());
					}
				}

			gGroupeCourant.supprimerGroupe(getTransaction());
		}
		//Cas on modifie ou on cree
	}else{
		
		if(null==gGroupeCourant){
			gGroupeCourant=new Groupe();
		}
		if (null==gGroupeCourant.getCdgrou()){
			gGroupeCourant.setCdgrou(String.valueOf(gGroupeCourant.nouvId(getTransaction())));
		}
		gGroupeCourant.setLigrou(getVAL_EF_NOM());
		gGroupeCourant.setDefinition(getVAL_EF_DESCRIPTION());
		

		if (sActionGroupeCourant.equals(GestionGroupes.AJOUTER_GROUPE)){
			//Cas création
			gGroupeCourant.creerGroupe(getTransaction());
		}else if (sActionGroupeCourant.equals(GestionGroupes.MODIFIER_GROUPE)){
			gGroupeCourant.modifierGroupe(getTransaction());
		}
		
		//Ici on s'occupe des enregistrements des 2 listes.
		if (gGroupeCourant!=null){

			ArrayList<Compte> listCompteAAjouter=new ArrayList<Compte>();
			ArrayList<Compte> listCompteAEnlever=new ArrayList<Compte>();
			ArrayList<DroitsApp> listDroitsAppAAjouter=new ArrayList<DroitsApp>();
			ArrayList<DroitsApp> listDroitsAppAEnlever=new ArrayList<DroitsApp>();
			
			//COMPTES
			if (getListGroupeDroiteInitCompte().size()>0){
				listCompteAAjouter=Utils.Elim_doubure_Compte(getListGroupeDroiteCompte(), getListGroupeDroiteInitCompte());
				listCompteAEnlever=Utils.Elim_doubure_Compte(getListGroupeDroiteInitCompte(), getListGroupeDroiteCompte());
			}else{
				listCompteAAjouter=listGroupeDroiteCompte;
			}

			//AEnlever
			if (listCompteAEnlever.size()>0){
				for (int i=0; i<listCompteAEnlever.size(); i++){
					cg=CompteGroupe.chercherCompteGroupe(getTransaction(), listCompteAEnlever.get(i).getCdidut(), gGroupeCourant.getCdgrou());
					if (null!=cg)
						cg.supprimerCompteGroupe(getTransaction());
				}
			}

			//AAjouter
			if (listCompteAAjouter.size()>0){
				for (int i=0; i<listCompteAAjouter.size(); i++){
					cg=new CompteGroupe();
					cg.setCdidut(listCompteAAjouter.get(i).getCdidut());
					cg.setCdgrou(gGroupeCourant.getCdgrou());
					cg.creerCompteGroupe(getTransaction());
				}
			}
			
			//DROITS
			if (getListGroupeDroiteInitDroit().size()>0){
				listDroitsAppAAjouter=Utils.Elim_doubure_Droit(getListGroupeDroiteDroit(), getListGroupeDroiteInitDroit());
				listDroitsAppAEnlever=Utils.Elim_doubure_Droit(getListGroupeDroiteInitDroit(), getListGroupeDroiteDroit());
			}else{
				listDroitsAppAAjouter=listGroupeDroiteDroit;
			}

			//AEnlever
			if (listDroitsAppAEnlever.size()>0){
				for (int i=0; i<listDroitsAppAEnlever.size(); i++){
					gd=GroupeDroits.chercherGroupeDroits(getTransaction(), listDroitsAppAEnlever.get(i).getCddrap(), gGroupeCourant.getCdgrou());
					if (null!=gd)
						gd.supprimerGroupeDroits(getTransaction());
				}
			}

			//AAjouter
			if (listDroitsAppAAjouter.size()>0){
				for (int i=0; i<listDroitsAppAAjouter.size(); i++){
					gd=new GroupeDroits();
					gd.setCddrap(listDroitsAppAAjouter.get(i).getCddrap());
					gd.setCdgrou(gGroupeCourant.getCdgrou());
					gd.creerGroupeDroits(getTransaction());
				}

		}
		}
	}
	
//	si erreur
	if(getTransaction().isErreur()){
		return false;
	}
	//tout s'est bien passé
	commitTransaction();
	
	VariableActivite.enlever(this,GestionGroupes.GROUPE_ID);
	VariableActivite.enlever(this,GestionGroupes.ACTION_GROUPE);
	setStatut(STATUT_PROCESS_APPELANT);
	return true;

}
/**
 * Retourne pour la JSP le nom de la zone statique :
 * ST_DESCRIPTION
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_ST_DESCRIPTION() {
	return "NOM_ST_DESCRIPTION";
}
/**
 * Retourne la valeur à afficher par la JSP  pour la zone :
 * ST_DESCRIPTION
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_ST_DESCRIPTION() {
	return getZone(getNOM_ST_DESCRIPTION());
}
/**
 * Retourne pour la JSP le nom de la zone statique :
 * ST_NOM
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_ST_NOM() {
	return "NOM_ST_NOM";
}
/**
 * Retourne la valeur à afficher par la JSP  pour la zone :
 * ST_NOM
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_ST_NOM() {
	return getZone(getNOM_ST_NOM());
}
/**
 * Retourne le nom d'une zone de saisie pour la JSP :
 * EF_DESCRIPTION
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_EF_DESCRIPTION() {
	return "NOM_EF_DESCRIPTION";
}
/**
 * Retourne la valeur à afficher par la JSP pour la zone de saisie  :
 * EF_DESCRIPTION
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_EF_DESCRIPTION() {
	return getZone(getNOM_EF_DESCRIPTION());
}
/**
 * Retourne le nom d'une zone de saisie pour la JSP :
 * EF_NOM
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_EF_NOM() {
	return "NOM_EF_NOM";
}
/**
 * Retourne la valeur à afficher par la JSP pour la zone de saisie  :
 * EF_NOM
 * Date de création : (07/05/09 10:16:09)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_EF_NOM() {
	return getZone(getNOM_EF_NOM());
}
public Groupe getGGroupeCourant() {
	return gGroupeCourant;
}
public void setGGroupeCourant(Groupe groupeCourant) {
	gGroupeCourant = groupeCourant;
}
public String getSActionGroupeCourant() {
	return sActionGroupeCourant;
}
public void setSActionGroupeCourant(String actionGroupeCourant) {
	sActionGroupeCourant = actionGroupeCourant;
}
public String getSGroupeIdCourant() {
	return sGroupeIdCourant;
}
public void setSGroupeIdCourant(String groupeIdCourant) {
	sGroupeIdCourant = groupeIdCourant;
}

/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_AJOUTER
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_AJOUTER_DROIT() {
	return "NOM_PB_AJOUTER_DROIT";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_AJOUTER_DROIT(javax.servlet.http.HttpServletRequest request) throws Exception {
	// on ajoute le groupe à la liste des groupes pour le compte
	int indice = (Services.estNumerique(getVAL_LB_GROUPES_GAUCHE_DROIT_SELECT()) ? Integer.parseInt(getVAL_LB_GROUPES_GAUCHE_DROIT_SELECT()): -1); 
	if (indice == -1) {
		getTransaction().declarerErreur("Vous devez sélectionner un droit");
		return false;
	}
	DroitsApp unDroit = (DroitsApp)getListGroupeGaucheDroit().get(indice);
	// on ajoute à la liste
	getListGroupeDroiteDroit().add(unDroit);
	getListGroupeGaucheDroit().remove(indice);
	return true;
}

/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_AJOUTER
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_AJOUTER_COMPTE() {
	return "NOM_PB_AJOUTER_COMPTE";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_AJOUTER_COMPTE(javax.servlet.http.HttpServletRequest request) throws Exception {
	// on ajoute le groupe à la liste des groupes pour le compte
	int indice = (Services.estNumerique(getVAL_LB_GROUPES_GAUCHE_COMPTE_SELECT()) ? Integer.parseInt(getVAL_LB_GROUPES_GAUCHE_COMPTE_SELECT()): -1);
	if (indice == -1) {
		getTransaction().declarerErreur("Vous devez sélectionner un compte");
		return false;
	}
	Compte unCompte = (Compte)getListGroupeGaucheCompte().get(indice);
	// on ajoute à la liste
	getListGroupeDroiteCompte().add(unCompte);
	getListGroupeGaucheCompte().remove(indice);
	return true;
}

/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_SUPPRIMER
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_SUPPRIMER_DROIT() {
	return "NOM_PB_SUPPRIMER_DROIT";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_SUPPRIMER_DROIT(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	// on ajoute le groupe à la liste des groupes pour le compte
	int indice = (Services.estNumerique(getVAL_LB_GROUPES_DROITE_DROIT_SELECT()) ? Integer.parseInt(getVAL_LB_GROUPES_DROITE_DROIT_SELECT()): -1); 
	if (indice == -1) {
		getTransaction().declarerErreur("Vous devez sélectionner un droit");
		return false;
	}
	DroitsApp unDroit = (DroitsApp)getListGroupeDroiteDroit().get(indice);
	// on ajoute à la liste
	getListGroupeGaucheDroit().add(unDroit);
	getListGroupeDroiteDroit().remove(indice);
	return true;
}


/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_SUPPRIMER
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_SUPPRIMER_COMPTE() {
	return "NOM_PB_SUPPRIMER_COMPTE";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_SUPPRIMER_COMPTE(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	// on ajoute le groupe à la liste des groupes pour le compte
	int indice = (Services.estNumerique(getVAL_LB_GROUPES_DROITE_COMPTE_SELECT()) ? Integer.parseInt(getVAL_LB_GROUPES_DROITE_COMPTE_SELECT()): -1); 
	if (indice == -1) {
		getTransaction().declarerErreur("Vous devez sélectionner un compte");
		return false;
	}
	Compte unCompte = (Compte)getListGroupeDroiteCompte().get(indice);
	// on ajoute à la liste
	getListGroupeGaucheCompte().add(unCompte);
	getListGroupeDroiteCompte().remove(indice);
	return true;
}

/**
 * Getter de la liste avec un lazy initialize :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private String [] getLB_GROUPES_DROITE_DROIT() {
	if (LB_GROUPES_DROITE_DROIT == null)
		LB_GROUPES_DROITE_DROIT = initialiseLazyLB();
	return LB_GROUPES_DROITE_DROIT;
}
/**
 * Setter de la liste:
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private void setLB_GROUPES_DROITE_DROIT(java.lang.String[] newLB_GROUPES_DROITE_DROIT) {
	LB_GROUPES_DROITE_DROIT = newLB_GROUPES_DROITE_DROIT;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_GROUPES_DROITE_DROIT() {
	return "NOM_LB_GROUPES_DROITE_DROIT";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_GROUPES_DROITE_SELECT
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_GROUPES_DROITE_DROIT_SELECT() {
	return "NOM_LB_GROUPES_DROITE_DROIT_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String [] getVAL_LB_GROUPES_DROITE_DROIT() {
	return getLB_GROUPES_DROITE_DROIT();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_LB_GROUPES_DROITE_DROIT_SELECT() {
	return getZone(getNOM_LB_GROUPES_DROITE_DROIT_SELECT());
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private String [] getLB_GROUPES_GAUCHE_DROIT() {
	if (LB_GROUPES_GAUCHE_DROIT == null)
		LB_GROUPES_GAUCHE_DROIT = initialiseLazyLB();
	return LB_GROUPES_GAUCHE_DROIT;
}
/**
 * Setter de la liste:
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private void setLB_GROUPES_GAUCHE_DROIT(java.lang.String[] newLB_GROUPES_GAUCHE_DROIT) {
	LB_GROUPES_GAUCHE_DROIT = newLB_GROUPES_GAUCHE_DROIT;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_GROUPES_GAUCHE_DROIT() {
	return "NOM_LB_GROUPES_GAUCHE_DROIT";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_GROUPES_GAUCHE_SELECT
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_GROUPES_GAUCHE_DROIT_SELECT() {
	return "NOM_LB_GROUPES_GAUCHE_DROIT_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String [] getVAL_LB_GROUPES_GAUCHE_DROIT() {
	return getLB_GROUPES_GAUCHE_DROIT();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_LB_GROUPES_GAUCHE_DROIT_SELECT() {
	return getZone(getNOM_LB_GROUPES_GAUCHE_DROIT_SELECT());
}

/**
 * Getter de la liste avec un lazy initialize :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private String [] getLB_GROUPES_DROITE_COMPTE() {
	if (LB_GROUPES_DROITE_COMPTE == null)
		LB_GROUPES_DROITE_COMPTE = initialiseLazyLB();
	return LB_GROUPES_DROITE_COMPTE;
}
/**
 * Setter de la liste:
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private void setLB_GROUPES_DROITE_COMPTE(java.lang.String[] newLB_GROUPES_DROITE_COMPTE) {
	LB_GROUPES_DROITE_COMPTE = newLB_GROUPES_DROITE_COMPTE;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_GROUPES_DROITE_COMPTE() {
	return "NOM_LB_GROUPES_DROITE_COMPTE";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_GROUPES_DROITE_SELECT
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_GROUPES_DROITE_COMPTE_SELECT() {
	return "NOM_LB_GROUPES_DROITE_COMPTE_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String [] getVAL_LB_GROUPES_DROITE_COMPTE() {
	return getLB_GROUPES_DROITE_COMPTE();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_GROUPES_DROITE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_LB_GROUPES_DROITE_COMPTE_SELECT() {
	return getZone(getNOM_LB_GROUPES_DROITE_COMPTE_SELECT());
}
/**
 * Getter de la liste avec un lazy initialize :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private String [] getLB_GROUPES_GAUCHE_COMPTE() {
	if (LB_GROUPES_GAUCHE_COMPTE == null)
		LB_GROUPES_GAUCHE_COMPTE = initialiseLazyLB();
	return LB_GROUPES_GAUCHE_COMPTE;
}
/**
 * Setter de la liste:
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
private void setLB_GROUPES_GAUCHE_COMPTE(java.lang.String[] newLB_GROUPES_GAUCHE_COMPTE) {
	LB_GROUPES_GAUCHE_COMPTE = newLB_GROUPES_GAUCHE_COMPTE;
}
/**
 * Retourne le nom de la zone pour la JSP :
 * NOM_LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_GROUPES_GAUCHE_COMPTE() {
	return "NOM_LB_GROUPES_GAUCHE_COMPTE";
}
/**
 * Retourne le nom de la zone de la ligne sélectionnée pour la JSP :
 * NOM_LB_GROUPES_GAUCHE_SELECT
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_LB_GROUPES_GAUCHE_COMPTE_SELECT() {
	return "NOM_LB_GROUPES_GAUCHE_COMPTE_SELECT";
}
/**
 * Méthode à personnaliser
 * Retourne la valeur à afficher pour la zone de la JSP :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String [] getVAL_LB_GROUPES_GAUCHE_COMPTE() {
	return getLB_GROUPES_GAUCHE_COMPTE();
}
/**
 * Méthode à personnaliser
 * Retourne l'indice à sélectionner pour la zone de la JSP :
 * LB_GROUPES_GAUCHE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_LB_GROUPES_GAUCHE_COMPTE_SELECT() {
	return getZone(getNOM_LB_GROUPES_GAUCHE_COMPTE_SELECT());
}
public ArrayList<Compte> getListGroupeDroiteCompte() {
	return listGroupeDroiteCompte;
}
public void setListGroupeDroiteCompte(ArrayList<Compte> listGroupeDroiteCompte) {
	this.listGroupeDroiteCompte = listGroupeDroiteCompte;
}
public ArrayList<DroitsApp> getListGroupeDroiteDroit() {
	return listGroupeDroiteDroit;
}
public void setListGroupeDroiteDroit(ArrayList<DroitsApp> listGroupeDroiteDroit) {
	this.listGroupeDroiteDroit = listGroupeDroiteDroit;
}
public ArrayList<Compte> getListGroupeGaucheCompte() {
	return listGroupeGaucheCompte;
}
public void setListGroupeGaucheCompte(ArrayList<Compte> listGroupeGaucheCompte) {
	this.listGroupeGaucheCompte = listGroupeGaucheCompte;
}
public ArrayList<DroitsApp> getListGroupeGaucheDroit() {
	return listGroupeGaucheDroit;
}
public void setListGroupeGaucheDroit(ArrayList<DroitsApp> listGroupeGaucheDroit) {
	this.listGroupeGaucheDroit = listGroupeGaucheDroit;
}
public ArrayList<Compte> getListGroupeDroiteInitCompte() {
	return listGroupeDroiteInitCompte;
}
public void setListGroupeDroiteInitCompte(
		ArrayList<Compte> listGroupeDroiteInitCompte) {
	this.listGroupeDroiteInitCompte = listGroupeDroiteInitCompte;
}
public ArrayList<DroitsApp> getListGroupeDroiteInitDroit() {
	return listGroupeDroiteInitDroit;
}
public void setListGroupeDroiteInitDroit(
		ArrayList<DroitsApp> listGroupeDroiteInitDroit) {
	this.listGroupeDroiteInitDroit = listGroupeDroiteInitDroit;
}

}
