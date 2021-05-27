
public class pList_Client extends Person{
	
	DateTime datetime = new DateTime(); 
	pList_Client(String name, String phone, int infectionStatus, DateTime datetime) {
		super(name, phone, infectionStatus);
		this.datetime = datetime;
	}
	
}
