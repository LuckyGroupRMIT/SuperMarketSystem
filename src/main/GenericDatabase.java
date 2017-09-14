package main;
import java.util.HashMap;

public class GenericDatabase {
	protected static int idCounter=0;
	private int myDatabaseId;
	private HashMap<Integer,Object> data;
	private int myIdCounter;
	public GenericDatabase() {
		this.myDatabaseId=GenericDatabase.idCounter;
		GenericDatabase.idCounter++;
		this.myIdCounter = 0;
		this.data = new HashMap<Integer,Object>();
		
		
	}
	protected Id addItem(Object obj) {
		Id id = new Id(this.myDatabaseId,myIdCounter);
		this.data.put(id.getIndividual(),obj);
		return id;
	}
	protected Object getItem(Id i) {
		assert (i.getRoot() == this.myDatabaseId);
		return this.data.get(i.getIndividual());
		// Need to handle circumstances where an item is deleted from
		// the database
	}
}
