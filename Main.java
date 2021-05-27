import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		List list = new List();
		//list.addTo_iList(makeInstitutionInfo());
		
		
		//Scanner sc = new Scanner(System.in);
		//String input;
		//while(true) {
		//	input = sc.nextLine();
		//	if (input.equals("show institution")) {
		//		list.showInstitution();
		//		System.out.println("choose index to show client, -1 to quit");
		//		input = sc.nextLine();
		//		if(!input.equals("-1")) {
		//			list.showClient(list.ilist.get(Integer.parseInt(input)));
		//		}
		//	}
		//	if (input.equals("show plist")) {
		//		list.showpList();
		//	}
		//	if (input.equals("send person info")) {
		//		list.addTo_pList(makePersonInfo(sc.nextLine(), sc.nextLine(), sc.nextInt()));
		//		list.showpList();
		//	}
		//}
		
	}
	//**NOT DEFINED** gui 격리 해제 버튼을 통해 List의 quitIsolation() 메소드를 호출하는 메소드 필요
	
	//**NOT DEFINED** file 파일을 불러와 makePersonInfo에 정보를 넘겨주는 메소드 필요
	
	public static Person makePersonInfo(String name, String phone, int infectionStatus){
		Person p = new Person(name, phone, infectionStatus);
		return p;
	}
	//**NOT DEFINED** file 파일을 불러와 Institution에 정보를 넘겨주는 메소드 필요
	
	public static Institution makeInstitutionInfo(String name, String address, ArrayList<pList_Client> pc) { 
		//DateTime temptime1 = new DateTime(2021,5,22,10,46);
		//pList_Client client1 = new pList_Client("우세진", "010-9858-9060", 0, temptime1);
		
		//DateTime temptime2 = new DateTime(2021,5,22,12,33);
		//pList_Client client2 = new pList_Client("고양이", "010-1111-9060", 0, temptime2);
		
		//DateTime temptime3 = new DateTime(2021,5,22,11,10);
		//pList_Client client3 = new pList_Client("강아지", "010-2222-9060", 0, temptime3);
		
		//ArrayList<pList_Client> al = new ArrayList<pList_Client>();
		//al.add(client1);
		//al.add(client2);
		//al.add(client3);
		
		//Institution i = new Institution("본찌돈까스", " 수원시 장안구 서부로2106번길 22", al);
		Institution i = new Institution(name, address, pc);
		return i;
	}
	
	public static ArrayList<DateTime> scanDate(ArrayList<Institution> visited) {////**NOT DEFINED** gui 확진자가 이용한 기관의 날짜를 입력받고 넘겨주는 메소드 
		ArrayList<DateTime> dtemp = new ArrayList<DateTime>();
		DateTime temptime1 = new DateTime(2021,5,22,10,46);
		dtemp.add(temptime1);
		return dtemp;
	}

	public static ArrayList<Person> scanPeople() {//**NOT DEFINED** 확진자가 접촉한 사람의 명단을 입력받고 넘겨주는 메소드
		
		ArrayList<Person> ptemp = new ArrayList<Person>();
		ptemp.add(makePersonInfo("우세진", "010-9858-9060", 2));
		ptemp.add(makePersonInfo("강형욱", "010-9999-9999", 2));
		return ptemp;
	}
}
