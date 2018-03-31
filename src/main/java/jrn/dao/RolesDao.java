package jrn.dao;




import java.awt.Window;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.JOptionPane;

import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;
import jrn.dao.entities.ValidationException;
import jrn.dao.interfaces.IRolesDao;
import jrn.dao.interfaces.IUserDao;
import jrn.dao.setters.RolesRowMapper;
import jrn.dao.setters.UserRowMapper;
import jrn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class RolesDao extends JdbcDaoSupport  implements IRolesDao{

private DataSource dataSource; 
	
	
	@Autowired
	public void setDataSource(org.apache.commons.dbcp.BasicDataSource dataSource){
	    this.dataSource = (DataSource)dataSource;
	}
	
	
	//HACK Nicchi 29/10/2014 : function to get the list of roles.
	public List<Roles> getRoles() throws Exception 
	{
		List<Roles> _roles = null;
		try {
				_roles=  this.getJdbcTemplate().query(IRolesDao.SELECT_ALL_ROLES_STATEMENT, new RolesRowMapper(), null);
		} 
		catch (Exception e) 
		{
		
			throw new Exception("Exception querying database to fetch Users",e);
		} 
		
		
		
		
		return _roles;
		
	}
	
	
	//HACK Nicchi 29/10/2014 : function to get the list of filtered-roles
	public List<Roles> getFilterRoles(String permName, String permId) throws Exception 
	{
		List<Roles> _roles = new ArrayList<Roles>();

		String select = "SELECT";
		String selectDistinct = "SELECT DISTINCT";
		//String orderByDesc = " ORDER BY role_id DESC";
		String nessuna_condizione = " * FROM ROLES ORDER BY role_id DESC";
		String elementi = " r.role_id, r.role_name, r.role_desc";
		String permName_cond = " FROM roles r inner join rel_roles_permissions rp on r.role_id = rp.role_id inner join permissions p on rp.permission_id = p.permission_id and p.permission_name = ";
		String andPermInd_cond = " and p.permission_id = ";
		String permId_cond = " FROM roles r inner join rel_roles_permissions rp on r.role_id = rp.role_id inner join permissions p on rp.permission_id = p.permission_id and p.permission_id = ";

		try {

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;

			try {
				connection = dataSource.getConnection();

				if(permName.equals("") && permId.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							select + nessuna_condizione);
				}
				else if(!permName.equals("") && permId.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + permName_cond + "?");

					preparedStatement.setString(1, permName);
				}
				else if(permName.equals("") && !permId.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + permId_cond + "?");
					preparedStatement.setString(1, permId);
				}
				else if(!permName.equals("") && !permId.equals(""))
				{
					preparedStatement = connection.prepareStatement(
							selectDistinct + elementi + permName_cond + "?" + andPermInd_cond + "?");
					preparedStatement.setString(1, permName);
					preparedStatement.setString(2, permId);
				}


				rs = preparedStatement.executeQuery();



				while(rs.next()) {	
					
					Roles r = new Roles();

					int idRuolo = rs.getInt("role_id");
					String nomeRuolo = rs.getString("role_name");
					String descRuolo = rs.getString("role_desc");

					r.setRole_id(idRuolo);
					r.setRole_name(nomeRuolo);
					r.setRole_desc(descRuolo);


					_roles.add(r);
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
		
		
		return _roles;

	}
	
	
	//HACK Nicchi 29/10/2014 : function to get the list of role's permissions by IDrole
	public List<Permissions> getRolePermissionsById(int id) throws Exception {
		
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
	
	
	//HACK Nicchi 29/10/2014 : function to get the list of IDroles by IDuser
	public ArrayList<Integer> listaIdRuoliByUserId(int id) throws Exception{
		
		ArrayList<Integer> lista = new ArrayList<Integer>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT rur.role_id FROM rel_user_roles rur WHERE rur.login_id=?");
			
			preparedStatement.setInt(1, id);
							
			
			rs = preparedStatement.executeQuery();
			
				while(rs.next()) {
					
					lista.add(rs.getInt("role_id"));
				}
				
		} catch (Exception e) {
			
			throw new Exception("Exception querying database to fetch User",e);
		} finally {
			try {
				
				preparedStatement.close();
				
				connection.close();
				
			} catch (SQLException e) {
				
			}
		}
		
								
				return lista;
			
	}
	
	
	//HACK Nicchi 29/10/2014 : function to insert a relationship between users and roles.
	public void insertAssociazioneUserRuolo(String[] listaRuoli, String userId)
			throws Exception{

		Connection connection = null;
		PreparedStatement preparedStatemen = null;

		int contatore = listaRuoli.length;
		for (int i = 0; i < contatore; i++) {
			
			try {
				connection = dataSource.getConnection();
				
				preparedStatemen = connection.prepareStatement("INSERT INTO rel_user_roles (login_id, role_id) values (?,?)");
				int idUtente = Integer.parseInt(userId);
				int idRuolo = Integer.parseInt(listaRuoli[i]);
				preparedStatemen.setInt(1, idUtente);
				preparedStatemen.setInt(2, idRuolo);
				
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

	}
	
	//HACK Nicchi 29/10/2014 : function to delete a relationship between roles and permissions.
	public void deleteAssociazioneUserRuoloByUserId(String userId) throws Exception{
		
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		String query = "DELETE FROM rel_user_roles WHERE login_id=?";
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("DELETE FROM rel_user_roles WHERE login_id=?");
				
			int idUtente = Integer.parseInt(userId);
			preparedStatemen.setInt(1, idUtente);
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


	//HACK Nicchi 29/10/2014 : function to insert a new role.
	public Roles insert(Roles role) throws Exception{
			
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("INSERT INTO roles (role_name, role_desc) VALUES (?,?)");
			
			preparedStatemen.setString(1, role.getRole_name());
			preparedStatemen.setString(2, role.getRole_desc());
				
			preparedStatemen.executeUpdate();
			
		} 
		catch (Exception e) {
		     
		    	 	throw new Exception("Exception querying database to fetch User",e);
		     
		} 
		
		finally {
			try {
				
				preparedStatemen.close();
				
				connection.close();
				
			} catch (SQLException e) {
				
			}
			
		}
		
		return role;	
					
	}


	//HACK Nicchi 29/10/2014 : function to update a role.
	public void updateRole(Roles role) throws Exception{
				
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("UPDATE roles SET role_name = ?, role_desc = ? WHERE role_id=?");
								
			preparedStatemen.setString(1, role.getRole_name());
			preparedStatemen.setString(2, role.getRole_desc());
			preparedStatemen.setInt(3, role.getRole_id());
					
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

	//HACK Nicchi 29/10/2014 : function to count relationship between users and roles.
	@Override
	public int contaOccorrenzeRoleUser(int id) throws Exception {
		
		int occorrenze = 0;
		
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM rel_user_roles WHERE role_id= ?");
							
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
	
	
	
	//HACK Nicchi 29/10/2014 : function to count relationship between permissions and roles.
	@Override
	public int contaOccorrenzeRolePermissions(int id) throws Exception {
		
		int occorrenze = 0;
		
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM rel_roles_permissions WHERE role_id= ?");
							
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
	
	
	
	//HACK Nicchi 29/10/2014 : function to delete a role.
	public String delete(int id) throws Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		PreparedStatement preparedStatemen2 = null;
		String message = "";
		try {
			int occorrenze2 = contaOccorrenzeRoleUser(id);
			if(occorrenze2 > 0 )
			{
				message=  "Non è possibile proseguire con la cancellazione del ruolo"+
						" poichè associato ad uno o più utenti!";
				
			}
			else
			{
			
					int occorrenze = contaOccorrenzeRolePermissions(id);
				
					connection = dataSource.getConnection();
		
					if(occorrenze > 0)
					{
						preparedStatemen = connection.prepareStatement("DELETE rel_roles_permissions FROM rel_roles_permissions INNER JOIN roles WHERE rel_roles_permissions.role_id = roles.role_id and roles.role_id = ?");
	
						preparedStatemen.setInt(1, id);
		
						preparedStatemen.executeUpdate();
						
						preparedStatemen2 = connection.prepareStatement("DELETE FROM roles WHERE role_id=?");
						
						preparedStatemen2.setInt(1, id);
						
						preparedStatemen2.executeUpdate();
					}
					else
					{
						preparedStatemen = connection.prepareStatement("DELETE FROM roles WHERE role_id=?");
						
						
						preparedStatemen.setInt(1, id);
						
						preparedStatemen.executeUpdate();
					}
			}
			
			
		} catch (Exception e) {
		
			throw new Exception("Exception querying database to fetch User",e);
		} finally {
			try {	
				
				if(preparedStatemen != null && preparedStatemen2 != null)
				{
					preparedStatemen.close();
					preparedStatemen2.close();
				}
				
				if(preparedStatemen != null)
				preparedStatemen.close();
				
				
				if(connection != null)
				connection.close();
				
			} catch (SQLException e) {
				
			}
			
		}
		return message;
	}

		
		
	}
	
	
	

