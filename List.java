import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

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
	
	public void showpList(JTextArea print_PList) {//** DEFINED** gui를 통해 출력해주세요
		
		for(int i = 0; i < plist.size() ; i++) {
			print_PList.append((i+1) + ". " + plist.get(i).name + " " +plist.get(i).phone + " " + plist.get(i).infectionStatus+"\n");
			
		}
	}
	public void showInstitution(JTextArea print_Inst) {//**DEFINED** gui
		for(int i = 0; i < ilist.size() ; i++) {
			print_Inst.append((i+1) + ". " + ilist.get(i).name + " " +ilist.get(i).address+"\n");
		}
	}
	public void showClient(JTextArea print_Inst, Institution inst) {//**DEFINED** gui
		print_Inst.append("<< " + inst.name + " >>\n");
		for(int i = 0; i < inst.client.size() ; i++) {
			print_Inst.append(inst.client.get(i).name + " " +inst.client.get(i).phone+"\n");
		}
	}
	public void showpListBystatus(JTextArea print_status, int infectionstatus) {
		//**DEFINED** gui
		//System.out.println(infectionstatus);
		for (int i = 0; i < plist.size(); i++)
		{
			
		}
		switch(infectionstatus) {
		case 0:
			print_status.append("비접촉자는 : "+numberOfStatus[infectionstatus]+"명 입니다.\n");
			break;
		case 1:
			print_status.append("의심환자는 : "+numberOfStatus[infectionstatus]+"명 입니다.\n");
			break;
		case 2:
			print_status.append("접촉자는 : "+numberOfStatus[infectionstatus]+"명 입니다.\n");
			break;
		case 3:
			print_status.append("확진자는 : "+numberOfStatus[infectionstatus]+"명 입니다.\n");
			break;
		default:
			print_status.append("저장되어 있는 사람이 없습니다.");
				
		}
		
	}
	
	public static void getVisitedInfo(Person p)
	{
		String name = "";
		String address = "";
		try{
	         File file = new File("./src/Visited-" + p.name + "-" + p.phone + ".txt"); // File 이름 필요
	         FileReader filereader = new FileReader(file);
	         BufferedReader bufReader = new BufferedReader(filereader);
	         String line = "";
	         while((line = bufReader.readLine()) != null){
	             name = line.split("|")[0];
	             address = line.split("|")[1];
	             
	             p.addTo_vList((Covid.makeInstitutionInfo(name, address)));
	             //System.out.print(name + " "+ phone + "\n");
	         }
	         bufReader.close();
		 }catch (FileNotFoundException e) {
	         // TODO: handle exception
		 }catch(IOException e){
	         System.out.println(e);
	     }
	}
	
	public static void getContactorInfo(Person p)
	{
		String name = "";
		String phone = "";
		int infectionStatus = 0;
		try{
	         File file = new File("./src/List/Contactor-" + p.name + "-" + p.phone + ".txt"); // File 이름 필요
	         FileReader filereader = new FileReader(file);
	         BufferedReader bufReader = new BufferedReader(filereader);
	         String line = "";
	         while((line = bufReader.readLine()) != null){
	             name = line.split(" ")[0];
	             phone = line.split(" ")[1];
	             infectionStatus = Integer.parseInt(line.split(" ")[2]);
	             
	             p.addTo_cList((Covid.makePersonInfo(name, phone, infectionStatus)));
	             //System.out.print(name + " "+ phone + "\n");
	         }
	         bufReader.close();
		 }catch (FileNotFoundException e) {
	         // TODO: handle exception
		 }catch(IOException e){
	         System.out.println(e);
	     }
	}
	
	
	public void addTo_pList(Person p, JTextArea show) {
		if(p.infectionStatus == 3) { //확진자 발생시
			
			//itemp = updateVisited(p, show); //1번 (확진의심자) 업데이트
			getContactorInfo(p);
			getVisitedInfo(p);
			dtemp = Covid.scanDate(p, p.visited); //**NOT DEFINED**
			for(int i = 0; i < p.visited.size(); i++) {//visited를 검색
				ctemp = p.visited.get(i).client; //visited Institution의 클라이언트 목록을 받아옴
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
			ptemp = Covid.scanPeople(p);//2번 (접촉자) 업데이트
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
	
	public ArrayList<Institution> updateVisited(Person p, JTextArea show) { //확진자가 방문한 기관의 리스트를 확진자 객체의 visited에 저장하는 메소드입니다. **NOT DEFINED**
		//Scanner sc = new Scanner(System.in); //gui를 통한 입력으로 변경해주세요
		
		String input=show.getText();
		
		
		Institution temp;
		ArrayList<Institution> searched = new ArrayList<Institution>(); //검색된 기관의 목록을 저장 (같은 이름의 기관이 검색될 경우를 대비)
		while(true){
			//input = sc.nextLine();
			if(input.equals("process termination"))
				break;
			else
				temp = new Institution(input);
			if(ilist.contains(temp)) {
				searched = ilist;
				//System.out.println("방문 기관의 인덱스를 입력해주세요");
				show.append("방문 기관의 인덱스를 입력해주세요\n");
				for(int i = 0; i < searched.size(); i++) {
					//System.out.println(i + ". " + searched.get(i).name + " / " + searched.get(i).address);
					show.append(i + ". " + searched.get(i).name + " / " + searched.get(i).address+"\n");
				}
				int num=Integer.parseInt(show.getText()); //textArea 에 숫자입력
				//p.visited.add(searched.get(sc.nextInt()));
				p.visited.add(searched.get(num));
				
			}
			else
				show.append("검색 결과가 없습니다.\n");
				//System.out.println("검색 결과가 없습니다.");
			
		}
		
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
