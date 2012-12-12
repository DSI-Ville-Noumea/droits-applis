package nc.mairie.droitsapplis.servlets;

import javax.servlet.http.HttpServletRequest;

import nc.mairie.droitsapplis.client.CheckDroits;
import nc.mairie.droitsapplis.robot.DroitsApplisRobot;
import nc.mairie.robot.Robot;
import nc.mairie.servlets.Frontale;
import nc.mairie.technique.Transaction;
import nc.mairie.technique.UserAppli;



public class DroitsApplisServlet extends Frontale {

	@Override
	protected Robot getServletRobot() {
		return new DroitsApplisRobot();
	}

	public DroitsApplisServlet() {
		super();
	}
	
	public void init() {
		super.init();
		System.out.println("BASE DE DONNEE SUR "+getMesParametres().get("HOST_SGBD"));
		
	}
	
	public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException, ClassCastException {
		super.doPost(request, response);
	}

	public void initialiseHabilitations(javax.servlet.http.HttpServletRequest request) throws Exception{
        //recup du userAppli
        UserAppli aUserAppli = getUserAppli(request);
       Transaction t = new Transaction(getUserAppli(request));
       try{
    	   aUserAppli.setListeDroits(CheckDroits.getListDroitsFromCompteAppli(t, aUserAppli.getUserName(), (String)Frontale.getMesParametres().get("APPLICATION")));
           t.rollbackTransaction();
    	   //System.out.println("setListeDroits("+aUserAppli.getUserName()+" - "+(String)Frontale.getMesParametres().get("APPLICATION")+" de taille="+aUserAppli.getListeDroits().size());
    	   //for (int i=0;i<aUserAppli.getListeDroits().size();i++){
    		//   System.out.println("TTTTTT-"+aUserAppli.getListeDroits().get(i)+"-TTTTTT");
    	  // }
   		nc.mairie.technique.VariableGlobale.ajouter(request,nc.mairie.technique.VariableGlobale.GLOBAL_USER_APPLI,aUserAppli);
   	}catch (Exception e){
   		System.out.println("ERREUR de CHECKDROITS "+e);
   	}
}



protected boolean performControleHabilitation(HttpServletRequest request)
                               throws Exception {
	//System.out.println("performControleHabilitation");
                    if (! super.performControleHabilitation(request)) return false;
                    UserAppli aUserAppli = getUserAppli(request);
                   //Si pas de droits
                    if (aUserAppli.getListeDroits().size() == 0) {
                               //init des ghabilitations
                               initialiseHabilitations(request);
                              //Si pas d'habilitation alors erreur
                               if (aUserAppli.getListeDroits().size() == 0) {
                                           String message = "Le user "+aUserAppli.getUserName()+" n'est pas habilité à utiliser l'application";
                                           System.out.println(message);
                                           //throw new Exception (message);
                                           //return false;
                                           }
                    }
                   return true;
       }


}
