package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.BasicRecord;
/**
 * Broker de l'Objet métier CompteGroupe
 */
public class CompteGroupeBroker extends nc.mairie.technique.BasicBroker {
/**
 * Constructeur CompteGroupeBroker.
 */
/**
 * @param aMetier aMetier
 */
public CompteGroupeBroker(nc.mairie.technique.BasicMetier aMetier) {
	super(aMetier);
}
/**
 * @return src/nc.mairie.droitsapplis.metier.CompteGroupeMetier
 */
protected nc.mairie.technique.BasicMetier definirMyMetier() {
	return new CompteGroupe() ;
}
/**
 * @return src/nc.mairie.droitsapplis.metier.CompteGroupeMetier
 */
protected CompteGroupe getMyCompteGroupe() {
	return (CompteGroupe)getMyBasicMetier();
}
/**
 * Retourne le nom de la table.
 */
protected java.lang.String definirNomTable() {
	return "MAIRIE.SIGRUT";
}
/**
 * Retourne le mappage de chaque colonne de la table.
 */
protected java.util.Hashtable<String, BasicRecord> definirMappageTable() throws NoSuchFieldException {
	java.util.Hashtable<String, BasicRecord>  mappage = new java.util.Hashtable<String, BasicRecord> ();
	mappage.put("CDIDUT", new BasicRecord("CDIDUT", "CHAR", getMyCompteGroupe().getClass().getField("cdidut"), "STRING"));
	mappage.put("CDGROU", new BasicRecord("CDGROU", "INTEGER", getMyCompteGroupe().getClass().getField("cdgrou"), "STRING"));
	return mappage;
}
/**
 * Methode creerObjetMetierBroker qui retourne
 * true ou false
 */
/**
 * @param aTransaction aTransaction
 * @return boolean
 * @throws Exception Exception
 */
public boolean creerCompteGroupe(nc.mairie.technique.Transaction aTransaction)  throws Exception{
	return creer(aTransaction);
}
/**
 * Methode modifierObjetMetierBroker qui retourne
 * true ou false
 */
/**
 * @param aTransaction aTransaction
 * @return boolean
 * @throws java.lang.Exception java.lang.Exception
 */
public boolean modifierCompteGroupe(nc.mairie.technique.Transaction aTransaction) throws java.lang.Exception {
	return modifier(aTransaction);
}
/**
 * Methode supprimerObjetMetierBroker qui retourne
 * true ou false
 */
/**
 * @param aTransaction aTransaction
 * @return boolean
 * @throws java.lang.Exception java.lang.Exception
 */
public boolean supprimerCompteGroupe(nc.mairie.technique.Transaction aTransaction) throws java.lang.Exception {
	return supprimer(aTransaction);
}
/**
 * Retourne un ArrayList d'objet métier : CompteGroupe.
 * @return java.util.ArrayList
 */
/**
 * @param aTransaction aTransaction
 * @return ArrayList
 * @throws Exception Exception
 */
public java.util.ArrayList<CompteGroupe> listerCompteGroupe(nc.mairie.technique.Transaction aTransaction) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+"");
}
/**
 * Retourne un ArrayList d'objet métier : CompteGroupe.
 * @return java.util.ArrayList
 */
/**
 * @param aTransaction aTransaction
 * @param sCompte sCompte
 * @return ArrayList
 * @throws Exception Exception
 */
public java.util.ArrayList<CompteGroupe> listerCompteGroupe(nc.mairie.technique.Transaction aTransaction, String sCompte) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" WHERE CDIDUT='"+sCompte+"'");
}

public java.util.ArrayList<CompteGroupe> listerCompteGroupefromGroupe(nc.mairie.technique.Transaction aTransaction, String sGroupe) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" WHERE CDGROU='"+sGroupe+"'");
}
/**
 * Retourne un CompteGroupe.
 * @return CompteGroupe
 */
/**
 * @param aTransaction aTransaction
 * @param cle cle
 * @return CompteGroupe
 * @throws Exception Exception
 */
public CompteGroupe chercherCompteGroupe(nc.mairie.technique.Transaction aTransaction, String cle) throws Exception {
	return (CompteGroupe)executeSelect(aTransaction,"select * from "+getTable()+" where CODE = "+cle+"");
}
/**
 * Retourne un CompteGroupe.
 * @return CompteGroupe
 */
/**
 * @param aTransaction aTransaction
 * @param sCompte sCompte
 * @param cdGrou cdGrou
 * @return CompteGroupe
 * @throws Exception Exception
 */
public CompteGroupe chercherCompteGroupe(nc.mairie.technique.Transaction aTransaction, String sCompte, String cdGrou) throws Exception {
	return (CompteGroupe)executeSelect(aTransaction,"select * from "+getTable()+" where CDIDUT = '"+sCompte+"' and CDGROU= '"+cdGrou+"'");
}
}
