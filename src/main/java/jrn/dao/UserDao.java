package jrn.dao;

import jrn.dao.entities.ValidationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import oracle.jdbc.*;
import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;
import jrn.dao.interfaces.IUserDao;
import jrn.dao.setters.UserRowMapper;


@Repository
public class UserDao extends JdbcDaoSupport  implements IUserDao 
{
	
	
	 private DataSource dataSource; 
	
	
	@Autowired
	public void setDataSource(org.apache.commons.dbcp.BasicDataSource dataSource){
	    this.dataSource = (DataSource)dataSource;
	}
	
	
	//HACK Nicchi 29/10/2014 : function to insert a user.
		public User insert(User user)  throws Exception, ValidationException
		{
			Connection connection = null;
			PreparedStatement preparedStatemen = null;
			
			try {
				connection = dataSource.getConnection();
				preparedStatemen = connection.prepareStatement("INSERT INTO user (username, password, email, tel, enabled) VALUES (?,?,?,?,?)");
				
				preparedStatemen.setString(1, user.getUsername());
				preparedStatemen.setString(2, user.getPassword());
				preparedStatemen.setString(3, user.getEmail());
				preparedStatemen.setString(4, user.getTel());
				preparedStatemen.setInt(5, user.getEnabled());
				
				try
				{
				preparedStatemen.executeUpdate();
				}
				catch(SQLException ex)
				{
					throw new ValidationException("Ins numeri");
				}
			} 
			catch (Exception e) {
			     if(e instanceof ValidationException)
			     {
						HttpServletRequest request = null;
						HttpServletResponse response = null;
					
						request.setAttribute("errore", e.getMessage());
						RequestDispatcher test = request.getRequestDispatcher("/insertNewUser.jsp");
						test.forward(request, response);
			     }
			     else
			     {
			    	 	throw new Exception("Exception querying database to fetch User",e);
			     }
			} 
			
			finally {
				try {
					
					preparedStatemen.close();
					
					connection.close();
					
				} catch (SQLException e) {
					
				}
				
			}
			
			return user;	
						
		}
		
		//HACK Nicchi 10/11/2014 : function to count exist username.
				@Override
				public int contaOccorrenzeUsername(String username) throws Exception {
					
					int occorrenze = 0;
					
					Connection connection = null;
					PreparedStatement preparedStatemen = null;
					
					try {
						connection = dataSource.getConnection();
						preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM user WHERE username= ?");
										
						preparedStatemen.setString(1, username);
										
						 ResultSet rs = preparedStatemen.executeQuery();
						 while(rs.next()) {
								
							 occorrenze = rs.getInt("TOT");
						 }
						
						
					} catch (Exception e) {
					
						throw new Exception("Exception querying database to fetch User",e);
					} finally {
						try {
							
							preparedStatemen.close();
							
							connection.close();
							
						} catch (SQLException e) {
							
						}
						
					}
					return occorrenze;
					
				}	
		
	
		//HACK Nicchi 29/10/2014 : function to count relationship between users and roles.
		@Override
		public int contaOccorrenzeUserRoles(int id) throws Exception {
			
			int occorrenze = 0;
			
			Connection connection = null;
			PreparedStatement preparedStatemen = null;
			
			try {
				connection = dataSource.getConnection();
				preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM rel_user_roles WHERE login_id= ?");
								
				preparedStatemen.setInt(1, id);
				
				ResultSet rs = preparedStatemen.executeQuery();
				 while(rs.next()) {
						
					 occorrenze = rs.getInt("TOT");
				 }
								
				
			} catch (Exception e) {
			
				throw new Exception("Exception querying database to fetch User",e);
			} finally {
				try {
					
					preparedStatemen.close();
					
					connection.close();
					
				} catch (SQLException e) {
					
				}
				
			}
			return occorrenze;
			
		}	
		
