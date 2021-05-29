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
	
	public void showpList(JTextArea print_PList) {//** DEFINED** gui�� ���� ������ּ���
		
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
			print_status.append("�������ڴ� : "+numberOfStatus[infectionstatus]+"�� �Դϴ�.\n");
			break;
		case 1:
			print_status.append("�ǽ�ȯ�ڴ� : "+numberOfStatus[infectionstatus]+"�� �Դϴ�.\n");
			break;
		case 2:
			print_status.append("�����ڴ� : "+numberOfStatus[infectionstatus]+"�� �Դϴ�.\n");
			break;
		case 3:
			print_status.append("Ȯ���ڴ� : "+numberOfStatus[infectionstatus]+"�� �Դϴ�.\n");
			break;
		default:
			print_status.append("����Ǿ� �ִ� ����� �����ϴ�.");
				
		}
		
	}
	
	public static void getVisitedInfo(Person p)
	{
		String name = "";
		String address = "";
		try{
	         File file = new File("./src/Visited-" + p.name + "-" + p.phone + ".txt"); // File �̸� �ʿ�
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
	         File file = new File("./src/List/Contactor-" + p.name + "-" + p.phone + ".txt"); // File �̸� �ʿ�
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
		if(p.infectionStatus == 3) { //Ȯ���� �߻���
			
			//itemp = updateVisited(p, show); //1�� (Ȯ���ǽ���) ������Ʈ
			getContactorInfo(p);
			getVisitedInfo(p);
			dtemp = Covid.scanDate(p, p.visited); //**NOT DEFINED**
			for(int i = 0; i < p.visited.size(); i++) {//visited�� �˻�
				ctemp = p.visited.get(i).client; //visited Institution�� Ŭ���̾�Ʈ ����� �޾ƿ�
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
			ptemp = Covid.scanPeople(p);//2�� (������) ������Ʈ
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
	
	public ArrayList<Institution> updateVisited(Person p, JTextArea show) { //Ȯ���ڰ� �湮�� ����� ����Ʈ�� Ȯ���� ��ü�� visited�� �����ϴ� �޼ҵ��Դϴ�. **NOT DEFINED**
		//Scanner sc = new Scanner(System.in); //gui�� ���� �Է����� �������ּ���
		
		String input=show.getText();
		
		
		Institution temp;
		ArrayList<Institution> searched = new ArrayList<Institution>(); //�˻��� ����� ����� ���� (���� �̸��� ����� �˻��� ��츦 ���)
		while(true){
			//input = sc.nextLine();
			if(input.equals("process termination"))
				break;
			else
				temp = new Institution(input);
			if(ilist.contains(temp)) {
				searched = ilist;
				//System.out.println("�湮 ����� �ε����� �Է����ּ���");
				show.append("�湮 ����� �ε����� �Է����ּ���\n");
				for(int i = 0; i < searched.size(); i++) {
					//System.out.println(i + ". " + searched.get(i).name + " / " + searched.get(i).address);
					show.append(i + ". " + searched.get(i).name + " / " + searched.get(i).address+"\n");
				}
				int num=Integer.parseInt(show.getText()); //textArea �� �����Է�
				//p.visited.add(searched.get(sc.nextInt()));
				p.visited.add(searched.get(num));
				
			}
			else
				show.append("�˻� ����� �����ϴ�.\n");
				//System.out.println("�˻� ����� �����ϴ�.");
			
		}
		
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
