import java.util.ArrayList;

public class Institution {
	public Institution() {
	}
	public Institution(String name) {
		this.name = name;
	}
	Institution(String name, String address, ArrayList<pList_Client> client){
		this.name = name;
		this.address = address;
		this.client = client;
	}
	
	String name, address;
	ArrayList<pList_Client> client = new ArrayList<pList_Client>();
	
	public boolean equals(Object object) { //called by ArrayList.contains @override
	    boolean equal = false;

	    if (object != null && object instanceof Institution){
	        equal = this.name.compareTo(((Institution) object).name) == 0;
	    }
	    SendInstitution.saveInstInfo(this);
	    return equal;
	}


	
}
