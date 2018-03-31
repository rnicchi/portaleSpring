package jrn.dao.setters;

import java.sql.ResultSet;
import java.sql.SQLException;

import jrn.dao.entities.Roles;
import jrn.dao.entities.User;

import org.springframework.jdbc.core.RowMapper;

public class RolesRowMapper implements RowMapper<Roles>{

	
	public Roles mapRow(ResultSet rs, int rowCount) throws SQLException {
		
		Roles role = new Roles();
			role.setRole_id(rs.getInt("role_id"));
			role.setRole_name(rs.getString("role_name"));
			role.setRole_desc(rs.getString("role_desc"));
	            
	       return role;
		
	}

}
