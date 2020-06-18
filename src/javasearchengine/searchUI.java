package javasearchengine;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
//import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
//import javax.swing.DropMode;
//import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class searchUI extends JFrame {

	private JPanel contentPane;
	private static JTextField searchTxt;
	private static JTextArea contentsTxt;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchUI frame = new searchUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public searchUI() {
		setResizable(false);
		setTitle("Document Retrieval System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel keywordtitle = new JLabel("Search (Deafult By Title)");
		keywordtitle.setFont(new Font("Calibri", Font.BOLD, 15));
		keywordtitle.setBounds(10, 43, 154, 31);
		contentPane.add(keywordtitle);

		searchTxt = new JTextField();
		searchTxt.setFont(new Font("Calibri", Font.BOLD, 13));
		searchTxt.setBounds(174, 48, 212, 19);
		contentPane.add(searchTxt);
		searchTxt.setColumns(10);

		contentsTxt = new JTextArea();
		contentsTxt.setBounds(137, 121, 312, 78);
		contentsTxt.setColumns(10);
		contentsTxt.setLineWrap(true);
		contentsTxt.setWrapStyleWord(true);

		scrollPane = new JScrollPane(contentsTxt);
		scrollPane.setBounds(136, 145, 347, 266);
		// scrollPane.add(contentsTxt);
		contentPane.add(scrollPane);
		// contentPane.add(contentsTxt);
		
		JRadioButton date = new JRadioButton("Date");
		date.setHorizontalAlignment(SwingConstants.RIGHT);
		date.setFont(new Font("Calibri", Font.BOLD, 14));
		date.setBounds(167, 90, 54, 25);
		contentPane.add(date);
		
		JRadioButton topics = new JRadioButton("Topics");
		topics.setFont(new Font("Calibri", Font.BOLD, 14));
		topics.setBounds(223, 92, 65, 21);
		contentPane.add(topics);

		JButton srchBTN = new JButton("SEARCH");
		srchBTN.setBackground(Color.GREEN);
		srchBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Thread mt =new Thread(new searchUI());
				//mt.start();
				if(date.isSelected()) {
					date();
				}else if(topics.isSelected()) {
					topic();
				}
				else {
					title();
				}
			}
		});
		
		
		
		
		
		
		
		
		srchBTN.setFont(new Font("Calibri", Font.BOLD, 15));
		srchBTN.setBounds(396, 39, 108, 38);
		contentPane.add(srchBTN);

		
		JLabel lblNewLabel = new JLabel("Resault :");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(59, 146, 55, 19);
		contentPane.add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Search Filter :");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_1.setBounds(74, 89, 87, 27);
		contentPane.add(lblNewLabel_1);
		
		JButton clean = new JButton("REFRESH");
		clean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchTxt.setText(" ");
				contentsTxt.setText(" ");
				
			}
		});
		clean.setBackground(Color.YELLOW);
		clean.setForeground(Color.BLACK);
		clean.setFont(new Font("Calibri", Font.BOLD, 15));
		clean.setBounds(514, 39, 108, 38);
		contentPane.add(clean);

	}
	public static void title() {
		String temp="<TITLE>"+searchTxt.getText()+ "</TITLE>";
		//System.out.println("Hello World");
		
        try {
     	   for(int i=0;i<22;i++) {
     			BufferedReader reader= new BufferedReader(new FileReader("src\\\\javasearchengine/read/reut2-"+i+".sgm"));
     			StringBuilder strB=new StringBuilder();
     			String str= "";
     			StringBuilder testB=new StringBuilder();
     			String body= "";
     			
     			do {
     				str =reader.readLine();
     				if(str==null)break;
     						if(str.contentEquals(temp)) {
     							strB.append(str);
     							//contentsTxt.setText(strB.toString());
     								do {									
     								body=reader.readLine();
     								if(body==null)break;
     								testB.append(body);
     								testB.append("\n");	
     								ArrayList<String> bodyContents = new ArrayList<>();
     								bodyContents.add(testB.toString());
     												//System.out.println(body.toString());
     								contentsTxt.setText(bodyContents.toString());
     												if(body.contentEquals("&#3;</BODY></TEXT>")==true)break;	
     								}while(body!=null);	
     										
     						}
     			}while(str!=null);
     			
     	   }
				}catch(Exception e1) {
					JOptionPane.showInternalMessageDialog(null, "Error :"+e1.toString());
				}
		}
	public static void date() {
		String date="<DATE>"+searchTxt.getText()+ "</DATE>";//1;
		//System.out.println("Hello World");
		
        try {
     	   for(int i=0;i<22;i++) {
     			BufferedReader reader= new BufferedReader(new FileReader("src\\\\javasearchengine/read/reut2-"+i+".sgm"));
     			StringBuilder strB=new StringBuilder();
     			String str= "";
     			StringBuilder testB=new StringBuilder();
     			String body= "";
     			
     			do {
     				str =reader.readLine();
     				if(str==null)break;
     						if(str.contentEquals(date)) {
     							strB.append(str);
     							//contentsTxt.setText(strB.toString());
     								do {									
     								body=reader.readLine();
     								if(body==null)break;
     								testB.append(body);
     								testB.append("\n");	
     								ArrayList<String> bodyContents = new ArrayList<>();
     								bodyContents.add(testB.toString());
     												//System.out.println(body.toString());
     								contentsTxt.setText(bodyContents.toString());
     												if(body.contentEquals("&#3;</BODY></TEXT>")==true)break;	
     								}while(body!=null);	
     										
     						}
     			}while(str!=null);
     			
     	   }
				}catch(Exception e1) {
					JOptionPane.showInternalMessageDialog(null, "Error :"+e1.toString());
				}
		}
	public static void topic() {
		String topic="<TOPICS><D>"+searchTxt.getText()+ "</D></TOPICS>"; //<TOPICS><D>money-fx</D></TOPICS>
		try {
        	   for(int i=0;i<22;i++) {
        			BufferedReader reader= new BufferedReader(new FileReader("src\\\\javasearchengine/read/reut2-"+i+".sgm"));
        			StringBuilder strB=new StringBuilder();
        			String str= "";
        			StringBuilder testB=new StringBuilder();
        			String body= "";
        			
        			do {
        				str =reader.readLine();
        				if(str==null)break;
        						if(str.contentEquals(topic)) {
        							strB.append(str);
        							//contentsTxt.setText(strB.toString());
        								do {									
        								body=reader.readLine();
        								if(body==null)break;
        								testB.append(body);
        								testB.append("\n");	
        								ArrayList<String> bodyContents = new ArrayList<>();
        								bodyContents.add(testB.toString());
        												//System.out.println(body.toString());
        								contentsTxt.setText(bodyContents.toString());
        												if(body.contentEquals("&#3;</BODY></TEXT>")==true)break;	
        								}while(body!=null);	
        										
        						}
        			}while(str!=null);
        			
        	   }
				}catch(Exception e1) {
					JOptionPane.showInternalMessageDialog(null, "Error :"+e1.toString());
				}
		
	}
	}
