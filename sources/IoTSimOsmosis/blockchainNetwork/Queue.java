package IoTSimOsmosis.blockchainNetwork;

import java.util.ArrayList;

public class Queue {

	private static ArrayList<Event> eventList = new ArrayList<>();
	

	public static void addEvent(Event e) {
		getEventList().add(e);
		//System.out.println("New event has been added successfully [ " + e.getType() + " ].");
	}

	public static void removeEvent(Event e) {
		getEventList().remove(getEventList().indexOf(e));
		//System.out.println("last event has been removed successfully [ " + e.getType() + " ].");
	}

	public static Event getNextEvent() {
		getEventList().sort((t1, t2) -> Double.compare(t1.getTime(), t2.getTime()));
		return getEventList().get(0);
	}

	public static int size() {
		return getEventList().size();
	}

	public static boolean isEmpty() {
		return getEventList().size() == 0;
	}

	public static ArrayList<Event> getEventList() {
		return eventList;
	}
	

	public static void setEventList(ArrayList<Event> eventList) {
		Queue.eventList = eventList;
	}

}
