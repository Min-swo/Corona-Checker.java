import java.util.ArrayList;

public class SendInstitution {
	static ArrayList<Institution> tempInst = new ArrayList<Institution>();
	static void saveInstInfo(Institution i) {
		tempInst.add(i);
	}
	static ArrayList<Institution> retInst() {
		System.out.println(tempInst.get(0).address);
		return tempInst;
	}
}
