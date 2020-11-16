import java.util.ArrayList;

public abstract class Subject {

	//Array List which holds all the observers of this subject
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	//methods for attaching and detaching an observer
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	
	//notify method 
	public void notifyObserver() {
		for(Observer observer:observers) {
			observer.update(this);
		}
		
	}
}
