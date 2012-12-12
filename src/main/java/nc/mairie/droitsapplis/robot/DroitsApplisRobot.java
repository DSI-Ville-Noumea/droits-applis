package nc.mairie.droitsapplis.robot;

import java.util.Hashtable;

import nc.mairie.droitsapplis.process.DroitsApplis;
import nc.mairie.droitsapplis.process.DroitsMain;
import nc.mairie.droitsapplis.process.GestionCompteAvance;
import nc.mairie.droitsapplis.process.GestionComptes;
import nc.mairie.droitsapplis.process.GestionDroitAvance;
import nc.mairie.droitsapplis.process.GestionDroits;
import nc.mairie.droitsapplis.process.GestionGroupeAvance;
import nc.mairie.droitsapplis.process.GestionGroupes;
import nc.mairie.droitsapplis.process.OeAGENTSelection;
import nc.mairie.robot.Robot;
import nc.mairie.robot.Testeur;
import nc.mairie.technique.BasicProcess;

public class DroitsApplisRobot extends Robot {

	@Override
	public BasicProcess getDefaultProcess() throws Exception {
		return new DroitsApplis();
	}

	@Override
	public BasicProcess getFirstProcess(String activite) throws Exception {
		if ("GestionComptes".equals(activite)) 
			return new GestionComptes();
		else if ("GestionDroits".equals(activite)) 
			return new GestionDroits();
		else if ("GestionGroupes".equals(activite)) 
			return new GestionGroupes();
		else if ("DroitsApplis".equals(activite)) 
			return new DroitsApplis();
		
		return null;
	}

	@Override
	protected Hashtable initialiseNavigation() {
		java.util.Hashtable navigation = new java.util.Hashtable();
		
		//Gestion Comptes
		navigation.put(GestionComptes.class.getName() + GestionComptes.STATUT_EDITCOMPTE, GestionCompteAvance.class.getName());
		navigation.put(GestionComptes.class.getName() + GestionComptes.STATUT_AJOUTCOMPTE, OeAGENTSelection.class.getName());
		navigation.put(GestionComptes.class.getName() + GestionComptes.STATUT_RETOUR, DroitsMain.class.getName());
				
		navigation.put(OeAGENTSelection.class.getName() + OeAGENTSelection.STATUT_EDITCOMPTE, GestionCompteAvance.class.getName());
		navigation.put(OeAGENTSelection.class.getName() + OeAGENTSelection.STATUT_LISTES_COMPTES, GestionComptes.class.getName());

		//Gestion Droits
		navigation.put(GestionDroits.class.getName() + GestionDroits.STATUT_EDITDROIT, GestionDroitAvance.class.getName());
		navigation.put(GestionDroits.class.getName() + GestionDroits.STATUT_RETOUR, DroitsMain.class.getName());
		
		//Gestion Groupes
		navigation.put(GestionGroupes.class.getName() + GestionGroupes.STATUT_EDITGROUPE, GestionGroupeAvance.class.getName());
		navigation.put(GestionGroupes.class.getName() + GestionGroupes.STATUT_RETOUR, DroitsMain.class.getName());
		
		return navigation;

	}

	@Override
	protected Testeur initialiseTesteur() {
		// TODO Raccord de méthode auto-généré
		return null;
	}

}
