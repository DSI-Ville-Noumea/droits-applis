package nc.mairie.droitsapplis.process;

import java.util.ArrayList;
import nc.mairie.metier.*;

import nc.mairie.droitsapplis.metier.CompteGroupe;
import nc.mairie.droitsapplis.metier.Groupe;
import nc.mairie.technique.*;
/**
 * Process GestionCompteAvance
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
*/
public class GestionCompteAvance extends nc.mairie.technique.BasicProcess {
	private java.lang.String[] LB_GROUPES_DROITE;
	private java.lang.String[] LB_GROUPES_GAUCHE;
	
	public boolean isEmptyGroupeDroite=true;
	
	private ArrayList<Groupe> listGroupeDroiteInit;
	private ArrayList<Groupe> listGroupeGauche;
	private ArrayList<Groupe> listGroupeDroite;
	
	private String sCompteCourant="";
	private String sActionCompteCourant="";
	public boolean isSuppression = false;
	
	private boolean isfirst=true;
	
	
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{
	//System.out.println("PASS initZone GestionCompteAvance first="+isfirst);
	
	if (isfirst){
		if (null!=VariableActivite.recuperer(this, GestionComptes.COMPTE_NOM)){
			sCompteCourant=(String)VariableActivite.recuperer(this, GestionComptes.COMPTE_NOM);
			addZone(getNOM_ST_NOM_COMPTE(), sCompteCourant);
		}
		if (null!=VariableActivite.recuperer(this, GestionComptes.ACTION_COMPTE)){
			sActionCompteCourant=(String)VariableActivite.recuperer(this, GestionComptes.ACTION_COMPTE);
		}

		if(sCompteCourant.equals("")){
			getTransaction().declarerErreur("Veuillez choisir un compte à modifier.");
			return ;
		}

		if (!sActionCompteCourant.equals(GestionComptes.SUPPRIMER_COMPTE)){
			try{
				setListGroupeGauche(Groupe.listerGroupe(getTransaction()));
				setListGroupeDroiteInit(Groupe.listerGroupefromCompte(getTransaction(), sCompteCourant));
				setListGroupeDroite(new ArrayList<Groupe>(getListGroupeDroiteInit()));
				setListGroupeGauche(new ArrayList<Groupe>(nc.mairie.metier.Utils.Elim_doubure_Groupe(getListGroupeGauche(), getListGroupeDroite())));
			}catch (Exception e){
				System.out.println("ARF ERREUR"+e);
				getTransaction().declarerErreur("Erreur sur les groupes...\n"+e);
				return ;
			}
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
			//System.out.println("lignesGauche["+i+"]="+lignesGauche[i]);
		}
	}
	if(null!=getListGroupeDroite()){
		lignesDroite=new String[getListGroupeDroite().size()];
		for (int i=0;i<getListGroupeDroite().size();i++){
			lignesDroite[i]=getListGroupeDroite().get(i).getLigrou();
			//System.out.println("lignesDroite["+i+"]="+lignesDroite[i]);
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
 * Date de création : (27/04/09 16:06:40)
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
 * Constructeur du process GestionCompteAvance.
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public GestionCompteAvance() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public String getJSP() {
	return "GestionCompteAvance.jsp";
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
 * PB_ANNULER
 * Date de création : (27/04/09 16:06:40)
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
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public boolean performPB_ANNULER(javax.servlet.http.HttpServletRequest request) throws Exception {
	setStatut(STATUT_PROCESS_APPELANT);
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
 * PB_VALIDER
 * Date de création : (27/04/09 16:06:40)
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
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public boolean performPB_VALIDER(javax.servlet.http.HttpServletRequest request) throws Exception {

	CompteGroupe cg;

	//Cas on supprime
	if (isSuppression){
		ArrayList<CompteGroupe> listcg=CompteGroupe.listerCompteGroupe(getTransaction(), sCompteCourant);
		if (null!=listcg){
			for (int i=0;i<listcg.size(); i++){
				cg=listcg.get(i);
				cg.supprimerCompteGroupe(getTransaction());
			}
		}
	//Cas on modifie ou on cree
	}else{
		if (sCompteCourant!=null && !sCompteCourant.equals("")){
			//System.out.println("pass1");
			//System.out.println("passDroiteInit="+getListGroupeDroiteInit());
			//System.out.println("passDroite="+getListGroupeDroite());
			//System.out.println("passGauche"+getListGroupeGauche());
			ArrayList<Groupe> listAAjouter=new ArrayList<Groupe>();
			ArrayList<Groupe> listAEnlever=new ArrayList<Groupe>();
			if (getListGroupeDroiteInit().size()>0){
				//System.out.println("pass2");
				listAAjouter=Utils.Elim_doubure_Groupe(getListGroupeDroite(), getListGroupeDroiteInit());
				listAEnlever=Utils.Elim_doubure_Groupe(getListGroupeDroiteInit(), getListGroupeDroite());
			}else{
				//System.out.println("pass3");
				listAAjouter=listGroupeDroite;
			}
			//System.out.println("pass4");

			//AEnlever
			if (listAEnlever.size()>0){
				//System.out.println("pass5.0");
				for (int i=0; i<listAEnlever.size(); i++){
					//System.out.println("pass5");
					cg=CompteGroupe.chercherCompteGroupe(getTransaction(), sCompteCourant, listAEnlever.get(i).getCdgrou());
					//cg.setCdidut(sCompteCourant);
					//cg.setCdgrou(listAEnlever.get(i).getCdgrou());
					if (null!=cg)
						cg.supprimerCompteGroupe(getTransaction());
				}
			}

			//AAjouter
			if (listAAjouter.size()>0){
				for (int i=0; i<listAAjouter.size(); i++){
					cg=new CompteGroupe();
					cg.setCdidut(sCompteCourant);
					cg.setCdgrou(listAAjouter.get(i).getCdgrou());
					cg.creerCompteGroupe(getTransaction());
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
	VariableActivite.enlever(this,GestionComptes.COMPTE_NOM);
	VariableActivite.enlever(this,GestionComptes.ACTION_COMPTE);
	setStatut(STATUT_PROCESS_APPELANT);
	return true;
}
/**
 * Retourne pour la JSP le nom de la zone statique :
 * ST_NOM_COMPTE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getNOM_ST_NOM_COMPTE() {
	return "NOM_ST_NOM_COMPTE";
}
/**
 * Retourne la valeur à afficher par la JSP  pour la zone :
 * ST_NOM_COMPTE
 * Date de création : (27/04/09 16:06:40)
 * @author : Générateur de process
 */
public java.lang.String getVAL_ST_NOM_COMPTE() {
	return getZone(getNOM_ST_NOM_COMPTE());
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
	return listGroupeDroite;
}

public void setListGroupeDroite(ArrayList<Groupe> listGroupeDroite) {
	this.listGroupeDroite = listGroupeDroite;
}

public ArrayList<Groupe> getListGroupeGauche() {
	return listGroupeGauche;
}

public void setListGroupeGauche(ArrayList<Groupe> listGroupeGauche) {
	this.listGroupeGauche = listGroupeGauche;
}

public ArrayList<Groupe> getListGroupeDroiteInit() {
	return listGroupeDroiteInit;
}

public void setListGroupeDroiteInit(ArrayList<Groupe> listGroupeDroiteInit) {
	this.listGroupeDroiteInit = listGroupeDroiteInit;
}

}
