package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.BasicMetier;
import nc.mairie.technique.BasicRecord;
import nc.mairie.technique.Transaction;

/**
 * Broker de l'Objet métier Compte.
 */
public class CompteBroker extends nc.mairie.technique.BasicBroker {
	
/**
 * Constructeur CompteBroker.
 * 
 * @param aMetier l'objet Métier
 */
public CompteBroker(final nc.mairie.technique.BasicMetier aMetier) {
	super(aMetier);
}

/**
 * @return src/nc.mairie.droitsapplis.metier.CompteMetier
 */
protected final BasicMetier definirMyMetier() {
	return new Compte();
}

/**
 * @return src/nc.mairie.droitsapplis.metier.CompteMetier
 */
protected final Compte getMyCompte() {
	return (Compte) getMyBasicMetier();
}

/**
 * Retourne le nom de la table.
 * 
 * @return le nom de la table
 */
protected final String definirNomTable() {
	return "MAIRIE.SIIDUT";
}

/**
 * Retourne le mappage de chaque colonne de la table.
 */
protected final java.util.Hashtable definirMappageTable() throws NoSuchFieldException {
	java.util.Hashtable mappage = new java.util.Hashtable();
	mappage.put("CDIDUT", new BasicRecord("CDIDUT", "CHAR", getMyCompte().getClass().getField("cdidut"), "STRING"));
	mappage.put("LIIDUT", new BasicRecord("LIIDUT", "CHAR", getMyCompte().getClass().getField("liidut"), "STRING"));
	mappage.put("PRINTER", new BasicRecord("PRINTER", "CHAR", getMyCompte().getClass().getField("printer"), "STRING"));
	return mappage;
}

/**
 * Methode creerObjetMetierBroker qui retourne
 * true ou false.
 * 
 * @param aTransaction la transaction en cours
 * @return vrai ou faux si le compte n'est pas créé
 * @throws Exception 
 */
public final boolean creerCompte(final Transaction aTransaction)  throws Exception {
	return creer(aTransaction);
}

/**
 * Methode modifierObjetMetierBroker qui retourne
 * true ou false.
 * 
 * @param aTransaction la transaction en cours
 * @return vrai ou faux si le compte n'est pas modifié
 * @throws Exception 
 */
public final boolean modifierCompte(final Transaction aTransaction) throws Exception {
	return modifier(aTransaction);
}

/**
 * Methode supprimerObjetMetierBroker qui retourne
 * true ou false.
 * 
 * @param aTransaction la transaction en cours
 * @return vrai ou faux si le compte n'est pas supprimé
 * @throws Exception 
 */
public final boolean supprimerCompte(final Transaction aTransaction) throws Exception {
	return supprimer(aTransaction);
}

/**
 * Retourne un ArrayList d'objet métier : Compte.
 * @return java.util.ArrayList
 */
public java.util.ArrayList listerCompte(nc.mairie.technique.Transaction aTransaction) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" order by LIIDUT asc");
}
public java.util.ArrayList listerCompteOrderByCode(nc.mairie.technique.Transaction aTransaction) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" order by CDIDUT asc");
}
public java.util.ArrayList listerComptesfromGroupe(nc.mairie.technique.Transaction aTransaction, String groupe) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where CDIDUT IN (SELECT CDIDUT from MAIRIE.SIGRUT where CDGROU = "+groupe+")");
}
public java.util.ArrayList listerComptesfromGroupeOrderByCode(nc.mairie.technique.Transaction aTransaction, String groupe) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where CDIDUT IN (SELECT CDIDUT from MAIRIE.SIGRUT where CDGROU = "+groupe+") order by CDIDUT asc");
}

/**
 * Retourne un Compte.
 * 
 * @param aTransaction la transaction en cours
 * @param sCompte le nom du compte
 * @return Compte
 * @throws Exception 
 */
public final Compte chercherCompte(final Transaction aTransaction, final String sCompte) throws Exception {
	//String requete="select * from "+getTable()+" where upper(CDIDUT) = '"+sCompte+"'";
	return (Compte) executeSelect(aTransaction, "select * from " + getTable() + " where upper(CDIDUT) = '" + sCompte + "' order by CDIDUT asc");
}

public java.util.ArrayList listerCompteLikeCompte(nc.mairie.technique.Transaction aTransaction, String sCompte) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where upper(CDIDUT) like '%"+sCompte+"%' order by CDIDUT asc");
}

public java.util.ArrayList listerCompteLikeNom(nc.mairie.technique.Transaction aTransaction, String sNom) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where upper(LIIDUT) like '%"+sNom+"%' order by LIIDUT asc");
}
}
