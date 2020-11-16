import java.util.ArrayList;


public class User extends Subject implements SystemEntry, Observer   {

	
    //names of the user and its parent group
	private String user_name;
	private String group_name;
	//the interfacePanel of this user
	private UserInterfacePanel myInterface;
	//an Array list which keeps the users which this users follows them
	private ArrayList<User> people_followed = new ArrayList<User>();
	
	
	
	//getter and setter methods 
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGroup_name() {
		return group_name;
	}
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
    public ArrayList<User> getFollowedUsers(){
    	return people_followed;
    }
	
	
	//array list which holds all the sent posts by this user
	private ArrayList<Message> total_posts = new ArrayList<Message>();
	
	//getter method for my_news_feed
    public ArrayList<Message> getTotalSentPosts(){
    	return total_posts;
    }
    
    //method for adding a message to total sent posts
    public void addTotalSentPosts(Message message) {
    	Message post = new Message();
    	post.setSender(message.getSender());
    	post.setText(message.getText());
    	
    	total_posts.add(post);
    	
    }
    
    //array list which holds total posts received from users who this user followers
    private ArrayList<Message> total_received = new ArrayList<Message>();
	
	

	//a Message variable that holds the last posted message by this user
	private Message last_post=new Message();
	
	//getter for the last sent post
	public Message getLastSentMessage() {
		return last_post;
	}
	//setter for last sent post
	public void setLastSentPost(String text) {
		last_post.setSender(user_name);
		last_post.setText(text);
	}
	
	
	
	//constructor
	public User(String userName, String groupName, UserInterfacePanel my_interface) {
		
		this.setUser_name(userName);
		this.setGroup_name(groupName);
		myInterface=my_interface;
		myInterface.set_user(this);
		myInterface.setTitle(userName);
	}
	
	
	
	
	//implementing the update method of the Observer Interface
	public void update(Subject subject) {
		//adding the message to the user's message list
		if (subject instanceof User) {
			Message recieved=new Message();
			recieved.setSender(((User)subject).getLastSentMessage().getSender());
			recieved.setText(((User)subject).getLastSentMessage().getText());
			total_received.add(recieved);
			myInterface.setNewsFeed(total_received);
			
		}
	}
	
	
	//a method to show the interface of this user
	public void show_my_interface() {
		myInterface.setVisible(true);
	}
	
	//method for adding a user to the follower list
	public void addFollowerList(User user) {
		people_followed.add(user);
	}
	
	
	//overriding the toString method for displaying the name of the user in the tree
	public String toString() {
		return user_name;
	}
	
	//set interface's user
    public void setInterfaceUser(User user) {
    	myInterface.set_user(this);
    }
    

	
}//end of User class
