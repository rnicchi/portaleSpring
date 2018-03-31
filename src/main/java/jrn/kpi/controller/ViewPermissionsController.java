package jrn.kpi.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;
import jrn.dao.entities.User;
import jrn.service.PermissionsService;
import jrn.service.RolesService;
import jrn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewPermissionsController {
	
	@Autowired
	PermissionsService permissionsService;
	
	@Autowired
	UserService userService;
	
	
	@RequestMapping(value="/listPermissions")
	public ModelAndView rolesList(ModelAndView model, Principal principal) throws Exception {  
		 
		  List<Permissions> permissionsList = permissionsService.getAllPermissions();
		  
		 // String loggedUsername = principal.getName();
		 
		 // model.addObject("loggedUser", loggedUsername);
		  User fullLoggedUser = userService.getLoggedUser(principal.getName());
			
			String tel = fullLoggedUser.getTel();
			String email = fullLoggedUser.getEmail();
			
				 model.addObject("loggedUserTel", tel);
				 model.addObject("loggedUserEmail", email);

		  
		  model.addObject("permissionsList", permissionsList);
		  model.setViewName("listPermissions");
		  
		  return model;
		 
		 } 
	
	@RequestMapping(value = "/insertNewPermission", method = RequestMethod.GET)
	public ModelAndView newPermission(ModelAndView model, Principal principal) throws Exception {
		Permissions newPermission = new Permissions();
		
		//String loggedUsername = principal.getName();
		 
		//model.addObject("loggedUser", loggedUsername);
User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
			 model.addObject("loggedUserTel", tel);
			 model.addObject("loggedUserEmail", email);
		
		model.addObject("permission", newPermission);
		model.setViewName("insertNewPermission");
		return model;
	}
 
 @RequestMapping(value = "/saveNewPermission", method = RequestMethod.POST)
	public ModelAndView savePermission(HttpServletRequest request) throws Exception {
	 
	 
	 String permissionname = request.getParameter("permission_name");
	 String permissionDesc = request.getParameter("permission_desc");
	    	   
	 Permissions permission = new Permissions(permissionname, permissionDesc);	 
	    
	 permissionsService.insert(permission);
	 	
		return new ModelAndView("redirect:/listPermissions");
	}
	
	
 @RequestMapping(value = "/updatePermissionInfo", method = RequestMethod.GET)
	public ModelAndView updatePermissionInfo (@RequestParam Map<String,String> maps, Principal principal) throws Exception {

	    int permissionId = Integer.parseInt(maps.get("id"));
	 	String permissionName = maps.get("permissionname");
	 	String permissionDesc = maps.get("permissiondesc");
	 	
	 	//String loggedUsername = principal.getName();
	 			
		ModelAndView model = new ModelAndView();
		model.addObject("permissionId", permissionId);
		model.addObject("permissionName", permissionName);
		model.addObject("permissionDesc", permissionDesc);
		
		//model.addObject("loggedUser", loggedUsername);
		
User fullLoggedUser = userService.getLoggedUser(principal.getName());
		
		String tel = fullLoggedUser.getTel();
		String email = fullLoggedUser.getEmail();
		
			 model.addObject("loggedUserTel", tel);
			 model.addObject("loggedUserEmail", email);
		
		model.setViewName("modifyPermissionInfo");
		return model;
	}

@RequestMapping(value="/updatePermission")  
public ModelAndView permissionEdit(HttpServletRequest request) throws Exception {  

	 int permissionId = Integer.parseInt(request.getParameter("permission_id"));
	 String permissionName = request.getParameter("permission_name");
	 String permissionDesc = request.getParameter("permission_desc");
	 
	 
	 Permissions permission = new Permissions(permissionId, permissionName, permissionDesc);
	 
	 //User user = new User(userId, username, password, email, tel, enabled);
	 		 
	 permissionsService.updatePermission(permission);
	 
return new ModelAndView("redirect:/listPermissions");

}

/*@RequestMapping(value = "/deletePermission", method = RequestMethod.GET)
public ModelAndView deletePermission(HttpServletRequest request) throws Exception {
	int permissionId = Integer.parseInt(request.getParameter("id"));
	permissionsService.delete(permissionId);
	return new ModelAndView("redirect:/listPermissions");
}*/

@RequestMapping(value = "/deletePermission", method = RequestMethod.GET)
public ModelAndView deletePermission(HttpServletRequest request) throws Exception {
	int permissionId = Integer.parseInt(request.getParameter("id"));
	
	ModelAndView model = new ModelAndView("listPermissions");
	
		String message = permissionsService.delete(permissionId);
	if (!message.equals("")){
		model.addObject("messageScript", "<script>alert(\""+ message  + "\");</script>");
		
	}
	
	List<Permissions> permissionsList = permissionsService.getAllPermissions();  
	 
	model.addObject("permissionsList", permissionsList);

	return model;
	
}
	

}
