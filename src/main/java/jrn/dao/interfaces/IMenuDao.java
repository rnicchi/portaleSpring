package jrn.dao.interfaces;

import java.util.List;

import jrn.dao.entities.Menu;

public interface IMenuDao {
	
	/*  METHODS ADMIN MENU  */

	String SELECT_ALL_MENU_STATEMENT = "SELECT * FROM menulistadmin ORDER BY link_id";
	
	public List<Menu> getMenu() throws Exception;
	
	public Menu getMenuElementById(int id) throws Exception;
	
	public Menu insert(Menu menu)  throws Exception;
	
	public int contaOccorrenzeNomeLink(String linkName) throws Exception;

	public void delete(String id) throws Exception;

	public int contaOccorrenzeChild(int parent_id) throws Exception;
	
	public void updateLink(Menu menu) throws Exception;
	
	
	/*  METHODS USER MENU  */
	
	String SELECT_ALL_MENU_USER_STATEMENT = "SELECT * FROM menulistuser ORDER BY link_id";
	
	public List<Menu> getMenuUser() throws Exception;
	
	public Menu getMenuElementByIdUserMenu(int id) throws Exception;
	
	public Menu insertUserMenu(Menu menu)  throws Exception;
	
	public int contaOccorrenzeNomeLinkUserMenu(String linkName) throws Exception;

	public void deleteUserMenu(String id) throws Exception;

	public int contaOccorrenzeChildUserMenu(int parent_id) throws Exception;
	
	public void updateLinkUserMenu(Menu menu) throws Exception;
	
	
}
