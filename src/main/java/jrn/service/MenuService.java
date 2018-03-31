package jrn.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jrn.dao.MenuDao;
import jrn.dao.entities.Menu;
import jrn.dao.entities.User;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
	
	@Autowired
	private MenuDao dao;
	
	/*  METHODS ADMIN MENU  */
	
	public List<Menu> getAllMenuElements() throws Exception
	{	
		return dao.getMenu();
	}
	
	
	public Menu getMenuElementById(int id) throws Exception{
		
		return dao.getMenuElementById(id);
		
	}
	
public Menu insert(Menu menu)  throws Exception {
		
		return dao.insert(menu);
		
	}

public int contaOccorrenzeNomeLink(String linkName) throws Exception{
	
	return dao.contaOccorrenzeNomeLink(linkName);
	
}

public void delete(String link_id) throws Exception{
	
	dao.delete(link_id);
}


public int contaOccorrenzeChild(int parent_id) throws Exception{
	
	return dao.contaOccorrenzeChild(parent_id);
	
}

public void updateLink(Menu menu) throws Exception {
	
	dao.updateLink(menu);
	
}


/*  METHODS USER MENU  */

public List<Menu> getAllMenuUserElements() throws Exception
{	
	return dao.getMenuUser();
}


public Menu getMenuElementByIdUserMenu(int id) throws Exception{
	
	return dao.getMenuElementByIdUserMenu(id);
	
}

public Menu insertUserMenu(Menu menu)  throws Exception {
	
	return dao.insertUserMenu(menu);
	
}

public int contaOccorrenzeNomeLinkUserMenu(String linkName) throws Exception{

return dao.contaOccorrenzeNomeLinkUserMenu(linkName);

}

public void deleteUserMenu(String link_id) throws Exception{

dao.deleteUserMenu(link_id);
}


public int contaOccorrenzeChildUserMenu(int parent_id) throws Exception{

return dao.contaOccorrenzeChildUserMenu(parent_id);

}

public void updateLinkUserMenu(Menu menu) throws Exception {

dao.updateLinkUserMenu(menu);

}



}
