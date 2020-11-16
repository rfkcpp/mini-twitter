
public class Message {
	
	private String sender;
	private String text;
	
	
	
	
	
	//getter and setter methods
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	//overriding to string method
	public String toString() {
		return (sender +"   "+ text);
	}
	

}
