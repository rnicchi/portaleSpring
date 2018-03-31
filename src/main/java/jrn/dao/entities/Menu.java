package jrn.dao.entities;

public class Menu {
	
	private int link_id;
	private String link_name;
	private String link;
	private String img_link;
	private String target;
	private int parent_id;
	private int subParent_id;
	//private Menu subMenu;
	
	public Menu(){}

	public Menu(int link_id, String link_name, String link, String img_link,
			String target, int parent_id, int subParent_id) {
		super();
		this.link_id = link_id;
		this.link_name = link_name;
		this.link = link;
		this.img_link = img_link;
		this.target = target;
		this.parent_id = parent_id;
		this.subParent_id = subParent_id;
	}
	

	public Menu(String link_name, String link, String img_link, String target,
			int parent_id, int subParent_id) {
		super();
		this.link_name = link_name;
		this.link = link;
		this.img_link = img_link;
		this.target = target;
		this.parent_id = parent_id;
		this.subParent_id = subParent_id;
	}

	public int getLink_id() {
		return link_id;
	}

	public void setLink_id(int link_id) {
		this.link_id = link_id;
	}

	

	public String getLink_name() {
		return link_name;
	}

	public void setLink_name(String link_name) {
		this.link_name = link_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImg_link() {
		return img_link;
	}

	public void setImg_link(String img_link) {
		this.img_link = img_link;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public int getSubParent_id() {
		return subParent_id;
	}

	public void setSubParent_id(int subParent_id) {
		this.subParent_id = subParent_id;
	}
	
	

}
