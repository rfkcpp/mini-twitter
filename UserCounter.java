//visitor class which counts the total users of the system
public class UserCounter implements SystemVisitor {

	@Override
	public void visitSystem(Twitter my_twitter) {
		int counter = 0;
		
		for (SystemEntry element : my_twitter.getEntries()) {
			if (element instanceof User) {
				counter++;
			}
		}
		my_twitter.setTotal_users(counter);
	}

}
