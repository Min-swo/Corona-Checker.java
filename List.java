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
	
	public void showpList() {//**NOT DEFINED** gui를 통해 출력해주세요
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
	public void showpListBystatus(int infectionstatus) {//해당하는 상태의 plist만 출력하는 메소드입니다. plist는 상태별로 정렬되어있습니다!!! ex)0인 상태의 사람이 4명 저장되어있다면 numberOfStatus[0] == 4
		//**NOT DEFINED** gui
	}
	
	public void addTo_pList(Person p) {
		if(p.infectionStatus == 3) { //확진자 발생시
			
			itemp = updateVisited(p); //1번 (확진의심자) 업데이트
			dtemp = Main.scanDate(itemp); //**NOT DEFINED**
			for(int i = 0; i < itemp.size(); i++) {//visited를 검색
				ctemp = itemp.get(i).client; //visited Institution의 클라이언트 목록을 받아옴
				for(int j = 0 ; j < ctemp.size(); j++) {//visited Institution의 클라이언트 목록을 검색
					if(dtemp.get(i).day == ctemp.get(j).datetime.day && dtemp.get(i).hour < ctemp.get(j).datetime.hour) { //확진자가 이용한 기관과 같은날, 이용한 시간 이후에 방문한사람들
						Person newp = new Person();
						ctemp.get(j).infectionStatus = 1; //의심자로 지정
						newp.clientToList(ctemp.get(j));
						if(plist.contains(newp) && plist.get(plist.indexOf(newp)).infectionStatus == 0) {//기존 목록에 0으로 있다면 삭제 후 새로 추가
							numberOfStatus[0]--;
							plist.remove(plist.indexOf(newp));
							sortAdding(newp);
						}
						else if(!plist.contains(newp))
							sortAdding(newp);
						
					}
				}
			}
			ptemp = Main.scanPeople();//2번 (접촉자) 업데이트
			for(int i = 0; i < ptemp.size(); i++) {
				if(plist.contains(ptemp.get(i)) && plist.get(plist.indexOf(ptemp.get(i))).infectionStatus < 2) {//기존 목록에 0또는 1로 있다면 삭제 후 새로 추가
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
	
	public ArrayList<Institution> updateVisited(Person p) { //확진자가 방문한 기관의 리스트를 확진자 객체의 visited에 저장하는 메소드입니다. **NOT DEFINED**
		Scanner sc = new Scanner(System.in); //gui를 통한 입력으로 변경해주세요
		String input;
		Institution temp;
		ArrayList<Institution> searched = new ArrayList<Institution>(); //검색된 기관의 목록을 저장 (같은 이름의 기관이 검색될 경우를 대비)
		while(true){
			input = sc.nextLine();
			if(input.equals("process termination"))
				break;
			else
				temp = new Institution(input);
			if(ilist.contains(temp)) {
				searched = ilist;
				System.out.println("방문 기관의 인덱스를 입력해주세요");
				for(int i = 0; i < searched.size(); i++) {
					System.out.println(i + ". " + searched.get(i).name + " / " + searched.get(i).address);
				}
				p.visited.add(searched.get(sc.nextInt()));
				
			}
			else
				System.out.println("검색 결과가 없습니다.");
			
		}
		sc.close();
		return p.visited;
	}
	
	public void quitIsolation(Person p) {//격리 해제시 호출하는 메소드
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
