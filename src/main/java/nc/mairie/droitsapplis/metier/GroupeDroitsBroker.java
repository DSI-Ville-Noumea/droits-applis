package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.BasicRecord;
/**
 * Broker de l'Objet métier GroupeDroits
 */
public class GroupeDroitsBroker extends nc.mairie.technique.BasicBroker {
/**
 * Constructeur GroupeDroitsBroker.
 */
public GroupeDroitsBroker(nc.mairie.technique.BasicMetier aMetier) {
	super(aMetier);
}
/**
 * @return src/nc.mairie.droitsapplis.metier.GroupeDroitsMetier
 */
protected nc.mairie.technique.BasicMetier definirMyMetier() {
	return new GroupeDroits() ;
}
/**
 * @return src/nc.mairie.droitsapplis.metier.GroupeDroitsMetier
 */
protected GroupeDroits getMyGroupeDroits() {
	return (GroupeDroits)getMyBasicMetier();
}
/**
 * Retourne le nom de la table.
 */
protected java.lang.String definirNomTable() {
	return "MAIRIE.SIGRAP";
}
/**
 * Retourne le mappage de chaque colonne de la table.
 */
protected java.util.Hashtable definirMappageTable() throws NoSuchFieldException {
	java.util.Hashtable mappage = new java.util.Hashtable();
	mappage.put("CDGROU", new BasicRecord("CDGROU", "INTEGER", getMyGroupeDroits().getClass().getField("cdgrou"), "STRING"));
	mappage.put("CDDRAP", new BasicRecord("CDDRAP", "INTEGER", getMyGroupeDroits().getClass().getField("cddrap"), "STRING"));
	return mappage;
}
/**
 * Methode creerObjetMetierBroker qui retourne
 * true ou false
 */
public boolean creerGroupeDroits(nc.mairie.technique.Transaction aTransaction)  throws Exception{
	return creer(aTransaction);
}
/**
 * Methode modifierObjetMetierBroker qui retourne
 * true ou false
 */
public boolean modifierGroupeDroits(nc.mairie.technique.Transaction aTransaction) throws java.lang.Exception {
	return modifier(aTransaction);
}
/**
 * Methode supprimerObjetMetierBroker qui retourne
 * true ou false
 */
public boolean supprimerGroupeDroits(nc.mairie.technique.Transaction aTransaction) throws java.lang.Exception {
	return supprimer(aTransaction);
}
/**
 * Retourne un ArrayList d'objet métier : GroupeDroits.
 * @return java.util.ArrayList
 */
public java.util.ArrayList listerGroupeDroits(nc.mairie.technique.Transaction aTransaction) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+"");
}

public java.util.ArrayList listerGroupeDroits(nc.mairie.technique.Transaction aTransaction, String droit) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" WHERE CDDRAP='"+droit+"'");
}
public java.util.ArrayList listerGroupeDroitsfromGroupe(nc.mairie.technique.Transaction aTransaction, String groupe) throws Exception {
	return executeSelectListe(aTransaction,"select * from "+getTable()+" WHERE CDGROU='"+groupe+"'");
}
/**
 * Retourne un GroupeDroits.
 * @return GroupeDroits
 */
public GroupeDroits chercherGroupeDroits(nc.mairie.technique.Transaction aTransaction, String cle) throws Exception {
	return (GroupeDroits)executeSelect(aTransaction,"select * from "+getTable()+" where CODE = "+cle+"");
}

public GroupeDroits chercherGroupeDroits(nc.mairie.technique.Transaction aTransaction, String sDroit, String cdGrou) throws Exception {
	return (GroupeDroits)executeSelect(aTransaction,"select * from "+getTable()+" where CDDRAP = '"+sDroit+"' and CDGROU = '"+cdGrou+"'");
}
}
