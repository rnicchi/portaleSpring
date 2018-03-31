package jrn.dao.entities;

public class Permissions {
	
	private int permission_id;
	private String permission_name;
	private String permission_desc;
	
	public Permissions(){}
	
	public Permissions(String permission_name, String permission_desc) {
		super();
		this.permission_name = permission_name;
		this.permission_desc = permission_desc;
	}
	
	public Permissions(int permission_id, String permission_name,
			String permission_desc) {
		super();
		this.permission_id = permission_id;
		this.permission_name = permission_name;
		this.permission_desc = permission_desc;
	}

	public int getPermission_id() {
		return permission_id;
	}
	public void setPermission_id(int permission_id) {
		this.permission_id = permission_id;
	}
	public String getPermission_name() {
		return permission_name;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}
	public String getPermission_desc() {
		return permission_desc;
	}
	public void setPermission_desc(String permission_desc) {
		this.permission_desc = permission_desc;
	}
	
	

}
