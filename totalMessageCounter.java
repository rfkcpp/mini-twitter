
//visitor class that accepts total users of the system

public class totalMessageCounter implements SystemVisitor {

	@Override
	public void visitSystem(Twitter my_twitter) {
		
		int counter = 0;
		
		for (SystemEntry element : my_twitter.getEntries()) {
			if (element instanceof User) {
				counter = counter+((User)element).getTotalSentPosts().size();
			}
		}
		
		my_twitter.setTotal_messages(counter);
	}

}
