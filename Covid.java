import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollBar;

public class Covid {

	private JFrame frame;
	////Panel////
	JPanel InstitutionPage = new JPanel();
	JPanel Plist = new JPanel();
	ImagePanel welcomePanel = new ImagePanel(new ImageIcon("C:/Users/danho/eclipse-workspace/COVID-19/image/wallpaper.jpg").getImage()); //�̹��� �ּ� ����
	/////TextArea///////
	static JTextArea show_Inst = new JTextArea();

	
	///////////////////////////////////////
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Covid window = new Covid();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});		
		
				
	}

	/**
	 * Create the application.
	 */
	public Covid() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		///////////////public void main() ����//////////
		List list = new List();
		getPersonInfo(list);
		list.addTo_iList(makeInstitutionInfo("Alchon", "433-119, Yuljeon-dong"));
		DateTime temptime1 = new DateTime(2021,5,22,10,46);
		pList_Client client1 = new pList_Client("�켼��", "010-9858-9060", 1, temptime1);
	
		DateTime temptime2 = new DateTime(2021,5,22,12,33);
		pList_Client client2 = new pList_Client("������", "010-1111-9060", 0, temptime2);
		
		DateTime temptime3 = new DateTime(2021,5,22,11,10);
		pList_Client client3 = new pList_Client("������", "010-2222-9060", 0, temptime3);
		
		ArrayList<pList_Client> al = new ArrayList<pList_Client>();
		al.add(client1);
		al.add(client2);
		al.add(client3);
	
		Institution i = new Institution("����", " ������ ��ȱ� ���η�2106���� 22", al);
		////////////////////////TextArea///////////////////////////
		JTextArea print_PList = new JTextArea();
		
		
		////////////////////////
		frame = new JFrame();
		frame.setSize(639,480);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		
		
		
		////////////////button//////////////////////////
		JButton inputInstitution = new JButton("Input");
		JButton plist_1 = new JButton("search");
		JButton show_pList = new JButton("search");
		JButton showIns_1 = new JButton("\uCD9C\uB825");
		JButton inst_search_1 = new JButton("institution");
		JButton backTowelcome2_1 = new JButton("Back");
		String[] status= {"Coronic","Contactor", "Suspected", "Untactor", "All"};
		JComboBox search_status = new JComboBox(status);
		
		
		
		/////////////////////////////////////////////////
		InstitutionPage.setBounds(0, 0, 633, 453);
		frame.getContentPane().add(InstitutionPage);
		InstitutionPage.setLayout(null);
		
		
		showIns_1.setBackground(Color.WHITE);		
		showIns_1.setBounds(407, 370, 97, 23);
		InstitutionPage.add(showIns_1);
		
		//show_Inst.setEditable(false); // �Է�����
		
		JScrollPane scroll_Inst=new JScrollPane(show_Inst,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll_Inst.setLocation(33, 49);
		scroll_Inst.setVisible(true);
		
		scroll_Inst.setSize(343,382);
		
		show_Inst.setForeground(Color.BLACK);
		show_Inst.setBackground(Color.WHITE);
		show_Inst.setFont(new Font("Monospaced", Font.BOLD, 13));
		
		InstitutionPage.add(scroll_Inst);
		
		String[] list_Ins= {"Institution", "Client"};
		JComboBox search_Ins = new JComboBox(list_Ins);
		
		scroll_Inst.setColumnHeaderView(search_Ins);
		
		JButton backTowelcome_1 = new JButton("Back");
		backTowelcome_1.setBackground(Color.WHITE);
		backTowelcome_1.setBounds(524, 403, 97, 23);
		InstitutionPage.add(backTowelcome_1);
		
		JLabel lblNewLabel_1 = new JLabel("Institution");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(58, 10, 302, 29);
		InstitutionPage.add(lblNewLabel_1);
		
		
		
		
				
		
		InstitutionPage.setVisible(false);
			
			

			
			
			
			////////////////��ư Ŭ���� �̺�Ʈ///////////////////
			
						
			showIns_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton showIns=(JButton) e.getSource();
					if(showIns.getText().equals("\uCD9C\uB825")) {
						show_Inst.setEditable(false);
						show_Inst.setText("");
						//list.showInstitution(show_Inst);
						String input=search_Ins.getSelectedItem().toString();
						switch(input) {
						case "Institution":
							list.showInstitution(show_Inst);
							break;
						case "Client":
							list.showClient(show_Inst, i);
						}
					}
					
				}
			});
			
			backTowelcome_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton backTowelcome=(JButton) e.getSource();
					if(backTowelcome.getText().equals("Back")) {
						welcomePanel.setVisible(true);
						InstitutionPage.setVisible(false);
						show_Inst.setText("");
					}
				}
			});
			
			plist_1.addActionListener(new ActionListener() { //welcome panel
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton plist = (JButton) e.getSource();
					if(plist.getText().equals("search")) {
						Plist.setVisible(true);
						welcomePanel.setVisible(false);
					}
						
				}
					
			});
			
			inst_search_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton inst_search=(JButton) e.getSource();
					if(inst_search.getText().equals("institution")) {
						welcomePanel.setVisible(false);
						InstitutionPage.setVisible(true);
					}
					
				}
			});
			
			show_pList.addActionListener(new ActionListener() {//Plist panel
				public void actionPerformed(ActionEvent e) {
					JButton show_PList = (JButton) e.getSource();
					if(show_PList.getText().equals("search")) {
						print_PList.append("");
						//list.showpList(print_PList);
						String state= search_status.getSelectedItem().toString();
						print_PList.setText("");
						switch(state) {
						case "Coronic":
							
							list.showpListBystatus(print_PList, 3);
							break;
						case "Contactor":
							list.showpListBystatus(print_PList, 2);
							break;
						case "Suspected":
							list.showpListBystatus(print_PList, 1);
							break;
						case "Untactor":
							list.showpListBystatus(print_PList, 0);
							break;
						default:
							list.showpList(print_PList);
						}
						
					}
					
					
					
				}
			});
			
			
			backTowelcome2_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton backTowelcome2 = (JButton) e.getSource();
					if(backTowelcome2.getText().equals("Back")) {
						Plist.setVisible(false);
						welcomePanel.setVisible(true);
						show_Inst.setText("");
					}
				}
			});
			
			
			////////////////////////////////////////////////////		
		
		JScrollPane scrollPane = new JScrollPane(print_PList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // plist page scrollâ
		
		
		
		
		
		
		///////////////////////////��Ÿ///////////////////////////////////
		scrollPane.setBounds(64, 55, 294, 358);
		scrollPane.setVisible(true);
		Plist.add(scrollPane);
		backTowelcome2_1.setBounds(509, 398, 97, 23);
		Plist.add(backTowelcome2_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uCF54\uB85C\uB098 \uD604\uD669");
		lblNewLabel_2.setFont(new Font("���õ������ Bold", Font.BOLD, 24));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(64, 10, 294, 35);
		Plist.add(lblNewLabel_2);
		Plist.setVisible(false);
			
		scrollPane.setColumnHeaderView(search_status);
		
		Plist.setBounds(0, 0, 635, 453);
		frame.getContentPane().add(Plist);
		Plist.setLayout(null);
		
		
		show_pList.setFont(new Font("����", Font.PLAIN, 15));		
		show_pList.setBounds(400, 398, 97, 23);
		Plist.add(show_pList);
		
		
		print_PList.setBounds(63, 83, 297, 362);
		print_PList.setEditable(false); //�Է�����
		
		
		
		backTowelcome2_1.setFont(new Font("����", Font.PLAIN, 12));
		inputInstitution.setBounds(407, 403, 97, 23);
		InstitutionPage.add(inputInstitution);						
		

		
		welcomePanel.setBounds(0, 0, 633, 453);
		frame.getContentPane().add(welcomePanel);
		JLabel lblNewLabel = new JLabel("\uCF54\uB85C\uB098 \uB3D9\uC120");
		lblNewLabel.setFont(new Font("���ʷҵ���", Font.BOLD, 23));
		lblNewLabel.setBounds(260, 66, 149, 45);
		welcomePanel.add(lblNewLabel);
		
		
		plist_1.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		plist_1.setForeground(Color.BLACK);
		plist_1.setBounds(163, 346, 97, 23);
		
				
				
		welcomePanel.add(plist_1);
		
			
		inst_search_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		inst_search_1.setBounds(373, 346, 107, 23);
		welcomePanel.add(inst_search_1);
			
		
		
	}
	
	/////////////////Main//////////////////
	
	
	public static void getPersonInfo(List list)
	{
		String name = "";
		String phone = "";
		int infectionStatus = 0;
		try{
	         File file = new File("./src/sample.txt"); // File �̸� �ʿ�
	         FileReader filereader = new FileReader(file);
	         BufferedReader bufReader = new BufferedReader(filereader);
	         String line = "";
	         while((line = bufReader.readLine()) != null){
	             name = line.split(" ")[0];
	             phone = line.split(" ")[1];
	             infectionStatus = Integer.parseInt(line.split(" ")[2]);
	             list.addTo_pList(makePersonInfo(name, phone, infectionStatus), show_Inst);
	             System.out.print(name + " "+ phone + "\n");
	         }
	         bufReader.close();
		 }catch (FileNotFoundException e) {
	         // TODO: handle exception
		 }catch(IOException e){
	         System.out.println(e);
	     }
	}
	public static Person makePersonInfo(String name, String phone, int infectionStatus){
		Person p = new Person(name, phone, infectionStatus);
		return p;
	}
	//**DEFINING** file ������ �ҷ��� Institution�� ������ �Ѱ��ִ� �޼ҵ� �ʿ�
	
	public static Institution makeInstitutionInfo(String name, String address) { 

		ArrayList<pList_Client> pc = new ArrayList<pList_Client>();
		String cName = "";
		String cPhone = "";
		int infectionStatus = 0;
		String Date = new String();
		int year; int month; int  day; int hour; int minute;
		try{
	         File file = new File("./src/" + name + ".txt");
	         FileReader filereader = new FileReader(file);
	         BufferedReader bufReader = new BufferedReader(filereader);
	         String line = "";
	         while((line = bufReader.readLine()) != null){
	             cName = line.split(" ")[0];
	             cPhone = line.split(" ")[1];
	             infectionStatus = Integer.parseInt(line.split(" ")[2]);
	             
	             Date = line.split(" ")[3];
	             year = Integer.parseInt(Date.split("\\.")[0]);
	             month = Integer.parseInt(Date.split("\\.")[1]);
	             day = Integer.parseInt(Date.split("\\.")[2]);
	             hour = Integer.parseInt(Date.split("\\.")[3]);
	             minute = Integer.parseInt(Date.split("\\.")[4]);
	             
	             DateTime time = new DateTime(year, month, day, hour, minute);
	             pList_Client client = new pList_Client(cName, cPhone, infectionStatus, time);
	             pc.add(client);
	         }
	         bufReader.close();
		 }catch (FileNotFoundException e) {
	         // TODO: handle exception
		 }catch(IOException e){
	         System.out.println(e);
	     }

		Institution i = new Institution(name, address, pc);
		return i;
	}
	
	public static ArrayList<DateTime> scanDate(ArrayList<Institution> visited) {////**NOT DEFINED** gui Ȯ���ڰ� �̿��� ����� ��¥�� �Է¹ް� �Ѱ��ִ� �޼ҵ� 
		ArrayList<DateTime> dtemp = new ArrayList<DateTime>();
		DateTime temptime1 = new DateTime(2021,5,22,10,46);
		dtemp.add(temptime1);
		return dtemp;
	}

	public static ArrayList<Person> scanPeople() {//**NOT DEFINED** Ȯ���ڰ� ������ ����� ������ �Է¹ް� �Ѱ��ִ� �޼ҵ�
		
		ArrayList<Person> ptemp = new ArrayList<Person>();
		ptemp.add(makePersonInfo("�켼��", "010-9858-9060", 2));
		ptemp.add(makePersonInfo("������", "010-9999-9999", 2));
		return ptemp;
	}
}

class ImagePanel extends JPanel {
	private Image img;
	public ImagePanel(Image img) {
		this.img=img;
		setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
		setPreferredSize (new Dimension(img.getWidth(null), img.getHeight(null)));
		setLayout(null);
	}
	public int getWidth() {
		return img.getWidth(null);
		
	}
	public int getHeight() {
		return img.getHeight(null);
	}
	
	public void paintComponent (Graphics g) {
		g.drawImage(img,  0, 0, null); 
	}
	
}



