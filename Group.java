import java.util.ArrayList;


public class Group implements SystemEntry {
	
	private String group_name;
	
	//this is where we use the composite pattern.
	private ArrayList<SystemEntry> members;
	
	
    Group parent;
	
	
	
	//constructor
	public Group (String name) {
		
		group_name=  name;
		
		parent = null;
	}
	
	
	
	public void setParent(Group my_parent) {
		parent = my_parent;
	}
	
	public String toString() {
		return group_name;
	}
	
	public void addMember(User user) {
		members.add(user);
	}

}
