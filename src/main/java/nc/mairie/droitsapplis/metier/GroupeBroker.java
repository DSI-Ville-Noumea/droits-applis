package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.BasicRecord;
/**
 * Broker de l'Objet métier Groupe
 */
public class GroupeBroker extends nc.mairie.technique.BasicBroker {
/**
 * Constructeur GroupeBroker.
 */
public GroupeBroker(nc.mairie.technique.BasicMetier aMetier) {
	super(aMetier);
}
/**
 * @return src/nc.mairie.droitsapplis.metier.GroupeMetier
 */
protected nc.mairie.technique.BasicMetier definirMyMetier() {
	return new Groupe() ;
}
/**
 * @return src/nc.mairie.droitsapplis.metier.GroupeMetier
 */
protected Groupe getMyGroupe() {
	return (Groupe)getMyBasicMetier();
}
/**
 * Retourne le nom de la table.
 */
protected java.lang.String definirNomTable() {
	return "MAIRIE.SIGROU";
}
/**
 * Retourne le mappage de chaque colonne de la table.
 */
protected java.util.Hashtable definirMappageTable() throws NoSuchFieldException {
	java.util.Hashtable mappage = new java.util.Hashtable();
	mappage.put("LIGROU", new BasicRecord("LIGROU", "CHAR", getMyGroupe().getClass().getField("ligrou"), "STRING"));
	mappage.put("CDGROU", new BasicRecord("CDGROU", "INTEGER", getMyGroupe().getClass().getField("cdgrou"), "STRING"));
	mappage.put("DEFINITION", new BasicRecord("DEFINITION", "CHAR", getMyGroupe().getClass().getField("definition"), "STRING"));
	return mappage;
}
/**
 * Methode creerObjetMetierBroker qui retourne
 * true ou false
 */
public boolean creerGroupe(nc.mairie.technique.Transaction aTransaction)  throws Exception{
	return creer(aTransaction);
}
/**
 * Methode modifierObjetMetierBroker qui retourne
 * true ou false
 */
public boolean modifierGroupe(nc.mairie.technique.Transaction aTransaction) throws java.lang.Exception {
	return modifier(aTransaction);
}
/**
 * Methode supprimerObjetMetierBroker qui retourne
 * true ou false
 */
public boolean supprimerGroupe(nc.mairie.technique.Transaction aTransaction) throws java.lang.Exception {
	return supprimer(aTransaction);
}
/**
 * Retourne un ArrayList d'objet métier : Groupe.
 * @return java.util.ArrayList
 */
public java.util.ArrayList listerGroupe(nc.mairie.technique.Transaction aTransaction) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" order by LIGROU");
}
/**
 * Retourne un Groupe.
 * @return Groupe
 */
public Groupe chercherGroupe(nc.mairie.technique.Transaction aTransaction, String groupid) throws Exception {
	return (Groupe)executeSelect(aTransaction,"select * from "+getTable()+" where CDGROU = "+groupid+"");
}
/**
 * Retourne un ArrayList d'objet métier : CompteGroupe.
 * @return java.util.ArrayList
 */
public java.util.ArrayList<Groupe> listerGroupefromCompte(nc.mairie.technique.Transaction aTransaction, String compte) throws Exception {
	//select * from MAIRIE.SIGROU where MAIRIE.SIGROU.CDGROU in (SELECT  MAIRIE.SIGRUT.CDGROU from MAIRIE.SIGRUT where CDIDUT='FONOL77');
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where "+getTable()+".CDGROU IN (SELECT  MAIRIE.SIGRUT.CDGROU from MAIRIE.SIGRUT where CDIDUT='"+compte+"')");
}
/**
 * 
 * @param aTransaction
 * @param compte
 * @param appli
 * @return
 * @throws Exception
 * @comment not tested yet!!!!!!!!!!!!!
 */
public java.util.ArrayList<Groupe> listerGroupefromCompteAndAppli(nc.mairie.technique.Transaction aTransaction, String compte, String appli) throws Exception {
	//select * from MAIRIE.SIGROU where MAIRIE.SIGROU.CDGROU in (SELECT  MAIRIE.SIGRUT.CDGROU from MAIRIE.SIGRUT where CDIDUT='FONOL77');
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where "+getTable()+".CDGROU IN (SELECT  MAIRIE.SIGRUT.CDGROU from MAIRIE.SIGRUT where CDIDUT='"+compte+"') AND "+getTable()+".CDGROU IN (SELECT MAIRIE.SIGRAP.CDGROU from MAIRIE.SIGRAP where CDDRAP IN (SELECT MAIRIE.SIDRAP.CDDRAP from MAIRIE.SIDRAP where APPLICATION='"+appli+"'))");
}

/**
 * Retourne un ArrayList d'objet métier : CompteGroupe.
 * @return java.util.ArrayList
 */
public java.util.ArrayList<Groupe> listerGroupefromDroit(nc.mairie.technique.Transaction aTransaction, String droit) throws Exception {
	//select * from MAIRIE.SIGROU where MAIRIE.SIGROU.CDGROU in (SELECT  MAIRIE.SIGRUT.CDGROU from MAIRIE.SIGRUT where CDIDUT='FONOL77');
	return executeSelectListe(aTransaction,"select * from "+getTable()+" where "+getTable()+".CDGROU IN (SELECT  MAIRIE.SIGRAP.CDGROU from MAIRIE.SIGRAP where CDDRAP="+droit+")");
}

public int nouvId(nc.mairie.technique.Transaction aTransaction) throws Exception{
//	recherche du dernier 
	return executeCompter(aTransaction, "select max(cdgrou) from "+ getTable());
	
}
}
