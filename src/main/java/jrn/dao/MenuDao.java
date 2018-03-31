package jrn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import jrn.dao.entities.Menu;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;
import jrn.dao.entities.ValidationException;
import jrn.dao.interfaces.IMenuDao;
import jrn.dao.setters.MenuRowMapper;
import jrn.dao.setters.UserRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class MenuDao extends JdbcDaoSupport implements IMenuDao {
	
private DataSource dataSource; 
	
	
	@Autowired
	public void setDataSource(org.apache.commons.dbcp.BasicDataSource dataSource){
	    this.dataSource = (DataSource)dataSource;
	}
	
	
	// ******* METHODS ADMIN MENU ********
	
	
	
	//HACK Nicchi 19/11/2014 : function to get menu's element by IDLink.
		public Menu getMenuElementById(int id) throws Exception {
			
			Menu menu=null;
					
			Connection connection = null;
			PreparedStatement query = null;
			ResultSet rs = null;
			try {
				connection = dataSource.getConnection();
				query = connection.prepareStatement(
						"SELECT * FROM menulistadmin where link_id=?");
								
				query.setInt(1, id);
				rs = query.executeQuery();
				
					while(rs.next()) {
					
						
									
					int idLink = rs.getInt("link_id");
					String linkName = rs.getString("link_name");
					String link = rs.getString("link");
					String imgLink = rs.getString("img_link");
					String target = rs.getString("target");
					int parentId = rs.getInt("parent_id");
					int subParentId = rs.getInt("subParent_id");
				
					menu = new Menu();
					
					menu.setLink_id(idLink);
					menu.setLink_name(linkName);
					menu.setLink(link);
					menu.setLink(imgLink);
					menu.setTarget(target);
					menu.setParent_id(parentId);
					menu.setSubParent_id(subParentId);
					
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
			return menu;
		}
	
	
		//HACK Nicchi 19/11/2014 : function to insert a menu's element.
				public Menu insert(Menu menu)  throws Exception
				{
					Connection connection = null;
					PreparedStatement preparedStatemen = null;
					
					try {
						connection = dataSource.getConnection();
						preparedStatemen = connection.prepareStatement("INSERT INTO menulistadmin (link_name, link, img_link, target, parent_id, subParent_id) VALUES (?,?,?,?,?,?)");
						
						preparedStatemen.setString(1, menu.getLink_name());
						preparedStatemen.setString(2, menu.getLink());
						preparedStatemen.setString(3, menu.getImg_link());
						preparedStatemen.setString(4, menu.getTarget());
						preparedStatemen.setInt(5, menu.getParent_id());
						preparedStatemen.setInt(6, menu.getSubParent_id());
						
						
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
					
					return menu;	
								
				}
	
	
	
				//HACK Nicchi 19/11/2014 : function to count exist element's name of the menu.
				@Override
				public int contaOccorrenzeNomeLink(String linkName) throws Exception {
					
					int occorrenze = 0;
					
					Connection connection = null;
					PreparedStatement preparedStatemen = null;
					
					try {
						connection = dataSource.getConnection();
						preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM menulistadmin WHERE link_name= ?");
										
						preparedStatemen.setString(1, linkName);
										
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
	
				
				
				//HACK Nicchi 24/11/2014 : function to count exist element's with child.
				@Override
				public int contaOccorrenzeChild(int link_id) throws Exception {
					
					int occorrenze = 0;
					
					Connection connection = null;
					PreparedStatement preparedStatemen = null;
					
					try {
						connection = dataSource.getConnection();
						preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM menulistadmin WHERE parent_id = ?");
										
						preparedStatemen.setInt(1, link_id);
										
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
				
				

				//HACK Nicchi 24/11/2014 : function to delete a menu's element.
				
				@Override
				public void delete(String id) throws Exception {
					
					Connection connection = null;
					PreparedStatement preparedStatemen = null;
					
					
					try{
						
						connection = dataSource.getConnection();
						preparedStatemen = connection.prepareStatement("DELETE FROM menulistadmin WHERE link_id=?");
						
						int idLink = Integer.parseInt(id);
						preparedStatemen.setInt(1, idLink);
						
						
						preparedStatemen.executeUpdate();
						
						
						
					}
					catch (Exception e) {
						
						e.printStackTrace();
					}
					finally{
						
						preparedStatemen.close();
						
						
						connection.close();
						
						
					}
							
						
					
					
				}	
	
	
	
				//HACK Nicchi 26/11/2014 : function to update a link.	
				public void updateLink(Menu menu) throws Exception 
				{
							
					Connection connection = null;
					PreparedStatement preparedStatemen = null;
					
					try {
						connection = dataSource.getConnection();
						preparedStatemen = connection.prepareStatement("UPDATE menulistadmin SET link_name = ?, link = ?, img_link = ?, target = ?, parent_id = ?, subParent_id= ? WHERE link_id=?");
											
						preparedStatemen.setString(1, menu.getLink_name());
						preparedStatemen.setString(2, menu.getLink());
						preparedStatemen.setString(3, menu.getImg_link());
						preparedStatemen.setString(4, menu.getTarget());
						preparedStatemen.setInt(5, menu.getParent_id());
						preparedStatemen.setInt(6, menu.getSubParent_id());
						preparedStatemen.setInt(7, menu.getLink_id());
						
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
	
	
	public List<Menu> getMenu() throws Exception 
	{
		List<Menu> _menu = null;
		try {
				_menu =  this.getJdbcTemplate().query(IMenuDao.SELECT_ALL_MENU_STATEMENT, new MenuRowMapper(), null);
		} 
		catch (Exception e) 
		{
		
			throw new Exception("Exception querying database to fetch Users",e);
		} 
		return _menu;
	}
	
	
	// ******* METHODS USER MENU ********
	
	
	//HACK Nicchi 19/11/2014 : function to get menu's element by IDLink.
			public Menu getMenuElementByIdUserMenu(int id) throws Exception {
				
				Menu menu=null;
						
				Connection connection = null;
				PreparedStatement query = null;
				ResultSet rs = null;
				try {
					connection = dataSource.getConnection();
					query = connection.prepareStatement(
							"SELECT * FROM menulistuser where link_id=?");
									
					query.setInt(1, id);
					rs = query.executeQuery();
					
						while(rs.next()) {
						
							
										
						int idLink = rs.getInt("link_id");
						String linkName = rs.getString("link_name");
						String link = rs.getString("link");
						String imgLink = rs.getString("img_link");
						String target = rs.getString("target");
						int parentId = rs.getInt("parent_id");
						int subParentId = rs.getInt("subParent_id");
					
						menu = new Menu();
						
						menu.setLink_id(idLink);
						menu.setLink_name(linkName);
						menu.setLink(link);
						menu.setLink(imgLink);
						menu.setTarget(target);
						menu.setParent_id(parentId);
						menu.setSubParent_id(subParentId);
						
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
				return menu;
			}
		
		
			//HACK Nicchi 19/11/2014 : function to insert a menu's element.
					public Menu insertUserMenu(Menu menu)  throws Exception
					{
						Connection connection = null;
						PreparedStatement preparedStatemen = null;
						
						try {
							connection = dataSource.getConnection();
							preparedStatemen = connection.prepareStatement("INSERT INTO menulistuser (link_name, link, img_link, target, parent_id, subParent_id) VALUES (?,?,?,?,?,?)");
							
							preparedStatemen.setString(1, menu.getLink_name());
							preparedStatemen.setString(2, menu.getLink());
							preparedStatemen.setString(3, menu.getImg_link());
							preparedStatemen.setString(4, menu.getTarget());
							preparedStatemen.setInt(5, menu.getParent_id());
							preparedStatemen.setInt(6, menu.getSubParent_id());
							
							
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
						
						return menu;	
									
					}
		
		
		
					//HACK Nicchi 19/11/2014 : function to count exist element's name of the menu.
					@Override
					public int contaOccorrenzeNomeLinkUserMenu(String linkName) throws Exception {
						
						int occorrenze = 0;
						
						Connection connection = null;
						PreparedStatement preparedStatemen = null;
						
						try {
							connection = dataSource.getConnection();
							preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM menulistuser WHERE link_name= ?");
											
							preparedStatemen.setString(1, linkName);
											
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
		
					
					
					//HACK Nicchi 24/11/2014 : function to count exist element's with child.
					@Override
					public int contaOccorrenzeChildUserMenu(int link_id) throws Exception {
						
						int occorrenze = 0;
						
						Connection connection = null;
						PreparedStatement preparedStatemen = null;
						
						try {
							connection = dataSource.getConnection();
							preparedStatemen = connection.prepareStatement("SELECT COUNT(*) TOT FROM menulistuser WHERE parent_id = ?");
											
							preparedStatemen.setInt(1, link_id);
											
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
					
					

					//HACK Nicchi 24/11/2014 : function to delete a menu's element.
					
					@Override
					public void deleteUserMenu(String id) throws Exception {
						
						Connection connection = null;
						PreparedStatement preparedStatemen = null;
						
						
						try{
							
							connection = dataSource.getConnection();
							preparedStatemen = connection.prepareStatement("DELETE FROM menulistuser WHERE link_id=?");
							
							int idLink = Integer.parseInt(id);
							preparedStatemen.setInt(1, idLink);
							
							
							preparedStatemen.executeUpdate();
							
							
							
						}
						catch (Exception e) {
							
							e.printStackTrace();
						}
						finally{
							
							preparedStatemen.close();
							
							
							connection.close();
							
							
						}
								
							
						
						
					}	
		
		
		
					//HACK Nicchi 26/11/2014 : function to update a link.	
					public void updateLinkUserMenu(Menu menu) throws Exception 
					{
								
						Connection connection = null;
						PreparedStatement preparedStatemen = null;
						
						try {
							connection = dataSource.getConnection();
							preparedStatemen = connection.prepareStatement("UPDATE menulistuser SET link_name = ?, link = ?, img_link = ?, target = ?, parent_id = ?, subParent_id= ? WHERE link_id=?");
												
							preparedStatemen.setString(1, menu.getLink_name());
							preparedStatemen.setString(2, menu.getLink());
							preparedStatemen.setString(3, menu.getImg_link());
							preparedStatemen.setString(4, menu.getTarget());
							preparedStatemen.setInt(5, menu.getParent_id());
							preparedStatemen.setInt(6, menu.getSubParent_id());
							preparedStatemen.setInt(7, menu.getLink_id());
							
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
		
		
		public List<Menu> getMenuUser() throws Exception 
		{
			List<Menu> _menu = null;
			try {
					_menu =  this.getJdbcTemplate().query(IMenuDao.SELECT_ALL_MENU_USER_STATEMENT, new MenuRowMapper(), null);
			} 
			catch (Exception e) 
			{
			
				throw new Exception("Exception querying database to fetch Users",e);
			} 
			return _menu;
		}
	
	
	
	

}
