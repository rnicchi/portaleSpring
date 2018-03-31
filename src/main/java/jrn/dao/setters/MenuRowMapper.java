package jrn.dao.setters;

import java.sql.ResultSet;
import java.sql.SQLException;

import jrn.dao.entities.Menu;
import jrn.dao.entities.User;

import org.springframework.jdbc.core.RowMapper;

public class MenuRowMapper implements RowMapper<Menu> {
	
	
	public Menu mapRow(ResultSet rs, int rowCount) throws SQLException {
        
		Menu menu = new Menu();
		menu.setLink_id(rs.getInt("link_id"));
		menu.setLink_name(rs.getString("link_name"));
		menu.setLink(rs.getString("link"));
	       
		
		menu.setImg_link(rs.getString("img_link"));
		menu.setTarget(rs.getString("target"));
		menu.setParent_id(rs.getInt("parent_id"));
		menu.setSubParent_id(rs.getInt("subParent_id"));
	      
	    return menu;
	       
	    }

}
