import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;


public class Twitter {
	
	

	
	 
	//this tree object is the main tree of the system's groups and users 
	private JTree main_system_tree;
	//Array List that holds all the system entries
	private ArrayList<SystemEntry> all_entries;
	//Admin control panel for this Twitter system
	private AdminControlPanel admin_panel;
	
	//variables to hold total numbers of users,messages,groups and positive posts
	private int total_messages;
	private int total_users;
	private int total_groups;
	private double total_positive_posts;
	
	//getters and setters for the "total" variables above
	
	public int getTotal_messages() {
		return total_messages;
	}
	public void setTotal_messages(int total_messages) {
		this.total_messages = total_messages;
	}
	public int getTotal_users() {
		return total_users;
	}
	public void setTotal_users(int total_users) {
		this.total_users = total_users;
	}
	public int getTotal_groups() {
		return total_groups;
	}
	public void setTotal_groups(int total_groups) {
		this.total_groups = total_groups;
	}
	public double getTotal_positive_posts() {
		return total_positive_posts;
	}
	public void setTotal_positive_posts(double total_positive_posts) {
		this.total_positive_posts = total_positive_posts;
	}
	
	
	//getter method for the system main tree
	public JTree getSystemTree() {
		return main_system_tree;
	}
	
	//setter method for the system tree
	public void setSystemTree(JTree tree) {
		main_system_tree = tree;
	}



    //constructor
	public Twitter() {
		
		//creating the array lists which hold the system entries 
		all_entries = new ArrayList<SystemEntry>();

		
		//creating the JTree and instantiating it with the root group node
		Group Root = new Group("Root");
		DefaultMutableTreeNode root_node= new DefaultMutableTreeNode(Root);
		main_system_tree = new JTree(root_node);
		
		//creating the administrator control panel
		admin_panel=AdminControlPanel.getInstance(this);
		
		//as soon as the twitter instance is made , its administrator control panel becomes visible
		admin_panel.setVisible(true);
		
		}//end of constructor
	
	   
	    
	    //getting the system entries from the system for the visitor classes
	    public ArrayList<SystemEntry> getEntries() {
		return all_entries;
	    }
	    
	    
	    //accept method for the visitor pattern
	    public void accept(SystemVisitor visitor) {
	    	visitor.visitSystem(this);
	    }
	
	    
	  //method for adding systemEntries to the system
	  public void addEntry(SystemEntry entry) {
		  all_entries.add(entry);
	  }


}//end of Twitter class
