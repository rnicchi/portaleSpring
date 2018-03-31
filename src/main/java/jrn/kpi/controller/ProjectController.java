package jrn.kpi.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jrn.dao.entities.Menu;
import jrn.dao.entities.User;
import jrn.service.MenuService;
import jrn.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("menuElementsList")
public class ProjectController {
	
	@Autowired
	UserService userService;

	@Autowired
	MenuService menuService;
	
	
@RequestMapping(value = "/index", method = RequestMethod.GET)
public String printMessage(ModelMap modelMap,Principal principal, ModelAndView model, HttpServletRequest request) throws Exception {
	
String username = principal.getName(); 
modelMap.addAttribute("loggedUser", username);
//model.addAttribute("prova", authorities);

User fullLoggedUser = userService.getLoggedUser(principal.getName());

String tel = fullLoggedUser.getTel();
String email = fullLoggedUser.getEmail();


modelMap.addAttribute("loggedUserTel", tel);
modelMap.addAttribute("loggedUserEmail", email);


List<Menu> menuElementsList = menuService.getAllMenuElements();
modelMap.addAttribute("menuElementsList", menuElementsList);


List<Menu> menuUserElementsList = menuService.getAllMenuUserElements(); 
modelMap.addAttribute("menuUserElementsList", menuUserElementsList);


return "home";

}

@RequestMapping(value = "/login", method = RequestMethod.GET)
public String login(ModelMap model) {

return "login";

}

@RequestMapping(value = "/failLogin", method = RequestMethod.GET)
public String failedLogin(ModelMap model) {

model.addAttribute("error", "true");
return "login";

}

@RequestMapping(value = "/logout", method = RequestMethod.GET)
public String logoff(ModelMap model) {

return "login";

}


@RequestMapping(value = "/home", method = RequestMethod.GET)
public String viewHome() {
	
    return "home";
}

@RequestMapping(value = "/admin", method = RequestMethod.GET)
public String viewAdmin() {
    return "admin";
}

}