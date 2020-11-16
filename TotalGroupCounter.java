//a visitor class which counts total groups in the system

public class TotalGroupCounter implements SystemVisitor {

	@Override
	public void visitSystem(Twitter my_twitter) {
		int counter = 0;
		
		for (SystemEntry element : my_twitter.getEntries()) {
			if (element instanceof Group) {
				counter++;
			}
		}
		my_twitter.setTotal_groups(counter);
	}
}
