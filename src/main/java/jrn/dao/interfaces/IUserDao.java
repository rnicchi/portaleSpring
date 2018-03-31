package jrn.dao.interfaces;
import java.util.List;

import javax.sql.DataSource;

import jrn.dao.entities.*;
/**
* 
* HACK Nicchi 29/10/2014 
* interface dao with statement and functions for User entity
*/
public interface IUserDao 
{
	
	String SELECT_ALL_USER_STATEMENT = "SELECT * FROM user ORDER BY login_id DESC";
	
	public User getLoggedUser(String username) throws Exception;
	
    public int contaOccorrenzeUsername(String username) throws Exception;
	
	public User insert(User user)  throws Exception ;
	
	public List<Roles> getUserRolesById(int id) throws Exception;
	
	public User getUserById(int id) throws Exception;
	
	public void updateUser(User user) throws Exception ;
	
	public void updateUserSameUsername(User user) throws Exception;
	
	public int contaOccorrenzeUserRoles(int id) throws Exception ;
	
	public List<User> getFilterUsers(String roleName, String userName, String userAbilitation, String userEmail, String userTel) throws Exception;
	
	void delete(int id) throws Exception;
		

}