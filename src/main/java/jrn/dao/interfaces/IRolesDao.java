package jrn.dao.interfaces;

import java.util.List;

import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;

public interface IRolesDao {
	
	String SELECT_ALL_ROLES_STATEMENT = "SELECT * FROM roles"; //ORDER BY role_id DESC
	
	public Roles insert(Roles role) throws Exception;
	
	public void updateRole(Roles role) throws Exception;
	
	public String delete(int id) throws Exception;
	
	public int contaOccorrenzeRolePermissions(int id) throws Exception;
	
	public void insertAssociazioneUserRuolo(String[] listaRuoli, String userId)
			throws Exception;
	
	public void deleteAssociazioneUserRuoloByUserId(String userId) throws Exception;
	
	public List<Permissions> getRolePermissionsById(int id) throws Exception;

	int contaOccorrenzeRoleUser(int id) throws Exception;
	
	public List<Roles> getFilterRoles(String permName, String permId) throws Exception;

}
