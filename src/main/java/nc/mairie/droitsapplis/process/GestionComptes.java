package nc.mairie.droitsapplis.process;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import nc.mairie.droitsapplis.metier.CompteGroupe;
import nc.mairie.droitsapplis.metier.Groupe;
import nc.mairie.technique.*;
/**
 * Process GestionComptes
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
*/
public class GestionComptes extends nc.mairie.technique.BasicProcess {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3663117149158487055L;
	public static final int STATUT_EDITCOMPTE=1;
	public static final int STATUT_AJOUTCOMPTE=2;
	public static final int STATUT_RETOUR=4;
	
	public static final String ACTION_COMPTE= "ACTION_COMPTE";
	public static final String AJOUTER_COMPTE= "AJOUTER_COMPTE";
	public static final String MODIFIER_COMPTE= "MODIFIER_COMPTE";
	public static final String SUPPRIMER_COMPTE= "SUPPRIMER_COMPTE";
	public static final String COMPTE_NOM = "COMPTE_NOM";
	
	private java.util.ArrayList<Groupe>  listeGroupes;
	private java.util.ArrayList<CompteGroupe>  listeComptesGroupes;
	//key=compte utilisateur, ArrayList=liste des CDGROU (identifiants de groupe)
	private HashMap<String,Boolean[]> hTMapComptes;
/**
 * Initialisation des zones à afficher dans la JSP
 * Alimentation des listes, s'il y en a, avec setListeLB_XXX()
 * ATTENTION : Les Objets dans la liste doivent avoir les Fields PUBLIC
 * Utilisation de la méthode addZone(getNOMxxx, String);
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public void initialiseZones(javax.servlet.http.HttpServletRequest request) throws Exception{
	//System.out.println("PASS initZone GestionComptes");
		initialiseGroupes(request);
		initialiseComptesGroupes(request);
		initialiseHashMap(request);	
	}

public void initialiseGroupes(javax.servlet.http.HttpServletRequest request) throws Exception{
	listeGroupes=Groupe.listerGroupe(getTransaction());
}


public void initialiseComptesGroupes(javax.servlet.http.HttpServletRequest request) throws Exception{
	listeComptesGroupes=CompteGroupe.listerCompteGroupe(getTransaction());
}

public void initialiseHashMap(javax.servlet.http.HttpServletRequest request) throws Exception{
	String compteCourant="";
	hTMapComptes=new HashMap<String, Boolean[]>();
	//on boucle sur la liste complète des comptes groupe (SIGRUT)
	for(CompteGroupe cgCourant : listeComptesGroupes){
		//System.out.println("GROU="+cgCourant.cdgrou+" CDIDUT="+cgCourant.cdidut);
		Boolean[] tabBooGroupes=new Boolean[listeGroupes.size()];
		compteCourant=cgCourant.getCdidut();
		if(hTMapComptes.containsKey(compteCourant)){
			tabBooGroupes=hTMapComptes.get(compteCourant);
		}
		//on rempli à true lorsqu'on tombe sur le bon groupe
		for(int i=0; i<listeGroupes.size(); i++){
			if (null==tabBooGroupes[i])
				tabBooGroupes[i]=false;
			Groupe gCourant=listeGroupes.get(i);
				if (cgCourant.getCdgrou().equals(gCourant.getCdgrou())){
					tabBooGroupes[i]=true;
				}
		}
		hTMapComptes.put(compteCourant, tabBooGroupes);
		}
}

/**
 * Méthode appelée par la servlet qui aiguille le traitement : 
 * en fonction du bouton de la JSP 
 * Date de création : (23/04/09 10:40:08)
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
 * Constructeur du process GestionComptes.
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public GestionComptes() {
	super();
}
/**
 * Retourne le nom de la JSP du process
 * Zone à utiliser dans un champ caché dans chaque formulaire de la JSP.
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public String getJSP() {
	return "GestionComptes.jsp";
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_AJOUTER
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_AJOUTER() {
	return "NOM_PB_AJOUTER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public boolean performPB_AJOUTER(javax.servlet.http.HttpServletRequest request) throws Exception {

		VariableActivite.ajouter(this, ACTION_COMPTE, AJOUTER_COMPTE);
		setStatut(STATUT_AJOUTCOMPTE,true);
		return true;

}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_ANNULER
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_ANNULER() {
	return "NOM_PB_ANNULER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public boolean performPB_ANNULER(javax.servlet.http.HttpServletRequest request) throws Exception {
	setStatut(STATUT_RETOUR);
	return true;
}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_MODIFIER
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_MODIFIER() {
	return "NOM_PB_MODIFIER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public boolean performPB_MODIFIER(javax.servlet.http.HttpServletRequest request) throws Exception {
	
	if (ControleRadioBoxComptes()){
		VariableActivite.ajouter(this, ACTION_COMPTE, MODIFIER_COMPTE);
		setStatut(STATUT_EDITCOMPTE,true);
		return true;
	} else {
		getTransaction().declarerErreur(MairieMessages.getMessage("ERR997","Comptes"));
		return false;
	}

}
/**
 * Retourne le nom d'un bouton pour la JSP :
 * PB_SUPPRIMER
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public java.lang.String getNOM_PB_SUPPRIMER() {
	return "NOM_PB_SUPPRIMER";
}
/**
 * - Traite et affecte les zones saisies dans la JSP.
 * - Implémente les règles de gestion du process
 * - Positionne un statut en fonction de ces règles :
 *   setStatut(STATUT, boolean veutRetour) ou setStatut(STATUT,Message d'erreur)
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public boolean performPB_SUPPRIMER(javax.servlet.http.HttpServletRequest request) throws Exception {

		if (ControleRadioBoxComptes()){
			VariableActivite.ajouter(this, ACTION_COMPTE, SUPPRIMER_COMPTE);
			setStatut(STATUT_EDITCOMPTE,true);
			return true;
		} else {
			getTransaction().declarerErreur(MairieMessages.getMessage("ERR997","Comptes"));
			return false;
		}
}
/**
 * Retourne le nom du radio bouton pour la JSP :
 * RB_COMPTES
 * Date de création : (23/04/09 10:40:08)
 * @author : Générateur de process
 */
public java.lang.String getNOM_RB_COMPTES() {
	return "NOM_RB_COMPTES";
}

/**
 * controle la validite de la radiobox 
 * et ajoute le compte sélectionné dans la variable activité COMPTE_NOM
 * @return true si une radiobox a bien été sélectionnée
 */
public boolean ControleRadioBoxComptes(){
	if (getZone(getNOM_RB_COMPTES())!=null && getZone(getNOM_RB_COMPTES()).length()>0){
		VariableActivite.ajouter(this, COMPTE_NOM, getZone(getNOM_RB_COMPTES()));
		return true;
	}else{
		VariableActivite.ajouter(this, COMPTE_NOM, "");
		return false;
	}
}

/**
 * 
 * Affichage du tableau qui liste les comptes des utilisateurs
 * @return
 * @throws Exception
 * @throws NumberFormatException
 */
public String generateTABLO_COMPTES() throws Exception, NumberFormatException{
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
	
	StringBuffer sbTabloComptes = new StringBuffer();
	try{
	sbTabloComptes.append("<table class=\"scrollTable\">");
	sbTabloComptes.append("<thead class=\"headerFormat fixedHeader\">");  //fixedHeader 
	sbTabloComptes.append("<tr class=\"title\">");
	sbTabloComptes.append("<td style=\"border: 0px;background-color:white;\" colspan=\"2\">&nbsp;</td>");
	sbTabloComptes.append("<td align=\"center\" colspan=\"");
	sbTabloComptes.append(listeGroupes.size());
	sbTabloComptes.append("\">GROUPES</td>");
	sbTabloComptes.append("</tr>");
	sbTabloComptes.append("<tr class=\"title\">");
	sbTabloComptes.append("<td style=\"border: 0px;background-color:white;\">&nbsp;</td>");
	sbTabloComptes.append("<td class=\"point\" onclick=\"GoTop(); sortTable(this,2,0); HoverRow();\" title=\"Sort\"><b>COMPTE</b><img src=\"images/none.gif\" border=\"0\" /></td>");
	sbTabloComptes.append("\n");
	for(Groupe groupeCourant : listeGroupes){
		sbTabloComptes.append("<td class=\"point\" onclick=\"GoTop(); sortTable(this,2,0); HoverRow();\" title=\"Sort\"><b>");
		sbTabloComptes.append(groupeCourant.getLigrou().trim());
		sbTabloComptes.append("</b><img src=\"images/none.gif\" border=\"0\" /></td>");
		sbTabloComptes.append("\n");
	}
	sbTabloComptes.append("</tr>");
	sbTabloComptes.append("</thead>");
	sbTabloComptes.append("\n");
	sbTabloComptes.append("<tbody class=\"scrollContent bodyFormat\" style=\"height:200px;\">");
	
	//Traitement pour afficher les croix dans le tableau
	boolean ispaire=false;
	for ( Iterator<Map.Entry<String,Boolean[]>> iter = hTMapComptes.entrySet().iterator(); iter.hasNext(); ) {
		Map.Entry<String,Boolean[]> ent = iter.next();
		//La clé de la HashMap
		String compteCourant = ent.getKey();
		//La Valeur de la HashMap
		Boolean[] tabBooGroupes = ent.getValue();

		//Traitement
		if(ispaire){
			sbTabloComptes.append("<tr>");
			ispaire=false;
		}else{
			sbTabloComptes.append("<tr class=\"alternateRow\">");
			ispaire=true;
		}
		sbTabloComptes.append("<td>");
		sbTabloComptes.append("<INPUT type=\"radio\" name=\"");
		sbTabloComptes.append(getNOM_RB_COMPTES());
		sbTabloComptes.append("\" value=\"");
		sbTabloComptes.append(compteCourant);
		sbTabloComptes.append("\">");
		sbTabloComptes.append("</td>");
		sbTabloComptes.append("<td><b>");
		sbTabloComptes.append(compteCourant);
		sbTabloComptes.append("</b></td>");

		for(int i =0; i<tabBooGroupes.length; i++){
			sbTabloComptes.append("<td align=\"center\"><b>");
			//System.out.println("COMPTE="+compteCourant);
			//System.out.println("tab["+i+"]="+tabBooGroupes[i]);
			if(tabBooGroupes[i])
				sbTabloComptes.append("X");
			else
				sbTabloComptes.append("-");
			sbTabloComptes.append("</b></td>");
		}

		sbTabloComptes.append("</tr>");
	}
	

	sbTabloComptes.append("\n");
	sbTabloComptes.append("</tbody>");
	sbTabloComptes.append("</table>");
	}catch (Exception e){
		System.out.println("ERREUR :"+e+"\n"+e.getLocalizedMessage());
		e.printStackTrace();
		return ("");
	}
	return sbTabloComptes.toString();
}
}
