import java.util.ArrayList;
import java.util.Scanner;

public class List {
	List(){
		for(int i = 0; i < 4; i++)
			numberOfStatus[i] = 0;
	}
	
	int[] numberOfStatus = new int[4];
	ArrayList<Person> plist = new ArrayList<Person>();
	ArrayList<Institution> ilist = new ArrayList<Institution>();
	
	ArrayList<Institution> itemp = new ArrayList<Institution>();
	ArrayList<Person> ptemp = new ArrayList<Person>();
	ArrayList<DateTime> dtemp = new ArrayList<DateTime>();
	ArrayList<pList_Client> ctemp = new ArrayList<pList_Client>();
	
	public void showpList() {//**NOT DEFINED** gui�� ���� ������ּ���
		for(int i = 0; i < plist.size() ; i++) {
			System.out.println(i + ". " + plist.get(i).name + " " +plist.get(i).phone + " " + plist.get(i).infectionStatus);
		}
	}
	public void showInstitution() {//**NOT DEFINED** gui
		for(int i = 0; i < ilist.size() ; i++) {
			System.out.println(i + ". " + ilist.get(i).name + " " +ilist.get(i).address);
		}
	}
	public void showClient(Institution inst) {//**NOT DEFINED** gui
		for(int i = 0; i < inst.client.size() ; i++) {
			System.out.println(inst.client.get(i).name + " " +inst.client.get(i).phone);
		}
	}
	public void showpListBystatus(int infectionstatus) {//�ش��ϴ� ������ plist�� ����ϴ� �޼ҵ��Դϴ�. plist�� ���º��� ���ĵǾ��ֽ��ϴ�!!! ex)0�� ������ ����� 4�� ����Ǿ��ִٸ� numberOfStatus[0] == 4
		//**NOT DEFINED** gui
	}
	
	public void addTo_pList(Person p) {
		if(p.infectionStatus == 3) { //Ȯ���� �߻���
			
			itemp = updateVisited(p); //1�� (Ȯ���ǽ���) ������Ʈ
			dtemp = Main.scanDate(itemp); //**NOT DEFINED**
			for(int i = 0; i < itemp.size(); i++) {//visited�� �˻�
				ctemp = itemp.get(i).client; //visited Institution�� Ŭ���̾�Ʈ ����� �޾ƿ�
				for(int j = 0 ; j < ctemp.size(); j++) {//visited Institution�� Ŭ���̾�Ʈ ����� �˻�
					if(dtemp.get(i).day == ctemp.get(j).datetime.day && dtemp.get(i).hour < ctemp.get(j).datetime.hour) { //Ȯ���ڰ� �̿��� ����� ������, �̿��� �ð� ���Ŀ� �湮�ѻ����
						Person newp = new Person();
						ctemp.get(j).infectionStatus = 1; //�ǽ��ڷ� ����
						newp.clientToList(ctemp.get(j));
						if(plist.contains(newp) && plist.get(plist.indexOf(newp)).infectionStatus == 0) {//���� ��Ͽ� 0���� �ִٸ� ���� �� ���� �߰�
							numberOfStatus[0]--;
							plist.remove(plist.indexOf(newp));
							sortAdding(newp);
						}
						else if(!plist.contains(newp))
							sortAdding(newp);
						
					}
				}
			}
			ptemp = Main.scanPeople();//2�� (������) ������Ʈ
			for(int i = 0; i < ptemp.size(); i++) {
				if(plist.contains(ptemp.get(i)) && plist.get(plist.indexOf(ptemp.get(i))).infectionStatus < 2) {//���� ��Ͽ� 0�Ǵ� 1�� �ִٸ� ���� �� ���� �߰�
					numberOfStatus[plist.get(plist.indexOf(ptemp.get(i))).infectionStatus]--;
					plist.remove(plist.indexOf(ptemp.get(i)));
					sortAdding(ptemp.get(i));
				}
				else if(!plist.contains(ptemp.get(i))) {
					sortAdding(ptemp.get(i));
				}
			}
			itemp.clear();
			ptemp.clear();
			dtemp.clear();
			ctemp.clear();
		}
		if(plist.contains(p)) {
			if(plist.get(plist.indexOf(p)).infectionStatus != p.infectionStatus) {
				numberOfStatus[plist.get(plist.indexOf(p)).infectionStatus]--;
				plist.remove(plist.indexOf(p));
				sortAdding(p);
			}
		}
		else
			sortAdding(p);
		
	}
	
	public void addTo_iList(Institution i) {
		ilist.add(i);
	}
	
	public ArrayList<Institution> updateVisited(Person p) { //Ȯ���ڰ� �湮�� ����� ����Ʈ�� Ȯ���� ��ü�� visited�� �����ϴ� �޼ҵ��Դϴ�. **NOT DEFINED**
		Scanner sc = new Scanner(System.in); //gui�� ���� �Է����� �������ּ���
		String input;
		Institution temp;
		ArrayList<Institution> searched = new ArrayList<Institution>(); //�˻��� ����� ����� ���� (���� �̸��� ����� �˻��� ��츦 ���)
		while(true){
			input = sc.nextLine();
			if(input.equals("process termination"))
				break;
			else
				temp = new Institution(input);
			if(ilist.contains(temp)) {
				searched = ilist;
				System.out.println("�湮 ����� �ε����� �Է����ּ���");
				for(int i = 0; i < searched.size(); i++) {
					System.out.println(i + ". " + searched.get(i).name + " / " + searched.get(i).address);
				}
				p.visited.add(searched.get(sc.nextInt()));
				
			}
			else
				System.out.println("�˻� ����� �����ϴ�.");
			
		}
		sc.close();
		return p.visited;
	}
	
	public void quitIsolation(Person p) {//�ݸ� ������ ȣ���ϴ� �޼ҵ�
		numberOfStatus[3]--;
		Person temp = new Person(p.name, p.phone, 0);
		plist.remove(p);
		sortAdding(temp);
	}
	
	private void sortAdding(Person p) {
		int index = 0;
		for (int i = 0; i <= p.infectionStatus ; i++)
			index += numberOfStatus[i]; 
		plist.add(index,p);
		numberOfStatus[p.infectionStatus]++;
	}
	
	
}
