package nc.mairie.droitsapplis.process;

import javax.servlet.http.HttpServletRequest;

import nc.mairie.technique.BasicProcess;

public class DroitsApplis extends BasicProcess {

	@Override
	public String getJSP() {
		return "DroitsApplis.jsp";
	}

	@Override
	public void initialiseZones(HttpServletRequest request) throws Exception {
		//System.out.println("PASS initZone DroitsApplis");
	}

	@Override
	public boolean recupererStatut(HttpServletRequest request) throws Exception {
		//System.out.println("PASS recupStatut DroitsApplis");
		return false;
	}

}
