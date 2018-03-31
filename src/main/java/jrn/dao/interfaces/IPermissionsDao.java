package jrn.dao.interfaces;

import java.util.ArrayList;

import jrn.dao.entities.Permissions;

public interface IPermissionsDao {
	
	String SELECT_ALL_PERMISSIONS_STATEMENT = "SELECT * FROM permissions";//ORDER BY permission_id DESC
	
	public void insertAssociazioneRuoloPermessi(String[] listaPermessi, String roleId) throws Exception;
	
	public void deleteAssociazioneRuoloPermessiByRoleId(String roleId) throws Exception;
	
	public Permissions insert(Permissions permission) throws Exception;

	public void updatePermission(Permissions permission) throws Exception;

	public String delete(int id) throws Exception;

	int contaOccorrenzePermissionRole(int id) throws Exception;

	
	
}
