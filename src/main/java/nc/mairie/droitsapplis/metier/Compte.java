package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.BasicBroker;
import nc.mairie.technique.Transaction;

/**
 * Objet m�tier Compte.
 */
public class Compte extends nc.mairie.technique.BasicMetier {
	
	/** A REMPLIR. */
	private String cdidut;
	
	/** A REMPLIR. */
	private String liidut;
	
	/** A REMPLIR. */
	private String printer;
	
/**
 * Constructeur Compte.
 */
public Compte() {
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
 * Getter de l'attribut liidut.
 * 
 * @return A REMPLIR
 */
public final String getLiidut() {
	return liidut;
}

/**
 * Setter de l'attribut liidut.
 * 
 * @param newLiidut A REMPLIR
 */
public final void setLiidut(final String newLiidut) { 
	liidut = newLiidut;
}

/**
 * Getter de l'attribut printer.
 * 
 * @return A REMPLIR
 */
public final String getPrinter() {
	return printer;
}

/**
 * Setter de l'attribut printer.
 * 
 * @param newPrinter A REMPLIR
 */
public final void setPrinter(final String newPrinter) { 
	printer = newPrinter;
}

/**
 * Methode � d�finir dans chaque objet M�tier pour instancier un Broker.
 * 
 * @return une instance de BasicBroker
 */
protected final BasicBroker definirMyBroker() { 
	return new CompteBroker(this); 
}

/**
 * Methode � d�finir dans chaque objet M�tier pour instancier un Broker.
 * 
 * @return une instance de CompteBroker
 */
protected final CompteBroker getMyCompteBroker() {
	return (CompteBroker) getMyBasicBroker();
}

/**
* Renvoie une cha�ne correspondant � la valeur de cet objet.
* @return une repr�sentation sous forme de cha�ne du destinataire
*/
public final String toString() {
	// Ins�rez ici le code pour finaliser le destinataire
	// Cette impl�mentation transmet le message au super. Vous pouvez remplacer ou compl�ter le message.
	return super.toString();
}

/**
 * Retourne un ArrayList d'objet m�tier : Compte.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerCompte(nc.mairie.technique.Transaction aTransaction) throws Exception{
	Compte unCompte = new Compte();
	return unCompte.getMyCompteBroker().listerCompte(aTransaction);
}
public static java.util.ArrayList listerCompteOrderByCode(nc.mairie.technique.Transaction aTransaction) throws Exception{
	Compte unCompte = new Compte();
	return unCompte.getMyCompteBroker().listerCompteOrderByCode(aTransaction);
}
public static java.util.ArrayList listerComptesfromGroupe(nc.mairie.technique.Transaction aTransaction, String groupe) throws Exception{
	Compte unCompte = new Compte();
	return unCompte.getMyCompteBroker().listerComptesfromGroupe(aTransaction, groupe);
}
public static java.util.ArrayList listerComptesfromGroupeOrderByCode(nc.mairie.technique.Transaction aTransaction, String groupe) throws Exception{
	Compte unCompte = new Compte();
	return unCompte.getMyCompteBroker().listerComptesfromGroupeOrderByCode(aTransaction, groupe);
}

/**
 * Retourne un Compte.
 * 
 * @param aTransaction la transaction en cours
 * @param sCompte le nom du compte
 * @return le Compte demand�
 * @throws Exception 
 */
public static Compte chercherCompte(final Transaction aTransaction, final String sCompte) throws Exception {
	Compte unCompte = new Compte();
	return unCompte.getMyCompteBroker().chercherCompte(aTransaction, sCompte);
}

public static java.util.ArrayList listerCompteLikeCompte(nc.mairie.technique.Transaction aTransaction, String sCompte) throws Exception{
	Compte unCompte = new Compte();
	return unCompte.getMyCompteBroker().listerCompteLikeCompte(aTransaction, sCompte);
}

public static java.util.ArrayList listerCompteLikeNom(nc.mairie.technique.Transaction aTransaction, String sNom) throws Exception{
	Compte unCompte = new Compte();
	return unCompte.getMyCompteBroker().listerCompteLikeNom(aTransaction, sNom);
}

/**
 * Methode creerObjetMetier qui retourne
 * true ou false.
 * 
 * @param aTransaction la transaction en cours
 * @return vrai ou faux si le compte n'est pas cr��
 * @throws Exception 
 */
public final boolean creerCompte(final Transaction aTransaction)  throws Exception {
	//Creation du Compte
	return getMyCompteBroker().creerCompte(aTransaction);
}

/**
 * Methode modifierObjetMetier qui retourne
 * true ou false.
 * 
 * @param aTransaction la transaction en cours
 * @return vrai ou faux si le compte n'est pas modifi�
 * @throws Exception 
 */
public final boolean modifierCompte(final Transaction aTransaction) throws Exception {
	//Modification du Compte
	return getMyCompteBroker().modifierCompte(aTransaction);
}

/**
 * Methode supprimerObjetMetier qui retourne
 * true ou false.
 * 
 * @param aTransaction la transaction en cours
 * @return vrai ou faux si l'objet n'est pas supprim�
 * @throws Exception 
 */
public final boolean supprimerCompte(final Transaction aTransaction) throws Exception{
	//Suppression de l'Compte
	return getMyCompteBroker().supprimerCompte(aTransaction);
}
}
