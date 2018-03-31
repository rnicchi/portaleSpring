package jrn.service;



import java.util.List;












import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;












import jrn.dao.UserDao;
import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;


@Service
public class UserService {
	@Autowired private UserDao dao;
	
	
	public User getLoggedUser(String username) throws Exception {
		
		return dao.getLoggedUser(username);
	}
	
	
	public User insert(User user)  throws Exception {
		
		return dao.insert(user);
		
	}
	
	public int contaOccorrenzeUsername(String username) throws Exception {
		
		return dao.contaOccorrenzeUsername(username);
	}
	
	public void delete(int id) throws Exception {
		
		dao.delete(id);
		
	}
	
	
	public List<User> getAllUsers() throws Exception
	{
		return dao.getUsers();
	}
	
	
public List<User> getAllFilterUsers(String roleName, String userName, String userAbilitation, 
		String userEmail, String userTel) throws Exception {
		
		return dao.getFilterUsers(roleName, userName, userAbilitation, userEmail, userTel);
		
	}
	
	
	public User getUserById(int id) throws Exception{
		
		return dao.getUserById(id);
		
	}
	
	public List<Roles> getUserRolesById(int id) throws Exception
	{
		return dao.getUserRolesById(id);
	}
	
	public List<Permissions> getUserRolesPermissionsByRoleId(int id) throws Exception
	{
		return dao.getUserRolesPermissionsByRoleId(id);
	}
	
		
	public void updateUser(User user) throws Exception 
	{
		dao.updateUser(user);
		
	}
	
	public void updateUserSameUsername(User user) throws Exception{
		
		dao.updateUserSameUsername(user);		
	}
	
		
}


