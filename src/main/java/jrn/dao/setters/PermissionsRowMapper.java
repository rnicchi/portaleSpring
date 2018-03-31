package jrn.dao.setters;

import java.sql.ResultSet;
import java.sql.SQLException;

import jrn.dao.entities.Permissions;
import jrn.dao.entities.Roles;

import org.springframework.jdbc.core.RowMapper;

public class PermissionsRowMapper implements RowMapper<Permissions> {

	
	public Permissions mapRow(ResultSet rs, int rowCount) throws SQLException {

		Permissions permission = new Permissions();
		permission.setPermission_id(rs.getInt("permission_id"));
		permission.setPermission_name(rs.getString("permission_name"));
		permission.setPermission_desc(rs.getString("permission_desc"));
            
       return permission;
		
	}

}
