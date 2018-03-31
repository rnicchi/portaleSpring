package jrn.kpi.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;





import jrn.dao.entities.Menu;
import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;
import jrn.dao.interfaces.IUserDao;
import jrn.service.MenuService;
import jrn.service.RolesService;
import jrn.service.UserService;
import jrn.kpi.controller.ProjectController;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewUtentiController {

	@Autowired
	UserService userService;
	
	@Autowired
	RolesService rolesService;
	
	
	@Autowired
	MenuService menuService;
	
	
	ModelAndView modelRolesPermissions;
	ModelAndView modelUsersList;
	
	/*
	@Autowired
	private UserService service; 
	
	@RequestMapping("/loginUser")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) throws Exception {
	
		
		User _u =service.getUser("");
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("message", message );
		mv.addObject("name", _u.getName());
		//mv.addObject("name", " TEST ");
		return mv;
	*/
	
	
	//	@Autowired
	//private IUserDao iUserDao;

	@RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
	public ModelAndView oboutUs(ModelAndView model, Principal principal) throws Exception {
	 
	/* String username = principal.getName(); 
		
		 model.addObject("loggedUser", username);*/
		
	User fullLoggedUser = userService.getLoggedUser(principal.getName());
	
	String tel = fullLoggedUser.getTel();
	String email = fullLoggedUser.getEmail();
	
		 model.addObject("loggedUserTel", tel);
		 model.addObject("loggedUserEmail", email);
		 
		 List<Menu> menuUserElementsList = menuService.getAllMenuUserElements(); 
		 model.addObject("menuUserElementsList", menuUserElementsList);
		 
		model.setViewName("aboutUs");
		return model;
	}
	 
	 @RequestMapping(value="/listUsers")  
	 public ModelAndView userList(ModelAndView model,Principal principal) throws Exception {  
	 // List<User> userList = userService.getAllUsers();  
	  List<User> userList = userService.getAllUsers();
	  
	  List<User> userListFilter = userService.getAllUsers(); 
	 // return new ModelAndView("userList", "userList", userList); 
	  
	  List<Roles> rolesList = rolesService.getAllRoles();  
	  
	  //String username = principal.getName(); 
	  
		 	  
	  modelUsersList = model;
	  model.addObject("userList", userList);
	  model.addObject("userListFilter", userListFilter);
	  model.addObject("rolesList", rolesList);
	  
	  User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
			 model.addObject("loggedUserTel", tel);
			 model.addObject("loggedUserEmail", email);
	  
	  model.setViewName("listUsers");
	  
	  return model;
	  	  
	 }
	 
	 
	 @RequestMapping(value="/filterUser")
		public ModelAndView filterRolesList(HttpServletRequest request, Principal principal) throws Exception {  
			 
			String roleName = request.getParameter("roleName");
			String userName = request.getParameter("userName");
			String userAbilitation = request.getParameter("userAbilitation");
			String userEmail = request.getParameter("userEmail");
			String userTel = request.getParameter("userTel");
			
			//String username = principal.getName(); 
			
			
			ModelAndView model = new ModelAndView("listUsers");
			
			  List<User> filterUsersList = userService.getAllFilterUsers(roleName, userName, userAbilitation, userEmail, userTel);  
			  
			  List<User> userListFilter = userService.getAllUsers(); 
			  
			  List<Roles> rolesList = rolesService.getAllRoles();  
			 
			  model.addObject("userList", filterUsersList);
			  model.addObject("userListFilter", userListFilter);
			  model.addObject("rolesList", rolesList);
			//  model.addObject("permissionsList", permissionsList);
			  
			  User fullLoggedUser = userService.getLoggedUser(principal.getName());
				
				String tel = fullLoggedUser.getTel();
				String email = fullLoggedUser.getEmail();
				
					 model.addObject("loggedUserTel", tel);
					 model.addObject("loggedUserEmail", email);
			 		  
			  return model;
			  
			 }
	 
	 
	 
	 @RequestMapping(value = "/insertNewUser", method = RequestMethod.GET)
		public ModelAndView newUser(ModelAndView model, Principal principal) throws Exception {
		 
		 //String username = principal.getName(); 
		 
			User newUser = new User();
			model.addObject("user", newUser);
			
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);
			
			model.setViewName("insertNewUser");
			return model;
		}
	 
	 @RequestMapping(value = "/saveNewUser", method = RequestMethod.POST)
		public ModelAndView saveContact(HttpServletRequest request) throws Exception {
		 
		 
		 
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String email = request.getParameter("email");
		 String tel = request.getParameter("tel");
		 int enabled = 0;
	          try {
	        	  
	        	  if((enabled = Integer.parseInt(request.getParameter("enabled")))==1)  
	        	  {
	        		  enabled = 1;
	        		  
	        	  }
	        	  else
	        	  {
	        		  enabled = 0;
	        	  }
	        	   
	          
	          
	          } catch(Exception Ex) {}
	           
	          
	          String message = "";
		 
		 User user = new User(username, password, email, tel, enabled);
		 
		 	int occorrenze = userService.contaOccorrenzeUsername(username);
		 
		 if(occorrenze > 0)
		 {
			 message = "L'Username " + username + " è già presente nel database! Scegliere un altro Username o modificare quello già esistente!";
			 
			 ModelAndView model = new ModelAndView("insertNewUser");
			 model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
			 model.addObject("loggedUser", username);
			 return model;
		 }
		 else{
		 	userService.insert(user);
		 	}
			return new ModelAndView("redirect:/listUsers");
		}
	 
	
	 /*@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
		public ModelAndView saveContact(@ModelAttribute User user) throws Exception {
		    
		 	userService.insert(user);
		 	
			return new ModelAndView("redirect:/listUsers");
		}*/
	 
	 
	 @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
		public ModelAndView deleteContact(HttpServletRequest request) throws Exception {
			int userId = Integer.parseInt(request.getParameter("id"));
			userService.delete(userId);
			return new ModelAndView("redirect:/listUsers");
		} 
	 
	/* @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
		public ModelAndView deleteContact(HttpServletRequest request, ModelAndView model) throws Exception {
			int userId = Integer.parseInt(request.getParameter("id"));
			
			ModelAndView model = null;
			
			userService.delete(userId);
			
			List<User> userList = userService.getAllUsers(); 
					
			modelUsersList = model;
			  model.addObject("userList", userList);
			  model.setViewName("listUsers");
			

			return model;
		}*/
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET)
		public ModelAndView updateUserInfo (@RequestParam Map<String,String> maps, Principal principal) throws Exception {

		 		 
		    int userId = Integer.parseInt(maps.get("id"));
		 	String username = maps.get("username");
		 	String password = maps.get("password");
		 	String email = maps.get("email");
		 	String tel = maps.get("tel");
		 	int enabled = Integer.parseInt(maps.get("enabled"));
		 	
		 	//String loggedUsername = principal.getName(); 
		 	
			
			ModelAndView model = new ModelAndView();
			model.addObject("userId", userId);
			model.addObject("username", username);
			model.addObject("password", password);
			model.addObject("email", email);
			model.addObject("tel", tel);
			model.addObject("enabled", enabled);
			
			
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel2 = fullLoggedUser.getTel();
			String email2 = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel2);
				 model.addObject("loggedUserEmail", email2);
			
			model.setViewName("modifyUserInfo");
			return model;
		}
	 
	 @RequestMapping(value="/updateUser")  
	 public ModelAndView userEdit(HttpServletRequest request) throws Exception {  

		 int userId = Integer.parseInt(request.getParameter("login_id"));
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String email = request.getParameter("email");
		 String tel = request.getParameter("tel");
		 
		 String userOriginalUsername = request.getParameter("originalUsername");
		 
		 int enabled = 0;
		 try{
			 
			 if((enabled = Integer.parseInt(request.getParameter("enabled")))==1)  
			 {
				 enabled = 1;

			 }
			 else
			 {
				 enabled = 1;
			 }

		 } catch(Exception Ex) {}
		 
		 String message = "";
		 
		 User user = new User(userId, username, password, email, tel, enabled);
		 	
		 int occorrenze = userService.contaOccorrenzeUsername(username);
		 
		if(username.equals(userOriginalUsername))
		{  
					 User user2 = new User(userId, password, email, tel, enabled);
					 userService.updateUserSameUsername(user2);
		}
		else if(occorrenze > 0)
		{
			 message = "Il nuovo Username '" + username + "' che è stato scelto risulta già presente nel database! Scegliere un altro Username o modificare quello già esistente!";
			 
			 ModelAndView model = new ModelAndView("modifyUserInfo");
			 model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
			 model.addObject("loggedUser", username);
			 
			 model.addObject("userId", userId);
				model.addObject("username", userOriginalUsername);
				model.addObject("password", password);
				model.addObject("email", email);
				model.addObject("tel", tel);
				model.addObject("enabled", enabled);
			 return model;
		}
		else
		{
			 
			 userService.updateUser(user);
			 
		}
		 
	  return new ModelAndView("redirect:/listUsers");
		 	  
	 }
	 
	 @RequestMapping(value = "/updateLoggedUserInfo", method = RequestMethod.GET)
		public ModelAndView updateLoggedUserInfo (@RequestParam Map<String,String> maps, Principal principal) throws Exception {

		 
		 User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			 
		 
		    int userId = fullLoggedUser.getLogin_id();
		 	String username = fullLoggedUser.getUsername();
		 	String password = fullLoggedUser.getPassword();
		 	String email = fullLoggedUser.getEmail();
		 	String tel = fullLoggedUser.getTel();
		 	int enabled = fullLoggedUser.getEnabled();
		 	
		 	//String loggedUsername = principal.getName(); 
		 	
			
			ModelAndView model = new ModelAndView();
			model.addObject("userId", userId);
			model.addObject("username", username);
			model.addObject("password", password);
			model.addObject("email", email);
			model.addObject("tel", tel);
			model.addObject("enabled", enabled);
			
			//User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel2 = fullLoggedUser.getTel();
			String email2 = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel2);
				 model.addObject("loggedUserEmail", email2);
			
			model.setViewName("modifyLoggedUserInfo");
			return model;
		}
	 
	 @RequestMapping(value="/updateLoggedUser")  
	 public ModelAndView loggedUserEdit(HttpServletRequest request,ModelMap modelMap,Principal principal) throws Exception {  

		 int userId = Integer.parseInt(request.getParameter("login_id"));
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String email = request.getParameter("email");
		 String tel = request.getParameter("tel");
		 
		 String userOriginalUsername = request.getParameter("originalUsername");
		 
		 int enabled = 0;
		 try{
			 
			 if((enabled = Integer.parseInt(request.getParameter("enabled")))==1)  
			 {
				 enabled = 1;

			 }
			 else
			 {
				 enabled = 1;
			 }

		 } catch(Exception Ex) {}
		 
		 
		 
		// User user = new User(userId, username, password, email, tel, enabled);
		 
		 
		 String message = "";
		 
		 
		 	
		 int occorrenze = userService.contaOccorrenzeUsername(username);
		 
		if(username.equals(userOriginalUsername))
		{  
					 User user2 = new User(userId, password, email, tel, enabled);
					 userService.updateUserSameUsername(user2);
		}
		else if(occorrenze > 0)
		{
			 message = "Il nuovo Username '" + username + "' che è stato scelto risulta già presente nel database! Scegliere un altro Username o modificare quello già esistente!";
			 
			 ModelAndView model = new ModelAndView("modifyLoggedUserInfo");
			 model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
			 model.addObject("loggedUser", username);
			 
			 model.addObject("userId", userId);
				model.addObject("username", userOriginalUsername);
				model.addObject("password", password);
				model.addObject("email", email);
				model.addObject("tel", tel);
				model.addObject("loggedUserEmail", email);
				model.addObject("loggedUserTel", tel);
				model.addObject("enabled", enabled);
			 return model;
		}
		else 
		{
			User user = new User(userId, username, password, email, tel, enabled);
			userService.updateUser(user);
			
			 String message1 = "La modifica dell'username è avvenuta con successo! Per apportare le dovute modifiche verrai scollegato e sarà necessario rieffettuare il login!";			 
			 ModelAndView model = new ModelAndView("login");
			 model.addObject("messageScript", "<script>alert(\""+ message1 + "\");</script>");
			
			 return model;
						 
		}
		 
		 
	  return new ModelAndView("redirect:/index");
		 	  
	 }
	 
	/* @RequestMapping(value="/updateUser")  
	 public ModelAndView userEdit(@RequestParam Map<String,String> maps) throws Exception {  

		 int userId = Integer.parseInt(maps.get("id"));
		 
		 User user1 = userService.updateUser(userService.getUserById(userId));
		 
		// User utenteModificato = userService.updateUser(user1);
		 modelUsersList.addObject("utenteModificato", user1);
	  
	  return new ModelAndView("redirect:/listUsers");
	  
	  
	 }*/
	 
	 	
	 /*DA FARE METODI
	  */
	 @RequestMapping(value = "/viewRoles", method = RequestMethod.GET)
		public ModelAndView viewUserRolesList(@RequestParam Map<String,String> maps, Principal principal) throws Exception {
		 
		 
			int userId = Integer.parseInt(maps.get("id"));
			String username = maps.get("username");
			List<Roles> userRolesList = userService.getUserRolesById(userId);
			
			//String loggedUsername = principal.getName();			
			
			//ModelAndView model = new ModelAndView("viewUserRoles");
			modelRolesPermissions = new ModelAndView("viewUserRoles");
			modelRolesPermissions.addObject("username", username);
			modelRolesPermissions.addObject("userId", userId);
			modelRolesPermissions.addObject("userRolesList", userRolesList);
			
			//modelRolesPermissions.addObject("loggedUser", loggedUsername);
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
			modelRolesPermissions.addObject("loggedUserTel", tel);
			modelRolesPermissions.addObject("loggedUserEmail", email);
			
			return modelRolesPermissions;
		}
	/*  
	 @RequestMapping(value = "/saveAssUserRoles", method = RequestMethod.POST)
		public ModelAndView saveAssUserRoles(HttpServletRequest request) throws Exception {
		    		 	
		 	String[] idsel = request.getParameterValues("elementoSelezionato");
			String idUtente = request.getParameter("id");
			
			rolesService.cancellaRelazioneUserRuoloByUserId(idUtente);
			
			if (idsel != null) {
				
				rolesService.associazioneUserRuolo(idsel, idUtente);
				
			}
			
				 	
			return new ModelAndView("redirect:/listUsers");
			//return new ModelAndView("redirect:/viewRoles");
			//return modelRolesPermissions;
		}
		*/
	 
	 
	 @RequestMapping(value = "/viewRolesPermissions", method = RequestMethod.GET)//"/viewRolePermissions"
		public ModelAndView viewUserRolesPermissionsList(@RequestParam Map<String,String> maps) throws Exception {
		 
		 
		 
		 //ModelAndView modelRolesPermissions2 = new ModelAndView("viewUserRoles");
			//modelRolesPermissions = new ModelAndView("viewUserRoles");
			
		 	int userId = Integer.parseInt(maps.get("id"));
		 	
			String username = maps.get("username");
			List<Roles> userRolesList = userService.getUserRolesById(userId);
		 
			modelRolesPermissions.addObject("userId", userId);
			modelRolesPermissions.addObject("username", username);
			modelRolesPermissions.addObject("userRolesList", userRolesList); 
		 
		 	int roleId = Integer.parseInt(maps.get("role_id"));
			List<Permissions> userRolesPermissionsList = userService.getUserRolesPermissionsByRoleId(roleId);
			//ModelAndView modelRolesPermission = new ModelAndView("viewUserRoles"); //("viewRolesPermissions");
			modelRolesPermissions.addObject("userRolesPermissionsList", userRolesPermissionsList);
			
			return modelRolesPermissions;
		}
	 
	 /*       FUNZIONA CON QUALCHE PROBLEMINO
	  * 
	 @RequestMapping(value = "/viewRolesPermissions", method = RequestMethod.GET)//"/viewRolePermissions"
		public ModelAndView viewUserRolesPermissionsList(@RequestParam Map<String,String> maps) throws Exception {
		 
		 	int roleId = Integer.parseInt(maps.get("role_id"));
			List<Permissions> userRolesPermissionsList = userService.getUserRolesPermissionsByRoleId(roleId);
			//ModelAndView modelRolesPermissions = new ModelAndView("viewUserRoles"); //("viewRolesPermissions");
			modelRolesPermissions.addObject("userRolesPermissionsList", userRolesPermissionsList);
			
			return modelRolesPermissions;
		}
		*/
	 
	 
	 
	 /*
	 @RequestMapping("/viewRoles")  
	 public ModelAndView editUser(@RequestParam int id,  
	   @ModelAttribute User user) {  
	  
	   
		 user = iUserDao.findUser(id);
	
	  return new ModelAndView("viewRoles");  
	  
	 }  
*/

	
/*	
	 @RequestMapping("/insert")  
	 public String inserData(@ModelAttribute User user) {  
	  if (user != null)  
	  // userService.addUser(user);
		  iUserDao.addUser(user);
	  return "redirect:/getList";  
	 }  
	  
	 
	  
	 @RequestMapping("/edit")  
	 public ModelAndView editUser(@RequestParam int id,  
	   @ModelAttribute User user) {  
	  
	 // user = userService.findUser(id);  
		 user = iUserDao.findUser(id);
	*/  
	  /*
	  List<String> genderList = new ArrayList<String>();  
	  genderList.add("male");  
	  genderList.add("female");  
	  
	  List<String> cityList = new ArrayList<String>();  
	  cityList.add("delhi");  
	  cityList.add("gurgaon");  
	  cityList.add("meerut");  
	  cityList.add("noida");  
	  
	  Map<String, Object> map = new HashMap<String, Object>();  
	  map.put("genderList", genderList);  
	  map.put("cityList", cityList);  
	  map.put("user", user);  
	  */
	  
	 /*
	  return new ModelAndView("edit");  
	  
	 }  
	  
	 @RequestMapping("/update")  
	 public String updateUser(@ModelAttribute User user) {  
	  //userService.editUser(user);  
		 iUserDao.editUser(user);
	  return "redirect:/getList";  
	  
	 }  
	  
	 @RequestMapping("/delete")  
	 public String deleteUser(@RequestParam int id) {  
	  System.out.println("id = " + id);  
	  //userService.deleteUser(id);  
	  iUserDao.deleteUser(id);
	  return "redirect:/getList";  
	 }  
	
	*/
	
}