		//HACK Nicchi 29/10/2014 : function to delete a user.
		@Override
		public void delete(int id) throws Exception {
			
			Connection connection = null;
			PreparedStatement preparedStatemen = null;
			PreparedStatement preparedStatemen2 = null;
			
			try {
				
				int occorrenze = contaOccorrenzeUserRoles(id);
				
				connection = dataSource.getConnection();
				
				if(occorrenze > 0)
				{
				preparedStatemen = connection.prepareStatement("DELETE rel_user_roles FROM rel_user_roles INNER JOIN user WHERE rel_user_roles.login_id = user.login_id and user.login_id = ?");

				
				preparedStatemen.setInt(1, id);
				
				preparedStatemen.executeUpdate();
				
				preparedStatemen2 = connection.prepareStatement("DELETE FROM user WHERE login_id=?");
				
				preparedStatemen2.setInt(1, id);
				
				preparedStatemen2.executeUpdate();
				
				}
				else
				{
					preparedStatemen = connection.prepareStatement("DELETE FROM user WHERE login_id=?");
					
					
					preparedStatemen.setInt(1, id);
					
					
					preparedStatemen.executeUpdate();
				}
				
			} catch (Exception e) {
			
				throw new Exception("Exception querying database to fetch User",e);
			} finally {
				try {
					
					if(preparedStatemen2 != null)
					{
						preparedStatemen.close();
						preparedStatemen2.close();
					}
					else
					{
					preparedStatemen.close();
					}
					
					connection.close();
					
				} catch (SQLException e) {
					
				}
				
			}
			
		}	
	
		
	
