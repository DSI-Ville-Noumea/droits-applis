package nc.mairie.droitsapplis.metier;

import nc.mairie.technique.MairieMessages;
/**
 * Objet m�tier GroupeDroits
 */
public class GroupeDroits extends nc.mairie.technique.BasicMetier {
	public String cdgrou;
	public String cddrap;
/**
 * Constructeur GroupeDroits.
 */
public GroupeDroits() {
	super();
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
 * Getter de l'attribut cddrap.
 */
public String getCddrap() {
	return cddrap;
}
/**
 * Setter de l'attribut cddrap.
 */
public void setCddrap(String newCddrap) { 
	cddrap = newCddrap;
}
/**
 Methode � d�finir dans chaque objet M�tier pour instancier un Broker 
*/
protected nc.mairie.technique.BasicBroker definirMyBroker() { 
	return new GroupeDroitsBroker(this); 
}
/**
 Methode � d�finir dans chaque objet M�tier pour instancier un Broker 
*/
protected GroupeDroitsBroker getMyGroupeDroitsBroker() {
	return (GroupeDroitsBroker)getMyBasicBroker();
}
/**
* Renvoie une cha�ne correspondant � la valeur de cet objet.
* @return une repr�sentation sous forme de cha�ne du destinataire
*/
public String toString() {
	// Ins�rez ici le code pour finaliser le destinataire
	// Cette impl�mentation transmet le message au super. Vous pouvez remplacer ou compl�ter le message.
	return super.toString();
}
/**
 * Retourne un ArrayList d'objet m�tier : GroupeDroits.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerGroupeDroits(nc.mairie.technique.Transaction aTransaction) throws Exception{
	GroupeDroits unGroupeDroits = new GroupeDroits();
	return unGroupeDroits.getMyGroupeDroitsBroker().listerGroupeDroits(aTransaction);
}

/**
 * Retourne un ArrayList d'objet m�tier : GroupeDroits.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerGroupeDroits(nc.mairie.technique.Transaction aTransaction, String droit) throws Exception{
	GroupeDroits unGroupeDroits = new GroupeDroits();
	return unGroupeDroits.getMyGroupeDroitsBroker().listerGroupeDroits(aTransaction, droit);
}

public static java.util.ArrayList listerGroupeDroitsfromGroupe(nc.mairie.technique.Transaction aTransaction, String groupe) throws Exception{
	GroupeDroits unGroupeDroits = new GroupeDroits();
	return unGroupeDroits.getMyGroupeDroitsBroker().listerGroupeDroitsfromGroupe(aTransaction, groupe);
}
/**
 * Retourne un GroupeDroits.
 * @return GroupeDroits
 */
public static GroupeDroits chercherGroupeDroits(nc.mairie.technique.Transaction aTransaction, String code) throws Exception{
	GroupeDroits unGroupeDroits = new GroupeDroits();
	return unGroupeDroits.getMyGroupeDroitsBroker().chercherGroupeDroits(aTransaction, code);
}

public static GroupeDroits chercherGroupeDroits(nc.mairie.technique.Transaction aTransaction, String sDroit, String cdGrou) throws Exception{
	GroupeDroits unGroupeDroits = new GroupeDroits();
	return unGroupeDroits.getMyGroupeDroitsBroker().chercherGroupeDroits(aTransaction, sDroit, cdGrou);
}
/**
 * Methode creerObjetMetier qui retourne
 * true ou false
 */
public boolean creerGroupeDroits(nc.mairie.technique.Transaction aTransaction )  throws Exception {
	//Creation du GroupeDroits
	return getMyGroupeDroitsBroker().creerGroupeDroits(aTransaction);
}
/**
 * Methode modifierObjetMetier qui retourne
 * true ou false
 */
public boolean modifierGroupeDroits(nc.mairie.technique.Transaction aTransaction) throws Exception {
	//Modification du GroupeDroits
	return getMyGroupeDroitsBroker().modifierGroupeDroits(aTransaction);
}
/**
 * Methode supprimerObjetMetier qui retourne
 * true ou false
 */
public boolean supprimerGroupeDroits(nc.mairie.technique.Transaction aTransaction) throws Exception{
	//Suppression de l'GroupeDroits
	return getMyGroupeDroitsBroker().supprimerGroupeDroits(aTransaction);
}
}
