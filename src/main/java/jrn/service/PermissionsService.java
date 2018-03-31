package jrn.service;

import java.util.ArrayList;
import java.util.List;

import jrn.dao.PermissionsDao;
import jrn.dao.RolesDao;
import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionsService {
	
	@Autowired 
	private PermissionsDao dao;
	
	public List<Permissions> getAllPermissions() throws Exception
	{
		return dao.getPermissions();
	}

	
	
public ArrayList<Integer> listaIdPermessiByRoleId(int id) throws Exception{
		
		return dao.listaIdPermessiByRoleId(id);
		
	}
	

public void associazioneRuoloPermessi(String[] listaPermessi, String roleId) throws Exception{
	
	dao.insertAssociazioneRuoloPermessi(listaPermessi, roleId);
	
}


public void cancellaRelazioneRuoloPermessiByRoleId(String roleId) throws Exception{
	
	dao.deleteAssociazioneRuoloPermessiByRoleId(roleId);
	
}


public Permissions insert(Permissions permission) throws Exception {
	
	return dao.insert(permission);
	
}

public void updatePermission(Permissions permission) throws Exception {
	
	dao.updatePermission(permission);
	
}

public String delete(int id) throws Exception {

	return dao.delete(id);

}

/*
public void delete(int id) throws Exception {

	dao.delete(id);

}
*/
	
}
