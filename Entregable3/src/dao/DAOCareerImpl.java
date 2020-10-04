package dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import EntityManagerFactory.EMF;
import entity.Career;

public class DAOCareerImpl extends EMF{
	EntityManager em = initialiceEntityManager();
	
	public List<Career> getAllEnrolled() {
		Query query2f = em.createQuery("SELECT c FROM Career c WHERE c.registered > 0 ORDER BY registered",
				Career.class);
		@SuppressWarnings("unchecked")
		List<Career> careers = query2f.getResultList();
		return careers;
	}
	
	
	public List<Object> getReport() {
		
		Query firstQuery = em.createQuery("SELECT c.name, r.registrationYear, COUNT(r) FROM Registration r JOIN r.career c "+
				 "GROUP BY c.name, r.registrationYear ORDER BY c.name, r.registrationYear");
		@SuppressWarnings("unchecked")
		List<Object[]> listFirstQuery = firstQuery.getResultList();
		
		Query secondQuery = em.createQuery("SELECT c.name, r.graduationYear, COUNT(r) FROM Registration r JOIN r.career c "+
				 "WHERE r.finished = true "+
				 "GROUP BY c.name, r.graduationYear ORDER BY c.name, r.graduationYear");
		@SuppressWarnings("unchecked")
		List<Object[]> listSecondQuery = secondQuery.getResultList();
		
		List<Object> list = new ArrayList<Object>();
		
		String nameCareer = null;
		

		for(int i=0; i<listFirstQuery.size(); i++) {
			
			if(nameCareer == null) {				
				nameCareer = (String) listFirstQuery.get(i)[0];
			
			} else if(!listFirstQuery.get(i)[0].equals(nameCareer)) {
				
				for(int l=0; l<listSecondQuery.size(); l++) {
					
					if(listSecondQuery.get(l)[0].equals(nameCareer)) {
						if(!list.contains(listSecondQuery.get(l)[1])) {
							list.add(listSecondQuery.get(l)[1]);
							list.add(0);
							list.add(listSecondQuery.get(l)[2]);
						}	
					}
				}	
				nameCareer = (String) listFirstQuery.get(i)[0];
			}
			
			if(!list.contains(listFirstQuery.get(i)[0])) {
				list.add(listFirstQuery.get(i)[0]);
			}
				for(int k=0; k<listSecondQuery.size(); k++) {
					if(listFirstQuery.get(i)[0].equals(listSecondQuery.get(k)[0])) { 
						
						if((((int) listFirstQuery.get(i)[1]) < (int) (listSecondQuery.get(k)[1]))) {
							list.add(listFirstQuery.get(i)[1]);
							list.add(listFirstQuery.get(i)[2]);
							list.add(0);
							break;
							
							
						} else if((((int) listFirstQuery.get(i)[1]) == (int) (listSecondQuery.get(k)[1]))) {
							list.add(listFirstQuery.get(i)[1]);
							list.add(listFirstQuery.get(i)[2]);
							list.add(listSecondQuery.get(k)[2]);
							break;
							
						} else {
							if(!list.contains(listFirstQuery.get(i)[1])) {
								list.add(listSecondQuery.get(k)[1]);
								list.add(0);
								list.add(listSecondQuery.get(k)[2]);	
							}	
						}
					}
					if((k == listSecondQuery.size()-1) && (!list.contains(listFirstQuery.get(i)[1]))) {
						list.add(listFirstQuery.get(i)[1]);
						list.add(listFirstQuery.get(i)[2]);
						list.add(0);
					}
				}
		}
		return list;
	}

}
