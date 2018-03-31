package jrn.kpi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jrn.dao.entities.Menu;
import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;
import jrn.service.MenuService;
import jrn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @    SessionAttributes   ("menuElementsList")
public class ViewMenuController {
	
	@Autowired
	MenuService menuService;
	
	@Autowired
	UserService userService;
	
	//ModelAndView modelMenuElementsList;
	
	/*  METHODS ADMIN MENU  */
	
	@RequestMapping(value="/listMenuElements")
	 public ModelAndView menuElementsList(ModelAndView model,Principal principal) throws Exception {  
	   
	  List<Menu> menuElementsList = menuService.getAllMenuElements();
	  
	    
		 	  
	 // modelMenuElementsList = model;
	  
	  model.addObject("menuElementsList", menuElementsList);
	   
	  
	  User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
			 model.addObject("loggedUserTel", tel);
			 model.addObject("loggedUserEmail", email);
	  
	  model.setViewName("listMenuElements");
	  
	 // ModelAndView modelRealMenu = new ModelAndView("menuDinamico");
	  //modelRealMenu.addObject("menuElementsList", menuElementsList);
	 // modelRealMenu.setViewName("menuDinamico");
	  
	  
	  return model;
	  	  
	 }
	
	
	
	public ModelAndView modelRealMenu(ModelAndView model,Principal principal) throws Exception {  
		   
		  List<Menu> menuElementsList = menuService.getAllMenuElements();
		  
		//  List<Menu> subMenuElementsList = menuService.getAllMenuElements();
		  
		 
		    
			 	  
		//  modelMenuElementsList = model;
		  
		  model.addObject("menuElementsList", menuElementsList);
		//  model.addObject("subMenuElementsList", subMenuElementsList);
		  
		  User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);
		  
		  model.setViewName("menuDinamico");
		  
