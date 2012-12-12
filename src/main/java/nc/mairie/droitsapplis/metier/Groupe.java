package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.MairieMessages;
/**
 * Objet métier Groupe
 */
public class Groupe extends nc.mairie.technique.BasicMetier {
	public String ligrou;
	public String cdgrou;
	public String definition;
/**
 * Constructeur Groupe.
 */
public Groupe() {
	super();
}
/**
 * Getter de l'attribut ligrou.
 */
public String getLigrou() {
	return ligrou;
}
/**
 * Setter de l'attribut ligrou.
 */
public void setLigrou(String newLigrou) { 
	ligrou = newLigrou;
}
/**
 * Getter de l'attribut cdgrou.
 */
public String getCdgrou() {
	return cdgrou;
}
/**
 * Setter de l'attribut cdgrou.
 */
public void setCdgrou(String newCdgrou) { 
	cdgrou = newCdgrou;
}
/**
 * Getter de l'attribut definition.
 */
public String getDefinition() {
	return definition;
}
/**
 * Setter de l'attribut definition.
 */
public void setDefinition(String newDefinition) { 
	definition = newDefinition;
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
*/
protected nc.mairie.technique.BasicBroker definirMyBroker() { 
	return new GroupeBroker(this); 
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
*/
protected GroupeBroker getMyGroupeBroker() {
	return (GroupeBroker)getMyBasicBroker();
}
/**
* Renvoie une chaîne correspondant à la valeur de cet objet.
* @return une représentation sous forme de chaîne du destinataire
*/
public String toString() {
	// Insérez ici le code pour finaliser le destinataire
	// Cette implémentation transmet le message au super. Vous pouvez remplacer ou compléter le message.
	return super.toString();
}
/**
 * Retourne un ArrayList d'objet métier : Groupe.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerGroupe(nc.mairie.technique.Transaction aTransaction) throws Exception{
	Groupe unGroupe = new Groupe();
	return unGroupe.getMyGroupeBroker().listerGroupe(aTransaction);
}
/**
 * Retourne un Groupe.
 * @return Groupe
 */
public static Groupe chercherGroupe(nc.mairie.technique.Transaction aTransaction, String groupid) throws Exception{
	Groupe unGroupe = new Groupe();
	return unGroupe.getMyGroupeBroker().chercherGroupe(aTransaction, groupid);
}
/**
 * Methode creerObjetMetier qui retourne
 * true ou false
 */
public boolean creerGroupe(nc.mairie.technique.Transaction aTransaction )  throws Exception {
	
	//	on ajoute le code
	int nouvCode = nouvId(aTransaction);
	if (null==getCdgrou())
		setCdgrou(String.valueOf(nouvCode));

	//Creation du Groupe
	return getMyGroupeBroker().creerGroupe(aTransaction);
}
/**
 * Methode modifierObjetMetier qui retourne
 * true ou false
 */
public boolean modifierGroupe(nc.mairie.technique.Transaction aTransaction) throws Exception {
	//Modification du Groupe
	return getMyGroupeBroker().modifierGroupe(aTransaction);
}
/**
 * Methode supprimerObjetMetier qui retourne
 * true ou false
 */
public boolean supprimerGroupe(nc.mairie.technique.Transaction aTransaction) throws Exception{
	//Suppression de l'Groupe
	return getMyGroupeBroker().supprimerGroupe(aTransaction);
}

public static java.util.ArrayList<Groupe> listerGroupefromCompte(nc.mairie.technique.Transaction aTransaction, String compte) throws Exception{
	Groupe unGroupe = new Groupe();
	return unGroupe.getMyGroupeBroker().listerGroupefromCompte(aTransaction, compte);
}

public static java.util.ArrayList<Groupe> listerGroupefromDroit(nc.mairie.technique.Transaction aTransaction, String droit) throws Exception{
	Groupe unGroupe = new Groupe();
	return unGroupe.getMyGroupeBroker().listerGroupefromDroit(aTransaction, droit);
}

public static java.util.ArrayList<Groupe> listerGroupefromCompteAndAppli(nc.mairie.technique.Transaction aTransaction, String compte, String appli) throws Exception{
	Groupe unGroupe = new Groupe();
	return unGroupe.getMyGroupeBroker().listerGroupefromCompteAndAppli(aTransaction, compte, appli);
}

public int nouvId(nc.mairie.technique.Transaction aTransaction) throws Exception{
	//	recherche du dernier 
	int nouveauCode = getMyGroupeBroker().nouvId(aTransaction);
	
	//si pas trouvé
	if (nouveauCode == -1) {
		//fonctionnellement normal: table vide
		nouveauCode = 1;
	} else {
		nouveauCode++;
	}
	return nouveauCode;
}
}
