package nc.mairie.droitsapplis.process;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nc.mairie.droitsapplis.metier.DroitsApp;
import nc.mairie.droitsapplis.metier.Groupe;
import nc.mairie.droitsapplis.metier.GroupeDroits;
import nc.mairie.technique.*;
/**
 * Process GestionDroits
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
*/
public class GestionDroits extends nc.mairie.technique.BasicProcess {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2515995159541312217L;
	public static final int STATUT_EDITDROIT=1;
	public static final int STATUT_RETOUR=4;
	
	public static final String ACTION_DROIT= "ACTION_DROIT";
	public static final String AJOUTER_DROIT= "AJOUTER_DROIT";
	public static final String MODIFIER_DROIT= "MODIFIER_DROIT";
	public static final String SUPPRIMER_DROIT= "SUPPRIMER_DROIT";
	public static final String DROIT_ID = "DROIT_ID";
	
	private java.util.ArrayList<Groupe>  listeGroupes;
	private java.util.ArrayList<GroupeDroits>  listeGroupesDroits;
	//key=compte utilisateur, ArrayList=liste des CDGROU (identifiants de groupe)
	private HashMap<String,boolean[]> hTMapDroits;
	
	
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{
	//System.out.println("PASS initZone GestionDroits");
	initialiseGroupes(request);
	initialiseGroupeDroits(request);
	initialiseHashMap(request);	
}

public void initialiseGroupes(javax.servlet.http.HttpServletRequest request) throws Exception{
	listeGroupes=Groupe.listerGroupe(getTransaction());
}


public void initialiseGroupeDroits(javax.servlet.http.HttpServletRequest request) throws Exception{
	listeGroupesDroits=GroupeDroits.listerGroupeDroits(getTransaction());
}

public synchronized void initialiseHashMap(javax.servlet.http.HttpServletRequest request) throws Exception{
	java.util.ArrayList<DroitsApp> listDroits=DroitsApp.listerDroitsApp(getTransaction());
	hTMapDroits=new HashMap<String, boolean[]>();
	boolean[] tabBooGroupes;
	
	//On initialise la hMapDroits avec tous les droits trouvés
	for(DroitsApp daCourant : listDroits){
		tabBooGroupes=new boolean[listeGroupes.size()];
		java.util.Arrays.fill(tabBooGroupes, false);
		hTMapDroits.put(daCourant.getCddrap(),tabBooGroupes);
	}

	//on boucle sur la liste complète des droitsgroupe (SIGRAP)
	for(GroupeDroits gdCourant : listeGroupesDroits){
		//System.out.println("GROU="+gdCourant.getCdgrou()+" CDDRAP="+gdCourant.getCddrap());
		String droitCourant=gdCourant.getCddrap();
		//tabBooGroupes=new boolean[listeGroupes.size()];
		tabBooGroupes=hTMapDroits.get(droitCourant);
		//on rempli à true lorsqu'on tombe sur le bon groupe, l'ordre des groupes est important
		//il faut penser comme une matrice
		for(int j=0; j<listeGroupes.size(); j++){
			Groupe gCourant=listeGroupes.get(j);
			if (gdCourant.getCdgrou().equals(gCourant.getCdgrou())){
				tabBooGroupes[j]=true;
			}
		}
		//hTMapDroits.put(droitCourant, tabBooGroupes);
	}
}


/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
public boolean recupererStatut(javax.servlet.http.HttpServletRequest request) throws Exception{

	//Si on arrive de la JSP alors on traite le get
	if (request.getParameter("JSP")!=null && request.getParameter("JSP").equals(getJSP())) {

		//Si clic sur le bouton PB_AJOUTER
		if (testerParametre(request, getNOM_PB_AJOUTER())) {
			return performPB_AJOUTER(request);
		}

		//Si clic sur le bouton PB_ANNULER
		if (testerParametre(request, getNOM_PB_ANNULER())) {
			return performPB_ANNULER(request);
		}

		//Si clic sur le bouton PB_MODIFIER
		if (testerParametre(request, getNOM_PB_MODIFIER())) {
			return performPB_MODIFIER(request);
		}

		//Si clic sur le bouton PB_SUPPRIMER
		if (testerParametre(request, getNOM_PB_SUPPRIMER())) {
			return performPB_SUPPRIMER(request);
		}

	}
	//Si TAG INPUT non géré par le process
	setStatut(STATUT_MEME_PROCESS);
	return true;
}
/**
 * Constructeur du process GestionDroits.
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
public GestionDroits() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
public String getJSP() {
	return "GestionDroits.jsp";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_AJOUTER
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_AJOUTER() {
	return "NOM_PB_AJOUTER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_AJOUTER(javax.servlet.http.HttpServletRequest request) throws Exception {

	VariableActivite.ajouter(this, ACTION_DROIT, AJOUTER_DROIT);
	setStatut(STATUT_EDITDROIT,true);
	return true;

}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_ANNULER
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_ANNULER() {
	return "NOM_PB_ANNULER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_ANNULER(javax.servlet.http.HttpServletRequest request) throws Exception {
	setStatut(STATUT_RETOUR);
	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_MODIFIER
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_MODIFIER() {
	return "NOM_PB_MODIFIER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_MODIFIER(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	if (ControleRadioBoxComptes()){
		VariableActivite.ajouter(this, ACTION_DROIT, MODIFIER_DROIT);
		setStatut(STATUT_EDITDROIT,true);
		return true;
	} else {
		getTransaction().declarerErreur(MairieMessages.getMessage("ERR997","Droits"));
		return false;
	}

}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_SUPPRIMER
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_PB_SUPPRIMER() {
	return "NOM_PB_SUPPRIMER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_SUPPRIMER(javax.servlet.http.HttpServletRequest request) throws Exception {

	if (ControleRadioBoxComptes()){
		VariableActivite.ajouter(this, ACTION_DROIT, SUPPRIMER_DROIT);
		setStatut(STATUT_EDITDROIT,true);
		return true;
	} else {
		getTransaction().declarerErreur(MairieMessages.getMessage("ERR997","Droits"));
		return false;
	}
}
/**
 * Retourne le nom du groupe de radio boutons coché pour la JSP :
 * RG_DROITS
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RG_DROITS() {
	return "NOM_RG_DROITS";
}
/**
 * Retourne la valeur du radio bouton (RB_) coché dans la JSP :
 * RG_DROITS
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_RG_DROITS() {
	return getZone(getNOM_RG_DROITS());
}
/**
 * Retourne le nom du radio bouton pour la JSP :
 * RB_DROITS
 * Date de création : (30/04/09 13:39:03)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RB_DROITS() {
	return "NOM_RB_DROITS";
}



/**
 * controle la validite de la radiobox 
 * et ajoute le droit sélectionné dans la variable activité DROIT_NOM
 * @return true si une radiobox a bien été sélectionnée
 */
public boolean ControleRadioBoxComptes(){
	if (getZone(getNOM_RB_DROITS())!=null && getZone(getNOM_RB_DROITS()).length()>0){
		VariableActivite.ajouter(this, DROIT_ID, getZone(getNOM_RB_DROITS()));
		return true;
	}else{
		VariableActivite.ajouter(this, DROIT_ID, "");
		return false;
	}
}


/**
 * 
 * Affichage du tableau qui liste les droits des groupes
 * @return String
 * @throws Exception Exception
 * @throws NumberFormatException NumberFormatException
 */
public String generateTABLO_DROITS() throws Exception, NumberFormatException{
	/*
		 <table class="scrollTable">
		  <thead class="fixedHeader headerFormat">
		      <tr class="title">
		       <td class="point" onclick="GoTop(); sortTable(this,1); HoverRow();" title="Sort"><b>NAME</b> <img src="images/none.gif" border="0" /></td>
		       <td class="point" onclick="GoTop(); sortTable(this,1); HoverRow();" title="Sort" align="right"><b>Amt</b> <img src="images/none.gif" border="0" /></td>
		       <td class="point" onclick="GoTop(); sortTable(this,1); HoverRow();" title="Sort" align="right"><b>Lvl</b> <img src="images/none.gif" border="0" /></td>
		       <td class="point" onclick="GoTop(); sortTable(this,1); HoverRow();" title="Sort" align="right"><b>Rank</b> <img src="images/none.gif" border="0" /></td>
		       <td class="point" onclick="GoTop(); sortTable(this,1); HoverRow();" title="Sort"><b>Position</b> <img src="images/none.gif" border="0" /></td>
		       <td class="point" onclick="GoTop(); sortTable(this,1); HoverRow();" title="Sort" align="center"><b>Date</b> <img src="images/none.gif" border="0" /></td>
		       <td class="point" onclick="GoTop(); sortTable(this,1); HoverRow();" title="Sort" align="center"><b>CRUX</b> <img src="images/none.gif" border="0" /></td>
		      </tr>
		  </thead>
		  <tbody class="scrollContent bodyFormat" style="height:200px;">
	    <tr>
	    <td>Mrs. Robinson</td>
	    <td align="right">$99</td>
	    <td align="right">99</td>
	    <td align="right">(-10.42%)</td>
	    <td>Wife</td>
	    <td align="right">07/04/1963</td>
	    <td align="center"></td>
	   </tr>
	   <tr class="alternateRow">
	    <td>Maha</td>
	    <td align="right">-$19,923.19</td>
	    <td align="right">100</td>
	    <td align="right">(-10.01%)</td>
	    <td>Owner</td>
	    <td align="right">01/02/2001</td>
	    <td align="center"></td>
	   </tr>
	   </tbody>
	 </table>
	*/
		
		StringBuffer sbTabloDroits = new StringBuffer();
		try{
		sbTabloDroits.append("<table class=\"scrollTable\">");
		sbTabloDroits.append("<thead class=\"headerFormat fixedHeader\">"); //fixedHeader 
		sbTabloDroits.append("<tr class=\"title\">");
		sbTabloDroits.append("<td style=\"border: 0px;background-color:white;\" colspan=\"3\">&nbsp;</td>");
		sbTabloDroits.append("<td align=\"center\" colspan=\"");
		sbTabloDroits.append(listeGroupes.size());
		sbTabloDroits.append("\">GROUPES</td>");
		sbTabloDroits.append("</tr>");
		sbTabloDroits.append("<tr class=\"title\">");
		sbTabloDroits.append("<td style=\"border: 0px;background-color:white;\">&nbsp;</td>");
		sbTabloDroits.append("<td class=\"point\" onclick=\"GoTop(); sortTable(this,2,0); HoverRow();\" title=\"Sort\"><b>DROITS</b><img src=\"images/none.gif\" border=\"0\" /></td>");
		sbTabloDroits.append("<td class=\"point\" onclick=\"GoTop(); sortTable(this,2,0); HoverRow();\" title=\"Sort\"><b>APPLICATION</b><img src=\"images/none.gif\" border=\"0\" /></td>");
		sbTabloDroits.append("\n");
		for(Groupe groupeCourant : listeGroupes){
			sbTabloDroits.append("<td class=\"point\" onclick=\"GoTop(); sortTable(this,2,0); HoverRow();\" title=\"Sort\"><b>");
			sbTabloDroits.append(groupeCourant.getLigrou().trim());
			sbTabloDroits.append("</b><img src=\"images/none.gif\" border=\"0\" /></td>");
			sbTabloDroits.append("\n");
		}
		sbTabloDroits.append("</tr>");
		sbTabloDroits.append("</thead>");
		sbTabloDroits.append("\n");
		sbTabloDroits.append("<tbody class=\"scrollContent bodyFormat\" style=\"height:200px;\">");
		
		//Traitement pour afficher les croix dans le tableau
		boolean ispaire=false;
		for ( Iterator<Map.Entry<String,boolean[]> > iter = hTMapDroits.entrySet().iterator(); iter.hasNext(); ) {
			Map.Entry<String,boolean[]> ent = iter.next();
			//La clé de la HashMap
			String droitCourant = ent.getKey();
			DroitsApp daEnCours=DroitsApp.chercherDroitsApp(getTransaction(), droitCourant);
			//La Valeur de la HashMap
			boolean[] tabBooGroupes = ent.getValue();

			//Traitement
			if(ispaire){
				sbTabloDroits.append("<tr>");
				ispaire=false;
			}else{
				sbTabloDroits.append("<tr class=\"alternateRow\">");
				ispaire=true;
			}
			sbTabloDroits.append("<td>");
			sbTabloDroits.append("<INPUT type=\"radio\" name=\"");
			sbTabloDroits.append(getNOM_RB_DROITS());
			sbTabloDroits.append("\" value=\"");
			sbTabloDroits.append(droitCourant);
			sbTabloDroits.append("\">");
			sbTabloDroits.append("</td>");
			sbTabloDroits.append("<td><b>");
			sbTabloDroits.append(daEnCours.getDroit());
			sbTabloDroits.append("</b></td>");
			sbTabloDroits.append("<td>");
			sbTabloDroits.append(daEnCours.getApplication());
			sbTabloDroits.append("</td>");
			
			for(int i =0; i<tabBooGroupes.length; i++){
				sbTabloDroits.append("<td align=\"center\"><b>");
				//System.out.println("COMPTE="+droitCourant);
				//System.out.println("tab["+i+"]="+tabBooGroupes[i]);
				if(tabBooGroupes[i])
					sbTabloDroits.append("X");
				else
					sbTabloDroits.append("-");
				sbTabloDroits.append("</b></td>");
			}

			sbTabloDroits.append("</tr>");
		}
		

		sbTabloDroits.append("\n");
		sbTabloDroits.append("</tbody>");
		sbTabloDroits.append("</table>");
		}catch (Exception e){
			getTransaction().rollbackTransaction();
			System.out.println("ERREUR :"+e+"\n"+e.getLocalizedMessage());
			e.printStackTrace();
			return ("");
		}
		getTransaction().rollbackTransaction();
		return sbTabloDroits.toString();
	}

}