	//HACK Nicchi 29/10/2014 : function to update a user.	
	public void updateUser(User user) throws Exception 
	{
				
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("UPDATE user SET username = ?, password = ?, email = ?, tel = ?, enabled = ? WHERE login_id=?");
								
			preparedStatemen.setString(1, user.getUsername());
			preparedStatemen.setString(2, user.getPassword());
			preparedStatemen.setString(3, user.getEmail());
			preparedStatemen.setString(4, user.getTel());
			preparedStatemen.setInt(5, user.getEnabled());
			preparedStatemen.setInt(6, user.getLogin_id());
			
			preparedStatemen.executeUpdate();
			
			
		} catch (Exception e) {
		
			throw new Exception("Exception querying database to fetch User",e);
		} finally {
			try {
				
				preparedStatemen.close();
				
				connection.close();
				
			} catch (SQLException e) {
				
			}
			
		}
				
	}
	
	//HACK Nicchi 12/11/2014 : function to update a user whitout change username.	
		public void updateUserSameUsername(User user) throws Exception 
		{
					
			Connection connection = null;
			PreparedStatement preparedStatemen = null;
			
			try {
				connection = dataSource.getConnection();
				preparedStatemen = connection.prepareStatement("UPDATE user SET password = ?, email = ?, tel = ?, enabled = ? WHERE login_id=?");
									
				preparedStatemen.setString(1, user.getPassword());
				preparedStatemen.setString(2, user.getEmail());
				preparedStatemen.setString(3, user.getTel());
				preparedStatemen.setInt(4, user.getEnabled());
				preparedStatemen.setInt(5, user.getLogin_id());
				
				preparedStatemen.executeUpdate();
				
				
			} catch (Exception e) {
			
				throw new Exception("Exception querying database to fetch User",e);
			} finally {
				try {
					
					preparedStatemen.close();
					
					connection.close();
					
				} catch (SQLException e) {
					
				}
				
			}
					
		}
	
	//HACK Nicchi 29/10/2014 : function to get user by IDuser.
	public User getUserById(int id) throws Exception {
		
		User user=null;
				
		Connection connection = null;
		PreparedStatement query = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			query = connection.prepareStatement(
					"SELECT * FROM user where login_id=?");
							
			query.setInt(1, id);
			rs = query.executeQuery();
			
				while(rs.next()) {
				
					
								
				int idUser = rs.getInt("login_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String tel = rs.getString("tel");
				int enabled = rs.getInt("enabled");
			
				user = new User();
				
				user.setLogin_id(idUser);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				user.setTel(tel);
				user.setEnabled(enabled);
				
			}
			
			
		} catch (Exception e) {
		
			throw new Exception("Exception querying database to fetch User",e);
		} finally {
			try {
				
				query.close();
				
				connection.close();
				
			} catch (SQLException e) {
				
			}
		}
		return user;
	}
	
	
	
	//HACK Nicchi 10/11/2014 : function to get Logged user.
		public User getLoggedUser(String principalUsername) throws Exception {
			
			User user=null;
					
			Connection connection = null;
			PreparedStatement query = null;
			ResultSet rs = null;
			try {
				connection = dataSource.getConnection();
				query = connection.prepareStatement(
						"SELECT * FROM user where username=?");
								
				query.setString(1, principalUsername);
				
				rs = query.executeQuery();
				
					while(rs.next()) {
					
						
									
					int idUser = rs.getInt("login_id");
					String userName = rs.getString("username");
					String password = rs.getString("password");
					String email = rs.getString("email");
					String tel = rs.getString("tel");
					int enabled = rs.getInt("enabled");
				
					user = new User();
					
					user.setLogin_id(idUser);
					user.setUsername(userName);
					user.setPassword(password);
					user.setEmail(email);
					user.setTel(tel);
					user.setEnabled(enabled);
					
				}
				
				
			} catch (Exception e) {
			
				throw new Exception("Exception querying database to fetch User",e);
			} finally {
				try {
					
					query.close();
					
					connection.close();
					
				} catch (SQLException e) {
					
				}
			}
			return user;
		}
	
	
	
	
	//HACK Nicchi 29/10/2014 : function to get the list of user's roles by IDuser.
	public List<Roles> getUserRolesById(int id) throws Exception {
		
		List<Roles> rolesList = new ArrayList<Roles>();
				
		Connection connection = null;
		PreparedStatement query = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			query = connection.prepareStatement(
					"SELECT r.role_id, r.role_name, r.role_desc from roles r inner join rel_user_roles ur on r.role_id = ur.role_id inner join user u on ur.login_id = u.login_id and u.login_id=?");
							
			
			query.setInt(1, id);
			rs = query.executeQuery();
			
		
			while(rs.next()) {
				//user = new User();
				Roles role = new Roles();
				
				int idRuolo = rs.getInt("role_id");
				String nomeRuolo = rs.getString("role_name");
				String descRuolo = rs.getString("role_desc");
			
				role.setRole_id(idRuolo);
				role.setRole_name(nomeRuolo);
				role.setRole_desc(descRuolo);
				
				rolesList.add(role);
				
			}
			
			
		} catch (Exception e) {
		
			throw new Exception("Exception querying database to fetch User",e);
		} finally {
			try {
				
				query.close();
				
				connection.close();
				
			} catch (SQLException e) {
				
			}
		}
		return rolesList;
	}
	
	//HACK Nicchi 29/10/2014 : function to the list of the user's role's permissions by IDrole.
	public List<Permissions> getUserRolesPermissionsByRoleId(int id) throws Exception {
		
		List<Permissions> permissionsList = new ArrayList<Permissions>();
				
		Connection connection = null;
		PreparedStatement query = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			query = connection.prepareStatement(
					"SELECT p.permission_id, p.permission_name, p.permission_desc from permissions p inner join rel_roles_permissions rp on p.permission_id = rp.permission_id inner join roles r on rp.role_id = r.role_id and r.role_id=?");
						
			
			query.setInt(1, id);
			rs = query.executeQuery();
			
			while(rs.next()) {
				
				Permissions permission = new Permissions();
				
				int idPermesso = rs.getInt("permission_id");
				String nomePermesso = rs.getString("permission_name");
				String descPermesso = rs.getString("permission_desc");
			
				permission.setPermission_id(idPermesso);
				permission.setPermission_name(nomePermesso);
				permission.setPermission_desc(descPermesso);
				
				permissionsList.add(permission);
				
			}
			
			
		} catch (Exception e) {
		
			throw new Exception("Exception querying database to fetch User",e);
		} finally {
			try {
				
				query.close();
				
				connection.close();
				
			} catch (SQLException e) {
				
			}
		}
		return permissionsList;
	}
	
	//HACK Nicchi 29/10/2014 : function to get the list of filtered-users
	public List<User> getFilterUsers(String roleName, String userName, String userAbilitation, 
		String userEmail, String userTel) throws Exception
		{
			
		List<User> _users = new ArrayList<User>();

		String selectDistinct = "SELECT DISTINCT";
		String nessuna_condizione = " * FROM user ORDER BY login_id DESC";
		String elementi = " u.login_id, u.username, u.password, u.email, u.tel, u.enabled";
		String from = " FROM user u inner join rel_user_roles ur on u.login_id = ur.login_id inner join roles r on ur.role_id = r.role_id";
		String andRoleName_cond = " and r.role_name = ";
		String andUserName_cond = " and u.username = ";
		String andUserEmail_cond = " and u.email = ";
		String andUserTel_cond = " and u.tel = ";
		String andUserAbilitation_cond = " and u.enabled = ";
		
		try {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;

			try {
				connection = dataSource.getConnection();

				if(roleName.equals("") && userName.equals("") && userAbilitation.equals("")
						&& userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							"SELECT" + nessuna_condizione);
				}
				else if(!roleName.equals("") && userName.equals("") && userAbilitation.equals("")
						&& userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?");

					preparedStatement.setString(1, roleName);
				}
				else if(roleName.equals("") && !userName.equals("") && userAbilitation.equals("")
						&& userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserName_cond + "?");
					preparedStatement.setString(1, userName);
				}
				else if(roleName.equals("") && userName.equals("") && !userAbilitation.equals("")
						&& userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserAbilitation_cond + "?");
					preparedStatement.setString(1, userAbilitation);
				}
				else if(roleName.equals("") && userName.equals("") && userAbilitation.equals("")
						&& !userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserEmail_cond + "?");
					preparedStatement.setString(1, userEmail);
				}
				else if(roleName.equals("") && userName.equals("") && userAbilitation.equals("")
						&& userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserTel_cond + "?");
					preparedStatement.setString(1, userTel);
				}
				else if(!roleName.equals("") && !userName.equals("") && userAbilitation.equals("")
						&& userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserName_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userName);
				}
				else if(!roleName.equals("") && userName.equals("") && !userAbilitation.equals("")
						&& userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserAbilitation_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userAbilitation);
				}else if(!roleName.equals("") && userName.equals("") && userAbilitation.equals("")
						&& !userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserEmail_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userEmail);
				}else if(!roleName.equals("") && userName.equals("") && userAbilitation.equals("")
						&& userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userTel);
				}else if(roleName.equals("") && !userName.equals("") && !userAbilitation.equals("")
						&& userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserName_cond + "?" + andUserAbilitation_cond + "?" );
					preparedStatement.setString(1, userName);
					preparedStatement.setString(2, userAbilitation);
				}else if(roleName.equals("") && !userName.equals("") && userAbilitation.equals("")
						&& !userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserName_cond + "?" + andUserEmail_cond + "?" );
					preparedStatement.setString(1, userName);
					preparedStatement.setString(2, userEmail);
				}else if(roleName.equals("") && !userName.equals("") && userAbilitation.equals("")
						&& userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserName_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, userName);
					preparedStatement.setString(2, userTel);
				}else if(roleName.equals("") && userName.equals("") && !userAbilitation.equals("")
						&& !userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserAbilitation_cond + "?" + andUserEmail_cond + "?" );
					preparedStatement.setString(1, userAbilitation);
					preparedStatement.setString(2, userEmail);
				}else if(roleName.equals("") && userName.equals("") && !userAbilitation.equals("")
						&& userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserAbilitation_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, userAbilitation);
					preparedStatement.setString(2, userTel);	
				}else if(roleName.equals("") && userName.equals("") && userAbilitation.equals("")
						&& !userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserEmail_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, userEmail);
					preparedStatement.setString(2, userTel);	
					
					//DA QUI SOTTO COMBINAZIONI A TRE
					
				}
				else if(!roleName.equals("") && !userName.equals("") && !userAbilitation.equals("")
						&& userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserName_cond + "?" + andUserAbilitation_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userName);
					preparedStatement.setString(3, userAbilitation);
				}
				else if(!roleName.equals("") && !userName.equals("") && userAbilitation.equals("")
						&& !userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserName_cond + "?" + andUserEmail_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userName);
					preparedStatement.setString(3, userEmail);
				}else if(!roleName.equals("") && !userName.equals("") && userAbilitation.equals("")
						&& userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserName_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userName);
					preparedStatement.setString(3, userTel);
					
					
				}else if(!roleName.equals("") && userName.equals("") && !userAbilitation.equals("")
						&& !userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserAbilitation_cond + "?" + andUserEmail_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userAbilitation);
					preparedStatement.setString(3, userEmail);
				}else if(!roleName.equals("") && userName.equals("") && !userAbilitation.equals("")
						&& userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserAbilitation_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userAbilitation);
					preparedStatement.setString(3, userTel);					
				}else if(!roleName.equals("") && userName.equals("") && userAbilitation.equals("")
						&& !userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserEmail_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userEmail);
					preparedStatement.setString(3, userTel);
					
				}else if(roleName.equals("") && !userName.equals("") && !userAbilitation.equals("")
						&& !userEmail.equals("") && userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserName_cond + "?" + andUserAbilitation_cond + "?" + andUserEmail_cond + "?" );
					preparedStatement.setString(1, userName);
					preparedStatement.setString(2, userAbilitation);
					preparedStatement.setString(3, userEmail);
				}else if(roleName.equals("") && !userName.equals("") && !userAbilitation.equals("")
						&& userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserName_cond + "?" + andUserAbilitation_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, userName);
					preparedStatement.setString(2, userAbilitation);
					preparedStatement.setString(3, userTel);
				}else if(roleName.equals("") && !userName.equals("") && userAbilitation.equals("")
						&& !userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserName_cond + "?" + andUserEmail_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, userName);
					preparedStatement.setString(2, userEmail);
					preparedStatement.setString(3, userTel);	
					
					
				}else if(roleName.equals("") && userName.equals("") && !userAbilitation.equals("")
						&& !userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserAbilitation_cond + "?" + andUserEmail_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, userAbilitation);
					preparedStatement.setString(2, userEmail);
					preparedStatement.setString(3, userTel);	
					
				}else if(!roleName.equals("") && !userName.equals("") && !userAbilitation.equals("")
					&& !userEmail.equals("") && userTel.equals(""))
			{
				preparedStatement = connection.prepareStatement(
						selectDistinct + elementi + from + andRoleName_cond + "?" + andUserName_cond + "?" + andUserAbilitation_cond + "?" + andUserEmail_cond + "?" );
				preparedStatement.setString(1, roleName);
				preparedStatement.setString(2, userName);
				preparedStatement.setString(3, userAbilitation);
				preparedStatement.setString(4, userEmail);
			}
				else if(!roleName.equals("") && userName.equals("") && !userAbilitation.equals("")
						&& !userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserAbilitation_cond + "?" + andUserEmail_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userAbilitation);
					preparedStatement.setString(3, userEmail);
					preparedStatement.setString(4, userTel);
				}
				else if(!roleName.equals("") && !userName.equals("") && userAbilitation.equals("")
						&& !userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserName_cond + "?" + andUserEmail_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userName);
					preparedStatement.setString(3, userEmail);
					preparedStatement.setString(4, userTel);
				}
				else if(!roleName.equals("") && !userName.equals("") && !userAbilitation.equals("")
						&& userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andRoleName_cond + "?" + andUserAbilitation_cond + "?" + andUserName_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userAbilitation);
					preparedStatement.setString(3, userName);
					preparedStatement.setString(4, userTel);
				}
				else if(roleName.equals("") && !userName.equals("") && !userAbilitation.equals("")
						&& !userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from + andUserName_cond + "?" + andUserAbilitation_cond + "?" + andUserEmail_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, userName);
					preparedStatement.setString(2, userAbilitation);
					preparedStatement.setString(3, userEmail);
					preparedStatement.setString(4, userTel);
				}
				else if(!roleName.equals("") && !userName.equals("") && !userAbilitation.equals("")
						&& !userEmail.equals("") && !userTel.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + from +  andRoleName_cond + "?" + andUserName_cond + "?" + andUserAbilitation_cond + "?" + andUserEmail_cond + "?" + andUserTel_cond + "?" );
					preparedStatement.setString(1, roleName);
					preparedStatement.setString(2, userName);
					preparedStatement.setString(3, userAbilitation);
					preparedStatement.setString(4, userEmail);
					preparedStatement.setString(5, userTel);
				}
				
				rs = preparedStatement.executeQuery();

				while(rs.next()) {		

					User u = new User();

					int idUser = rs.getInt("login_id");
					String uName = rs.getString("username");
					String uPassword = rs.getString("password");
					String uEmail = rs.getString("email");
					String uTel = rs.getString("tel");
					int uEnabled = rs.getInt("enabled");

					u.setLogin_id(idUser);
					u.setUsername(uName);
					u.setPassword(uPassword);
					u.setEmail(uEmail);
					u.setTel(uTel);
					u.setEnabled(uEnabled);
					
					
					_users.add(u);
					
				}


			} 
			catch (Exception e) 
			{

				throw new Exception("Exception querying database to fetch Users",e);
			} 
			finally
			{
				rs.close();
				preparedStatement.close();
				connection.close();		
			
			}
		}
		catch (Exception e) 
		{
		
			throw new Exception("Exception querying database to fetch Users",e);
		
		}
		finally{
					
					
					
				}
		
		
		return _users;
		}
									
	
	
	
	
	

	
    //HACK Lippolis 22/11/2013 : get all users
		
	public List<User> getUsers() throws Exception 
	{
		List<User> _users = null;
		try {
				_users=  this.getJdbcTemplate().query(IUserDao.SELECT_ALL_USER_STATEMENT, new UserRowMapper(), null);
		} 
		catch (Exception e) 
		{
		
			throw new Exception("Exception querying database to fetch Users",e);
		} 
		return _users;
	}


	
	
	
	
}
