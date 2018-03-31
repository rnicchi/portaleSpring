package jrn.service;

import java.util.ArrayList;
import java.util.List;

import jrn.dao.RolesDao;
import jrn.dao.UserDao;
import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
	
	@Autowired 
	private RolesDao dao;
	
	public List<Roles> getAllRoles() throws Exception
	{
		return dao.getRoles();
	}
	

	public List<Roles> getAllFilterRoles(String permName, String permId) throws Exception {
		
		return dao.getFilterRoles(permName, permId);
		
	}
	
	
	public ArrayList<Integer> listaIdRuoliByUserId(int id) throws Exception{
		
		return dao.listaIdRuoliByUserId(id);
		
	}
	
	
	public void associazioneUserRuolo(String[] listaRuoli, String userId) throws Exception{
		
		dao.insertAssociazioneUserRuolo(listaRuoli, userId);
		
	}
	
	
	public void cancellaRelazioneUserRuoloByUserId(String userId) throws Exception{
		
		dao.deleteAssociazioneUserRuoloByUserId(userId);
		
	}
	
	
	public List<Permissions> getRolePermissionsById(int id) throws Exception{
		
		return dao.getRolePermissionsById(id) ;
			
	}

	public Roles insert(Roles role) throws Exception {
		
		return dao.insert(role);
		
	}

	public void updateRole(Roles role) throws Exception {
		
		dao.updateRole(role);
		
	}

	
	/*public void delete(int id) throws Exception {

		dao.delete(id);

	}*/
	
	public String delete(int id) throws Exception {

		return dao.delete(id);

	}
	
	
	
	
}
