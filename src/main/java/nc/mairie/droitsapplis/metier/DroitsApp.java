package nc.mairie.droitsapplis.metier;

/**
 * Objet métier DroitsApp
 */
public class DroitsApp extends nc.mairie.technique.BasicMetier {
	public String cddrap;
	public String application;
	public String droit;
/**
 * Constructeur DroitsApp.
 */
public DroitsApp() {
	super();
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
 * Getter de l'attribut application.
 */
public String getApplication() {
	return application;
}
/**
 * Setter de l'attribut application.
 */
public void setApplication(String newApplication) { 
	application = newApplication;
}
/**
 * Getter de l'attribut droit.
 */
public String getDroit() {
	return droit;
}
/**
 * Setter de l'attribut droit.
 */
public void setDroit(String newDroit) { 
	droit = newDroit;
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
*/
protected nc.mairie.technique.BasicBroker definirMyBroker() { 
	return new DroitsAppBroker(this); 
}
/**
 Methode à définir dans chaque objet Métier pour instancier un Broker 
*/
protected DroitsAppBroker getMyDroitsAppBroker() {
	return (DroitsAppBroker)getMyBasicBroker();
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
 * Retourne un ArrayList d'objet métier : DroitsApp.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList listerDroitsApp(nc.mairie.technique.Transaction aTransaction) throws Exception{
	DroitsApp unDroitsApp = new DroitsApp();
	return unDroitsApp.getMyDroitsAppBroker().listerDroitsApp(aTransaction);
}
public static java.util.ArrayList listerDroitsAppfromGroupe(nc.mairie.technique.Transaction aTransaction, String groupe) throws Exception{
	DroitsApp unDroitsApp = new DroitsApp();
	return unDroitsApp.getMyDroitsAppBroker().listerDroitsAppfromGroupe(aTransaction, groupe);
}
public static java.util.ArrayList<DroitsApp> listerDroitsAppfromCompteAndApplication(nc.mairie.technique.Transaction aTransaction, String compte, String appli) throws Exception{
	DroitsApp unDroitsApp = new DroitsApp();
	return unDroitsApp.getMyDroitsAppBroker().listerDroitsAppfromCompteAndApplication(aTransaction, compte, appli);
}

/**
 * Retourne un ArrayList d'objet métier : DroitsApp.
 * @return java.util.ArrayList
 */
public static java.util.ArrayList<String> listerApplications(nc.mairie.technique.Transaction aTransaction) throws Exception{
	java.util.ArrayList<String> stabApplications;
	DroitsApp unDroitsApp = new DroitsApp();
	java.util.ArrayList<DroitsApp> databApplications=unDroitsApp.getMyDroitsAppBroker().listerApplications(aTransaction);
	stabApplications = new java.util.ArrayList<String>(databApplications.size());
	for(int i=0; i<databApplications.size(); i++){
		stabApplications.add(i,databApplications.get(i).getApplication());
	}
	return stabApplications;
}

/**
 * Retourne un DroitsApp.
 * @return DroitsApp
 */
public static DroitsApp chercherDroitsApp(nc.mairie.technique.Transaction aTransaction, String cdDroit) throws Exception{
	DroitsApp unDroitsApp = new DroitsApp();
	return unDroitsApp.getMyDroitsAppBroker().chercherDroitsApp(aTransaction, cdDroit);
}
/**
 * Methode creerObjetMetier qui retourne
 * true ou false
 */
public boolean creerDroitsApp(nc.mairie.technique.Transaction aTransaction )  throws Exception {

	
	
	//Creation du DroitsApp
	return getMyDroitsAppBroker().creerDroitsApp(aTransaction);
}
/**
 * Methode modifierObjetMetier qui retourne
 * true ou false
 */
public boolean modifierDroitsApp(nc.mairie.technique.Transaction aTransaction) throws Exception {
	//Modification du DroitsApp
	return getMyDroitsAppBroker().modifierDroitsApp(aTransaction);
}
/**
 * Methode supprimerObjetMetier qui retourne
 * true ou false
 */
public boolean supprimerDroitsApp(nc.mairie.technique.Transaction aTransaction) throws Exception{
	//Suppression de l'DroitsApp
	return getMyDroitsAppBroker().supprimerDroitsApp(aTransaction);
}


}
