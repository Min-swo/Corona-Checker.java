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
	//**NOT DEFINED** gui �ݸ� ���� ��ư�� ���� List�� quitIsolation() �޼ҵ带 ȣ���ϴ� �޼ҵ� �ʿ�
	
	//**NOT DEFINED** file ������ �ҷ��� makePersonInfo�� ������ �Ѱ��ִ� �޼ҵ� �ʿ�
	
	public static Person makePersonInfo(String name, String phone, int infectionStatus){
		Person p = new Person(name, phone, infectionStatus);
		return p;
	}
	//**NOT DEFINED** file ������ �ҷ��� Institution�� ������ �Ѱ��ִ� �޼ҵ� �ʿ�
	
	public static Institution makeInstitutionInfo(String name, String address, ArrayList<pList_Client> pc) { 
		//DateTime temptime1 = new DateTime(2021,5,22,10,46);
		//pList_Client client1 = new pList_Client("�켼��", "010-9858-9060", 0, temptime1);
		
		//DateTime temptime2 = new DateTime(2021,5,22,12,33);
		//pList_Client client2 = new pList_Client("�����", "010-1111-9060", 0, temptime2);
		
		//DateTime temptime3 = new DateTime(2021,5,22,11,10);
		//pList_Client client3 = new pList_Client("������", "010-2222-9060", 0, temptime3);
		
		//ArrayList<pList_Client> al = new ArrayList<pList_Client>();
		//al.add(client1);
		//al.add(client2);
		//al.add(client3);
		
		//Institution i = new Institution("����", " ������ ��ȱ� ���η�2106���� 22", al);
		Institution i = new Institution(name, address, pc);
		return i;
	}
	
	public static ArrayList<DateTime> scanDate(ArrayList<Institution> visited) {////**NOT DEFINED** gui Ȯ���ڰ� �̿��� ����� ��¥�� �Է¹ް� �Ѱ��ִ� �޼ҵ� 
		ArrayList<DateTime> dtemp = new ArrayList<DateTime>();
		DateTime temptime1 = new DateTime(2021,5,22,10,46);
		dtemp.add(temptime1);
		return dtemp;
	}

	public static ArrayList<Person> scanPeople() {//**NOT DEFINED** Ȯ���ڰ� ������ ����� ����� �Է¹ް� �Ѱ��ִ� �޼ҵ�
		
		ArrayList<Person> ptemp = new ArrayList<Person>();
		ptemp.add(makePersonInfo("�켼��", "010-9858-9060", 2));
		ptemp.add(makePersonInfo("������", "010-9999-9999", 2));
		return ptemp;
	}
}
