package jrn.dao.setters;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

//import jrn.dao.entities.Profile;
import jrn.dao.entities.User;

public class UserRowMapper implements RowMapper<User> {
	
	
	public User mapRow(ResultSet rs, int rowCount) throws SQLException {
        
	       User user = new User();
	       user.setLogin_id(rs.getInt("login_id"));
	       user.setUsername(rs.getString("username"));
	       user.setPassword(rs.getString("password"));
	       
	       user.setEmail(rs.getString("email"));
		 	  user.setTel(rs.getString("tel"));
		 	  user.setEnabled(rs.getInt("enabled"));
	      // user.setName(rs.getString("name"));
	       //user.setSurname(rs.getString("surname"));
	      // Profile _p = new Profile();
	      // _p.setId(rs.getInt("id_profile"));
	      // _p.setName(rs.getString("name_profile"));
	     //  user.setProfile(_p);
	       return user;
	       
	       
	       
	      
	    }

	
	 /*public User mapRow(ResultSet resultSet, int rowCount) throws SQLException {
	        
	       
	       UserExtractor userExtractor = new UserExtractor();  
	       return userExtractor.extractData(resultSet);
	       
	       
	    }*/
}
