// a visitor class which counts the total positive posts
public class TotalPositiveCounter implements SystemVisitor {

	@Override
	public void visitSystem(Twitter my_twitter) {
		 double counter = 0.0;
		 
		 for (SystemEntry element : my_twitter.getEntries()) {
			 
			 if (element instanceof User) {
				 for (Message post : ((User) element).getTotalSentPosts()) {
				   if (isPositive(post.getText()))
					   counter++;
				 }
			 }//end of second for
			 
		 }//end of first for
		 
		 //--------------------------
				
				double counter2 = 0.0;
				
				for (SystemEntry element : my_twitter.getEntries()) {
					if (element instanceof User) {
						counter2 = counter+((User)element).getTotalSentPosts().size();
					}
				}
		 //--------------------------
		 
		my_twitter.setTotal_positive_posts((counter/counter2)*100);
	}//end of visitSystem
	
	
	//method for determining if a message is positive or not
    private boolean isPositive(String text) {
    	if (text.contains("good"))
    		return true;
    	else if (text.contains("Hello"))
    		return true;
    	else if (text.contains("love"))
    		return true;
    	else if (text.contains("beutiful"))
    		return true;
    	else
    		return false;
    }

}//end of class