		 // ModelAndView modelRealMenu = new ModelAndView("menuDinamico");
		 // modelRealMenu.addObject("menuElementsList", menuElementsList);
		 // modelRealMenu.setViewName("menuDinamico");
		  
		  
		  return model;
		  	  
		 }
	
	
	 @RequestMapping(value = "/insertNewMenuElement", method = RequestMethod.GET)
		public ModelAndView newMenuElement(ModelAndView model, Principal principal) throws Exception {
		 
		 //String username = principal.getName(); 
		 
		 
		 // NON SERVE PERCHE' HO CARICATO LA LISTA NELPROJECT INIZIALE CHE MANDA AD "HOME"
		// List<Menu> menuElementsList = menuService.getAllMenuElements();
		// model.addObject("menuElementsList", menuElementsList);
		 
		 
			Menu newMenuElement = new Menu();
			model.addObject("newMenuElement", newMenuElement);
			
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);
			
			model.setViewName("insertNewMenuElement");
			return model;
		}
	 
	 @RequestMapping(value = "/saveNewMenuElement", method = RequestMethod.POST)
		public ModelAndView saveNewMenuElement(HttpServletRequest request) throws Exception {
		 
		 
		 
		 String linkName = request.getParameter("link_name");
		 String link = request.getParameter("link");
		 String imgLink = request.getParameter("img_link");
		 String target = request.getParameter("target");
		 int parentId = Integer.parseInt(request.getParameter("parent_id")); 
		 int subParent_id = Integer.parseInt(request.getParameter("subParent_id"));
	          
	          String message = "";
		 
		 Menu menuElement = new Menu(linkName, link, imgLink, target, parentId, subParent_id);
		 
		 	int occorrenze = menuService.contaOccorrenzeNomeLink(linkName);
		 
		 if(occorrenze > 0)
		 {
			 message = "Nel Database è già presente un link chiamato " + linkName + " ! Scegliere un altro nome o modificare quello già esistente!";
			 
			 ModelAndView model = new ModelAndView("insertNewMenuElement");
			 model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
			// model.addObject("loggedUser", username);
			 return model;
		 }
		 else{
		 	menuService.insert(menuElement);
		 	}
			return new ModelAndView("redirect:/listMenuElements");
		}
	
	
	 
	 
	 
	 @RequestMapping(value = "/deleteMenuElement", method = RequestMethod.GET)
	 public ModelAndView deleteMenuElement(HttpServletRequest request) throws Exception {
	 	
		 String link_id = request.getParameter("link_id");
		 int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		 int subParent_id = Integer.parseInt(request.getParameter("subParent_id"));
	 	
	 	ModelAndView model = new ModelAndView("listMenuElements");
	 	
	 	//int occorrenzeChild = menuService.contaOccorrenzeChild(parent_id);
	 	
	 		String message = "";  
	 	if (parent_id == 1 || subParent_id == 1){
	 		message = "Il link che si vuole eliminare ha dei figli. Eliminandolo si creerebbero degli errori. In caso si volesse eleminarlo procedere prima all'eliminazione dei figli e settare l'elemento come vuoto (0).";
	 		model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
	 		model.addObject("menuElement.parent_id", 1);
	 		return model;
	 		
	 	}
	 	else
	 	{
	 		menuService.delete(link_id);
	 		
	 	}
	 	
	 	// new ModelAndView("redirect:/listMenuElements");
	 	
	 	List<Menu> menuElementsList = menuService.getAllMenuElements(); 
	 	 
	 	model.addObject("menuElementsList", menuElementsList);

	 	return model;
	 	
	 }
	 
	 @RequestMapping(value = "/updateLinkInfo", method = RequestMethod.GET)
		public ModelAndView updateLinkInfo (@RequestParam Map<String,String> maps, Principal principal) throws Exception {
		 
			//String loggedUsername = principal.getName();	

		    int link_id = Integer.parseInt(maps.get("link_id"));
		 	String link_name = maps.get("link_name");
		 	String link = maps.get("link");
		 	String img_link = maps.get("img_link");
		 	String target = maps.get("target");
		 	int parent_id = Integer.parseInt(maps.get("parent_id"));
		 	int subParent_id = Integer.parseInt(maps.get("subParent_id"));
		 			
			ModelAndView model = new ModelAndView();
			model.addObject("link_id", link_id);
			model.addObject("link_name", link_name);
			model.addObject("link", link);
			model.addObject("img_link", img_link);
			model.addObject("target", target);
			model.addObject("parent_id", parent_id);
			model.addObject("subParent_id", subParent_id);
			
			//model.addObject("loggedUser", loggedUsername);
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);
			
			model.setViewName("modifyLinkInfo");
			return model;
		}
	 
	 
	 @RequestMapping(value="/updateLink")  
	 public ModelAndView linkEdit(HttpServletRequest request) throws Exception {  
	 	 
	 	int link_id = Integer.parseInt(request.getParameter("link_id"));
	 	String link_name = request.getParameter("link_name");
	 	String link = request.getParameter("link");
	 	String img_link = request.getParameter("img_link");
	 	String target = request.getParameter("target");
	 	int parent_id = Integer.parseInt(request.getParameter("parent_id"));
	 	int subParent_id = Integer.parseInt(request.getParameter("subParent_id"));
	 	 
	 	 
	 	 Menu menu = new Menu(link_id, link_name, link, img_link, target, parent_id, subParent_id);
	
	 	 		 
	 	 menuService.updateLink(menu);
	 	 
	 return new ModelAndView("redirect:/listMenuElements");

	 }
	 
	 
	 
	 
	 /*  METHODS USER MENU  */
	 
	 
	 
	 
	 
	 @RequestMapping(value="/listMenuUserElements")
	 public ModelAndView menuUserElementsList(ModelAndView model,Principal principal) throws Exception {  
	   
	  List<Menu> menuUserElementsList = menuService.getAllMenuUserElements();
	  
	    
		 	  
	 // modelMenuElementsList = model;
	  
	  model.addObject("menuUserElementsList", menuUserElementsList);
	   
	  
	  User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
			 model.addObject("loggedUserTel", tel);
			 model.addObject("loggedUserEmail", email);
	  
	  model.setViewName("listMenuUserElements");
	  
	 // ModelAndView modelRealMenu = new ModelAndView("menuDinamico");
	  //modelRealMenu.addObject("menuElementsList", menuElementsList);
	 // modelRealMenu.setViewName("menuDinamico");
	  
	  
	  return model;
	  	  
	 }
	
	
	
	public ModelAndView modelRealMenuUser(ModelAndView model,Principal principal) throws Exception {  
		   
		  List<Menu> menuUserElementsList = menuService.getAllMenuUserElements();
		  
		//  List<Menu> subMenuElementsList = menuService.getAllMenuElements();
		  
		 
		    
			 	  
		//  modelMenuElementsList = model;
		  
		  model.addObject("menuUserElementsList", menuUserElementsList);
		//  model.addObject("subMenuElementsList", subMenuElementsList);
		  
		  User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);
		  
		  model.setViewName("menuDinamico");
		  
		 // ModelAndView modelRealMenu = new ModelAndView("menuDinamico");
		 // modelRealMenu.addObject("menuElementsList", menuElementsList);
		 // modelRealMenu.setViewName("menuDinamico");
		  
		  
		  return model;
		  	  
		 }
	
	
	 @RequestMapping(value = "/insertNewMenuUserElement", method = RequestMethod.GET)
		public ModelAndView newMenuUserElement(ModelAndView model, Principal principal) throws Exception {
		 
		 //String username = principal.getName(); 
		 
		 
		 // NON SERVE PERCHE' HO CARICATO LA LISTA NELPROJECT INIZIALE CHE MANDA AD "HOME"
		// List<Menu> menuElementsList = menuService.getAllMenuElements();
		// model.addObject("menuElementsList", menuElementsList);
		 
		 
			Menu newMenuUserElement = new Menu();
			model.addObject("newMenuUserElement", newMenuUserElement);
			
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);
				 
				 List<Menu> menuUserElementsList = menuService.getAllMenuUserElements(); 
			 	 
				 	model.addObject("menuUserElementsList", menuUserElementsList);
			
			model.setViewName("insertNewMenuUserElement");
			return model;
		}
	 
	 @RequestMapping(value = "/saveNewMenuUserElement", method = RequestMethod.POST)
		public ModelAndView saveNewMenuUserElement(HttpServletRequest request) throws Exception {
		 
		 
		 
		 String linkName = request.getParameter("link_name");
		 String link = request.getParameter("link");
		 String imgLink = request.getParameter("img_link");
		 String target = request.getParameter("target");
		 int parentId = Integer.parseInt(request.getParameter("parent_id")); 
		 int subParent_id = Integer.parseInt(request.getParameter("subParent_id"));
	          
	          String message = "";
		 
		 Menu menuUserElement = new Menu(linkName, link, imgLink, target, parentId, subParent_id);
		 
		 	int occorrenze = menuService.contaOccorrenzeNomeLinkUserMenu(linkName);
		 
		 if(occorrenze > 0)
		 {
			 message = "Nel Database è già presente un link chiamato " + linkName + " ! Scegliere un altro nome o modificare quello già esistente!";
			 
			 ModelAndView model = new ModelAndView("insertNewMenuUserElement");
			 model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
			// model.addObject("loggedUser", username);
			 return model;
		 }
		 else{
		 	menuService.insertUserMenu(menuUserElement);
		 	}
			return new ModelAndView("redirect:/listMenuUserElements");
		}
	
	
	 
	 
	 
	 @RequestMapping(value = "/deleteMenuUserElement", method = RequestMethod.GET)
	 public ModelAndView deleteMenuUserElement(HttpServletRequest request) throws Exception {
	 	
		 String link_id = request.getParameter("link_id");
		 int parent_id = Integer.parseInt(request.getParameter("parent_id"));
		 int subParent_id = Integer.parseInt(request.getParameter("subParent_id"));
	 	
	 	ModelAndView model = new ModelAndView("listMenuUserElements");
	 	
	 	//int occorrenzeChild = menuService.contaOccorrenzeChild(parent_id);
	 	
	 		String message = "";  
	 	if (parent_id == 1 || subParent_id == 1){
	 		message = "Il link che si vuole eliminare ha dei figli. Eliminandolo si creerebbero degli errori. In caso si volesse eleminarlo procedere prima all'eliminazione dei figli e settare l'elemento come vuoto (0).";
	 		model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
	 		model.addObject("menuElement.parent_id", 1);
	 		
	 		
	 		List<Menu> menuUserElementsList = menuService.getAllMenuUserElements(); 
		 	 
		 	model.addObject("menuUserElementsList", menuUserElementsList);
	 		
	 		return model;
	 		
	 	}
	 	else
	 	{
	 		menuService.deleteUserMenu(link_id);
	 		
	 	}
	 	
	 	// new ModelAndView("redirect:/listMenuElements");
	 	
	 	List<Menu> menuUserElementsList = menuService.getAllMenuUserElements(); 
	 	 
	 	model.addObject("menuUserElementsList", menuUserElementsList);

	 	return model;
	 	
	 }
	 
	 @RequestMapping(value = "/updateLinkUserInfo", method = RequestMethod.GET)
		public ModelAndView updateLinkUserInfo (@RequestParam Map<String,String> maps, Principal principal) throws Exception {
		 
			//String loggedUsername = principal.getName();	

		    int link_id = Integer.parseInt(maps.get("link_id"));
		 	String link_name = maps.get("link_name");
		 	String link = maps.get("link");
		 	String img_link = maps.get("img_link");
		 	String target = maps.get("target");
		 	int parent_id = Integer.parseInt(maps.get("parent_id"));
		 	int subParent_id = Integer.parseInt(maps.get("subParent_id"));
		 			
			ModelAndView model = new ModelAndView();
			model.addObject("link_id", link_id);
			model.addObject("link_name", link_name);
			model.addObject("link", link);
			model.addObject("img_link", img_link);
			model.addObject("target", target);
			model.addObject("parent_id", parent_id);
			model.addObject("subParent_id", subParent_id);
			
			//model.addObject("loggedUser", loggedUsername);
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);
			
			model.setViewName("modifyLinkUserInfo");
			return model;
		}
	 
	 
	 @RequestMapping(value="/updateLinkUserMenu")  
	 public ModelAndView linkUserEdit(HttpServletRequest request) throws Exception {  
	 	 
	 	int link_id = Integer.parseInt(request.getParameter("link_id"));
	 	String link_name = request.getParameter("link_name");
	 	String link = request.getParameter("link");
	 	String img_link = request.getParameter("img_link");
	 	String target = request.getParameter("target");
	 	int parent_id = Integer.parseInt(request.getParameter("parent_id"));
	 	int subParent_id = Integer.parseInt(request.getParameter("subParent_id"));
	 	 
	 	 
	 	 Menu menu = new Menu(link_id, link_name, link, img_link, target, parent_id, subParent_id);
	
	 	 		 
	 	 menuService.updateLinkUserMenu(menu);
	 	 
	 return new ModelAndView("redirect:/listMenuUserElements");

	 }

}
