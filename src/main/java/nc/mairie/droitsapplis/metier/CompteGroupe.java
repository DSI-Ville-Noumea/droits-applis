package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.BasicBroker;
import nc.mairie.technique.Transaction;

/**
 * Objet métier CompteGroupe.
 */
public class CompteGroupe extends nc.mairie.technique.BasicMetier {
	
	/** A REMPLIR. */
	public String cdidut;
	
	/** A REMPLIR. */
	public String cdgrou;
	
/**
 * Constructeur CompteGroupe.
 */
public CompteGroupe() {
	super();
}

/**
 * Getter de l'attribut cdidut.
 * 
 * @return A REMPLIR
 */
public final String getCdidut() {
	return cdidut;
}

/**
 * Setter de l'attribut cdidut.
 * 
 * @param newCdidut A REMPLIR
 */
public final void setCdidut(final String newCdidut) { 
	cdidut = newCdidut;
}

/**
 * Getter de l'attribut cdgrou.
 * 
 * @return le code du groupe
 */
public final String getCdgrou() {
	return cdgrou;
}

/**
 * Setter de l'attribut cdgrou.
 * 
 * @param newCdgrou le nouveau code du groupe
 */
public final void setCdgrou(final String newCdgrou) { 
	cdgrou = newCdgrou;
}

/**
 * Methode à définir dans chaque objet Métier pour instancier un Broker.
 * 
 * @return une instance de BasicBroker
 */
protected final BasicBroker definirMyBroker() { 
	return new CompteGroupeBroker(this); 
}

/**
 * Methode à définir dans chaque objet Métier pour instancier un Broker.
 * 
 * @return une instance de BasicBroker
 */
protected final CompteGroupeBroker getMyCompteGroupeBroker() {
	return (CompteGroupeBroker) getMyBasicBroker();
}

/**
* Renvoie une chaîne correspondant à la valeur de cet objet.
* @return une représentation sous forme de chaîne du destinataire
*/
public final String toString() {
	// Insérez ici le code pour finaliser le destinataire
	// Cette implémentation transmet le message au super. Vous pouvez remplacer ou compléter le message.
	return super.toString();
}
/**
 * Retourne un ArrayList d'objet métier : CompteGroupe.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerCompteGroupe(nc.mairie.technique.Transaction aTransaction) throws Exception{
	CompteGroupe unCompteGroupe = new CompteGroupe();
	return unCompteGroupe.getMyCompteGroupeBroker().listerCompteGroupe(aTransaction);
}

/**
 * Retourne un ArrayList d'objet métier : CompteGroupe.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerCompteGroupe(nc.mairie.technique.Transaction aTransaction, String sCompte) throws Exception{
	CompteGroupe unCompteGroupe = new CompteGroupe();
	return unCompteGroupe.getMyCompteGroupeBroker().listerCompteGroupe(aTransaction, sCompte);
}

/**
 * Retourne un ArrayList d'objet métier : CompteGroupe.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerCompteGroupefromGroupe(nc.mairie.technique.Transaction aTransaction, String sGroupe) throws Exception{
	CompteGroupe unCompteGroupe = new CompteGroupe();
	return unCompteGroupe.getMyCompteGroupeBroker().listerCompteGroupefromGroupe(aTransaction, sGroupe);
}

/**
 * Retourne un CompteGroupe.
 * 
 * @param aTransaction la transaction en cours
 * @param code code du compte recherché
 * @return le groupe recherché
 * @throws Exception 
 */
public static CompteGroupe chercherCompteGroupe(final Transaction aTransaction, final String code) throws Exception {
	CompteGroupe unCompteGroupe = new CompteGroupe();
	return unCompteGroupe.getMyCompteGroupeBroker().chercherCompteGroupe(aTransaction, code);
}

/**
 * Retourne un CompteGroupe.
 * 
 * @param aTransaction la transaction en cours
 * @param sCompte le nom du compte
 * @param cdGrou le code du groupe
 * @return le CompteGroupe cherché
 * @throws Exception 
 */
public static CompteGroupe chercherCompteGroupe(final Transaction aTransaction, final String sCompte, final String cdGrou) throws Exception {
	CompteGroupe unCompteGroupe = new CompteGroupe();
	return unCompteGroupe.getMyCompteGroupeBroker().chercherCompteGroupe(aTransaction, sCompte, cdGrou);
}

/**
 * Methode creerObjetMetier qui retourne
 * true ou false
 */
public boolean creerCompteGroupe(nc.mairie.technique.Transaction aTransaction )  throws Exception {
	//Creation du CompteGroupe
	return getMyCompteGroupeBroker().creerCompteGroupe(aTransaction);
}
/**
 * Methode modifierObjetMetier qui retourne
 * true ou false
 */
public boolean modifierCompteGroupe(nc.mairie.technique.Transaction aTransaction) throws Exception {
	//Modification du CompteGroupe
	return getMyCompteGroupeBroker().modifierCompteGroupe(aTransaction);
}
/**
 * Methode supprimerObjetMetier qui retourne
 * true ou false
 */
public boolean supprimerCompteGroupe(nc.mairie.technique.Transaction aTransaction) throws Exception{
	//Suppression de l'CompteGroupe
	return getMyCompteGroupeBroker().supprimerCompteGroupe(aTransaction);
}
}
