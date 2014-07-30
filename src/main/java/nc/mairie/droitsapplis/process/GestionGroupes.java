package nc.mairie.droitsapplis.process;

import nc.mairie.droitsapplis.metier.Groupe;
import nc.mairie.technique.*;
/**
 * Process GestionGroupes
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
*/
public class GestionGroupes extends nc.mairie.technique.BasicProcess {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8533704049179271994L;
	public static final int STATUT_EDITGROUPE=1;
	public static final int STATUT_RETOUR=4;
	
	public static final String ACTION_GROUPE= "ACTION_GROUPE";
	public static final String AJOUTER_GROUPE= "AJOUTER_GROUPE";
	public static final String MODIFIER_GROUPE= "MODIFIER_GROUPE";
	public static final String SUPPRIMER_GROUPE= "SUPPRIMER_GROUPE";
	public static final String GROUPE_ID = "GROUPE_ID";
	
	private java.util.ArrayList<Groupe>  listeGroupes;
	
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{
	//System.out.println("PASS initZone GestionGroupes");
	initialiseGroupes(request);
}

public void initialiseGroupes(javax.servlet.http.HttpServletRequest request) throws Exception{
	listeGroupes=Groupe.listerGroupe(getTransaction());
	getTransaction().rollbackTransaction();
}



/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (06/05/09 15:36:28)
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
 * Constructeur du process GestionGroupes.
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
public GestionGroupes() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
public String getJSP() {
	return "GestionGroupes.jsp";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_AJOUTER
 * Date de création : (06/05/09 15:36:28)
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
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_AJOUTER(javax.servlet.http.HttpServletRequest request) throws Exception {
	VariableActivite.ajouter(this, ACTION_GROUPE, AJOUTER_GROUPE);
	setStatut(STATUT_EDITGROUPE,true);
	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_ANNULER
 * Date de création : (06/05/09 15:36:28)
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
 * Date de création : (06/05/09 15:36:28)
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
 * Date de création : (06/05/09 15:36:28)
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
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_MODIFIER(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	if (ControleRadioBoxComptes()){
		VariableActivite.ajouter(this, ACTION_GROUPE, MODIFIER_GROUPE);
		setStatut(STATUT_EDITGROUPE,true);
		return true;
	} else {
		getTransaction().declarerErreur(MairieMessages.getMessage("ERR997","Groupes"));
		return false;
	}

}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_SUPPRIMER
 * Date de création : (06/05/09 15:36:28)
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
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
/**
 * @param request request
 * @return boolean
 * @throws Exception Exception
 */
public boolean performPB_SUPPRIMER(javax.servlet.http.HttpServletRequest request) throws Exception {

	if (ControleRadioBoxComptes()){
		VariableActivite.ajouter(this, ACTION_GROUPE, SUPPRIMER_GROUPE);
		setStatut(STATUT_EDITGROUPE,true);
		return true;
	} else {
		getTransaction().declarerErreur(MairieMessages.getMessage("ERR997","Groupes"));
		return false;
	}
}
/**
 * Retourne le nom du groupe de radio boutons coché pour la JSP :
 * RG_GROUPES
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RG_GROUPES() {
	return "NOM_RG_GROUPES";
}
/**
 * Retourne la valeur du radio bouton (RB_) coché dans la JSP :
 * RG_GROUPES
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getVAL_RG_GROUPES() {
	return getZone(getNOM_RG_GROUPES());
}
/**
 * Retourne le nom du radio bouton pour la JSP :
 * RB_GROUPES
 * Date de création : (06/05/09 15:36:28)
 * @author : Générateur de process
 */
/**
 * @return String
 */
public java.lang.String getNOM_RB_GROUPES() {
	return "NOM_RB_GROUPES";
}


/**
 * controle la validite de la radiobox 
 * et ajoute le droit sélectionné dans la variable activité DROIT_NOM
 * @return true si une radiobox a bien été sélectionnée
 */
public boolean ControleRadioBoxComptes(){
	if (getZone(getNOM_RB_GROUPES())!=null && getZone(getNOM_RB_GROUPES()).length()>0){
		VariableActivite.ajouter(this, GROUPE_ID, getZone(getNOM_RB_GROUPES()));
		return true;
	}else{
		VariableActivite.ajouter(this, GROUPE_ID, "");
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
public String generateTABLO_GROUPES() throws Exception, NumberFormatException{
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

	StringBuffer sbTabloGroupes = new StringBuffer();
	try{
		sbTabloGroupes.append("<table class=\"scrollTable\">");
		sbTabloGroupes.append("<thead class=\"headerFormat fixedHeader\">");  //fixedHeader 
		sbTabloGroupes.append("<tr class=\"title\">");
		sbTabloGroupes.append("<td style=\"border: 0px;background-color:white;\">&nbsp;</td>");
		sbTabloGroupes.append("<td class=\"point\" onclick=\"GoTop(); sortTable(this,1,0); HoverRow();\" title=\"Sort\"><b>GROUPES</b><img src=\"images/none.gif\" border=\"0\" /></td>");
		sbTabloGroupes.append("<td class=\"point\" onclick=\"GoTop(); sortTable(this,1,0); HoverRow();\" title=\"Sort\"><b>DESCRIPTION</b><img src=\"images/none.gif\" border=\"0\" /></td>");
		sbTabloGroupes.append("</tr>");
		sbTabloGroupes.append("</thead>");
		sbTabloGroupes.append("\n");
		sbTabloGroupes.append("<tbody class=\"scrollContent bodyFormat\" style=\"height:200px;\">");

		boolean ispaire=false;
		for(Groupe groupeCourant : listeGroupes){

			//Traitement
			if(ispaire){
				sbTabloGroupes.append("<tr>");
				ispaire=false;
			}else{
				sbTabloGroupes.append("<tr class=\"alternateRow\">");
				ispaire=true;
			}

			sbTabloGroupes.append("<td>");
			sbTabloGroupes.append("<INPUT type=\"radio\" name=\"");
			sbTabloGroupes.append(getNOM_RB_GROUPES());
			sbTabloGroupes.append("\" value=\"");
			sbTabloGroupes.append(groupeCourant.getCdgrou());
			sbTabloGroupes.append("\">");
			sbTabloGroupes.append("</td>");
			sbTabloGroupes.append("<td><b>");
			sbTabloGroupes.append(groupeCourant.getLigrou());
			sbTabloGroupes.append("</b></td>");
			sbTabloGroupes.append("<td>");
			sbTabloGroupes.append(groupeCourant.getDefinition());
			sbTabloGroupes.append("</td>");

			sbTabloGroupes.append("</tr>");
		}


		sbTabloGroupes.append("\n");
		sbTabloGroupes.append("</tbody>");
		sbTabloGroupes.append("</table>");
	}catch (Exception e){
		System.out.println("ERREUR :"+e+"\n"+e.getLocalizedMessage());
		e.printStackTrace();
		return ("");
	}
	return sbTabloGroupes.toString();
}

}
