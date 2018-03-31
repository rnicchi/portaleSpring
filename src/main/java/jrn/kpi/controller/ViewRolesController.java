package jrn.kpi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;
import jrn.service.MenuService;
import jrn.service.PermissionsService;
import jrn.service.RolesService;
import jrn.service.UserService;
import jrn.kpi.controller.ViewUtentiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ViewRolesController {
	
	@Autowired
	RolesService rolesService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PermissionsService permissionsService;
	
	//@Autowired
	//MenuService menuService;
	
	//	FiltersService filtersService;
	
	ModelAndView modelPermissionsRole;
	
	
	@RequestMapping(value="/listRoles")
	public ModelAndView rolesList(ModelAndView model, Principal principal) throws Exception {  
		 
		 List<Roles> rolesList = rolesService.getAllRoles();  
		  
		  List<Permissions> permissionsList = permissionsService.getAllPermissions();
		  
		  //String loggedUsername = principal.getName();	
		  
		 // model.addObject("loggedUser", loggedUsername);
		  User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);

		 
		  model.addObject("rolesList", rolesList);
		  model.addObject("permissionsList", permissionsList);
		  
		  model.setViewName("listRoles");
		  
		  
		  return model;
		  
		 }
	
	@RequestMapping(value="/filterPermission")
	public ModelAndView filterRolesList(HttpServletRequest request, Principal principal) throws Exception {  
		 
		String permName = request.getParameter("nomePermesso");
		String permId = request.getParameter("idPermesso");
		
		 // String loggedUsername = principal.getName();	

		
		
		ModelAndView model = new ModelAndView("listRoles");
		
		  List<Roles> filterRolesList = rolesService.getAllFilterRoles(permName, permId); 
		
		// List<Roles> filterRolesList = filtersService.getAllFilterRoles(permName, permId); 
		  
		  List<Permissions> permissionsList = permissionsService.getAllPermissions();
		 
		  model.addObject("rolesList", filterRolesList);
		  model.addObject("permissionsList", permissionsList);
		 // model.addObject("loggedUser", loggedUsername);
		  User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);

		 		  
		  return model;
		  
		 }
	
	
	@RequestMapping(value = "/insertNewRole", method = RequestMethod.GET)
	public ModelAndView newRole(ModelAndView model, Principal principal) throws Exception {
		
		//String loggedUsername = principal.getName();	

		Roles newRole = new Roles();
		
		//model.addObject("loggedUser", loggedUsername);
		User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
			 model.addObject("loggedUserTel", tel);
			 model.addObject("loggedUserEmail", email);

		model.addObject("role", newRole);
		model.setViewName("insertNewRole");
		return model;
	}
 
 @RequestMapping(value = "/saveNewRole", method = RequestMethod.POST)
	public ModelAndView saveRole(HttpServletRequest request) throws Exception {
	 
	 
	 String rolename = request.getParameter("role_name");
	 String roleDesc = request.getParameter("role_desc");
	    	   
	 Roles role = new Roles(rolename, roleDesc);	 
	    
	 	rolesService.insert(role);
	 	
		return new ModelAndView("redirect:/listRoles");
	}
	
	
 @RequestMapping(value = "/updateRoleInfo", method = RequestMethod.GET)
	public ModelAndView updateRoleInfo (@RequestParam Map<String,String> maps, Principal principal) throws Exception {
	 
		//String loggedUsername = principal.getName();	

	    int roleId = Integer.parseInt(maps.get("id"));
	 	String roleName = maps.get("rolename");
	 	String roleDesc = maps.get("roledesc");
	 			
		ModelAndView model = new ModelAndView();
		model.addObject("roleId", roleId);
		model.addObject("roleName", roleName);
		model.addObject("roleDesc", roleDesc);
		
		//model.addObject("loggedUser", loggedUsername);
		User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
			 model.addObject("loggedUserTel", tel);
			 model.addObject("loggedUserEmail", email);
		
		model.setViewName("modifyRoleInfo");
		return model;
	}

@RequestMapping(value="/updateRole")  
public ModelAndView roleEdit(HttpServletRequest request) throws Exception {  

	 int roleId = Integer.parseInt(request.getParameter("role_id"));
	 String roleName = request.getParameter("role_name");
	 String roleDesc = request.getParameter("role_desc");
	 
	 
	 Roles role = new Roles(roleId, roleName, roleDesc);
	 
	 //User user = new User(userId, username, password, email, tel, enabled);
	 		 
	 rolesService.updateRole(role);
	 
return new ModelAndView("redirect:/listRoles");

}

