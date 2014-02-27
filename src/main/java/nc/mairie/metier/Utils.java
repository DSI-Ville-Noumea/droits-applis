/*
 * Created on 6 févr. 2009
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nc.mairie.metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nc.mairie.droitsapplis.metier.Compte;
import nc.mairie.droitsapplis.metier.DroitsApp;
import nc.mairie.droitsapplis.metier.Groupe;

/**
 * @author fonol77
 * Méthodes génériques utilitaires
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Utils {
	
	/**
	 * 
	 * @param l1
	 * @param l2
	 * @return ArrayListe l1 ayant éléminé de la liste l1 les éléments en communs avec l2
	 * fonctionne uniquement avec une liste l1 n'ayant pas 2 elements identiques
	 */
	public static ArrayList<?> Elim_doubure(ArrayList<?> l1,ArrayList<?> l2)
	{
		if (null==l1)
			return null;
		
		if (null!=l2){
			for(int i = 0; i <l2.size(); i++){
				for(int j = 0; j <l1.size(); j++)
				{
					if(l2.get(i) == l1.get(j))
						l1.remove(j);
					
				}
			}
		}
		return l1;
		
	} 
	
	
	public static ArrayList<Groupe> Elim_doubure_Groupe(ArrayList<Groupe> l1,ArrayList<Groupe> l2)
	{
		//System.out.println("L1="+l1);
		//System.out.println("L2="+l2);
		if (null==l1)
			return null;
		
		ArrayList<Groupe> ltemp=new ArrayList<Groupe>(l1);
		
		if (null!=l2){
			for(int i = 0; i <l2.size(); i++){
				for(int j = 0; j <ltemp.size(); j++)
				{
					//System.out.println("L2["+i+"]:"+l2.get(i).getCdgrou());
					//System.out.println("L1["+j+"]:"+ltemp.get(j).getCdgrou());
					if(l2.get(i).getCdgrou().equals(ltemp.get(j).getCdgrou()))
						ltemp.remove(j);
				}
			}
		}
		return ltemp;
		
	} 
	
	public static ArrayList<Compte> Elim_doubure_Compte(ArrayList<Compte> l1,ArrayList<Compte> l2)
	{
		//System.out.println("L1="+l1);
		//System.out.println("L2="+l2);
		if (null==l1)
			return null;
		
		ArrayList<Compte> ltemp=new ArrayList<Compte>(l1);
		
		if (null!=l2){
			for(int i = 0; i <l2.size(); i++){
				for(int j = 0; j <ltemp.size(); j++)
				{
					//System.out.println("L2["+i+"]:"+l2.get(i).getCdidut());
					//System.out.println("L1["+j+"]:"+ltemp.get(j).getCdidut());
					if(l2.get(i).getCdidut().equals(ltemp.get(j).getCdidut()))
						ltemp.remove(j);
				}
			}
		}
		return ltemp;
		
	} 

	public static ArrayList<DroitsApp> Elim_doubure_Droit(ArrayList<DroitsApp> l1,ArrayList<DroitsApp> l2)
	{
		//System.out.println("L1="+l1);
		//System.out.println("L2="+l2);
		if (null==l1)
			return null;
		
		ArrayList<DroitsApp> ltemp=new ArrayList<DroitsApp>(l1);
		
		if (null!=l2){
			for(int i = 0; i <l2.size(); i++){
				for(int j = 0; j <ltemp.size(); j++)
				{
					//System.out.println("L2["+i+"]:"+l2.get(i).getCddrap());
					//System.out.println("L1["+j+"]:"+ltemp.get(j).getCddrap());
					if(l2.get(i).getCddrap().equals(ltemp.get(j).getCddrap()))
						ltemp.remove(j);
				}
			}
		}
		return ltemp;
		
	} 
	/**
	 * 
	 * @param l1
	 * @param l2
	 * @return ArrayListe l1 ayant éléminé de la liste l1 les elements e2
	 * 
	 */
	public static ArrayList<?> Elim_element(ArrayList<?> l1, Object e2)
	{
		if (null==l1)
			return null;
		
		if (null!=e2){
				for(int j = 0; j <l1.size(); j++)
				{
					if(e2 == l1.get(j))
						l1.remove(j);
				}
		}
		return l1;
		
	} 

	
	/**
	 * 
	 * @param al
	 * @return ArrayList entrée moins ses doublons.
	 */
	public static ArrayList<Object> antiDoublon(ArrayList<?> al) {
        
        ArrayList<Object> al2 = new ArrayList<Object>();
        for (int i=0; i<al.size(); i++) {
            Object o = al.get(i);
            if (!al2.contains(o))
                al2.add(o);
        }
        al = null;
        return al2;
        
    }

	public static void affichhMap(HashMap<String,boolean[]> hTMapDroits) {
		for ( Iterator<Map.Entry<String,boolean[]>> iter = hTMapDroits.entrySet().iterator(); iter.hasNext(); ) {
			Map.Entry<String,boolean[]> ent = iter.next();
			//La clé de la HashMap
			String droit = ent.getKey();
			//La Valeur de la HashMap
			boolean[] tablo = ent.getValue();
			System.out.println();
			System.out.print("droit="+droit+" ");
			for (int i=0; i<tablo.length; i++)
				System.out.print(tablo[i]+" ");
		}
		System.out.println();
	}
	
}
