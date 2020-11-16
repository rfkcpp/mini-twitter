import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;




public class AdminControlPanel extends JFrame implements ActionListener{
	
	//defining a private pointer to make the class follow singleton pattern
    private static AdminControlPanel pointer;
    
    //defining the getInstance method for purpose of singleton pattern
    public static AdminControlPanel getInstance(Twitter my_twitter) {
    	
    	if (pointer == null) {
    		pointer = new AdminControlPanel(my_twitter);
    	}
    	
    	return pointer;
    }
    
    //the twitter object for this panel
    private Twitter myTwitter;
	
	//Defining Buttons 
	private JButton add_user;
	private JButton add_group;
	private JButton open_user_view;
	private JButton show_user_total;
	private JButton show_group_total;
	private JButton show_message_total;
	private JButton show_positive_percentage;
	
	
	
	//Defining text boxes
	JTextField user_id_text_field;
	JTextField group_id_text_field;
	//Defining the tree 
	JTree panel_tree;
	
	
	
	//Constructor , gets a tree object for the system's user and group data
	private AdminControlPanel(Twitter my_twitter) {
		
		//setting the layout of the frame components
		setLayout(new FlowLayout());
		
		//assigning objects to components
		add_user = new JButton("ADD USER");
		add_group = new JButton("ADD GROUP");
		open_user_view = new JButton("OPEN USER VIEW");
		show_user_total = new JButton("SHOW USER TOTAL");
		show_group_total = new JButton("SHOW GROUP TOTAL");
		show_message_total = new JButton("SHOW MESSAGE TOTAL");
		show_positive_percentage = new JButton("SHOW POSITIVE PERCENTAGE");
		user_id_text_field = new JTextField("Enter User Name");
		group_id_text_field = new JTextField("Enter Group Name");
		panel_tree = my_twitter.getSystemTree();
		myTwitter=my_twitter;
		
		
		//adding the components to the control panel
		add(add_user);
		add(add_group);
		add(open_user_view);
		add(show_user_total);
		add(show_group_total);
		add(show_message_total);
		add(show_positive_percentage);
		add(user_id_text_field);
		add(group_id_text_field);
		add(panel_tree);
		
		
		//adding action listeners to the components
		add_user.addActionListener(this);
		add_group.addActionListener(this);
		open_user_view.addActionListener(this);
		show_user_total.addActionListener(this);
		show_group_total.addActionListener(this);
		show_message_total.addActionListener(this);
		show_positive_percentage.addActionListener(this);
		
		
		//setting the size and the title of the frame
		this.setSize(500,500);
		this.setTitle("Admin Control Pannel");
		
		//the Administrator control panel should always be visible
		this.setVisible(true);
		
		//the program closes when the administrator control panel is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//adjusting layout of components
		
		
	}
	
	
	
	//event listeners for components
	public void actionPerformed(ActionEvent event) {
		
		//code for adding a user
		if (event.getSource()==add_user) {
			
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)( panel_tree.getLastSelectedPathComponent());
			
			
			String group_name=parent.getUserObject().toString();
			String user_name = user_id_text_field.getText();
			User new_user=new User(user_name,group_name,new UserInterfacePanel(myTwitter));
			
			myTwitter.addEntry(new_user);
			
		    //updating panel_tree
			
			parent.add(new DefaultMutableTreeNode(new_user));
			DefaultTreeModel model = (DefaultTreeModel) panel_tree.getModel();
			model.reload();
			
			
			//updating panel tree
			
			myTwitter.setSystemTree(panel_tree);
			
			
			
		}
		
		//code for adding a group
		if (event.getSource() == add_group) {
			
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)( panel_tree.getLastSelectedPathComponent());
			
			

			String new_group_name = group_id_text_field.getText();
			Group new_group = new Group(new_group_name);
			myTwitter.addEntry(new_group);
			
		    //updating panel_tree
			
			parent.add(new DefaultMutableTreeNode(new_group));
			
			DefaultTreeModel model = (DefaultTreeModel) panel_tree.getModel();
			model.reload();
			
			//updating panel tree
			
			myTwitter.setSystemTree(panel_tree);
		}
		
		//code for open user view
		if (event.getSource() == open_user_view) {
			
			DefaultMutableTreeNode selected_node = (DefaultMutableTreeNode)( panel_tree.getLastSelectedPathComponent());
			
			//getting the selected user object from the tree
			String user_name= selected_node.getUserObject().toString();
			int index=-1;
			
			for(int i=0 ; i<myTwitter.getEntries().size(); i++) {
				if ( myTwitter.getEntries().get(i) instanceof User && ((User)(myTwitter.getEntries().get(i))).getUser_name().equals(user_name) ) {
					index=i;
				}
			}
			
			((User)(myTwitter.getEntries().get(index))).show_my_interface();
			
		}
		
		//code for show user total
		if (event.getSource() == show_user_total) {
			SystemVisitor user_counter = new UserCounter();
			myTwitter.accept(user_counter);
			infoBox((String.valueOf(myTwitter.getTotal_users())),"Total Users");
			
		}
		
		//code for show group total
		if (event.getSource() == show_group_total) {
			
			SystemVisitor group_counter = new TotalGroupCounter();
			myTwitter.accept(group_counter);
			infoBox((String.valueOf(myTwitter.getTotal_groups())),"Total Groups");
		}
		
		//code for show message total
		if (event.getSource() == show_message_total) {
			
			SystemVisitor message_counter = new totalMessageCounter();
			myTwitter.accept(message_counter);
			infoBox((String.valueOf(myTwitter.getTotal_messages())),"Total Messages");
		}
		
		//code for show positive percentage
		if (event.getSource()==show_positive_percentage) {
			SystemVisitor positive_counter = new TotalPositiveCounter();
			myTwitter.accept(positive_counter);
			infoBox((String.valueOf(myTwitter.getTotal_positive_posts()))+"%","Total Positive Posts");
			
		}
		
		
		
	}//end of event listeners
	
	
	//a method for displaying message boxes
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	


}//end of AdminControlPanel class