@RequestMapping(value = "/deleteRole", method = RequestMethod.GET)
public ModelAndView deleteRole(HttpServletRequest request) throws Exception {
	int roleId = Integer.parseInt(request.getParameter("id"));
	
	ModelAndView model = new ModelAndView("listRoles");
	
		String message = rolesService.delete(roleId);
	if (!message.equals("")){
		model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
		
	}
	
	 List<Roles> rolesList = rolesService.getAllRoles();  
	 
	  model.addObject("rolesList", rolesList);

	return model;
}
	
	
	
	@RequestMapping(value = "/viewPermissions", method = RequestMethod.GET)
	public ModelAndView viewRolePermissionsList(@RequestParam Map<String,String> maps, Principal principal) throws Exception {
	 
		//String loggedUsername = principal.getName();	
		
		int roleId = Integer.parseInt(maps.get("id"));
		String rolename = maps.get("rolename");
		List<Permissions> rolePermissionsList = rolesService.getRolePermissionsById(roleId);
		//ModelAndView model = new ModelAndView("viewUserRoles");
		modelPermissionsRole = new ModelAndView("viewRolePermissions");
		modelPermissionsRole.addObject("rolename", rolename);
		modelPermissionsRole.addObject("roleId", roleId);
		modelPermissionsRole.addObject("rolePermissionsList", rolePermissionsList);
		
		//modelPermissionsRole.addObject("loggedUser", loggedUsername);
		User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
		modelPermissionsRole.addObject("loggedUserTel", tel);
		modelPermissionsRole.addObject("loggedUserEmail", email);

		
		return modelPermissionsRole;
	}
	
	@RequestMapping(value="/modifyUserRoles")
	public ModelAndView userRolesList(@RequestParam Map<String,String> maps,Principal principal) throws Exception {  
				
		//String loggedUsername = principal.getName();	
		
		//UserService userService = new UserService();
		//RolesService rolesService = new RolesService();
		String userName = maps.get("username");
		
		int userId = Integer.parseInt(maps.get("id"));
		
		//User utente = userService.getUserById(userId);
		
		List<Roles> listaRuoli = rolesService.getAllRoles();
		
		//ArrayList<Integer> listaIdRuoli = rolesService.listaIdRuoliByUserId(utente.getLogin_id());
		ArrayList<Integer> listaIdRuoli = rolesService.listaIdRuoliByUserId(userId);
		
		int i = 0; 
		
		ModelAndView modelModificaRuoliUser = new ModelAndView("modifyUserRoles");
		
		//modelModificaRuoliUser.addObject("utente",utente);
		modelModificaRuoliUser.addObject("username", userName);
		modelModificaRuoliUser.addObject("userId", userId);
		modelModificaRuoliUser.addObject("listaRuoli",listaRuoli);
		modelModificaRuoliUser.addObject("listaIdRuoli",listaIdRuoli);
		modelModificaRuoliUser.addObject("i", i);
		//modelModificaRuoliUser.setViewName("modifyUserRoles");
		
		//modelModificaRuoliUser.addObject("loggedUser", loggedUsername);
		
User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
			 modelModificaRuoliUser.addObject("loggedUserTel", tel);
			 modelModificaRuoliUser.addObject("loggedUserEmail", email);

		
		return modelModificaRuoliUser;	  
		  
		  
	}
	
	
	 @RequestMapping(value = "/saveAssUserRoles", method = RequestMethod.POST)
		public ModelAndView saveAssUserRoles(HttpServletRequest request, Principal principal) throws Exception {
		    		 	
		 	String[] idsel = request.getParameterValues("elementoSelezionato");
			String idUtente = request.getParameter("id");
			
			//String loggedUsername = principal.getName();	
			
			rolesService.cancellaRelazioneUserRuoloByUserId(idUtente);
			
			if (idsel != null) {
				
				rolesService.associazioneUserRuolo(idsel, idUtente);
				
			}
				
			int userId = Integer.parseInt(idUtente);
			
			List<Roles> userRolesList = userService.getUserRolesById(userId);
			
			String userName = request.getParameter("username");
			
			ModelAndView modelRolesPermissions2 = new ModelAndView("viewUserRoles");
			
			
			modelRolesPermissions2.addObject("userId", userId);
			modelRolesPermissions2.addObject("username", userName);
			modelRolesPermissions2.addObject("userRolesList", userRolesList);
			//modelRolesPermissions2.addObject("loggedUser", loggedUsername);
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 modelRolesPermissions2.addObject("loggedUserTel", tel);
				 modelRolesPermissions2.addObject("loggedUserEmail", email);

			
		return modelRolesPermissions2;
				
		}
	 
	 
	 
	 
	/* @RequestMapping(value="/modifyRolePermissions")
		public ModelAndView rolePermissionsList(@RequestParam Map<String,String> maps) throws Exception {  
			
			
			String roleName = maps.get("rolename");
			
			int roleId = Integer.parseInt(maps.get("id"));
			
			
			
			List<Permissions> listaPermessi = permissionsService.getAllPermissions();
			
			
			ArrayList<Integer> listaIdPermessi = permissionsService.listaIdPermessiByRoleId(roleId);
			
			
			ModelAndView modelModificaPermessiRuoli = new ModelAndView("modifyRolePermissions");
			
			
			modelModificaPermessiRuoli.addObject("rolename", roleName);
			modelModificaPermessiRuoli.addObject("roleId", roleId);
			modelModificaPermessiRuoli.addObject("listaPermessi", listaPermessi);
			modelModificaPermessiRuoli.addObject("listaIdPermessi",listaIdPermessi);
			
			
			return modelModificaPermessiRuoli;	  
			  
		}
		*/
	 
	 @RequestMapping(value="/modifyRolePermissions")
		public ModelAndView rolePermissionsList(@RequestParam Map<String,String> maps, Principal principal) throws Exception {  
			
			//String loggedUsername = principal.getName();	
			
			String roleName = maps.get("rolename");
			
			int roleId = Integer.parseInt(maps.get("id"));
			
			
			
			List<Permissions> listaPermessi = permissionsService.getAllPermissions();	
			ArrayList<Integer> listaIdPermessi = permissionsService.listaIdPermessiByRoleId(roleId);
			
			
			int i = 0; 
			
			
			ModelAndView modelModificaPermessiRuoli = new ModelAndView("modifyRolePermissions");
			
			
			modelModificaPermessiRuoli.addObject("rolename", roleName);
			modelModificaPermessiRuoli.addObject("roleId", roleId);
			modelModificaPermessiRuoli.addObject("listaPermessi", listaPermessi);
			modelModificaPermessiRuoli.addObject("listaIdPermessi",listaIdPermessi);
			modelModificaPermessiRuoli.addObject("i", i);
			//modelModificaPermessiRuoli.addObject("loggedUser", loggedUsername);
			
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 modelModificaPermessiRuoli.addObject("loggedUserTel", tel);
				 modelModificaPermessiRuoli.addObject("loggedUserEmail", email);

			
			return modelModificaPermessiRuoli;	  
			  
		}
	 
	 
	 @RequestMapping(value = "/saveAssRolePermissions", method = RequestMethod.POST)
		public ModelAndView saveAssRolePermissions(HttpServletRequest request, Principal principal) throws Exception {
		    		 	
		 	String[] idsel = request.getParameterValues("elementoSelezionato");
			String idRuolo = request.getParameter("id");
			
			//String loggedUsername = principal.getName();	
			
			permissionsService.cancellaRelazioneRuoloPermessiByRoleId(idRuolo);
			
			if (idsel != null) {
				
				permissionsService.associazioneRuoloPermessi(idsel, idRuolo);
				
			}
						
			int roleId = Integer.parseInt(idRuolo);
			
			List<Permissions> rolePermissionsList = rolesService.getRolePermissionsById(roleId);
			
			
			String roleName = request.getParameter("rolename");
			
			ModelAndView modelRolesPermissions2 = new ModelAndView("viewRolePermissions");
			//modelRolesPermissions = new ModelAndView("viewUserRoles");
			
			modelRolesPermissions2.addObject("roleId", roleId);
			modelRolesPermissions2.addObject("roleName", roleName);
			modelRolesPermissions2.addObject("rolePermissionsList", rolePermissionsList); 
			//modelRolesPermissions2.addObject("loggedUser", loggedUsername);
			
			User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 modelRolesPermissions2.addObject("loggedUserTel", tel);
				 modelRolesPermissions2.addObject("loggedUserEmail", email);

			
		return modelRolesPermissions2;
				 //	return new ModelAndView("redirect:/viewRoles");
			//return new ModelAndView("redirect:/listUsers");
				 //return new ModelAndView("redirect:/viewRoles?id=userId&username=userName");
		}
		

}
