import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UserInterfacePanel extends JFrame implements ActionListener{

	
	
	//Defining the components on the UserInterfacepanel
	private JButton follow_user;
	private JButton post_tweet;
	private JTextField user_id_text_field;
	private JTextField message_text_field;
	private JList followed_users;
	private JList news_feed_list;
	
	//variable that hold the user of this panel
	User myUser;
	//the twitter object corresponding to this interface
	Twitter myTwitter;

	//setter for myUser
	public void set_user(User my_user) {
		myUser = my_user;
	}
	//a setter for the news feed
	public void setNewsFeed(ArrayList<Message> message_list) {
		news_feed_list.setListData(message_list.toArray());
	}
	
	
	
	
	//constructor
	public UserInterfacePanel(Twitter my_twitter) {
		
		//assigning the value of myTwitter
		myTwitter=my_twitter;
		
		//setting the layout of the frame components
		//setLayout(new FlowLayout());
		
		
		
		//assigning objects to frame components
		follow_user = new JButton("FOLLOW USER");
		post_tweet = new JButton("POST TWEET");
		user_id_text_field = new JTextField("ENTER USER ID");
		message_text_field = new JTextField("ENTER YOU MESSAGE TO POST");
		followed_users = new JList();
		news_feed_list = new JList();
		
		
		//adjusting the layout
		JPanel p = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		
		//adding the components to panel
		c.gridx=0;
		c.gridy=5;
		p.add(user_id_text_field);
		c.gridx=2;
		c.gridy=5;
		p.add(follow_user);
		c.gridx=0;
		c.gridy=0;
		p.add(new JScrollPane(followed_users));
		p.add(message_text_field);
		p.add(post_tweet);
		p.add(news_feed_list);
		p.add(new JScrollPane(news_feed_list));
		
		//adding the panel to the frame
		add(p,BorderLayout.WEST);
		
		//adding action listeners to the buttons
		follow_user.addActionListener(this);
		post_tweet.addActionListener(this);
		
		
		//setting the size of the J list
		followed_users.setSize(20, 20);
		followed_users.setVisible(true);
		followed_users.setVisibleRowCount(4);
		
		
		
		//setting the size and title of the frame
		this.setSize(300,200);
		
		
	}
	
	

	
	
	//event listeners for components
		public void actionPerformed(ActionEvent event) {
			
			//code for following a user
			if (event.getSource() == follow_user) {
				
				String user_name= user_id_text_field.getText();
				int index=-1;
				
				ArrayList<SystemEntry> all_entries=myTwitter.getEntries();
				
				for(int i=0 ; i<myTwitter.getEntries().size(); i++) {
					
					if (  (all_entries.get(i) instanceof User)    &&   ( ((User)(all_entries.get(i))).getUser_name().equals(user_name) )    ) {
						index=i;
						
					}
				}
				 myUser.addFollowerList((User)all_entries.get(index));
				 ((User)(all_entries.get(index))).attach(myUser);
				 
				 //updating the follower list on the interface
				 followed_users.setListData(myUser.getFollowedUsers().toArray());

			}//end of following users
			
			
			
			//code for posting 
			else if(event.getSource()== post_tweet) {
				
				myUser.setLastSentPost(message_text_field.getText());
				myUser.notifyObserver();
				myUser.addTotalSentPosts(myUser.getLastSentMessage());
				
			}//end of posting 
				
		
		}//end of event listeners
	



}//end of UserInterface class
