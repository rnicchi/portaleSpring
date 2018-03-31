package jrn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.interfaces.IPermissionsDao;
import jrn.dao.interfaces.IRolesDao;
import jrn.dao.setters.PermissionsRowMapper;
import jrn.dao.setters.RolesRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;


@Repository
public class PermissionsDao extends JdbcDaoSupport  implements IPermissionsDao{
	
	private DataSource dataSource; 
	
	@Autowired
	public void setDataSource(org.apache.commons.dbcp.BasicDataSource dataSource){
	    this.dataSource = (DataSource)dataSource;
	}
	
	//HACK Nicchi 29/10/2014 : function to get the list of permissions
	public List<Permissions> getPermissions() throws Exception 
	{
		List<Permissions> _permissions = null;
		try {
			_permissions=  this.getJdbcTemplate().query(IPermissionsDao.SELECT_ALL_PERMISSIONS_STATEMENT, new PermissionsRowMapper(), null);
	} 
	catch (Exception e) 
	{
	
		throw new Exception("Exception querying database to fetch Users",e);
	} 
	return _permissions;
	}

	//HACK Nicchi 29/10/2014 : function to get the list of IDpermissions by IDrole
	public ArrayList<Integer> listaIdPermessiByRoleId(int id) throws Exception{

		ArrayList<Integer> lista = new ArrayList<Integer>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(
					"SELECT rop.permission_id FROM rel_roles_permissions rop WHERE rop.role_id=?");

			preparedStatement.setInt(1, id);


			rs = preparedStatement.executeQuery();

			while(rs.next()) {

				lista.add(rs.getInt("permission_id"));
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

	//HACK Nicchi 29/10/2014 : function to insert a relationship between roles and permissions.
	public void insertAssociazioneRuoloPermessi(String[] listaPermessi, String roleId) throws Exception{

		Connection connection = null;
		PreparedStatement preparedStatemen = null;

		int contatore = listaPermessi.length;
		for (int i = 0; i < contatore; i++) {
			
			try {
				connection = dataSource.getConnection();
											
				preparedStatemen = connection.prepareStatement("INSERT INTO rel_roles_permissions (role_id, permission_id) values (?,?)");
				int idRuolo = Integer.parseInt(roleId);
				int idPermesso = Integer.parseInt(listaPermessi[i]);
				preparedStatemen.setInt(1, idRuolo);
				preparedStatemen.setInt(2, idPermesso);
				
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
	public void deleteAssociazioneRuoloPermessiByRoleId(String roleId) throws Exception{
		
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("DELETE FROM rel_roles_permissions WHERE role_id=?");
				
			int idRuolo = Integer.parseInt(roleId);
			preparedStatemen.setInt(1, idRuolo);
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

	//HACK Nicchi 29/10/2014 : function to insert a new permission.
	public Permissions insert(Permissions permission) throws Exception{
				
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("INSERT INTO permissions (permission_name, permission_desc) VALUES (?,?)");
			
			preparedStatemen.setString(1, permission.getPermission_name());
			preparedStatemen.setString(2, permission.getPermission_desc());
				
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
		
		return permission;	
					
	}

	
	//HACK Nicchi 29/10/2014 : function to update a permission.
	public void updatePermission(Permissions permission) throws Exception{
				
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("UPDATE permissions SET permission_name = ?, permission_desc = ? WHERE permission_id=?");
								
			preparedStatemen.setString(1, permission.getPermission_name());
			preparedStatemen.setString(2, permission.getPermission_desc());
			preparedStatemen.setInt(3, permission.getPermission_id());
					
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
	
	//HACK Nicchi 29/10/2014 : function to count relationship between permissions and roles.
	@Override
	public int contaOccorrenzePermissionRole(int id) throws Exception {
		
		int occorrenze = 0;
		
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM rel_roles_permissions WHERE permission_id= ?");
							
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

	//HACK Nicchi 29/10/2014 : function to delete a permission.
public String delete(int id) throws Exception {
		
		Connection connection = null;
		PreparedStatement preparedStatemen = null;
		//PreparedStatement preparedStatemen2 = null;
		String message = "";
		try {
				int occorrenze = contaOccorrenzePermissionRole(id);
				
				if(occorrenze > 0 )
				{
					message = "Non è possibile proseguire con la cancellazione del permesso"+
							" poichè associato ad uno o più ruoli!";
	
				}
				else
				{
	
					connection = dataSource.getConnection();
	
					preparedStatemen = connection.prepareStatement("DELETE FROM permissions WHERE permission_id=?");
	
					preparedStatemen.setInt(1, id);
	
					preparedStatemen.executeUpdate();
				}
			
			
		} catch (Exception e) {
		
			throw new Exception("Exception querying database to fetch User",e);
		} finally {
			try {
								
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
