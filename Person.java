import java.util.*;

public class Person{
	Person() {
	}
	Person(String name, String phone, int infectionStatus){
		this.name = name;
		this.phone = phone;
		this.infectionStatus = infectionStatus;
	}
	
	String name;
	String phone;
	int infectionStatus;
	public void clientToList(pList_Client client) {
		this.name = client.name;
		this.phone = client.phone;
		this.infectionStatus = client.infectionStatus;
	}
	
	//아래 코드는 확진자일 경우에만 사용
	ArrayList<Institution> visited = new ArrayList<Institution>();
	ArrayList<Person> contactor = new ArrayList<Person>();
	
	public void addTo_vList(Institution i) {
		visited.add(i);
	}
	
	public void addTo_cList(Person p) {
		contactor.add(p);
	}
	
	public boolean equals(Object object) { //called by ArrayList.contains @override
	    boolean equal = false;

	    if (object != null && object instanceof Person){
	        equal = (this.name.compareTo(((Person) object).name)) == 0 && (this.phone.compareTo(((Person) object).phone) == 0);
	    }
	    return equal;
	}
}
